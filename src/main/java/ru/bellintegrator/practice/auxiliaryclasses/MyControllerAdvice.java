package ru.bellintegrator.practice.auxiliaryclasses;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collections;


@ControllerAdvice
public class MyControllerAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        return !returnType.getParameterType().getSimpleName().equals("ResponseEntity");
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
            if (methodParameter.getMethod()!=null
                    && (methodParameter.getMethod().getName().startsWith("add")
                    || methodParameter.getMethod().getName().startsWith("update"))) {
                return Collections.singletonMap("result", "success");
            } else return new MainDtoView(body);
        }

}
