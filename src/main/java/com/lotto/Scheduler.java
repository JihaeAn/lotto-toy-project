package com.lotto;

import com.lotto.drawNum.service.DrawNumService;
import com.lotto.lotto.dto.Lotto;
import com.lotto.lotto.dto.LottoDrawApiResult;
import com.lotto.lotto.service.LottoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class Scheduler {

    private final LottoService lottoService;
    private final DrawNumService drawNumService;

    @Scheduled(cron = "0 50 20 ? * 6")
    public void updateLottoResults() {

        // 행운 복권 사이트에서 당첨 번호 가져오기
        String lottoDrawResult = lottoService.getLottoDrawResultApi();

        // 받아온 JSON -> Lotto로 역직렬화
        LottoDrawApiResult newLotto = lottoService.lottoDrawResultToLotto(lottoDrawResult);

        // lottoDrawResult LOTTO 테이블(DB)에 저장하기
        lottoService.saveLottoDrawResult(newLotto);

        // 회차 새로 insert 해줘야 함
        drawNumService.updateDrawNum();
    }

    // 테스트 용
    @Scheduled(initialDelay = 3000, fixedDelay = 3000)
    public void runAfterTenSecondsRepeatTenSeconds() {
        String lottoDrawResult = lottoService.getLottoDrawResultApi();
        LottoDrawApiResult newLotto = lottoService.lottoDrawResultToLotto(lottoDrawResult);
        log.info("10초 후 실행 => time : " + LocalTime.now());
        log.info("lottoDrawResult = {}", lottoDrawResult);
        log.info("역직렬화 성공={}", newLotto);
    }
}
