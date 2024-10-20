package com.lotto.store.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
public class Store {

    private Integer storeSeq;                                        // 1등 당첨 판매점 SEQ
    private Integer drawNum;                                         // 회차
    private String address;                                          // 주소
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date crtDt;                                              // 생성 일자
}
