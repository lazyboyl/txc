package com.tx.core.config;

import com.tx.core.interceptor.TraceIntercept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author linzf
 * @since 2019/7/22
 * 类描述：实现txc的拦功能
 */
@Component
public class TxcInterceptorConfig implements WebMvcConfigurer {

    /**
     * 初始化日志
     */
    private Logger logger = LoggerFactory.getLogger(TxcInterceptorConfig.class);

    private TraceIntercept traceIntercept = new TraceIntercept();

    /**
     * 注入trace拦截器bean
     */
    @Bean
    TraceIntercept traceIntercept(){
        return  traceIntercept;
    }

    /**
     * 功能描述：配置trace拦截器
     * @param registry 注册对象
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.debug("------正在加载trace拦截器------");
        registry.addInterceptor(traceIntercept()).addPathPatterns("/**");
        logger.debug("------完成trace拦截器加载------");
    }

}
