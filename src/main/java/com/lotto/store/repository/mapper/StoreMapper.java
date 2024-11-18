package com.lotto.store.repository.mapper;

import com.lotto.store.dto.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {

    void saveStoreList(Store store);

    List<Store> getStoreList(Integer drawNum);
}
