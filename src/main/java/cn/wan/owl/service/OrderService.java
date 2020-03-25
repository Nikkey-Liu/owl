package cn.wan.owl.service;

import cn.wan.owl.mapper.MyOrderMapper;
import cn.wan.owl.model.MyOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderService {
    @Autowired
    MyOrderMapper orderMapper;
    public List<MyOrder> getOrdersbyUserId(int id){
        return orderMapper.selectByUserid(id);
}
    public void AddOrders(List<MyOrder> orders)
    {
        for (MyOrder order:orders)
        {
            orderMapper.insert(order);
        }

    }

}

