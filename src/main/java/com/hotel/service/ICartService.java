package com.hotel.service;

import com.hotel.common.ServerResponse;
import com.hotel.vo.CartVo;

/**
 * Created by HASEE on 2017/12/21.
 */
public interface ICartService {

    ServerResponse<CartVo> add(Integer userId, Integer roomId, Integer days);

    ServerResponse<CartVo> update(Integer userId,Integer roomId,Integer count);

    ServerResponse<CartVo> deleteProduct(Integer userId,Integer roomId);

    ServerResponse<CartVo> list (Integer userId);
}
