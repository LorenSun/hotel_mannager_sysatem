package com.hotel.controller.backend;

import com.hotel.common.Const;
import com.hotel.common.ResponseCode;
import com.hotel.common.ServerResponse;
import com.hotel.pojo.User;
import com.hotel.service.ICategoryService;
import com.hotel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by HASEE on 2017/12/11.
 */
@Controller
@RequestMapping("/mannage/category")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService ;
    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session,String categoryName,@RequestParam(value = "parentId", defaultValue = "0" ) int parentId){
        User user  = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
        {
         return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        //校验一下是否是经理
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是经理，增加处理分类的逻辑
            return iCategoryService.addCategory(categoryName,parentId);

        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要经理权限");
        }

    }
    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session,Integer categoryId,String categoryName){
        User user  = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //更新categoryname
            return  iCategoryService.updateCategoryName(categoryId,categoryName);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要经理权限");
        }
    }
    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session,@RequestParam(value = "categoryId", defaultValue = "0" )Integer categoryId){
        User user  = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //查询子节点的category信息，不递归
            return  iCategoryService.getChildrenParallelCategory(categoryId);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要经理权限");
        }
    }
    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public  ServerResponse  getCategoryAndDeepChildrenCategory(HttpSession session,@RequestParam(value = "categoryId", defaultValue = "0" )Integer categoryId){
        User user  = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //查询当前节点的id和递归子节点的id

            return  iCategoryService.selectCategoryAndChildrenById(categoryId);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要经理权限");
        }

    }
}
