package com.lotto.userLotto.controller;

import com.lotto.userLotto.dto.UserLotto;
import com.lotto.userLotto.service.UserLottoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user-lotto")
public class UserLottoController {

    private final UserLottoService userLottoService;

    @PostMapping("/save")
    public void saveLottoNumbers(@RequestBody UserLotto userLotto) {
        log.info("numbers={}", userLotto);
    }
}
