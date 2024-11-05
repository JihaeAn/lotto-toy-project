package com.lotto.drawNum.controller;

import com.lotto.drawNum.service.DrawNumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/drawNum")
public class DrawNumController {

    private final DrawNumService drawNumService;

    @ResponseBody
    @GetMapping("/get/list")
    public List<Integer> getDrawNumList() {
        List<Integer> list = drawNumService.getDrawNumList();
        // 토요일에 로또가 추첨되면 drawNum도 + 1 해줄 거기 때문에 최근 당첨번호를 보려면 - 1을 해주어야 한다.
        list.remove(0);
        return list;
    }
}
