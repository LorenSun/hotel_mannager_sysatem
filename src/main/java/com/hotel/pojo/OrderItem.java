package com.hotel.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderItem {
    private Integer id;

    private Integer userId;

    private Long orderNo;

    private Integer roomId;

    private Integer roomNum;

    private String roomOrientation;

    private Integer roomFloor;

    private String roomImage;

    private BigDecimal currrentUnitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    private Date createTime;

    private Date updateTime;

    public OrderItem(Integer id, Integer userId, Long orderNo, Integer roomId, Integer roomNum, String roomOrientation, Integer roomFloor, String roomImage, BigDecimal currrentUnitPrice, Integer quantity, BigDecimal totalPrice, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.orderNo = orderNo;
        this.roomId = roomId;
        this.roomNum = roomNum;
        this.roomOrientation = roomOrientation;
        this.roomFloor = roomFloor;
        this.roomImage = roomImage;
        this.currrentUnitPrice = currrentUnitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public OrderItem() {
        super();
    }

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

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public String getRoomOrientation() {
        return roomOrientation;
    }

    public void setRoomOrientation(String roomOrientation) {
        this.roomOrientation = roomOrientation == null ? null : roomOrientation.trim();
    }

    public Integer getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(Integer roomFloor) {
        this.roomFloor = roomFloor;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage == null ? null : roomImage.trim();
    }

    public BigDecimal getCurrrentUnitPrice() {
        return currrentUnitPrice;
    }

    public void setCurrrentUnitPrice(BigDecimal currrentUnitPrice) {
        this.currrentUnitPrice = currrentUnitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}