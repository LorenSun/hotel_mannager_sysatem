package com.hotel.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by HASEE on 2017/12/5.
 */
public class Const {

    public static final String CURRENT_USER = "current_user";


    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String PHONE = "phone";
    public static final String IDCARD = "idcard";

    public interface RoomListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }
    public interface Cart{
        int CHECKED = 1;
        int UNCHECKED = 0;
    }
    public interface Role{
        int ROLE_MANNAGER = 0; //经理
        int ROLE_RECEPTIONIST = 1; //前台接待员
        int ROLE_CUSTOMER = 2; //顾客

    }

    public enum RoomStatusEnum{
        ON_SALE(1,"在线");
        private String value;
        private int code;
        RoomStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

    }
}
