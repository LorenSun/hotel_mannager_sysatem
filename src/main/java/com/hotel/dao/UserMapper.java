package com.hotel.dao;

import com.hotel.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    int checkEmail(String email);

    int checkPhone(String phone);

    int checkIDCard(String id_card);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    int checkAnswer(@Param("username") String username,@Param("phone") String phone,@Param("id_card") String id_card);

}