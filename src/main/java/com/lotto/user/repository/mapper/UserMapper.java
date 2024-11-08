package com.lotto.user.repository.mapper;

import com.lotto.user.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insertUser(User user);
}
