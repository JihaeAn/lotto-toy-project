package com.lotto.store.service;

import com.lotto.drawNum.service.DrawNumService;
import com.lotto.store.dto.Store;
import com.lotto.store.repository.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDateTime.now;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreMapper storeMapper;
    private final DrawNumService drawNumService;

    // 크롤링해온 Document에서 판매점 이름, 주소만 뽑는 메서드
    public Map<String, String> getStoreInfo(Document doc) {

        Element table = doc.select("div.group_content table.tbl_data.tbl_data_col").first();
        Elements rows = table.select("tbody tr");

        Map<String, String> store = new HashMap<>();

        for (Element row : rows) {
            String storeName = row.select("td:nth-child(2)").text();
            String address = row.select("td:nth-child(4)").text();

            store.put(storeName, address);
        }

        log.info("1등 판매점 리스트 ={}", store);

        // DB에 저장하는 메서드
        saveStoreList(store);
        return store;
    }

    // 1등 판매점 목록 DB에 저장하는 메서드
    public void saveStoreList(Map<String, String> storeList) {

        String formattedDate = now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        for (Map.Entry<String, String> perStore : storeList.entrySet()) {
            String name = perStore.getKey();
            String address = perStore.getValue();

            Store store = Store.builder()
                    .drawNum(drawNumService.selectDrawNum())
                    .address(address)
                    .storeName(name)
                    .crtDt(formattedDate)
                    .build();

            storeMapper.saveStoreList(store);
        }
    }
}
