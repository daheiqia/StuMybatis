package com.zy.mapper;

import com.zy.pojo.Product;

import java.util.List;

public interface ProductMapper {
    List<Product> productList();

     Product getProduct(int id);
}
