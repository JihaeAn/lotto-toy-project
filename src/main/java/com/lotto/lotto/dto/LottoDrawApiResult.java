package com.lotto.lotto.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class LottoDrawApiResult {

    private String drwNo;                                   // 회차
    private Integer drwtNo1;                                // 당첨 번호 1
    private Integer drwtNo2;                                // 당첨 번호 2
    private Integer drwtNo3;                                // 당첨 번호 3
    private Integer drwtNo4;                                // 당첨 번호 4
    private Integer drwtNo5;                                // 당첨 번호 5
    private Integer drwtNo6;                                // 당첨 번호 6
    private Integer bnusNo;                                 // 보너스 번호
    private long firstWinamnt;                              // 1등 상금액
    private String drwNoDate;                               // 로또 추첨 날짜

}
