package com.lotto.stats.repository.mapper;

import com.lotto.stats.dto.Stats;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatsMapper {
    void saveStats(Stats firstStats);

    List<Stats> getStats(Integer drawNum);
}
