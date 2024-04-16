package com.kkmall.service.serviceImpl;

import com.kkmall.dao.AddressMapper;
import com.kkmall.dao.CartMapper;
import com.kkmall.dao.CommodityMapper;
import com.kkmall.dao.OrderMapper;
import com.kkmall.entity.Address;
import com.kkmall.entity.Cart;
import com.kkmall.entity.Commodity;
import com.kkmall.entity.Order;
import com.kkmall.enums.ErrorEnum;
import com.kkmall.enums.OrderEnum;
import com.kkmall.exception.BusinessException;
import com.kkmall.form.OrderForm;
import com.kkmall.service.OrderService;
import com.kkmall.utils.JwtUtil;
import com.kkmall.vo.AddressVo;
import com.kkmall.vo.OrderVo;
import com.kkmall.vo.OrderVoList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private CartMapper cartMapper;

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private CommodityMapper commodityMapper;

    @Override
    public OrderVoList buyCommodity(String token, Integer addressId) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        int uid = Integer.parseInt(JwtUtil.getUserId(token));
        List<Cart> cartList = cartMapper.selectCommodityIdByUid(uid);
        if (cartList.isEmpty()) {
            throw new BusinessException(ErrorEnum.CART_NOT_HAVE_COMMODITY);
        }
        List<Integer> commodityIdList = cartList.stream().map(Cart::getCommodityId).collect(Collectors.toList());
        List<Commodity> commodities = commodityMapper.selectCommodityByCommodityIdList(commodityIdList);
        if (commodities.isEmpty()) {
            throw new BusinessException(ErrorEnum.COMMODITY_ID_ERROR);
        }
        List<Order> orderList = new ArrayList<>();
        List<Commodity> commodityList = new ArrayList<>();
        for (Cart cart : cartList) {
            for (Commodity commodity : commodities) {
                if (Objects.equals(cart.getCommodityId(), commodity.getId())) {

                    long timestamp = Instant.now().toEpochMilli();  // 获取当前时间戳（毫秒）
                    String timestampStr = Long.toString(timestamp); // 将时间戳转换为字符串
                    Order order = new Order();
                    order.setOrderNumber(timestampStr);
                    order.setUserId(uid);
                    order.setCommodityId(commodity.getId());
                    order.setContent("速度");
                    order.setAddressId(addressId);
                    order.setStatus(OrderEnum.PAY.getCode());
                    orderList.add(order);
                    if (commodity.getInventory() - cart.getCount() < 0) {
                        throw new BusinessException(ErrorEnum.INVENTORY_NOT_ENOUGH);
                    }
                    commodity.setInventory(commodity.getInventory() - cart.getCount());
                    commodity.setSaleCount(commodity.getSaleCount() + cart.getCount());
                    commodityList.add(commodity);
                }
            }
        }
        int update = commodityMapper.updateInventoryAndSaleCount(commodityList);
        if (update == 0) {
            throw new BusinessException(ErrorEnum.ERROR);
        }
        boolean flag = orderMapper.insertOrderByList(orderList);
        if (!flag) {
            throw new BusinessException(ErrorEnum.ERROR);
        }

        int delete = cartMapper.deleteByUid(uid);
        if (delete != cartList.size()) {
            throw new BusinessException(ErrorEnum.ERROR);
        }

        Address address = addressMapper.selectByPrimaryKey(addressId);
        if (address == null) {
            throw new BusinessException(ErrorEnum.ADDRESS_NOT_EXIST);
        }
        List<OrderVo> voList = new ArrayList<>();
        AddressVo addressVo = new AddressVo();
        for (Order order : orderList) {
            OrderVo vo = new OrderVo();
            BeanUtils.copyProperties(order, vo);
            voList.add(vo);
        }
        BeanUtils.copyProperties(address, addressVo);
        OrderVoList orderVoList = new OrderVoList();
        orderVoList.setOrderVo(voList);
        orderVoList.setAddressVo(addressVo);

        return orderVoList;
    }

    @Override
    public Object deliverGoods(String token, OrderForm form) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        Order order = orderMapper.selectByOrderNum(form.getOrderNumber());
        if (order == null) {
            throw new BusinessException(ErrorEnum.ORDER_NOT_EXIST);
        }
        order.setCourierName(form.getCourierName());
        order.setCourierNumber(form.getCourierNumber());
        order.setStatus(OrderEnum.SENT.getCode());
        order.setSippingTime(new Date());
        int update = orderMapper.updateByPrimaryKeySelective(order);
        if (update == 0) {
            throw new BusinessException(ErrorEnum.ERROR);
        }
        return new Object();
    }

    @Override
    public Object cancelOrder(String token, String orderNumber) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        Order order = orderMapper.selectByOrderNum(orderNumber);
        if (order == null) {
            throw new BusinessException(ErrorEnum.ORDER_NOT_EXIST);
        }
        if (!Objects.equals(order.getStatus(), OrderEnum.NOT_SENT.getCode())) {
            throw new BusinessException(ErrorEnum.CANT_CANCEL_ORDER);
        }
        order.setStatus(OrderEnum.CANCEL.getCode());
        int update = orderMapper.updateByPrimaryKey(order);
        if (update == 0) {
            throw new BusinessException(ErrorEnum.ERROR);
        }
        return new Object();
    }

    @Override
    public Object confirmOrder(String token, String orderNumber) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        Order order = orderMapper.selectByOrderNum(orderNumber);
        if (order == null) {
            throw new BusinessException(ErrorEnum.ORDER_NOT_EXIST);
        }
        if (!Objects.equals(order.getStatus(), OrderEnum.ARRIVE.getCode())) {
            throw new BusinessException(ErrorEnum.ORDER_NOT_ARRIVE_OR_OBTAIN);
        }
        order.setStatus(OrderEnum.OBTAIN.getCode());
        order.setSuccessTime(new Date());
        int update = orderMapper.updateByPrimaryKey(order);
        if (update == 0) {
            throw new BusinessException(ErrorEnum.ERROR);
        }
        return new Object();
    }

    @Override
    public Object deleteOrder(String token, String orderNumber) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        Order order = orderMapper.selectByOrderNum(orderNumber);
        if (order == null) {
            throw new BusinessException(ErrorEnum.ORDER_NOT_EXIST);
        }
        if (!order.getStatus().equals(OrderEnum.NOT_SENT.getCode()) && !order.getStatus().equals(OrderEnum.OBTAIN.getCode())) {
            throw new BusinessException(ErrorEnum.ORDER_CANT_DELETE);
        }
        int update = orderMapper.deleteByPrimaryKey(order.getId());
        if (update == 0) {
            throw new BusinessException(ErrorEnum.ERROR);
        }
        return new Object();
    }
}
