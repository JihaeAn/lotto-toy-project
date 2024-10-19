package com.lotto.stats.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
public class Stats {

    private Integer statsRecordSeq;                                     // 당첨 통계 SEQ
    private Integer drawNum;                                            // 회차
    private Integer lottoRank;                                          // 등수
    private Integer winnerCount;                                        // 당첨자 수
    private Integer prizeAmount;                                        // 당첨 금액
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date crtDt;                                                 // 생성 일자
}
