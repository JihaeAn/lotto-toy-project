package com.lotto.lotto.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
public class Lotto {

    private String drawNum;                                     // 회차
    private Integer winningNum1;                                // 당첨 번호 1
    private Integer winningNum2;                                // 당첨 번호 2
    private Integer winningNum3;                                // 당첨 번호 3
    private Integer winningNum4;                                // 당첨 번호 4
    private Integer winningNum5;                                // 당첨 번호 5
    private Integer winningNum6;                                // 당첨 번호 6
    private Integer bonusNum;                                   // 보너스 번호
    private Integer firstPrizeAmount;                           // 1등 상금액
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date crtDt;                                         // 생성일자



}
