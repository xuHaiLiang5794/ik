package com.xuhailiang5794.ik.support.enhance;

import com.github.pagehelper.Page;
import com.xuhailiang5794.ik.support.result.BaseResult;
import com.xuhailiang5794.ik.support.result.OptResult;
import com.xuhailiang5794.ik.support.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/16 16:32
 */
@ControllerAdvice
@Slf4j
public class ResponseBodyAdvice implements org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice {
    private static final String SWAGGER_KEY_WORD = "io.swagger.";
    /**
     * 一般的操作方法：如save/update，这种方法返回值是一个整数，
     * 在这里定义一个集合，如果操作方法是save/update则通过返回的int值来决定返回的错误代码
     */
    private static List<String> optMethods = new ArrayList<>();

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return hasSwagger(methodParameter);
    }

    /**
     * 判定方法是否是含有swagger注解，若有则判定此方法为reset full api，统一返回格式
     *
     * @param methodParameter
     * @return
     */
    private boolean hasSwagger(MethodParameter methodParameter) {
        Executable executable = methodParameter.getExecutable();
        if (executable != null) {
            Annotation[] annotations = executable.getAnnotations();
            if (ArrayUtils.isNotEmpty(annotations)) {
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().getName()
                            .startsWith(SWAGGER_KEY_WORD)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object data, MethodParameter methodParameter,
                                  MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        log.info("return data value {}", data);
        if (data != null && data instanceof Page) {
            return new PageResult((Page) data);
        } else {
            BaseResult result = OptResult.success();
            result.setData(data);
            String methodName = methodParameter.getMethod().getName();
            log.info("methodName {}", methodName);
            return result;
        }
    }
}
