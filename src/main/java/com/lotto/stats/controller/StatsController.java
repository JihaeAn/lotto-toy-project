package com.lotto.stats.controller;

import com.lotto.stats.dto.Stats;
import com.lotto.stats.service.StatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    @ResponseBody
    @GetMapping("/get")
    public List<Stats> getStats() {
        return statsService.getStats();
    }
}
