package com.lotto.stats.repository.mapper;

import com.lotto.stats.dto.Stats;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatsMapper {
    void saveStats(Stats firstStats);
}
