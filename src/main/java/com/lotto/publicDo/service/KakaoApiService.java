package com.lotto.publicDo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class KakaoApiService {

    private final String kakaoApiKey;

    public KakaoApiService(@Value("${kakao.api.key}") String kakaoApiKey) {
        this.kakaoApiKey = kakaoApiKey;
    }

    // 주소로 위도 경도 받아오는 api 메서드(파싱 전)
    public String callKakaoApi(String address) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + address;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoApiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Kakao API 호출 실패");
        }
    }

    // 받아온 api 결과 x, y 값만 파싱하는 메서드
    public Optional<Map<String, Object>> parseKakaoApiResponse(String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode documentsNode = rootNode.path("documents");

            if (documentsNode.isArray() && !documentsNode.isEmpty()) {
                String x = documentsNode.get(0).path("x").asText();
                String y = documentsNode.get(0).path("y").asText();

                Map<String, Object> result = new HashMap<>();
                result.put("x", x);
                result.put("y", y);
                return Optional.of(result);
            }

            // if 조건이 만족되지 않으면 자연스럽게 log.warn과 Optional.empty()로 넘어가므로 else 노필요
            log.warn("주소를 파싱할 수 없습니다.");
            return Optional.empty(); // documentsNode가 비어 있을 때

        } catch (JsonProcessingException e) {
            log.error("Error parsing Kakao API response", e);
            throw new IllegalArgumentException("Failed to parse Kakao API response");
        }
    }
}
