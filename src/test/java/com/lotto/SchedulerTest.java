package com.lotto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


@SpringBootTest
class SchedulerTest {

    private final Scheduler scheduler;

    @Autowired
    public SchedulerTest(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Test
    public void schedulerTest() {
        // 토요일 오후 8:50로 임시 설정
        Clock fixedClock = Clock.fixed(Instant.parse("2024-11-09T21:50:00Z"), ZoneId.of("Asia/Seoul"));
        LocalDateTime testTime = LocalDateTime.now(fixedClock);

        scheduler.updateLottoResults();
    }
}