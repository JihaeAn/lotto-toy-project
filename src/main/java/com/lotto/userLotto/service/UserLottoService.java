package com.lotto.userLotto.service;

import com.lotto.drawNum.DrawNumService;
import com.lotto.userLotto.dto.UserLotto;
import com.lotto.userLotto.repository.mapper.UserLottoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class UserLottoService {

    private final UserLottoMapper userLottoMapper;
    private final DrawNumService drawNumService;

    public void saveLottoNumbers(UserLotto userLotto) {
        userLotto = UserLotto.builder()
                .drawNum(drawNumService.selectDrawNum()) // 제일 최신 회차 가져오기
                .userSeq(1) // test
                .lottoNum1(userLotto.getLottoNum1())
                .lottoNum2(userLotto.getLottoNum2())
                .lottoNum3(userLotto.getLottoNum3())
                .lottoNum4(userLotto.getLottoNum4())
                .lottoNum5(userLotto.getLottoNum5())
                .lottoNum6(userLotto.getLottoNum6())
                .crtDt(new Date())
                .lottoRank(0) // test
                .crtIp("127.0.0.1")
                .build();

        int result = userLottoMapper.saveLottoNumbers(userLotto);
    }

    public List<UserLotto> getUserWinningRecord(Integer drawNum) {

        if (drawNum == null) {
            drawNum = drawNumService.selectDrawNum();
            return userLottoMapper.getUserWinningRecord(drawNum);
        } else {
            return userLottoMapper.getUserWinningRecord(drawNum);
        }
    }
}
