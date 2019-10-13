package cn.yang.service;

import cn.yang.domain.Orders;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface IOrdersService {


    List<Orders> findAll(int page,int size);

    Orders findById(String ordersId);
}
