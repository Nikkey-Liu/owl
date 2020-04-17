package cn.wan.owl.config;

import cn.wan.owl.Interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Bean
    public HandlerInterceptor getLoginInterceptor(){
        return new LoginHandlerInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor( getLoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login/ui","/login/**","/register/**","/static/**");

    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}
