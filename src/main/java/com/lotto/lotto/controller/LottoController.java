package com.lotto.lotto.controller;

import com.lotto.drawNum.service.DrawNumService;
import com.lotto.lotto.dto.Lotto;
import com.lotto.lotto.service.LottoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/lotto")
public class LottoController {

    private final LottoService lottoService;
    private final DrawNumService drawNumService;

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

}
