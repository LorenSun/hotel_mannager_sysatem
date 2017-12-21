package com.hotel.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.hotel.common.Const;
import com.hotel.common.ResponseCode;
import com.hotel.common.ServerResponse;
import com.hotel.dao.CartMapper;
import com.hotel.dao.RoomMapper;
import com.hotel.pojo.Cart;
import com.hotel.pojo.Room;
import com.hotel.service.ICartService;
import com.hotel.util.BigDecimalUtil;
import com.hotel.vo.CartRoomVo;
import com.hotel.vo.CartVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by HASEE on 2017/12/21.
 */
@Service("iCartService")
public class CartServiceImpl implements ICartService{
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private RoomMapper roomMapper;

    public ServerResponse<CartVo> add(Integer userId,Integer roomId,Integer days) {

        if (roomId == null || days == null){
            return  ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUEMENT.getCode(),ResponseCode.ILLEGAL_ARGUEMENT.getDesc());
        }
        Cart cart = cartMapper.selectCartByUserIdRoomId(userId, roomId);
        if (cart == null) {
            //这个产品不在这个购物车里,需要新增一个这个产品的记录
            Cart cartItem = new Cart();
            cartItem.setQuantity(days);
            cartItem.setChecked(Const.Cart.CHECKED);
            cartItem.setRoomId(roomId);
            cartItem.setUserId(userId);
            cartMapper.insert(cartItem);
        } else {
            //这个房间已经在购物车里了.
            //如果房间已存在,数量相加
            days = cart.getQuantity() + days;
            cart.setQuantity(days);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
        return this.list(userId);
    }
    public ServerResponse<CartVo> update(Integer userId,Integer roomId,Integer days){
        if(roomId == null || days == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUEMENT.getCode(),ResponseCode.ILLEGAL_ARGUEMENT.getDesc());
        }
        Cart cart = cartMapper.selectCartByUserIdRoomId(userId,roomId);
        if(cart != null){
            cart.setQuantity(days);
        }
        cartMapper.updateByPrimaryKey(cart);
        return this.list(userId);
    }

    public ServerResponse<CartVo> deleteProduct(Integer userId,Integer roomId){
      //  List<String> roomList = Splitter.on(",").splitToList(roomIds);
     //   if(CollectionUtils.isEmpty(roomList)){
       //     return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUEMENT.getCode(),ResponseCode.ILLEGAL_ARGUEMENT.getDesc());
      //  }
        cartMapper.deleteByUserIdRoomIds(userId,roomId);
        return this.list(userId);
    }


    public ServerResponse<CartVo> list (Integer userId){
        CartVo cartVo = this.getCartVoLimit(userId);
        return ServerResponse.createBySuccess(cartVo);
    }





    private CartVo getCartVoLimit(Integer userId) {
        CartVo cartVo = new CartVo();
        List<Cart> cartList = cartMapper.selectCartByUserId(userId);
        List<CartRoomVo> cartRoomVoList = Lists.newArrayList();
        BigDecimal cartTotalPrice = new BigDecimal("0");
        if (CollectionUtils.isNotEmpty(cartList)) {
            for (Cart cartItem : cartList) {
                CartRoomVo cartRoomVo = new CartRoomVo();
                cartRoomVo.setId(cartItem.getId());
                cartRoomVo.setUserId(userId);
                cartRoomVo.setRoomId(cartItem.getRoomId());

                Room room = roomMapper.selectByPrimaryKey(cartItem.getRoomId());
                if (room != null) {
                    cartRoomVo.setRoomName(room.getRoomNum().toString());
                    cartRoomVo.setRoomSubtitle(room.getSubtitle());
                    cartRoomVo.setRoomStatus(room.getStatus());
                    cartRoomVo.setRoomPrice(room.getPrice());
                    cartRoomVo.setQuantity(cartItem.getQuantity());

                    cartRoomVo.setRoomTotalPrice(BigDecimalUtil.mul(room.getPrice().doubleValue(),cartRoomVo.getQuantity()));
                    cartRoomVo.setRoomChecked(cartItem.getChecked());

                }
                if (cartItem.getChecked() == Const.Cart.CHECKED) {
                    cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(), cartRoomVo.getRoomTotalPrice().doubleValue());
                }
                cartRoomVoList.add(cartRoomVo);
            }
        }
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setCartProductVoList(cartRoomVoList);
        cartVo.setAllChecked(this.getAllCheckedStatus(userId));
        return  cartVo;
    }
    private boolean getAllCheckedStatus(Integer userId){
        if (userId==null) {
            return false;
        }
        return cartMapper.selectCartRoomCheckedStatusByUserId(userId) == 0;
    }
}



