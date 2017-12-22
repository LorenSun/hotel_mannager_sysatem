package com.hotel.controller.backend;

import com.hotel.common.Const;
import com.hotel.common.ResponseCode;
import com.hotel.common.ServerResponse;
import com.hotel.pojo.User;
import com.hotel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by HASEE on 2017/12/8.
 */
@Controller
@RequestMapping("/mannage/user")
public class UserMannageController {
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value="login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username, password);
        if(response.isSuccess()){
            User user = response.getData();
            if(user.getRole() == Const.Role.ROLE_MANNAGER){
                //说明登录的是管理员
                session.setAttribute(Const.CURRENT_USER,user);
                return response;
            }else{
                return ServerResponse.createByErrorMessage("不是管理员，无法登陆");
            }
        }
        return response;
    }
    @RequestMapping("set_role_customer.do")
    @ResponseBody
    public ServerResponse setRoleCustomer(HttpSession session,Integer userId,Integer role){
        User user  = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            return  iUserService.setRoleCustomer(userId,role);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要经理权限");
        }
    }
    @RequestMapping("set_role_receptionist.do")
    @ResponseBody
    public ServerResponse setRoleReceptionist(HttpSession session,Integer userId,Integer role){
        User user  = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            return  iUserService.setRoleReceptionist(userId,role);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要经理权限");
        }
    }
}
