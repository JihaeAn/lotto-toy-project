package com.lotto.userLotto.repository.mapper;

import com.lotto.userLotto.dto.UserLotto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLottoMapper {

    int saveLottoNumbers(UserLotto userLotto);
}
