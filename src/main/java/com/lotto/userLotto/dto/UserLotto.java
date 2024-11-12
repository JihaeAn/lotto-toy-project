package com.lotto.userLotto.dto;

import com.lotto.publicDo.dto.ParentsDto;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLotto implements ParentsDto {

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
    private String crtDt;                                   // 생성 일자
    private String crtIp;                                   // 생성 IP

    // 별칭명으로 쓰는 컬럼들 추가
    private Integer matchCount;                             // 실제 당첨 번호와 비교했을 때 맞은 개수

    @Override
    public void setCrtDt(String crtDt) {
        this.crtDt = crtDt;
    }
}
