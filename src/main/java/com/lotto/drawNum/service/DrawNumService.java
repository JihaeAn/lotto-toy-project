package com.lotto.drawNum.service;

import com.lotto.drawNum.repository.mapper.DrawNumMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrawNumService {

    private final DrawNumMapper drawNumMapper;
    public Integer selectDrawNum() {
        return drawNumMapper.selectDrawNum();
    }

    public List<Integer> getDrawNumList() {
        return drawNumMapper.getDrawNumList();
    }
}
