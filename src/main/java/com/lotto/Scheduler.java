package com.lotto;

import com.lotto.drawNum.service.DrawNumService;
import com.lotto.lotto.dto.LottoDrawApiResult;
import com.lotto.lotto.service.LottoService;
import com.lotto.publicDo.service.PublicService;
import com.lotto.stats.service.StatsService;
import com.lotto.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Service
public class Scheduler {

    private final LottoService lottoService;
    private final DrawNumService drawNumService;
    private final StatsService statsService;
    private final PublicService publicService;
    private final StoreService storeService;

    @Scheduled(cron = "0 10 21 ? * 6")
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
            String statsUrl = "https://dhlottery.co.kr/gameResult.do?method=byWin";
            Document statsDoc = publicService.getDocumentToCrol(statsUrl);
            statsService.getStatsCrol(statsDoc);

            // 1등 판매점 크롤링해와서 DB에 저장
            String storeUrl = "https://www.dhlottery.co.kr/store.do?method=topStore&pageGubun=L645";
            Document doc = publicService.getDocumentToCrol(storeUrl);
            storeService.getStoreInfo(doc);

            // 기존 회차 데이터 + 1
            drawNumService.updateDrawNum();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 테스트 용
//    @Scheduled(initialDelay = 3000, fixedDelay = 10000)
//    public void runAfterTenSecondsRepeatTenSeconds() {
//
//        String toDate = lottoService.getLatestLottery().getCrtDt();
//        String fromDate = publicService.minusMonths(toDate, 6);
//
//        System.out.println("fromDate = " + fromDate);
//        System.out.println("toDate = " + toDate);
//
//        List<Map<Integer, Integer>> result = lottoService.getMostNumStats(fromDate, toDate);
//        log.info("제일 많이 나온 통계={}", result);
//
//        try {
//            // 원하는 페이지의 url로 Document 받아오기
//            String url = "https://www.dhlottery.co.kr/store.do?method=topStore&pageGubun=L645";
//            Document doc = publicService.getDocumentToCrol(url);
//
//            storeService.getStoreInfo(doc);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println("안녕 스케줄링 테스트야");
//    }
}
