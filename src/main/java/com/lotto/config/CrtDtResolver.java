package com.lotto.config;

import com.lotto.publicDo.dto.ParentsDto;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDateTime.now;

@ControllerAdvice
public class CrtDtResolver extends RequestBodyAdviceAdapter {

    // API 통신해서 받아온 LottoDrawApiResult DTO는 제외

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return ParentsDto.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {

        // LocalDateTime 포맷팅
        String formattedDate = now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if (body instanceof ParentsDto) {
            ParentsDto dto = (ParentsDto) body;
            dto.setCrtDt(formattedDate);
        }

        return body;
    }
}
