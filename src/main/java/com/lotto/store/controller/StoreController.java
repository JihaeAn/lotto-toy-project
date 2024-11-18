package com.lotto.store.controller;

import com.lotto.store.dto.Store;
import com.lotto.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @ResponseBody
    @GetMapping("/get/storeList")
    public List<Store> getStoreList() {
        return storeService.getStoreList();
    }
}
