package cn.wan.owl.Interceptor;

import cn.wan.owl.model.NUser;
import cn.wan.owl.util.UserUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Login check,
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    // Before the target method is executed
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        NUser user = (NUser) request.getSession().getAttribute("loginUser");
        if (user == null) {

            // Not logged in, return to the login page
            System.out.println("login failed-------------------");
            request.getRequestDispatcher("/login/ui").forward(request, response);
            return false;
        } else {
            // Logged in, release request
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
