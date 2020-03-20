package com.order.domain.converter;

import com.order.domain.dto.CategoryDto;
import com.order.domain.entity.Category;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryList2CategoryDtoMap {

    public static Map<Integer, CategoryDto> convert(List<Category> categoryList) {
        Map<Integer, CategoryDto> categoryDtoMap = new HashMap<>();
        for (Category category : categoryList) {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(category, categoryDto);
            categoryDtoMap.put(category.getId(), categoryDto);
        }
        return categoryDtoMap;
    }
}
