package com.lotto.userLotto.controller;

import com.lotto.userLotto.dto.UserLotto;
import com.lotto.userLotto.service.UserLottoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user-lotto")
public class UserLottoController {

    private final UserLottoService userLottoService;

    @ResponseBody
    @PostMapping("/save")
    public String saveLottoNumbers(@RequestBody UserLotto userLotto) {

        log.info("userIp={}", userLotto);
        int result = userLottoService.saveLottoNumbers(userLotto);

        return "성공";
    }

    @ResponseBody
    @GetMapping("/get")
    public List<UserLotto> getUserWinningRecord(@RequestParam (defaultValue = "null") Integer drawNum) {

        return userLottoService.getUserWinningRecord(drawNum);
    }
}
