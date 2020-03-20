package com.order.service.impl;

import com.order.domain.entity.Category;
import com.order.enums.ResultEnum;
import com.order.exception.AppException;
import com.order.repository.CategoryRepository;
import com.order.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateInfo(Category category) {
        Optional<Category> optional = categoryRepository.findById(category.getId());
        if (optional.isPresent()) {
            Category categoryTemp = optional.get();
            BeanUtils.copyProperties(category, categoryTemp);
            categoryRepository.updateInfo(category.getId(), categoryTemp.getName(), categoryTemp.getDescription());
            return category;
        } else {
            throw new AppException(ResultEnum.CATEGORY_NOT_FOUND);
        }
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAllByOrderByWeight();
    }

    @Override
    public List<Category> findAllByIdIn(List<Integer> list) {
        return categoryRepository.findAllByIdIn(list);
    }
}
