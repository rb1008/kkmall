package com.kkmall.form;

import lombok.Data;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CommodityForm {
    @Nullable
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String color;

    @Nullable
    private String info;

    @Nullable
    private String description;

    @NotNull
    private String material;

    @NotNull
    private String origin;

    @NotNull
    private Integer classifyId;

    @NotNull
    private BigDecimal originalPrice;

    @NotNull
    private BigDecimal nowPrice;

    @NotNull
    private Integer inventory;

    @NotNull
    private Date publishTime;

    @NotNull
    private Integer status;

    @Nullable
    private String img;

    @Nullable
    private Integer saleCount;
}
