package com.hotel.service;

import com.hotel.common.ServerResponse;
import com.hotel.pojo.Category;

/**
 * Created by HASEE on 2017/12/11.
 */
public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId,String categoryName);

    ServerResponse<java.util.List<Category>> getChildrenParallelCategory(Integer categoryId);

    ServerResponse selectCategoryAndChildrenById(Integer categoryId);
}
