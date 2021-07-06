package com.zy.mapper;

import com.zy.pojo.Orders;

import java.util.List;

public interface OrderMapper {
    List<Orders> orderList();

    Orders getOrder(int id);

    void delOrder(int id);
}
