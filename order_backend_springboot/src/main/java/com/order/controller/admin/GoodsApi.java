package com.order.controller.admin;

import com.order.domain.Result;
import com.order.repository.GoodsRepository;
import com.order.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api/goods/")
public class GoodsApi {

    @Autowired
    GoodsRepository goodsRepository;

    @PostMapping("/post")
    public Result<Object> list()
    {
        return ResultUtil.success();
    }
}
