package com.lotto.store.dto;

import com.lotto.publicDo.dto.ParentsDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class Store implements ParentsDto {

    private Integer storeSeq;                                        // 1등 당첨 판매점 SEQ
    private Integer drawNum;                                         // 회차
    private String address;                                          // 주소
    private String storeName;                                        // 판매점 이름
    private String crtDt;                                            // 생성 일자

    @Override
    public void setCrtDt(String crtDt) {
        this.crtDt = crtDt;
    }
}
