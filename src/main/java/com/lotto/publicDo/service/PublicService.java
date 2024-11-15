package com.lotto.publicDo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicService {

    // 기준 날짜에서 (원하는 달수만큼) 과거의 날짜 구하기
    public String minusMonths(String standardDate, Integer month) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(standardDate, formatter);

        return date.minusMonths(month).format(formatter);
    }

    // url 받아서 Document 파일로 돌려주는 메서드 (공용으로 빼봤음)
    public Document getDocumentToCrol(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
