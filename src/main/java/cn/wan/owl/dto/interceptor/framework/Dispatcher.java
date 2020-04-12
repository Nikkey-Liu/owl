package cn.wan.owl.dto.interceptor.framework;

import cn.wan.owl.dto.interceptor.framework.Service.Service;
import cn.wan.owl.dto.interceptor.framework.Service.ServiceImp;
import cn.wan.owl.dto.interceptor.interceptors.Interceptor;

import java.util.ArrayList;
import java.util.List;


public class Dispatcher {
    private List<Interceptor> interceptors = new ArrayList<Interceptor>();

    public void addInterceptor(Interceptor i){
        interceptors.add(i);
    }

    public void execute(Context ctx){ //callback
        for (Interceptor i : interceptors ) {
            i.execute(ctx);
        }
    }

//    Service service;
//    Not sure if the service belongs to Dispatcher or Framework
//    public void setServiceImp(ServiceImp serviceImp){
//        this.service = serviceImp;
//    }
}
