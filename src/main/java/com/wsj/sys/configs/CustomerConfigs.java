package com.wsj.sys.configs;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.wsj.sys.enums.SysConstants;
import com.wsj.sys.filter.LogInterceptor;
import com.wsj.sys.filter.UserSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by Jimmy on 2017/6/22.
 */
@Configuration
@ImportResource(locations = {"classpath:applications.xml"})
public class CustomerConfigs extends WebMvcConfigurerAdapter implements EnvironmentAware {
    @Autowired
    private UserSecurityInterceptor userSecurityInterceptor;
    @Autowired
    private LogInterceptor logInterceptor;
    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 使用bean注入,才能使其有效果,验证的话就在Entity字段中使用fastjson的
     * 注解@JSONField(serialize = false),转换出来的信息不含该字段,则成功
     *
     * @return
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
//        converters.add(new StringHttpMessageConverter(Charset.forName("utf8")));
//        converters.add(new MappingJackson2HttpMessageConverter());

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName, SerializerFeature.WriteMapNullValue);
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(fastConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(logInterceptor);
        registry.addInterceptor(userSecurityInterceptor);
    }

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new RequestContextListener());
        return servletListenerRegistrationBean;
    }

    /**
     * session超时时间
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (ConfigurableEmbeddedServletContainer container) -> {
            container.setSessionTimeout(1800);
//            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND , SysConstants.NotFound.getName()));
//            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,SysConstants.ServerError.getName()));
        };
    }

}
