package com.hotel.service;

import com.github.pagehelper.PageInfo;
import com.hotel.common.ServerResponse;
import com.hotel.pojo.Room;
import com.hotel.vo.RoomDetailVo;

/**
 * Created by HASEE on 2017/12/14.
 */
public interface IRoomService {

    ServerResponse saveOrUpdateRoom(Room room);

    ServerResponse<String> setSaleStaus(Integer roomId,Integer status);

    ServerResponse<RoomDetailVo> manageRoomDetail(Integer roomId);

    ServerResponse getProductList(int pageNum,int pageSize);

    ServerResponse<PageInfo> searchRoom(String roomNum, Integer roomId, int pageNum, int pageSize);

    ServerResponse<RoomDetailVo> getRoomDetail(Integer roomId);

    ServerResponse<PageInfo> getRoomByKeywordCategory(String keyword,Integer categoryId,int pageNum,int pageSize,String orderBy);

}
