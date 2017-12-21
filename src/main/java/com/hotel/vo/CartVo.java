package com.hotel.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by HASEE on 2017/12/21.
 */
public class CartVo {
    private List<CartRoomVo> cartProductVoList;
    private BigDecimal cartTotalPrice;
    private Boolean allChecked;//是否已经都勾选
    private String imageHost;

    public List<CartRoomVo> getCartProductVoList() {
        return cartProductVoList;
    }

    public void setCartProductVoList(List<CartRoomVo> cartProductVoList) {
        this.cartProductVoList = cartProductVoList;
    }

    public BigDecimal getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(BigDecimal cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public Boolean getAllChecked() {
        return allChecked;
    }

    public void setAllChecked(Boolean allChecked) {
        this.allChecked = allChecked;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }
}
