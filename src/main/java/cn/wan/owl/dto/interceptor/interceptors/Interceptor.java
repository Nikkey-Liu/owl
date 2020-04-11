package cn.wan.owl.dto.interceptor.interceptors;

import cn.wan.owl.dto.interceptor.framework.Context;


public interface Interceptor {
    public void execute(Context ctx);
}
