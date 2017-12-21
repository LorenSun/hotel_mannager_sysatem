package com.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hotel.common.Const;
import com.hotel.common.ResponseCode;
import com.hotel.common.ServerResponse;
import com.hotel.dao.CategoryMapper;
import com.hotel.dao.RoomMapper;
import com.hotel.pojo.Category;
import com.hotel.pojo.Room;
import com.hotel.service.ICategoryService;
import com.hotel.service.IRoomService;
import com.hotel.util.DateTimeUtil;
import com.hotel.vo.RoomDetailVo;
import com.hotel.vo.RoomListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HASEE on 2017/12/14.
 */
@Service("iRoomService")
public class RoomServiceImpl implements IRoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ICategoryService iCategoryService;

    public ServerResponse saveOrUpdateRoom(Room room){
        if(room !=null){
            if(StringUtils.isNotBlank(room.getSubImages())){
                String[] subImageArray = room.getSubImages().split(",");
                if(subImageArray.length>0){
                    room.setMainImage(subImageArray[0]);
                }
            }
            if (room.getId()!=null){
                int rowCount = roomMapper.updateByPrimaryKey(room);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("更新房间成功");
                }
                return ServerResponse.createByErrorMessage("更新房间失败");
            }else{
                int rowCount =  roomMapper.insert(room);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("新增房间成功");
                }
                return ServerResponse.createByErrorMessage("新增房间失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新房间参数不正确");
    }


    public ServerResponse<String> setSaleStaus(Integer roomId,Integer status){
        if (roomId == null || status == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUEMENT.getCode(),ResponseCode.ILLEGAL_ARGUEMENT.getDesc());
        }
        Room room = new Room();
        room.setId(roomId);
        room.setStatus(status);
        int rowCount = roomMapper.updateByPrimaryKeySelective(room);
        if (rowCount > 0){
            return ServerResponse.createBySuccess("修改房间销售状态成功");
        }
        return ServerResponse.createByErrorMessage("修改房间销售状态失败");
    }

    public ServerResponse<RoomDetailVo> manageRoomDetail(Integer roomId){
        if (roomId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUEMENT.getCode(),ResponseCode.ILLEGAL_ARGUEMENT.getDesc());
        }
        Room room = roomMapper.selectByPrimaryKey(roomId);
        if(room == null){
            return ServerResponse.createByErrorMessage("产品已下架或删除");
        }
        RoomDetailVo roomDetailVo = assembleRoomDetailVo(room);
        //Vo对象 value object
        //po->bo(business object)->vo(view object)
        return  ServerResponse.createBySuccess(roomDetailVo);
    }

    private RoomDetailVo assembleRoomDetailVo(Room room){
        RoomDetailVo roomDetailVo = new RoomDetailVo();
        roomDetailVo.setId(room.getId());
        roomDetailVo.setMainImage(room.getMainImage());
        roomDetailVo.setSubImage(room.getSubImages());
        roomDetailVo.setStatus(room.getStatus());
        roomDetailVo.setCategoryId(room.getCategoryId());
        roomDetailVo.setFloor(room.getFloor());
        roomDetailVo.setRoomNum(room.getRoomNum());
        roomDetailVo.setStatus(room.getStatus());
        roomDetailVo.setDetail(room.getDetail());
        roomDetailVo.setOrientation(room.getOrientation());

        //imageHost
        //roomDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","http://img.happymmall.com/"));
        Category category = categoryMapper.selectByPrimaryKey(room.getCategoryId());
        if(category == null){
            roomDetailVo.setParentCategoryId(0);//默认根节点
        }else{
            roomDetailVo.setParentCategoryId(category.getParentId());
        }

        //prentCategoryId

        roomDetailVo.setCreateTime(DateTimeUtil.dateToStr(room.getCreateTime()));
        roomDetailVo.setUpdateTime(DateTimeUtil.dateToStr(room.getUpdateTime()));
        //createTime
        //updateTime
        return roomDetailVo;

    }
    public ServerResponse<PageInfo> getProductList(int pageNum,int pageSize){
        //startPage--start
        //填充自己的sql查询逻辑
        //pageHelper--收尾
        PageHelper.startPage(pageNum,pageSize);
        List<Room> roomList = roomMapper.selectList();

        List<RoomListVo> roomListVoList = Lists.newArrayList();
        for (Room roomItem : roomList){
            RoomListVo roomListVo = assembleRoomListVo(roomItem);
            roomListVoList.add(roomListVo);
        }
        PageInfo pageResult = new PageInfo(roomList);

        pageResult.setList(roomListVoList);

        return  ServerResponse.createBySuccess(pageResult);
    }
    private RoomListVo assembleRoomListVo(Room room){
        RoomListVo roomListVo = new RoomListVo();
        roomListVo.setId(room.getId());
        roomListVo.setRoomNum(room.getRoomNum());
        roomListVo.setCategoryId(room.getCategoryId());
        roomListVo.setFloor(room.getFloor());
        roomListVo.setOrientation(room.getOrientation());
        //roomListVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","http://img.happymmall.com/"));
        roomListVo.setMainImage(room.getMainImage());
        roomListVo.setPrice(room.getPrice());
        roomListVo.setSubtitle(room.getSubtitle());
        roomListVo.setStatus(room.getStatus());
        return roomListVo;
    }
    public ServerResponse<PageInfo> searchRoom(String roomName,Integer roomId,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(roomName)){
            roomName = new StringBuilder().append("%").append(roomName).append("%").toString();
        }
        List<Room> roomList = roomMapper.selectByNameAndRoomId(roomName,roomId);
        List<RoomListVo> roomListVoList = Lists.newArrayList();
        for(Room roomItem : roomList){
            RoomListVo roomListVo = assembleRoomListVo(roomItem);
            roomListVoList.add(roomListVo);
        }
        PageInfo pageResult = new PageInfo(roomList);
        pageResult.setList(roomListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }
    public ServerResponse<RoomDetailVo> getRoomDetail(Integer productId){
        if(productId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUEMENT.getCode(),ResponseCode.ILLEGAL_ARGUEMENT.getDesc());
        }
        Room room = roomMapper.selectByPrimaryKey(productId);
        if(room == null){
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }
        if(room.getStatus() != Const.RoomStatusEnum.ON_SALE.getCode()){
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }
        RoomDetailVo roomDetailVo = assembleRoomDetailVo(room);
        return ServerResponse.createBySuccess(roomDetailVo);
    }


    public ServerResponse<PageInfo> getRoomByKeywordCategory(String keyword,Integer categoryId,int pageNum,int pageSize,String orderBy){
        if(StringUtils.isBlank(keyword) && categoryId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUEMENT.getCode(),ResponseCode.ILLEGAL_ARGUEMENT.getDesc());
        }
        List<Integer> categoryIdList = new ArrayList<Integer>();

        if(categoryId != null){
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            if(category == null && StringUtils.isBlank(keyword)){
                //没有该分类,并且还没有关键字,这个时候返回一个空的结果集,不报错
                PageHelper.startPage(pageNum,pageSize);
                List<RoomListVo> productListVoList = Lists.newArrayList();
                PageInfo pageInfo = new PageInfo(productListVoList);
                return ServerResponse.createBySuccess(pageInfo);
            }
            categoryIdList = iCategoryService.selectCategoryAndChildrenById(category.getId()).getData();
        }
        if(StringUtils.isNotBlank(keyword)){
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }

        PageHelper.startPage(pageNum,pageSize);
        //排序处理
        if(StringUtils.isNotBlank(orderBy)){
            if(Const.RoomListOrderBy.PRICE_ASC_DESC.contains(orderBy)){
                String[] orderByArray = orderBy.split("_");
                PageHelper.orderBy(orderByArray[0]+" "+orderByArray[1]);
            }
        }
        List<Room> roomList = roomMapper.selectByNameAndCategoryIds(StringUtils.isBlank(keyword)?null:keyword,categoryIdList.size()==0?null:categoryIdList);

        List<RoomListVo> roomListVoList = Lists.newArrayList();
        for(Room room : roomList){
            RoomListVo roomListVo = assembleRoomListVo(room);
            roomListVoList.add(roomListVo);
        }

        PageInfo pageInfo = new PageInfo(roomList);
        pageInfo.setList(roomListVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }


}
