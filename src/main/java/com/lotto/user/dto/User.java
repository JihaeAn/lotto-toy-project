package com.lotto.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
public class User {

    private Integer userSeq;                                // 회원 SEQ
    private String userId;                                  // 회원 아이디
    private String password;                                // 회원 비밀번호
    private String name;                                    // 회원 이름
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date crtDt;                                     // 생성 일자
    private String crtIp;                                   // 생성 IP
}
