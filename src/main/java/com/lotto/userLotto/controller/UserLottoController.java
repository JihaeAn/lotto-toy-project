package com.lotto.userLotto.controller;

import com.lotto.userLotto.service.UserLottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user-lotto")
public class UserLottoController {

    private final UserLottoService userLottoService;

    @PostMapping("/save")
    public ResponseEntity<String> saveLottoNumbers(@RequestBody Map<String, List<Integer>> body) {
        List<Integer> numbers = body.get("numbers");

        if (numbers.size() == 6) {
            userLottoService.saveLottoNumbers(numbers);
            return ResponseEntity.ok("로또 번호가 성공적으로 생성되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("로또 번호 생성 중에 문제가 발생하였습니다.");
        }
    }
}
