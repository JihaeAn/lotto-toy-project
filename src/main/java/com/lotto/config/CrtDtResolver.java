package com.lotto.config;

import com.lotto.drawNum.dto.DrawNum;
import com.lotto.lotto.dto.Lotto;
import com.lotto.publicDo.dto.ParentsDto;
import com.lotto.stats.dto.Stats;
import com.lotto.store.dto.Store;
import com.lotto.user.dto.User;
import com.lotto.userLotto.dto.UserLotto;
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
//        if (body instanceof UserLotto) {
//            UserLotto userLotto = (UserLotto) body;
//            userLotto.setCrtDt(formattedDate);
//        } else if (body instanceof User) {
//            User user = (User) body;
//            user.setCrtDt(formattedDate);
//        } else if (body instanceof Lotto) {
//            Lotto lotto = (Lotto) body;
//            lotto.setCrtDt(formattedDate);
//        } else if (body instanceof DrawNum) {
//            DrawNum drawNum = (DrawNum) body;
//            drawNum.setCrtDt(formattedDate);
//        } else if (body instanceof Stats) {
//            Stats stats = (Stats) body;
//            stats.setCrtDt(formattedDate);
//        } else if (body instanceof Store) {
//            Store store = (Store) body;
//            store.setCrtDt(formattedDate);
//        }
        return body;
    }
}
