package com.lotto.lotto.repository.mapper;

import com.lotto.lotto.dto.Lotto;
import com.lotto.lotto.dto.LottoDrawApiResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LottoMapper {

    Lotto getLatestLottery(Integer drawNum);

    void saveLottoDrawResult(LottoDrawApiResult newLotto);
}
