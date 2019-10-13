package cn.yang.service;

import cn.yang.domain.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll(int page,int size);

    void save(Product product);
}
