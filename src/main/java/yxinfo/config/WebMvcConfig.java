package yxinfo.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import yxinfo.yjh.web.interceptor.ApiInterceptor;
import yxinfo.yjh.web.interceptor.WebInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jn
 * @date 2017/9/13 13:25
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * fastjson配置
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters( List<HttpMessageConverter<?>> converters ) {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add( MediaType.APPLICATION_JSON_UTF8 );
        fastConverter.setSupportedMediaTypes( fastMediaTypes );
        converters.add( fastConverter );
    }

    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors( InterceptorRegistry registry ) {
        registry.addInterceptor( new ApiInterceptor() ).addPathPatterns( "/api/**" );
        registry.addInterceptor( new WebInterceptor() ).addPathPatterns(
                "/",
                "/articles/**",
                "/blocks/**" );
        super.addInterceptors( registry );
    }

    /**
     * 静态资源处理
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry ) {
        registry.addResourceHandler( "/static/**" ).addResourceLocations( "classpath:/static/" );
    }

    /**
     * 错误页面配置
     *
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize( ConfigurableEmbeddedServletContainer container ) {
                container.addErrorPages( new ErrorPage( HttpStatus.NOT_FOUND, "/static/error/404.html" ) );
                container.addErrorPages( new ErrorPage( HttpStatus.INTERNAL_SERVER_ERROR, "/static/error/500.html" ) );
                container.addErrorPages( new ErrorPage( HttpStatus.METHOD_NOT_ALLOWED, "/static/error/404.html" ) );
            }
        };
    }
}
