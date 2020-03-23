package cn.wan.owl.service;

import cn.wan.owl.mapper.OrderMapper;
import cn.wan.owl.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrdeService {
    @Autowired
    OrderMapper orderMapper;
    public List<Order> getOrdersbyUserId(int id){
        return orderMapper.selectByUserid(id);
}
    public void AddOrders(List<Order> orders)
    {
        for (Order order:orders)
        {
            orderMapper.insert(order);
        }

    }

}

