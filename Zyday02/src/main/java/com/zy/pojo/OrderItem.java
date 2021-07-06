package com.zy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author:小黑洽
 * @Date：2021/7/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private int id;
    private int number;

    private Product product;
    private Orders orders;
}
