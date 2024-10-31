package com.lotto.lotto.controller;

import com.lotto.drawNum.DrawNumService;
import com.lotto.lotto.dto.Lotto;
import com.lotto.lotto.service.LottoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

        Integer drawNum = drawNumService.selectDrawNum() - 1;
        return lottoService.getLatestLottery(drawNum);
    }
}
