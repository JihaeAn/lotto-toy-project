package com.lotto.drawNum.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
public class DrawNum {

    private Integer drawNum;                                    // 회차번호
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date crtDt;                                         // 생성일자
}
