package com.lotto.publicDo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/kakao")
@Slf4j
public class KakaoApiController {

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    @GetMapping("/search/address")
    public ResponseEntity<?> searchAddress(@RequestParam String query) {
        log.info("address={}", query);

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + query;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoApiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            log.info("response={}", response.getBody());  // 로그로 response 내용 출력

            // JSON 파싱 (x, y 값 추출)
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode documentsNode = rootNode.path("documents");

            if (documentsNode.isArray() && documentsNode.size() > 0) {
                String x = documentsNode.get(0).path("x").asText();
                String y = documentsNode.get(0).path("y").asText();

                log.info("Latitude: {}", y);
                log.info("Longitude: {}", x);

                // 응답으로 x, y 값 반환 (필요에 따라 수정)
                return ResponseEntity.ok("y: " + y + ", x: " + x);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주소를 찾을 수 없습니다.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
