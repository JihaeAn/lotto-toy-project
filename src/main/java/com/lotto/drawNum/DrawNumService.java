package com.lotto.drawNum;

import com.lotto.drawNum.repository.mapper.DrawNumMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DrawNumService {

    private final DrawNumMapper drawNumMapper;
    public Integer selectDrawNum() {
        return drawNumMapper.selectDrawNum();
    }
}
