package com.lotto.stats.dto;

import com.lotto.publicDo.dto.ParentsDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class Stats implements ParentsDto {

    private Integer statsRecordSeq;                                     // 당첨 통계 SEQ
    private Integer drawNum;                                            // 회차
    private Integer lottoRank;                                          // 등수
    private Integer winnerCount;                                        // 당첨자 수
    private long prizeAmount;                                           // 당첨 금액
    private String crtDt;                                               // 생성 일자

    @Override
    public void setCrtDt(String crtDt) {
        this.crtDt = crtDt;
    }
}
