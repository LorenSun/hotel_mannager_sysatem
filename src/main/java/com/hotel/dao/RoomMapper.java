package com.hotel.dao;

import com.hotel.pojo.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    List<Room> selectList();

    List<Room> selectByNameAndRoomId(@Param("roomNum")String roomNum,@Param("roomId")Integer roomId);

    List<Room> selectByNameAndCategoryIds(@Param("roomNum")String roomNum,@Param("categoryIdList")List<Integer> categoryIdList);
}