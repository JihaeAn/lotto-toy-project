package com.lotto.drawNum.service;

import com.lotto.drawNum.dto.DrawNum;
import com.lotto.drawNum.repository.mapper.DrawNumMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class DrawNumService {

    private final DrawNumMapper drawNumMapper;

    // 최신 회차 가져오기
    public Integer selectDrawNum() {
        return drawNumMapper.selectDrawNum();
    }

    // 회차 리스트 가져오기
    public List<Integer> getDrawNumList() {
        return drawNumMapper.getDrawNumList();
    }

    // 매주 토요일 9:00 마다 DrawNum 테이블 update
    public void updateDrawNum() {
        // Resolver를 안 거치기에 LocalDateTime 포맷팅
        String crtDt = now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        drawNumMapper.updateDrawNum(crtDt);
    }

    // 회차 전체 집어넣기위한 메서드
    public void saveAllDrawNum(Integer drawNum){
        DrawNum drawNumDto = new DrawNum();
        drawNumDto.setCrtDt(now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        drawNumDto.setDrawNum(drawNum);
        drawNumMapper.saveAllDrawNum(drawNumDto);
    }
}
