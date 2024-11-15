package com.lotto.stats.service;

import com.lotto.drawNum.service.DrawNumService;
import com.lotto.stats.dto.Stats;
import com.lotto.stats.repository.mapper.StatsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;

@Slf4j
@RequiredArgsConstructor
@Service
public class StatsService {

    private final StatsMapper statsMapper;
    private final DrawNumService drawNumService;


    // 최근 회차 당첨 결과 통계 (1등 몇 명이고 상금 얼만지)
    public void getStatsCrol(Document doc) throws IOException {

        // 1등 상금액 받아오는 메서드
        List<String> prizeAmount = getWinAmoutList(doc);
        // 당첨자 수 받아오는 메서드
        List<String> winnerCount = getWinnerCountList(doc);
        // DB에 저장하는 메서드
        saveStats(prizeAmount, winnerCount);
    }


    // 1등 상금액 받아오는 메서드
    public List<String> getWinAmoutList(Document document) {
        Elements elements = document.select(".tbl_data.tbl_data_col .tar");

        //공백을 기준으로 데이터 나누기
        String[] str = elements.text().split(" ");

        // 상금액 담아놓는 List
        List<String> prizeAmount = new ArrayList<>();
        for (int i = 1; i < 6; i += 2) {
            String win = str[i].substring(0, str[i].length() - 1); // "원" 자르기
            prizeAmount.add(win);
        }

        prizeAmount = removeDot(prizeAmount);

        System.out.println("prizeAmount = " + prizeAmount);
        return prizeAmount;
    }

    // 당첨자 수 받아오는 메서드
    public List<String> getWinnerCountList(Document document) {
        Elements tds = document.select("table.tbl_data_col tbody tr td:nth-child(3)"); // 3번째 <td> 선택

        List<String> winnerCount = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            winnerCount.add(tds.get(i).text());
        }

        winnerCount = removeDot(winnerCount);

        System.out.println("winnerCount = " + winnerCount);
        return winnerCount;
    }

    // , 빼는 메서드
    public List<String> removeDot(List<String> list) {
        List<String> result = new ArrayList<>();

        for (String s : list) {
            result.add(s.replace(",", ""));
        }

        return result;
    }


    // DB에 저장하는 메서드
    public void saveStats(List<String> prizeAmount, List<String> winnerCount) {

        // Resolver를 안 거치기에 LocalDateTime 포맷팅
        String formattedDate = now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Integer drawNum = drawNumService.selectDrawNum();
        for(int i = 0; i < 3; i++) {

            Stats firstStats = Stats.builder()
                    .drawNum(drawNum)
                    .lottoRank(i + 1)
                    .winnerCount(Integer.parseInt(winnerCount.get(i)))
                    .prizeAmount(Long.parseLong(prizeAmount.get(i)))
                    .crtDt(formattedDate)
                    .build();

            statsMapper.saveStats(firstStats);
        }
    }

    // DB에 저장된 통계 자료 가져오는 메서드
    public List<Stats> getStats() {
        Integer drawNum = drawNumService.selectDrawNum() - 1;
        return statsMapper.getStats(drawNum);
    }
}
