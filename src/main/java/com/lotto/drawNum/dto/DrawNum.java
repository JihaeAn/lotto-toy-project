package com.lotto.drawNum.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class DrawNum {

    private Integer drawNum;                                    // 회차번호
    private String crtDt;                                       // 생성일자
}
