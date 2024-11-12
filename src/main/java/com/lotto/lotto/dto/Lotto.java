package com.lotto.lotto.dto;

import com.lotto.publicDo.dto.ParentsDto;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Lotto implements ParentsDto {

    private String drawNum;                                     // 회차
    private Integer winningNum1;                                // 당첨 번호 1
    private Integer winningNum2;                                // 당첨 번호 2
    private Integer winningNum3;                                // 당첨 번호 3
    private Integer winningNum4;                                // 당첨 번호 4
    private Integer winningNum5;                                // 당첨 번호 5
    private Integer winningNum6;                                // 당첨 번호 6
    private Integer bonusNum;                                   // 보너스 번호
    private long firstPrizeAmount;                              // 1등 상금액
    private String crtDt;                                       // 생성일자

    @Override
    public void setCrtDt(String crtDt) {
        this.crtDt = crtDt;
    }
}
