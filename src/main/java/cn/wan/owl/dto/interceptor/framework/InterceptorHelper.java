package cn.wan.owl.dto.interceptor.framework;

import cn.wan.owl.dto.interceptor.framework.Service.NullService;
import cn.wan.owl.dto.interceptor.framework.Service.Service;
import cn.wan.owl.dto.interceptor.interceptors.Interceptor;
import cn.wan.owl.dto.interceptor.framework.Service.Message;



public class InterceptorHelper {
    private  Dispatcher interceptor_list  ;
    private  Service service;//List<Service> services; //List也行



    public InterceptorHelper(Service serviceImp) {

        this();
        service = serviceImp;
    }

    public InterceptorHelper() {
        interceptor_list = new Dispatcher();
        service = new NullService();
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptor_list.addInterceptor(interceptor);
    }

//    public void addService(Service service){
//        services.add(service);
//    }

    public void iterate_list(Context ctx) {
        interceptor_list.execute(ctx);
    }

    public void event(Message message) {
        Context ctxObj = new Context();
        ctxObj.setVlaue(message.getValue());


        // callback executes all interceptors
        iterate_list(ctxObj);


// After executing all the interceptors, execute the Message Request (execute real business logic services, or HTTP requests and the like: you can think of these requests as an event, and the following call is the method of responding to the event)
        service.execute(ctxObj);
    }
}
