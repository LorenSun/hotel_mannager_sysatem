package com.hotel.dao;

import com.hotel.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart selectCartByUserIdRoomId(@Param("userId") Integer userId, @Param("roomId")Integer roomId);

    List<Cart> selectCartByUserId(Integer userId);

    int selectCartRoomCheckedStatusByUserId(Integer userId);

    int deleteByUserIdRoomIds(@Param("userId") Integer userId, @Param("roomId")Integer roomId);

}