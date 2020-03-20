package com.order.domain.converter;

import com.order.domain.dto.CategoryDto;
import com.order.domain.dto.GoodsDto;
import com.order.domain.entity.Category;
import com.order.domain.entity.Goods;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoodsList2GoodsDtoList {

    public static List<GoodsDto> convert(List<Goods> goodsList, List<Category> categoryList)
    {
        Map<Integer, CategoryDto> categoryDtoMap = CategoryList2CategoryDtoMap.convert(categoryList);
        List<GoodsDto> goodsDtoList = new ArrayList<>();
        for (Goods goods : goodsList) {
            GoodsDto goodsDto = new GoodsDto();
            BeanUtils.copyProperties(goods, goodsDto);
            goodsDto.setCategory(categoryDtoMap.get(goods.getCategoryId()));
            goodsDtoList.add(goodsDto);
        }
        return goodsDtoList;
    }
}
