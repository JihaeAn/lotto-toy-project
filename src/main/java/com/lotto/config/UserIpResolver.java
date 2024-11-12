package com.lotto.config;

import com.lotto.userLotto.dto.UserLotto;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;


// WebConfig 파일에 따로 등록하지 않아도 됨
@ControllerAdvice
public class UserIpResolver extends RequestBodyAdviceAdapter {

    private final HttpServletRequest request;

    public UserIpResolver(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return UserLotto.class.isAssignableFrom(methodParameter.getParameterType());
    }

    // @RequestBody로 바인딩 된 후 타는 메서드
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {

        if (body instanceof UserLotto) {
            UserLotto userLotto = (UserLotto) body;
            userLotto.setCrtIp(request.getRemoteAddr());
        }
        return body;
    }
}
