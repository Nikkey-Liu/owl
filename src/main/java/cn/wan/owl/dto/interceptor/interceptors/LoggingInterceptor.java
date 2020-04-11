package cn.wan.owl.dto.interceptor.interceptors;

import cn.wan.owl.dto.interceptor.framework.Context;

public class LoggingInterceptor implements Interceptor {
    @Override
    public void execute(Context ctx) {
        System.out.println("## Log: " + ctx.getVlaue() +" ##");
    }
}
