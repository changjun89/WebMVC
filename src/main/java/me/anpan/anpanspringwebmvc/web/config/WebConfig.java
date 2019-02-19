package me.anpan.anpanspringwebmvc.web.config;

import me.anpan.anpanspringwebmvc.web.interceptor.AnotherIntercepter;
import me.anpan.anpanspringwebmvc.web.interceptor.GreetingIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GreetingIntercepter()).order(0);
        registry.addInterceptor(new AnotherIntercepter()).order(-1).addPathPatterns("/hi");
    }
}
