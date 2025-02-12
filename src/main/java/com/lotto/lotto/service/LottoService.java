package com.lotto.lotto.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotto.lotto.dto.Lotto;
import com.lotto.lotto.dto.LottoDrawApiResult;
import com.lotto.lotto.repository.mapper.LottoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class LottoService {

    private final LottoMapper lottoMapper;

    // 특정 회차의 당첨 결과 가져오는 메서드
    public Lotto getLottery(Integer drawNum) {
        return lottoMapper.getLottery(drawNum);
    }

    // 최신 회차 정보 객체로 가져오기
    public Lotto getLatestLottery() {
        return lottoMapper.getLatestLottery();
    }

    // 로또 당첨 결과 가져오는 API 타는 메서드
    public String getLottoDrawResultApi(String drawNum) {
        log.info("getLottoDrawResultApi drawNum={}", drawNum);

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + drawNum;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        return response.getBody();
    }

    // 받아온 로또 당첨 번호 역직렬화 JSON -> LottoDrawResult
    public LottoDrawApiResult lottoDrawResultToLotto(String lottoDrawResult) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            LottoDrawApiResult newLotto = objectMapper.readValue(lottoDrawResult, LottoDrawApiResult.class);
            log.info("new Lotto={}", newLotto);
            return newLotto;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new LottoDrawApiResult();
    }

    public void saveLottoDrawResult(LottoDrawApiResult newLotto) {
        try {
            lottoMapper.saveLottoDrawResult(newLotto);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    // 제일 많이 나온 숫자 통계 가져오기
    public List<Map<String, Object>> getMostNumStats(String fromDate, String toDate) {
        Map<String, String> date = new HashMap<>();
        date.put("fromDate", fromDate);
        date.put("toDate", toDate);
        return lottoMapper.getMostNumStats(date);
    }
}
