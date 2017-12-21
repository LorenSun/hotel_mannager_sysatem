package com.hotel.controller.portal;

import com.github.pagehelper.PageInfo;
import com.hotel.common.ServerResponse;
import com.hotel.service.IRoomService;
import com.hotel.vo.RoomDetailVo;
import com.hotel.common.ServerResponse;
import com.hotel.service.IRoomService;
import com.hotel.vo.RoomDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by geely
 */

@Controller
@RequestMapping("/room/")
public class RoomController {

    @Autowired
    private IRoomService iRoomService;



    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<RoomDetailVo> detail(Integer roomId){
        return iRoomService.getRoomDetail(roomId);
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword",required = false)String keyword,
                                         @RequestParam(value = "categoryId",required = false)Integer categoryId,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                         @RequestParam(value = "orderBy",defaultValue = "") String orderBy){
        return iRoomService.getRoomByKeywordCategory(keyword,categoryId,pageNum,pageSize,orderBy);
    }





}
