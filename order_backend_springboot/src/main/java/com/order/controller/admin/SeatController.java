package com.order.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/seat")
public class SeatController {

    @GetMapping("/console")
    public String console()
    {
        return "seat/console";
    }

    @GetMapping("/manage")
    public String manage()
    {
        return "seat/manage";
    }
}
