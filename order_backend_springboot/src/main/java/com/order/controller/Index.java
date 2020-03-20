package com.order.controller;


import com.order.domain.Result;
import com.order.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Index {

    private final Logger logger = LoggerFactory.getLogger(Index.class);

    @GetMapping("/")
    public Result<Object> index()
    {
        String data = "点餐系统";
        return ResultUtil.success(data);
    }

}
