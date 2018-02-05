package com.hotel.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by HASEE on 2017/12/21.
 */
public class OrderRoomVo {

        private List<OrderItemVo> orderItemVoList;
        private BigDecimal productTotalPrice;

        public List<OrderItemVo> getOrderItemVoList() {
            return orderItemVoList;
        }

        public void setOrderItemVoList(List<OrderItemVo> orderItemVoList) {
            this.orderItemVoList = orderItemVoList;
        }

        public BigDecimal getRoomTotalPrice() {
            return productTotalPrice;
        }

        public void setRoomTotalPrice(BigDecimal productTotalPrice) {
            this.productTotalPrice = productTotalPrice;
        }

//        public String getImageHost() {
//            return imageHost;
//        }
//
//        public void setImageHost(String imageHost) {
//            this.imageHost = imageHost;
//        }

}
