package cn.wan.owl.dto.interceptor.framework.Service;

import cn.wan.owl.dto.interceptor.framework.Context;

public class ServiceImp implements Service{ // service == message
    public void execute(Context ctxObj_msg) {
            System.out.println("@@@@ Service_Invoked! And, Message obejct is :"+ctxObj_msg.getVlaue()+"@@@@");
    }
}
