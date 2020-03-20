package com.order.controller.admin;

import com.order.domain.converter.GoodsList2GoodsDtoList;
import com.order.domain.dto.GoodsDto;
import com.order.domain.entity.Category;
import com.order.domain.entity.Goods;
import com.order.enums.GoodsStatusEnum;
import com.order.service.impl.CategoryServiceImpl;
import com.order.service.impl.GoodsServiceImpl;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/goods")
public class GoodsController {

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    GoodsServiceImpl goodsService;

    @GetMapping("/manage")
    public String manage(Map<String, Object> map)
    {
        return "goods/manage";
    }

    @GetMapping("/list")
    public String list(@RequestParam(value = "status", defaultValue = "-1") Integer status,
                       @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       Map<String, Object> map)
    {
        Pageable pageable = PageRequest.of(page-1, 20);
        Page<Goods> goodsPage = goodsService.findAll(pageable, categoryId, status);
        List<Category> categoryList = categoryService.findAll();
        List<GoodsDto> goodsDtoList = GoodsList2GoodsDtoList.convert(goodsPage.getContent(), categoryList);
        Page<GoodsDto> goodsDtoPage = new PageImpl<>(goodsDtoList, pageable, goodsPage.getTotalElements());
        Map<Integer, String> statusMap = new HashMap<>();
        statusMap.put(GoodsStatusEnum.UP.getStatus(), GoodsStatusEnum.UP.getStatusText());
        statusMap.put(GoodsStatusEnum.DOWN.getStatus(), GoodsStatusEnum.DOWN.getStatusText());
        map.put("categoryList", categoryList);
        map.put("statusMap", statusMap);
        map.put("goodsDtoPage", goodsDtoPage);
        return "goods/list";
    }
}
