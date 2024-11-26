package com.lotto.publicDo.controller;

import com.lotto.publicDo.service.KakaoApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/kakao")
@Slf4j
@RequiredArgsConstructor
public class KakaoApiController {

    private final KakaoApiService kakaoApiService;

    @GetMapping("/search/address/{address}")
    public Optional<Map<String, Object>> searchAddress(@PathVariable("address") String address) {
        log.info("Received address: {}", address);

        // API 호출
        String responseBody = kakaoApiService.callKakaoApi(address);

        // 응답 파싱 및 결과 반환
        return kakaoApiService.parseKakaoApiResponse(responseBody);
    }
}
