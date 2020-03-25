package cn.wan.owl.controller;

import cn.wan.owl.model.CommonResponse;
import cn.wan.owl.service.CartService;
import cn.wan.owl.service.NProductService;
import cn.wan.owl.service.OrderService;
import cn.wan.owl.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    NProductService nProductService;
    @Autowired
    CartService cartService;
    @GetMapping("/list/order")
    public Object list(String orderBy, String orderType){
        int userid= UserUtil.getCurrentUser().getUserid();
        return CommonResponse.success(orderService.getOrdersbyUserId(userid));
    }

}
