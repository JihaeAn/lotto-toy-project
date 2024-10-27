package com.lotto.userLotto.service;

import com.lotto.drawNum.dto.DrawNum;
import com.lotto.userLotto.dto.UserLotto;
import com.lotto.userLotto.repository.mapper.UserLottoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLottoService {

    private final UserLottoMapper userLottoMapper;

    public DrawNum selectDrawNum() {
        return userLottoMapper.selectDrawNum();
    }

    public void saveLottoNumbers(List<Integer> numbers) {
        UserLotto userLotto = UserLotto.builder()
                .drawNum(selectDrawNum().getDrawNum()) // selectDrawNum()을 사용할 예정인데 그럼 drawNum 테이블은 따로 뺄지 고민 중
                .userSeq(1) // test
                .lottoNum1(numbers.get(0))
                .lottoNum2(numbers.get(1))
                .lottoNum3(numbers.get(2))
                .lottoNum4(numbers.get(3))
                .lottoNum5(numbers.get(4))
                .lottoNum6(numbers.get(5))
                .lottoRank(0) // test
                .crtIp("127.0.0.1")
                .build();

        int result = userLottoMapper.saveLottoNumbers(userLotto);
    }
}
