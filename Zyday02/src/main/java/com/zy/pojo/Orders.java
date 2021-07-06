package com.zy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Author:小黑洽
 * @Date：2021/7/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private int id;
    private String codes;

    private List<OrderItem> orderItemList;
}

