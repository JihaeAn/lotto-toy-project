package com.lotto.lotto.service;

import com.lotto.lotto.dto.Lotto;
import com.lotto.lotto.repository.mapper.LottoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LottoService {

    private final LottoMapper lottoMapper;

    public Lotto getLatestLottery(Integer drawNum) {
        Lotto latestLottery = lottoMapper.getLatestLottery(drawNum);
        return latestLottery;
    }
}
