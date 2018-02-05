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

    int checkUsername(@Param("username") String username);

    int checkEmail(@Param("email") String email);

    int checkPhone(@Param("phone") String phone);

    int checkIDCard(@Param("id_card")String id_card);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    int checkAnswer(@Param("username") String username,@Param("phone") String phone,@Param("id_card") String id_card);

    int updatePasswordByUsername(@Param("username") String username,@Param("passwordNew") String passwordNew);

    int checkPassword(@Param("password") String password,@Param("userId") Integer userId);

    int checkEmailByUserId(@Param("email") String email,@Param("userId") Integer userId);
}