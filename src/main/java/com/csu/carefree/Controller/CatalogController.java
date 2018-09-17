package com.csu.carefree.Controller;


import com.csu.carefree.Model.ProductDT.*;
import com.csu.carefree.Model.TraverAsk.TraverNote;
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

        //写死长沙,后面调用百度地图api设置当前位置
        session.setAttribute("location", "长沙");
        //不需要判断是否登陆,前端可以直接从session中取得登陆的状态
        //行程信息存在session当中
        TraverMsg traverMsg = (TraverMsg) session.getAttribute("traverMsg");
        //还没有填写表单信息
        if (traverMsg == null)
            session.setAttribute("traverMsg", new TraverMsg());
        traverMsg = (TraverMsg) session.getAttribute("traverMsg");

        /*****************************热门产品推荐*********************************/
        List<FullProductInfo> hotProductList = catalogService.getHotProductList(session);
        session.setAttribute("product1", hotProductList.get(0));
        session.setAttribute("product2", hotProductList.get(1));

        FullProductInfo product1;
        if (session.getAttribute("product1") == null)
            product1 = new FullProductInfo();
        else
            product1 = (FullProductInfo) session.getAttribute("product1");
        //updateProduct1()
        String product1Price = (catalogService.getDepartCityPrice(product1.getId(), (String) session.getAttribute("location"))).getProduct_price();
        product1.setPrice(product1Price);

        FullProductInfo product2;
        if (session.getAttribute("product2") == null)
            product2 = new FullProductInfo();
        else
            product2 = (FullProductInfo) session.getAttribute("product2");
        String product2Price = (catalogService.getDepartCityPrice(product2.getId(), (String) session.getAttribute("location"))).getProduct_price();
        product2.setPrice(product2Price);

        /***************************热门游记推荐*********************************/
//        List<TraverNote> hotTraverNoteList = catalogService.getHotTraverNoteList();
//        System.out.println("热门游记个数："+ hotProductList.size());
//        session.setAttribute("hotTraverNoteList", hotTraverNoteList);

        /****************************热门酒店推荐********************************/
        List<HotelMsg> hotHotelList = catalogService.getHotHotelList();
        session.setAttribute("hotHotelList", hotHotelList);

        //将结果插入到model当中，用于返回给界面
        model.addAttribute("traverMsg", traverMsg);
        model.addAttribute("product1", product1);
        model.addAttribute("product2", product2);
        model.addAttribute("hotHotelList", hotHotelList);
        return "index";
    }

    //不填写表单直接跳转到目的地界面
    @GetMapping("/Catalog/Mdd")
    public String ViewMdd(HttpSession session) {
        /*****************************景点推荐*********************************/
        List<ScenicMsg> recommendScenicList = catalogService.getRecommendScenicList(session);
        System.out.println("-----------------------景点的个数" + recommendScenicList.size());
        session.setAttribute("recommendScenicList", recommendScenicList);


        /*****************************酒店推荐*********************************/
        List<HotelMsg> recommendHotelList = catalogService.getRecommendHotelList(session);
        System.out.println("-----------------------宾馆的个数" + recommendHotelList.size());
        session.setAttribute("recommendHotelList", recommendHotelList);

        /*****************************攻略推荐*********************************/
        List<StrategyMsg> recommendStrategyList = catalogService.getRecommendStrategyList(session);
        System.out.println("-----------------------攻略的个数" + recommendStrategyList.size());
        session.setAttribute("recommendStrategyList", recommendStrategyList);

        /*****************************游记推荐*********************************/
        List<TraverNote> recommendTraverNoteList = catalogService.getRecommendTraverNoteList(session);
        System.out.println("-----------------------游记的个数" + recommendTraverNoteList.size());
        session.setAttribute("recommendTraverNoteList", recommendTraverNoteList);
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

        /*****************************景点推荐*********************************/
        List<ScenicMsg> recommendScenicList = catalogService.getRecommendScenicList(session);
        System.out.println("-----------------------景点的个数" + recommendScenicList.size());
        session.setAttribute("recommendScenicList", recommendScenicList);


        /*****************************酒店推荐*********************************/
        List<HotelMsg> recommendHotelList = catalogService.getRecommendHotelList(session);
        System.out.println("-----------------------宾馆的个数" + recommendHotelList.size());
        session.setAttribute("recommendHotelList", recommendHotelList);

        /*****************************攻略推荐*********************************/
        List<StrategyMsg> recommendStrategyList = catalogService.getRecommendStrategyList(session);
        System.out.println("-----------------------攻略的个数" + recommendStrategyList.size());
        session.setAttribute("recommendStrategyList", recommendStrategyList);

        /*****************************游记推荐*********************************/
        List<TraverNote> recommendTraverNoteList = catalogService.getRecommendTraverNoteList(session);
        System.out.println("-----------------------游记的个数" + recommendTraverNoteList.size());
        session.setAttribute("recommendTraverNoteList", recommendTraverNoteList);
        return "ProductDT/Mdd";
    }


    //进入热门产品的界面控制器url
    @GetMapping("/Catalog/HotProductList")
    public String HotProductList(Model model) {
        //业务操作增删查改
        List<ProductMsg> productMsgList = catalogService.getProductList();
        System.out.println(productMsgList.size());
        // List<ProductCityMsg> productCityMsgList = catalogService.getProductCityList();
        model.addAttribute("productMsgList", productMsgList);
        //  model.addAttribute("productCityMsgList",productCityMsgList);
        return "ProductDT/Product";
    }

    //进入酒店页面的界面控制器
    @GetMapping("/Catalog/HotHotelList")
    public String HotHotelList(Model model) {
        //获取当前用户位置,推荐酒店
//        if (destination != null) {
//            //  List<HotelMsg> hotelMsgList = catalogService.getHotelListByDestination(destination);
//            List<HotelMsg> hotelMsgList = catalogService.getHotelMsgList();
//            System.out.println(hotelMsgList.size());
//            model.addAttribute("hotelMsgList", hotelMsgList);
//            model.addAttribute("destination", destination);
//        }
        return "ProductDT/Hotel";
    }

    /****************************攻略推荐模块**********************************/


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
