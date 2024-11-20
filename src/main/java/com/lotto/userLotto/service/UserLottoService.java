package com.lotto.userLotto.service;

import com.lotto.drawNum.service.DrawNumService;
import com.lotto.userLotto.dto.UserLotto;
import com.lotto.userLotto.repository.mapper.UserLottoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserLottoService {

    private final UserLottoMapper userLottoMapper;
    private final DrawNumService drawNumService;

    public int saveLottoNumbers(UserLotto userLotto) {

        userLotto = createUserLottoEntity(userLotto);
        return userLottoMapper.saveLottoNumbers(userLotto);
    }

    private UserLotto createUserLottoEntity(UserLotto userLotto) {
        userLotto = UserLotto.builder()
                .drawNum(drawNumService.selectDrawNum()) // 제일 최신 회차 가져오기
                .userSeq(1) // test
                .lottoNum1(userLotto.getLottoNum1())
                .lottoNum2(userLotto.getLottoNum2())
                .lottoNum3(userLotto.getLottoNum3())
                .lottoNum4(userLotto.getLottoNum4())
                .lottoNum5(userLotto.getLottoNum5())
                .lottoNum6(userLotto.getLottoNum6())
                .crtDt(userLotto.getCrtDt())
                .lottoRank(0) // test
                .crtIp(userLotto.getCrtIp())
                .build();
        return userLotto;
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
