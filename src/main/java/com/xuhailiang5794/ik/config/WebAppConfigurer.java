package com.xuhailiang5794.ik.config;

import com.xuhailiang5794.ik.support.converter.IKStringHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/16
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * swagger ui中的默认路径与spring boot声明的默认映射不同，
         * 所以这里需要添加自定义的静态资源映射
         */
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (int i = 0, size = converters.size(); i < size; i++) {
            HttpMessageConverter<?> converter = converters.get(i);
            if (converter instanceof StringHttpMessageConverter) {
                /**
                 * StringHttpMessageConverter导致ResponseBodyAdvice包装String类型时抛出异常，这里替换掉StringHttpMessageConverter
                 */
                converters.set(i, new IKStringHttpMessageConverter());
            }
        }
    }
}
