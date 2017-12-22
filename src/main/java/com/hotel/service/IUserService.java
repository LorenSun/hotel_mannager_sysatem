package com.hotel.service;


import com.hotel.common.ServerResponse;
import com.hotel.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by HASEE on 2017/12/5.
 */
public interface IUserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str,String type);

    ServerResponse<String> checkAnswer(String username,String phone,String id_card);

    ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInformation(Integer userId);

    ServerResponse checkAdminRole(User user);

    ServerResponse setRoleCustomer(@Param("userId") Integer userId, @Param("role")Integer role);

    ServerResponse setRoleReceptionist(@Param("userId") Integer userId, @Param("role")Integer role);

}
