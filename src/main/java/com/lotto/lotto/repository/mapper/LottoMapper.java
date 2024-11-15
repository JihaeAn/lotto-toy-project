package com.lotto.lotto.repository.mapper;

import com.lotto.lotto.dto.Lotto;
import com.lotto.lotto.dto.LottoDrawApiResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LottoMapper {

    Lotto getLottery(Integer drawNum);

    void saveLottoDrawResult(LottoDrawApiResult newLotto);

    Lotto getLatestLottery();

    List<Map<String, Object>> getMostNumStats(Map<String, String> date);
}
