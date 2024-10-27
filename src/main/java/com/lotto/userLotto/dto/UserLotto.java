package com.lotto.userLotto.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLotto {

    private Integer recordSeq;                              // 사용자 로또 기록 SEQ
    private Integer drawNum;                                // 회차
    private Integer userSeq;                                // 회원 SEQ
    private Integer lottoNum1;                              // 로또 번호 1
    private Integer lottoNum2;                              // 로또 번호 2
    private Integer lottoNum3;                              // 로또 번호 3
    private Integer lottoNum4;                              // 로또 번호 4
    private Integer lottoNum5;                              // 로또 번호 5
    private Integer lottoNum6;                              // 로또 번호 6
    private Integer lottoRank;                              // 등수
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date crtDt;                                     // 생성 일자
    private String crtIp;                                   // 생성 IP
}
