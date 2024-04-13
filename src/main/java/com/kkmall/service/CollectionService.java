package com.kkmall.service;

import com.kkmall.enums.ResponseEnum;

public interface CollectionService {
    ResponseEnum doCollect(String token,Integer id);
}
