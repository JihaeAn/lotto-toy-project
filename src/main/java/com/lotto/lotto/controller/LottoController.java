package com.lotto.lotto.controller;

import com.lotto.drawNum.service.DrawNumService;
import com.lotto.lotto.dto.Lotto;
import com.lotto.lotto.service.LottoService;
import com.lotto.publicDo.service.PublicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/lotto")
public class LottoController {

    private final LottoService lottoService;
    private final DrawNumService drawNumService;
    private final PublicService publicService;

    @ResponseBody
    @GetMapping("/get/latest-lottery")
    public Lotto getLatestLottery() {
        // 토요일에 로또가 추첨되면 drawNum도 + 1 해줄 거기 때문에 최근 당첨번호를 보려면 - 1을 해주어야 한다.
        Integer drawNum = drawNumService.selectDrawNum() - 1;
        return lottoService.getLottery(drawNum);
    }

    @ResponseBody
    @GetMapping("/get/lottery")
    public Lotto searchWinningNum(@RequestParam(defaultValue = "null") Integer drawNum) {
        return lottoService.getLottery(drawNum);
    }

    @ResponseBody
    @GetMapping("/get/Stats")
    public List<Map<String, Object>> getMostNumStats() {
        // winning_num 테이블에서 시작 날짜와 마지막 날짜 구해야 함
        // 제일 최신 회차의 날짜(fromDate) 구하고 6개월 전(toDate)부터 통계 내자
        String toDate = lottoService.getLatestLottery().getCrtDt();
        String fromDate = publicService.minusMonths(toDate, 6);

        List<Map<String, Object>> result = lottoService.getMostNumStats(fromDate, toDate);
        log.info("제일 많이 당첨된 숫자={}", result);

        return result;
    }
}
