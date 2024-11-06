package com.lotto.user.service;

import com.lotto.user.dto.User;
import com.lotto.user.repository.Mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public void testService() {
        User testUser = User.builder()
                        .userId("test2")
                        .password("123")
                        .name("지해")
                        .crtIp("127.0.0.1")
                        .build();
        int result = userMapper.insertUser(testUser);


        log.info("result={}", result);
    }
}
