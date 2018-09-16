package com.csu.carefree.Controller;


import com.csu.carefree.Model.ProductDT.FullProductInfo;
import com.csu.carefree.Model.ProductDT.ProductMsg;
import com.csu.carefree.Model.TraverMsg.ScenicMsg;
import com.csu.carefree.Model.TraverMsg.TraverMsg;
import com.csu.carefree.Service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CatalogController {
    /**
     * 实现产品推荐展示模块的业务逻辑
     * 实现与酒店有关的业务逻辑
     * 酒店信息展示，热门酒店推荐
     */

    @Autowired
    private CatalogService catalogService;

    @GetMapping("ProductDT/viewHotel")
    public String viewHotelMsgList(Model model) {
        model.addAttribute("HotelList");

        return "Hotel";
    }


    //请求主界面
    @GetMapping("/")
    public String viewIndex(HttpSession session, Model model) {
        //首先判断是否登陆
        String username = (String) session.getAttribute("username");
        //返回数据给前端页面
        if (username == null) {
            model.addAttribute("login", false);
        } else {
            model.addAttribute("login", true);
        }
        //行程信息存在session当中
        TraverMsg traverMsg = (TraverMsg) session.getAttribute("traverMsg");
        if (traverMsg == null)
            session.setAttribute("traverMsg", new TraverMsg());
        traverMsg = (TraverMsg) session.getAttribute("traverMsg");

        //热门产品推荐，每天都要进行更新
        List<FullProductInfo> hotProductList;

        FullProductInfo product1;
        if (session.getAttribute("product1") == null)
            product1 = new FullProductInfo();
        else
            product1 = (FullProductInfo) session.getAttribute("product1");
        //updateProduct1()
        System.out.println("**********************");
        if (catalogService.getDepartCityPrice(product1.getId(), getDepartCity(session)) == null)
            System.out.println("时空！！！");
        String product1Price = (catalogService.getDepartCityPrice(product1.getId(), getDepartCity(session))).getProduct_price();
        product1.setPrice(product1Price);

        FullProductInfo product2;
        if (session.getAttribute("product2") == null)
            product2 = new FullProductInfo();
        else
            product2 = (FullProductInfo) session.getAttribute("product2");
        String product2Price = (catalogService.getDepartCityPrice(product2.getId(), getDepartCity(session))).getProduct_price();
        product2.setPrice(product2Price);

        //将结果插入到model当中，用于返回给界面
        model.addAttribute("traverMsg", traverMsg);
        model.addAttribute("product1", product1);
        model.addAttribute("product2", product2);
        return "index";
    }

    //不填写表单直接跳转到目的地界面
    @GetMapping("/Catalog/Mdd")
    public String ViewMdd() {
        return "ProductDT/Mdd";
    }

    //填写表单请求跳转目的地界面
    @PostMapping("/Catalog/Mdd")
    public String ViewMdd(@RequestParam("startCity") String startCity, @RequestParam("destination") String destination,
                          @RequestParam("adultNum") String adultNum, @RequestParam("childrenNum") String childrenNum,
                          @RequestParam("travelDays") String travelDays, HttpSession session, Model model) {
        //行程数据
        TraverMsg traverMsg;
        if (session.getAttribute("traverMsg") == null)
            traverMsg = new TraverMsg();
        else
            traverMsg = (TraverMsg) session.getAttribute("traverMsg");
        //保证字段不为空
        traverMsg.setTraverdays(travelDays == null ? "" : travelDays);
        traverMsg.setAdult_num(adultNum == null ? "" : adultNum);
        traverMsg.setChild_num(childrenNum == null ? "" : childrenNum);
        traverMsg.setStart_city(startCity == null ? "" : startCity);
        traverMsg.setEnd_city(destination == null ? "" : destination);

        //**********************景点数据
        //控制逻辑：若已经获得目的地的信息，则显示目的地城市景点、地图、酒店信息；若没有获得即为定位城市
        //1、判断城市名是否存在
        String theDestination;
        if (catalogService.searchCityMsgByName(traverMsg.getEnd_city()) == null) {
            //定位城市
            theDestination = (String) session.getAttribute("positioningCity");
        } else {
            theDestination = traverMsg.getEnd_city();
        }
        List<ScenicMsg> scenicMsgList = catalogService.getScenicMsgListByCityName(theDestination);

        //景点排序功能（前端展示）

        //插入model
        model.addAttribute("scenicMsgList", scenicMsgList);

        //**********************酒店数据
        return "ProductDT/Mdd";
    }


    //进入热门产品的界面控制器url
    @GetMapping("/Catalog/HotProductList")
    public String HotProductList() {
        //业务操作增删查改
        return "ProductDT/Product";
    }

    //进入酒店页面的界面控制器
    @GetMapping("/Catalog/HotHotelList")
    public String HotHotelList() {
        //获取当前用户位置,推荐酒店
        return "ProductDT/Hotel";
    }

    /*****************************相关功能函数*************************************/
    List<FullProductInfo> getHotProductList(HttpSession session) {
        List<ProductMsg> productList = catalogService.getProductList();
        List<FullProductInfo> hotProductList = null;
        String positioningCity = (String) session.getAttribute("positioningCity");
        //热门产品推荐指标
        for (int i = 0; i < productList.size(); i++) {
            break;
        }
        hotProductList.add(new FullProductInfo(productList.get(0)));
        hotProductList.add(new FullProductInfo(productList.get(1)));
        return hotProductList;
    }

    private String getDepartCity(HttpSession session) {
        TraverMsg traverMsg = (TraverMsg) session.getAttribute("traverMsg");
        String city;
        if (traverMsg.getStart_city() == null)
            city = (String) session.getAttribute("positioningCity");
        else
            city = traverMsg.getStart_city();
        return city;
    }
}
