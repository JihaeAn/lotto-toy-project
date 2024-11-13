package com.lotto.drawNum.repository.mapper;

import com.lotto.drawNum.dto.DrawNum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DrawNumMapper {

    Integer selectDrawNum();

    List<Integer> getDrawNumList();

    void updateDrawNum(String crtDt);

    void saveAllDrawNum(DrawNum drawNum);
}
