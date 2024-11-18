package com.lotto.config;

import com.lotto.userLotto.dto.UserLotto;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.List;


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

        String clientIp = null;
        UserLotto userLotto = (UserLotto) body;

        List<String> headerList = List.of(
                "X-Forwarded-For", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR",
                "HTTP_X_FORWARDED", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED",
                "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_VIA", "IPV6_ADR"
        );

        for (String header : headerList) {
            clientIp = request.getHeader(header);
            if (StringUtils.hasText(clientIp) && !clientIp.equalsIgnoreCase("unknown")) {
                userLotto.setCrtIp(clientIp);
                break;
            }
        }

        if (!StringUtils.hasText(clientIp)) {
            // 그렇지 않은 경우에는 request.getRemoteAddr()를 통해 기본적인 IP 주소를 얻기
            userLotto.setCrtIp(request.getRemoteAddr());
        }

        return body;
    }
}
