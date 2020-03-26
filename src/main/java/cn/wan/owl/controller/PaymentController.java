package cn.wan.owl.controller;

import cn.wan.owl.dto.*;
import cn.wan.owl.dto.price.activity.GoodsActivityContext;
import cn.wan.owl.dto.price.activity.GoodsActivityStrategyFactory;
import cn.wan.owl.dto.receipt.LetterHeadPrinting;
import cn.wan.owl.dto.receipt.Receipt;
import cn.wan.owl.dto.receipt.ReceiptTypes;
import cn.wan.owl.mapper.PaymentsMapper;
import cn.wan.owl.model.*;
import cn.wan.owl.service.CartService;
import cn.wan.owl.service.NProductService;
import cn.wan.owl.service.OrderService;
import cn.wan.owl.service.PaymentsService;
import cn.wan.owl.util.Constantvalue;
import cn.wan.owl.util.UserUtil;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentsService paymentsService;
    @Autowired
    CartService cartService;
    @Autowired
    NProductService nProductService;
    @Autowired
    OrderService orderService;
    @Autowired
    private GoodsActivityStrategyFactory goodsActivityStrategyFactory;
    private PayhelperDto payhelperDto =new PayhelperDto();
    @PostMapping("/uibyid")
    public Object uibyid(HttpSession httpSession, @RequestBody IdDto id) {
        payhelperDto.setId(id.getid());
        httpSession.setAttribute("payhelpDto",payhelperDto);
        return CommonResponse.success();
    }

    @GetMapping("/uiAll")
    public void uiAll(HttpSession httpSession , HttpServletResponse response) throws IOException {
        List<Cart> carts = cartService.selectCartbyUserID(UserUtil.getCurrentUser().getUserid());
        System.out.println(carts);
       payhelperDto.setCarts(carts);
        httpSession.setAttribute("payhelpDto",payhelperDto);
        response.sendRedirect("/ui/ul-payments");
    }

    @PostMapping("/uiCart")
    public Object uiCart(HttpSession httpSession, @RequestBody IdDto idDto) {
        Cart cart = cartService.selectCartByID(idDto.getid());
       payhelperDto.setCart(cart);
        httpSession.setAttribute("payhelpDto",payhelperDto);
        return CommonResponse.success();
    }

    @PostMapping("/Payments")
    public Object checkPayments(@RequestBody PaymentsDto paymentsDto, HttpSession session) {
        System.out.println("--------------------------------");

        PayhelperDto payhelperDtos=(PayhelperDto)session.getAttribute("payhelpDto");
        System.out.println(payhelperDtos.getFlag());
        OrderDto orderDto = new OrderDto();
        System.out.println("--------------------------------");
        Payments payments1 = paymentsService.getPamentsBypaymentNumber(paymentsDto.getPayments().getCardnumber());
        boolean fl = paymentsDto.checkPayments(payments1, paymentsDto.getPayments());
        int price = 0;
        String activityType= Constantvalue.PAYMENT_TYPE_fullReduceActivity;//change the payment type

        GoodsActivityContext goodsActivityContext = goodsActivityStrategyFactory.getGoodsActivityStrategy(activityType);
        System.out.println("get activity success");
        List<MyOrder> orders = new ArrayList<MyOrder>();
        BigDecimal intToBigDecimal;
        if (fl) {// cofirm payment information
            System.out.println("// cofirm payment information");

                System.out.println("// pass carts");
                if (payhelperDtos.getFlag()==1) {
                    int id =payhelperDtos.getId();
                    System.out.println("list and cart is null use product id get value");
                    NProduct nProduct = nProductService.selectProductbyid(id);
                    intToBigDecimal=new BigDecimal(nProduct.getPrice());
//                    System.out.println("-----------------------------2");
                    intToBigDecimal=  goodsActivityContext.getPrice(intToBigDecimal);
//                    System.out.println("-----------------------------4");
                    if (payments1.getBalance()> intToBigDecimal.intValue()) {
//                        System.out.println("-----------------------------5");
                        MyOrder order = orderDto.getOrderByPublic(nProduct);
//                        System.out.println("-----------------------------6");
                        order.setPrice(intToBigDecimal.intValue());
                        Receipt receipt = Receiptdto.getInstance().createReceipt( order,ReceiptTypes.QR_CODE_RECEIPT, new LetterHeadPrinting() ,nProduct.getPrice());
                       receipt.print();
                        orders.add(order);
                        nProduct.setQuantity(nProduct.getQuantity()-1);
                        if (nProduct.getQuantity()==0)
                        {
                            nProduct.setProductstate(Constantvalue.SALEOUT);
                        }
                        nProductService.editProduct(nProduct);
                        orderService.AddOrders(orders);
                        System.out.println("Payments success and add Order ");
                        return CommonResponse.success();
                    }
                    System.out.println("Payments failed balance is not enough ");

                } else if (payhelperDtos.getFlag()==3) {// pass carts {
                    Cart cart = payhelperDtos.getCart();
                    intToBigDecimal=new BigDecimal(cart.getPrice());
                    intToBigDecimal=    goodsActivityContext.getPrice(intToBigDecimal);
                    if (payments1.getBalance() >intToBigDecimal.intValue()) {
                        MyOrder order = orderDto.getOrderByCart(cart);
                        order.setPrice(intToBigDecimal.intValue());
                        orders.add(order);
                        cartService.deletCartbyid(cart.getCartid());
                        Receipt receipt = Receiptdto.getInstance().createReceipt( order,ReceiptTypes.QR_CODE_RECEIPT, new LetterHeadPrinting() ,cart.getPrice());
                        receipt.print();
                        orderService.AddOrders(orders);
                        System.out.println("Payments success and add Order ");
                        return CommonResponse.success();
                    }
                    System.out.println("Payments failed balance is not enough ");

                }
             else {
                    List<Cart> carts =payhelperDtos.getCarts();
                    System.out.println("get carts");
                for (Cart c : carts) {
                    price = price + c.getPrice();
                }
                    System.out.println("get price");
                intToBigDecimal=new BigDecimal(price);
//                    System.out.println("-----------------------------2");
                intToBigDecimal= goodsActivityContext.getPrice(intToBigDecimal);
//                    System.out.println("-----------------------------3");
                if (payments1.getBalance() > intToBigDecimal.intValue()) {
//                    System.out.println("-----------------------------4");
                    for (Cart c : carts) {
//                        System.out.println("-----------------------------5");
                        MyOrder order = orderDto.getOrderByCart(c);
                        order.setPaymentstate(order.getPaymentstate()+"list pay as a order list");//price insert totall price
                        order.setPrice(intToBigDecimal.intValue());
                        Receipt receipt = Receiptdto.getInstance().createReceipt( order,ReceiptTypes.QR_CODE_RECEIPT, new LetterHeadPrinting() ,c.getPrice());
                        receipt.print();
                        orders.add(order);
                        cartService.deletCartbyid(c.getCartid());
                    }
//                    System.out.println("-----------------------------6  ");
                    orderService.AddOrders(orders);
                    System.out.println("Payments success and add Order");
                    return CommonResponse.success();
                }
                System.out.println("Payments failed balance is not enough");

            }

        }
        System.out.println("Payments information is error");
        return CommonResponse.failed();

    }
}
