package com.csu.carefree.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 *   Great by WLX
 */
@Controller
public class HotelController {

    /**
     * 实现与酒店有关的业务逻辑
     * 酒店信息展示，热门酒店推荐
     */

    @Autowired
    private HotelService hotelService;

    @GetMapping("/viewHotel")
    public String viewHotelMsgList(Model model){
        model.addAttribute("HotelList");

        return "/html/ProductDT/Hotelist";
    }
}
