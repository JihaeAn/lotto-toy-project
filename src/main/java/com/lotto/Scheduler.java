package com.lotto;

import com.lotto.drawNum.service.DrawNumService;
import com.lotto.lotto.dto.LottoDrawApiResult;
import com.lotto.lotto.service.LottoService;
import com.lotto.stats.service.StatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
@Service
public class Scheduler {

    private final LottoService lottoService;
    private final DrawNumService drawNumService;
    private final StatsService statsService;

    @Scheduled(cron = "0 5 21 ? * 6")
    public void updateLottoResults() {

        try {
            // 행운 복권 사이트에서 API로 당첨 번호 가져오기
            String drawNum = drawNumService.selectDrawNum().toString();
            String lottoDrawResult = lottoService.getLottoDrawResultApi(drawNum);

            // 받아온 데이터 역직렬화 JSON -> LottoDrawResult
            LottoDrawApiResult newLotto = lottoService.lottoDrawResultToLotto(lottoDrawResult);

            // 로또 당첨 번호 DB에 저장하기
            lottoService.saveLottoDrawResult(newLotto);

            // 당첨 통계 크롤링해와서 DB에 저장
            statsService.getStatsCrol();

            // 기존 회차 데이터 + 1
            drawNumService.updateDrawNum();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 테스트 용
//    @Scheduled(initialDelay = 3000, fixedDelay = 10000)
//    @PostConstruct
//    public void runAfterTenSecondsRepeatTenSeconds() {
//
//        // API로 역대 회차 당첨 정보 받아오기
//        for(int i = 100; i < 1144; i++) {
//            String drawNum = String.valueOf(i);
//            String lottoDrawResult = lottoService.getLottoDrawResultApi(drawNum);
//
//            LottoDrawApiResult newLotto = lottoService.lottoDrawResultToLotto(lottoDrawResult);
//            lottoService.saveLottoDrawResult(newLotto);
//        }

        // 회차부터 데이터 넣기
//        for(int i = 100; i < 1144; i++) {
//            System.out.println("TEST i = " + i);
//            drawNumService.saveAllDrawNum(i);
//        }
//    }
}
