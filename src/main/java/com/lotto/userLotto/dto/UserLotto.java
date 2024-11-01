package com.lotto.userLotto.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date crtDt;                                     // 생성 일자
    private String crtIp;                                   // 생성 IP

    // 별칭명으로 쓰는 컬럼들 추가
    private Integer matchCount;                             // 실제 당첨 번호와 비교했을 때 맞은 개수
}
