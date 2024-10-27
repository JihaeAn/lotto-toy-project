package com.lotto.userLotto.repository.mapper;

import com.lotto.drawNum.dto.DrawNum;
import com.lotto.userLotto.dto.UserLotto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLottoMapper {
    DrawNum selectDrawNum();

    int saveLottoNumbers(UserLotto userLotto);
}
