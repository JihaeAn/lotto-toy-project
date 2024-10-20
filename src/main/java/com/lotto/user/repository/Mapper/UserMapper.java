package com.lotto.user.repository.Mapper;

import com.lotto.user.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insertUser(User user);
}
