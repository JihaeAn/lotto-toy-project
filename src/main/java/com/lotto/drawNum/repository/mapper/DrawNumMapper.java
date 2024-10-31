package com.lotto.drawNum.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DrawNumMapper {

    Integer selectDrawNum();
}
