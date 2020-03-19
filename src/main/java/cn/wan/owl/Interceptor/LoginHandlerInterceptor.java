package cn.wan.owl.Interceptor;

import cn.wan.owl.model.NUser;
import cn.wan.owl.util.UserUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆检查，
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        NUser user = (NUser) request.getSession().getAttribute("loginUser");
        if(user == null){
            //未登陆，返回登陆页面
            System.out.println("login failed-------------------");

            request.getRequestDispatcher("/login/ui").forward(request,response);
            return false;
        }else{
            //已登陆，放行请求
            System.out.println("login success-------------------");

            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
