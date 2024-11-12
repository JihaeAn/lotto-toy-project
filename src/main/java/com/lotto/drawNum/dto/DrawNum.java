package com.lotto.drawNum.dto;

import com.lotto.publicDo.dto.ParentsDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class DrawNum implements ParentsDto {

    private Integer drawNum;                                    // 회차번호
    private String crtDt;                                       // 생성일자

    @Override
    public void setCrtDt(String crtDt) {
        this.crtDt = crtDt;
    }
}
