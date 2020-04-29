package cn.wan.owl.dto.interceptor.framework.Service;

import cn.wan.owl.dto.interceptor.framework.Context;



public interface Service {
    // These are all services defined by the MyInterceptor concrete framwork specific framework:
    // e.g. When the user browses the webpage and sends an HTTP request, the server can be regarded as our ConcreteFramwork, this time
    // Service can be seen as a REST API call ---> business logic call
    // The out-of-band service can be to monitor whether this HTTP request contains sensitive information. XSS injection and the like.
    public void execute(Context ctxObj_msg) ; //ctxObj_msg = ctxObj
}
