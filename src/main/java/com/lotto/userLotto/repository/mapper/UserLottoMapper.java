package com.lotto.userLotto.repository.mapper;

import com.lotto.userLotto.dto.UserLotto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserLottoMapper {

    int saveLottoNumbers(UserLotto userLotto);

    List<UserLotto> getUserWinningRecord(Integer drawNum);
}
