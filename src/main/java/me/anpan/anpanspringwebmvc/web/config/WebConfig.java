package me.anpan.anpanspringwebmvc.web.config;

import me.anpan.anpanspringwebmvc.web.dto.Person;
import me.anpan.anpanspringwebmvc.web.interceptor.AnotherIntercepter;
import me.anpan.anpanspringwebmvc.web.interceptor.GreetingIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hi").setViewName("hi");
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan(Person.class.getPackage().getName());
        return  jaxb2Marshaller;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GreetingIntercepter()).order(0);
        registry.addInterceptor(new AnotherIntercepter()).order(-1).addPathPatterns("/hi");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/mobile/**").addResourceLocations("classpath:/mobile/ ","file:/usr/bin","/mobile")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
    }

    //messageConverter 추가
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }
}
