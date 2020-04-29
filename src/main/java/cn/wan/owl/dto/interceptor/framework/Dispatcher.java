package cn.wan.owl.dto.interceptor.framework;

import cn.wan.owl.dto.interceptor.interceptors.MyInterceptor;

import java.util.ArrayList;
import java.util.List;


public class Dispatcher {
    private List<MyInterceptor> myInterceptors = new ArrayList<MyInterceptor>();

    public void addInterceptor(MyInterceptor i){
        myInterceptors.add(i);
    }

    public void execute(Context ctx){ //callback
        for (MyInterceptor i : myInterceptors) {
            i.execute(ctx);
        }
    }

//    Service service;
//    Not sure if the service belongs to Dispatcher or Framework
//    public void setServiceImp(ServiceImp serviceImp){
//        this.service = serviceImp;
//    }
}
