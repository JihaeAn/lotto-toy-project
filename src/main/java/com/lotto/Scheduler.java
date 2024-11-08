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

    @Scheduled(cron = "0 50 20 ? * 6")
    public void updateLottoResults() {

        // 행운 복권 사이트에서 API로 당첨 번호 가져오기
        String lottoDrawResult = lottoService.getLottoDrawResultApi();

        // 받아온 데이터 역직렬화 JSON -> LottoDrawResult
        LottoDrawApiResult newLotto = lottoService.lottoDrawResultToLotto(lottoDrawResult);

        // 로또 당첨 번호 DB에 저장하기
        lottoService.saveLottoDrawResult(newLotto);

        // 기존 회차 데이터 + 1
        drawNumService.updateDrawNum();
    }

    // 테스트 용
    @Scheduled(initialDelay = 3000, fixedDelay = 10000)
    public void runAfterTenSecondsRepeatTenSeconds() {

//        String lottoDrawResult = lottoService.getLottoDrawResultApi();
//        log.info("lottoDrawResult = {}", lottoDrawResult);
//        LottoDrawApiResult newLotto = lottoService.lottoDrawResultToLotto(lottoDrawResult);
//        log.info("역직렬화 성공={}", newLotto);
//        lottoService.saveLottoDrawResult(newLotto);
//        drawNumService.updateDrawNum();

        try {
            statsService.getStats();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
