package cn.wan.owl.dto.interceptor.framework;

import cn.wan.owl.dto.interceptor.framework.Service.NullService;
import cn.wan.owl.dto.interceptor.framework.Service.Service;
import cn.wan.owl.dto.interceptor.interceptors.Interceptor;
import cn.wan.owl.dto.interceptor.framework.Service.Message;


public class IntterceptorHelper {
    private  Dispatcher interceptor_list  ;
    private  Service service;//List<Service> services; //List也行

    public IntterceptorHelper(Service serviceImp){
        this();
        service = serviceImp;
    }

    public IntterceptorHelper() {
        interceptor_list = new Dispatcher();
        service = new NullService();
    }

    public void setService(Service service){
        this.service = service;
    }

    public void addInterceptor (Interceptor interceptor){
        interceptor_list.addInterceptor(interceptor);
    }

//    public void addService(Service service){
//        services.add(service);
//    }

    public void iterate_list(Context ctx){
        interceptor_list.execute(ctx);
    }

    public void event(Message message) {
        Context ctxObj = new Context();
        ctxObj.setVlaue(message.getValue());

        // callback 执行所有的interceptors
        iterate_list(ctxObj);

        //执行完所有的interceptors后，执行Message Request（执行真正的业务逻辑服务、或者是HTTP请求之类的东西：可以看做这些请求是一个事件，而下面的调用就是响应事件的方法）
        service.execute(ctxObj);
    }
}
