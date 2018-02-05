package com.hotel.vo;

import java.math.BigDecimal;

/**
 * Created by HASEE on 2017/12/21.
 */
public class CartRoomVo {
    private Integer id;
    private Integer userId;
    private Integer roomId;
    private Integer quantity;//购物车中此商品的数量
    private String roomName;
    private String roomSubtitle;
    private String roomMainImage;
    private BigDecimal roomPrice;
    private Integer roomStatus;
    private BigDecimal roomTotalPrice;
    private Integer roomChecked;//此商品是否勾选

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomSubtitle() {
        return roomSubtitle;
    }

    public void setRoomSubtitle(String roomSubtitle) {
        this.roomSubtitle = roomSubtitle;
    }

    public String getRoomMainImage() {
        return roomMainImage;
    }

    public void setRoomMainImage(String roomMainImage) {
        this.roomMainImage = roomMainImage;
    }

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public BigDecimal getRoomTotalPrice() {
        return roomTotalPrice;
    }

    public void setRoomTotalPrice(BigDecimal roomTotalPrice) {
        this.roomTotalPrice = roomTotalPrice;
    }

    public Integer getRoomChecked() {
        return roomChecked;
    }

    public void setRoomChecked(Integer roomChecked) {
        this.roomChecked = roomChecked;
    }
}

