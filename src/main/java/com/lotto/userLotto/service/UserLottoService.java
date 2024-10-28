package com.lotto.userLotto.service;

import com.lotto.drawNum.dto.DrawNum;
import com.lotto.userLotto.dto.UserLotto;
import com.lotto.userLotto.repository.mapper.UserLottoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserLottoService {

    private final UserLottoMapper userLottoMapper;

    public DrawNum selectDrawNum() {
        return userLottoMapper.selectDrawNum();
    }

    public void saveLottoNumbers(UserLotto userLotto) {
        userLotto = UserLotto.builder()
                .drawNum(selectDrawNum().getDrawNum()) // selectDrawNum()을 사용할 예정인데 그럼 drawNum 테이블은 따로 뺄지 고민 중
                .userSeq(1) // test
                .lottoNum1(userLotto.getLottoNum1())
                .lottoNum2(userLotto.getLottoNum2())
                .lottoNum3(userLotto.getLottoNum3())
                .lottoNum4(userLotto.getLottoNum4())
                .lottoNum5(userLotto.getLottoNum5())
                .lottoNum6(userLotto.getLottoNum6())
                .lottoRank(0) // test
                .crtIp("127.0.0.1")
                .build();

        int result = userLottoMapper.saveLottoNumbers(userLotto);
    }
}
