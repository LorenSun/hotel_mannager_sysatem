package com.hotel.common;

/**
 * Created by HASEE on 2017/12/5.
 */
public class Const {

    public static final String CURRENT_USER = "current_user";


    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String PHONE = "phone";
    public static final String IDCARD = "idcard";
    public interface Role{
        int ROLE_MANNAGER = 0; //经理
        int ROLE_RECEPTIONIST = 1; //前台接待员
        int ROLE_CUSTOMER = 2; //顾客

    }
}
