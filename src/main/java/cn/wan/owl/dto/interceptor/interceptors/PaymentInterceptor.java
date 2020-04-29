package cn.wan.owl.dto.interceptor.interceptors;

import cn.wan.owl.dto.PaymentsDto;
import cn.wan.owl.dto.interceptor.framework.Context;
import cn.wan.owl.model.Payments;
import cn.wan.owl.service.PaymentsService;


public class PaymentInterceptor implements MyInterceptor {
    @Override
    public void execute(Context ctx) {
        System.out.println("## Log:  processing" + ctx.getVlaue() +" ##");    }
}
