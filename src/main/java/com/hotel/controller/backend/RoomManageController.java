package com.hotel.controller.backend;

import com.google.common.collect.Maps;
import com.hotel.common.Const;
import com.hotel.common.ResponseCode;
import com.hotel.common.ServerResponse;
import com.hotel.pojo.Room;
import com.hotel.pojo.User;
import com.hotel.service.IFileService;
import com.hotel.service.IRoomService;
import com.hotel.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by HASEE on 2017/12/14.
 */
@Controller
@RequestMapping("/manage/room")
public class RoomManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IRoomService iRoomService;
   // @Autowired
  //  private IFileService iFileService;

    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse RoomSave(HttpSession session, Room room){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //增加房间的业务逻辑
            return iRoomService.saveOrUpdateRoom(room);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse SetSaleStatus(HttpSession session, Integer roomId,Integer status){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //修改房间的业务逻辑
            return iRoomService.setSaleStaus(roomId,status);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse GetDetail(HttpSession session, Integer roomId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return  iRoomService.manageRoomDetail(roomId);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse GetList(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return  iRoomService.getProductList(pageNum,pageSize);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }
    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse roomSearch(HttpSession session, String roomNum,Integer roomId,@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return  iRoomService.searchRoom(roomNum,roomId,pageNum,pageSize);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }
//    @RequestMapping("upload.do")
//    @ResponseBody
//    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);
//        if(user == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
//        }
//        if(iUserService.checkAdminRole(user).isSuccess()){
//            String path = request.getSession().getServletContext().getRealPath("upload");
//            String targetFileName = iFileService.upload(file,path);
//           // String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
//
//            Map fileMap = Maps.newHashMap();
//            fileMap.put("url",targetFileName);
//           // fileMap.put("url",url);
//            return ServerResponse.createBySuccess(fileMap);
//        }else{
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }
//    }
//    @RequestMapping("richtext_img_upload.do")
//    @ResponseBody
//    public Map richtextImgUpload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
//        Map resultMap = Maps.newHashMap();
//        User user = (User)session.getAttribute(Const.CURRENT_USER);
//        if(user == null){
//            resultMap.put("success",false);
//            resultMap.put("msg","请登录管理员");
//            return resultMap;
//        }
//        if(iUserService.checkAdminRole(user).isSuccess()){
//            String path = request.getSession().getServletContext().getRealPath("upload");
//            String targetFileName = iFileService.upload(file,path);
//            if(StringUtils.isBlank(targetFileName)){
//                resultMap.put("success",false);
//                resultMap.put("msg","上传失败");
//                return resultMap;
//            }
//         //   String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
//            resultMap.put("success",true);
//            resultMap.put("msg","上传成功");
//        //    resultMap.put("file_path",url);
//            response.addHeader("Access-Control-Allow-Headers","X-File-Name");
//            return resultMap;
//        }else{
//            resultMap.put("success",false);
//            resultMap.put("msg","无权限操作");
//            return resultMap;
//        }
//    }

}
