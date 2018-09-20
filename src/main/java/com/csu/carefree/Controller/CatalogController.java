package com.csu.carefree.Controller;

import com.csu.carefree.Model.ProductDT.*;
import com.csu.carefree.Model.TraverMsg.CityMsg;
import com.csu.carefree.Model.TraverAsk.TraverNote;

import com.csu.carefree.Model.TraverAsk.UserAnswer;
import com.csu.carefree.Model.TraverAsk.UserAsk;
import com.csu.carefree.Service.TraverAskService;
import com.csu.carefree.Util.PageInfo;
import com.csu.carefree.Model.TraverMsg.ScenicMsg;
import com.csu.carefree.Model.TraverMsg.TraverMsg;
import com.csu.carefree.Service.CatalogService;
import com.csu.carefree.Model.ProductDT.*;
import com.csu.carefree.Model.TraverAsk.TraverNote;
import com.csu.carefree.Util.CatalogUtils;
import com.csu.carefree.Util.LocationUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

@Controller
public class CatalogController {


    /**
     * 实现产品推荐展示模块的业务逻辑
     * 实现与酒店有关的业务逻辑
     * 酒店信息展示，热门酒店推荐
     */
    private static final int PRODUCTPAGESIZE = 8;
    private static final int HOTELPAGESIZE = 8;
    @Autowired
    private CatalogService catalogService;

    @Autowired
    private TraverAskService traverAskService;

    private CatalogUtils catalogUtils = new CatalogUtils();

    @GetMapping("ProductDT/viewHotel")
    public String viewHotelMsgList(Model model) {
        model.addAttribute("HotelList");
        return "Hotel";
    }

    //请求主界面
    @GetMapping("/")
    public String viewIndex(HttpSession session, Model model) {
        //首先调用百度地图api获得当前城市信息,如果获取失败,则设为长沙
        String location = null;
        try {
            location = LocationUtil.getCityLocation();
        } catch (Exception e) {
            location = "长沙";
        }
        if (location == null) {
            location = "长沙";
        }
        if (session.getAttribute("location") == null) {
            //第一次访问,首先设置session存入当前位置
            //设置会话中的location,全局使用
            session.setAttribute("location", location);
        }
        session.setAttribute("hotProductcheckedDaysAll", true);
        session.setAttribute("hotProductcheckedStoreAll", true);
        session.setAttribute("hotProductcheckedTypeAll", true);
        //行程信息存在session当中
        TraverMsg traverMsg = (TraverMsg) session.getAttribute("traverMsg");
        //还没有填写表单信息
        if (traverMsg == null) {
            traverMsg = new TraverMsg();
            //设置默认起始地为session中的当前位置
            traverMsg.setStart_city((String) session.getAttribute("location"));
            //行程信息存到session中
            session.setAttribute("traverMsg", traverMsg);
        }


        /*****************************热门产品推荐*********************************/
        //随机推荐两个产品
        List<FullProductInfo> hotProductList = catalogService.getHotProductList(location, 2);//默认两篇
        for (FullProductInfo productInfo : hotProductList) {
            String productPrice = (catalogService.getDepartCityPrice(productInfo.getId(), (String) session.getAttribute("location"))).getProduct_price();
            productInfo.setPrice(productPrice);
        }
        model.addAttribute("hotProductList", hotProductList);

        /***************************热门游记推荐*********************************/
        //得到两篇热门游记
        List<TraverNote> hotTraverNoteList = catalogService.getHotTraverNoteList(3);//默认三篇
        System.out.println("热门游记个数：" + hotProductList.size());
        model.addAttribute("hotTraverNoteList", hotTraverNoteList);
        /****************************热门酒店推荐********************************/
        //热门酒店推荐这个城市排名最高个酒店
        //1.价格最便宜的酒店
        List<HotelMsg> hotHotelList_01 = catalogService.getHotHotelListByCityName((String) session.getAttribute("location") + '市', 1);
        //2.评分最高的酒店
        List<HotelMsg> hotHotelList_02 = catalogService.getHotHotelListByCityName((String) session.getAttribute("location") + '市', 2);
        //存储数据到前端页面
        //1. 酒店信息
        model.addAttribute("hotHotelList_01", hotHotelList_01);
        model.addAttribute("hotHotelList_02", hotHotelList_02);
        //最后显示主页
        return "index";
    }


    //不填写表单直接跳转到目的地界面
    @GetMapping("/Catalog/Mdd")
    public String ViewMdd(@RequestParam(value = "view_city", defaultValue = "", required = false) String view_city,
                          HttpSession session, Model model) {
        TraverMsg traverMsg = (TraverMsg) session.getAttribute("traverMsg");
        if (traverMsg == null) {
            traverMsg = new TraverMsg();
            session.setAttribute("traverMsg", traverMsg);
        }
        if (view_city != null && !view_city.equals("")) {
            traverMsg.setEnd_city(view_city);
        }
        /*
         * 城市名列表存入model；
         * 传入页面显示城市viewCity；
         */
        List<String> cityNameList = catalogService.getCityNameList();
        model.addAttribute("cityNameList", cityNameList);

        if (traverMsg.getEnd_city().equals(""))
            model.addAttribute("viewCity", session.getAttribute("location"));
        else
            model.addAttribute("viewCity", traverMsg.getEnd_city());

        /*****************************景点推荐*********************************/
        List<ScenicMsg> recommendScenicList = catalogService.getRecommendScenicList(session);
        session.setAttribute("recommendScenicList", recommendScenicList);


        /*****************************酒店推荐*********************************/
        List<HotelMsg> recommendHotelList = catalogService.getRecommendHotelList(session);
        session.setAttribute("recommendHotelList", recommendHotelList);


        /*****************************游记推荐*********************************/
        String cityName;
        traverMsg = (TraverMsg) session.getAttribute("traverMsg");
        if (traverMsg.getEnd_city() == null) {
            cityName = session.getAttribute("location") + "市";
        } else {
            cityName = traverMsg.getEnd_city() + "市";
        }
        List<TraverNote> recommendTraverNoteList = catalogService.getHotTraverNoteListByCity(4, cityName);
        model.addAttribute("recommendTraverNoteList", recommendTraverNoteList);


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
        session.setAttribute("recommendScenicList", recommendScenicList);


        /*****************************酒店推荐*********************************/
        List<HotelMsg> recommendHotelList = catalogService.getRecommendHotelList(session);
        session.setAttribute("recommendHotelList", recommendHotelList);

        /*****************************游记推荐*********************************/
        String cityName;
        traverMsg = (TraverMsg) session.getAttribute("traverMsg");
        if (traverMsg.getEnd_city() == null) {
            cityName = session.getAttribute("location") + "市";
        } else {
            cityName = traverMsg.getEnd_city() + "市";
        }
        List<TraverNote> recommendTraverNoteList = catalogService.getHotTraverNoteListByCity(4, cityName);
        model.addAttribute("recommendTraverNoteList", recommendTraverNoteList);

        /*
         * 城市名列表存入model；
         * 传入页面显示城市viewCity；
         */
        List<String> cityNameList = catalogService.getCityNameList();
        model.addAttribute("cityNameList", cityNameList);

        if (traverMsg.getEnd_city().equals(""))
            model.addAttribute("viewCity", session.getAttribute("location"));
        else
            model.addAttribute("viewCity", traverMsg.getEnd_city());

        return "ProductDT/Mdd";
    }


    @GetMapping("/Catalog/HotProductList")
    public String HotProductList(@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                 HttpServletRequest httpServletRequest, HttpSession session, Model model) {
        System.out.println("keyword：" + keyword);

        //首先从session中得到
        System.out.println(pageNum);
        ProductForm productForm = (ProductForm) session.getAttribute("productForm");
        //从session中取出当前位置
        String destination = (String) session.getAttribute("location");
        if (productForm == null) {
            //首次登陆为null,设置初始值
            productForm = new ProductForm("0", "0", "0");
            session.setAttribute("productForm", productForm);
        } else {
            //已经存在session了,只需要获得请求过来的参数,获得一次请求
            if (httpServletRequest.getParameter("days") != null) {
                //只要一个存在肯定都存在
                productForm.setTraverDays(httpServletRequest.getParameter("days"));
                productForm.setProductType(httpServletRequest.getParameter("type"));
                productForm.setSupplierId(httpServletRequest.getParameter("store"));
            }
        }
        List<ProductMsg> productMsgList = new ArrayList<ProductMsg>();
        System.out.println("选择内容为" + productForm.toString());
        if (productForm.getTraverDays().equals("0") && productForm.getSupplierId().equals("0") && productForm.getProductType().equals("0")) {
            productMsgList = catalogService.getProductListByCityName(destination);
        }
        if (!productForm.getTraverDays().equals("0") && productForm.getSupplierId().equals("0") && productForm.getProductType().equals("0")) {
            productMsgList = catalogService.getProductListByTraverdays(productForm.getTraverDays(), destination);
        }
        if (productForm.getTraverDays().equals("0") && !productForm.getSupplierId().equals("0") && productForm.getProductType().equals("0")) {
            productMsgList = catalogService.getProductListBySupplierId(productForm.getSupplierId(), destination);
        }
        if (productForm.getTraverDays().equals("0") && productForm.getSupplierId().equals("0") && !productForm.getProductType().equals("0")) {
            productMsgList = catalogService.getProductListByProductType(productForm.getProductType(), destination);
        }
        if (!productForm.getTraverDays().equals("0") && !productForm.getSupplierId().equals("0") && productForm.getProductType().equals("0")) {
            productMsgList = catalogService.getProductListByDaysAndStore(productForm.getTraverDays(), productForm.getSupplierId(), destination);
        }
        if (!productForm.getTraverDays().equals("0") && productForm.getSupplierId().equals("0") && !productForm.getProductType().equals("0")) {
            productMsgList = catalogService.getProductListByDaysAndType(productForm.getTraverDays(), productForm.getProductType(), destination);
        }
        if (productForm.getTraverDays().equals("0") && !productForm.getSupplierId().equals("0") && !productForm.getProductType().equals("0")) {
            productMsgList = catalogService.getProductListByTypeAndStore(productForm.getProductType(), productForm.getSupplierId(), destination);
        }
        if (!productForm.getTraverDays().equals("0") && !productForm.getSupplierId().equals("0") && !productForm.getProductType().equals("0")) {
            productMsgList = catalogService.getProductListByThree(productForm.getTraverDays(), productForm.getProductType(), productForm.getSupplierId(), destination);
        }
        //把最新的表单存入session
        session.setAttribute("productForm", productForm);
        System.out.println("找到符合条件的产品" + productMsgList.size() + "条");
        PageInfo<ProductMsg> productMsgPageInfo = new PageInfo<>();
        productMsgPageInfo.setPageData(productMsgList, PRODUCTPAGESIZE, pageNum);
        model.addAttribute("productMsgPageInfo", productMsgPageInfo);
        return "ProductDT/Product";
    }

    // 进入热门酒店的控制器url
    @GetMapping("/Catalog/HotHotelList")
    public String HotHotelListByConditions(HttpServletRequest httpServletRequest, HttpSession session, Model model,
                                           @RequestParam(defaultValue = "1") Integer pageNum) {
        String supplierId = "0";
        String price = "0";
        String[] checkedStoreValues = httpServletRequest.getParameterValues("store");
        String[] checkedPriceValues = httpServletRequest.getParameterValues("price");
        String destination = (String) session.getAttribute("location");
        if (checkedStoreValues != null && checkedPriceValues != null) {
            supplierId = checkedStoreValues[0];
            price = checkedPriceValues[0];
        }
        List<HotelMsg> hotelMsgList = new ArrayList<HotelMsg>();
        if (supplierId.equals("0") && price.equals("0")) {
            hotelMsgList = catalogService.getHotelListByDestination(destination);
        }
        if (!supplierId.equals("0") && price.equals("0")) {
            hotelMsgList = catalogService.getHotelListByDestinationAndStore(destination, supplierId);
        }
        if (supplierId.equals("0") && !price.equals("0")) {
            hotelMsgList = catalogService.getHotelListByDestination(destination);
            catalogUtils.setHotelListByPrices(hotelMsgList, price);
        }
        if (!supplierId.equals("0") && !price.equals("0")) {
            hotelMsgList = catalogService.getHotelListByDestinationAndStore(destination, supplierId);
            catalogUtils.setHotelListByPrices(hotelMsgList, price);
        }
        /****分页模块***/
        PageInfo<HotelMsg> hotelMsgPageInfo = new PageInfo<>();
        hotelMsgPageInfo.setPageData(hotelMsgList, HOTELPAGESIZE, pageNum);
        model.addAttribute("hotelMsgPageInfo", hotelMsgPageInfo);
        return "ProductDT/Hotel";
    }

    //通过搜索关键字获得产品信息
    @GetMapping("/ProductDT/searchProductList")
    public String searchProductListByKeyword(@RequestParam("keyword") String keyword,
                                             Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
        List<ProductMsg> productMsgList = catalogService.searchProductListByKeyword(keyword);
        PageInfo<ProductMsg> productMsgPageInfo = new PageInfo<>();
        productMsgPageInfo.setPageData(productMsgList, PRODUCTPAGESIZE, pageNum);
        model.addAttribute("productMsgPageInfo", productMsgPageInfo);
        return "ProductDT/Product";
    }

    //通过关键子搜索获得酒店信息
    @GetMapping("/ProductDT/searchHotelList")
    public String searchHotelMsgList(@RequestParam("keyword") String keyword,
                                     Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
        List<HotelMsg> hotelMsgList = catalogService.searchHotelMsgList(keyword);
        /****分页模块***/
        PageInfo<HotelMsg> hotelMsgPageInfo = new PageInfo<>();
        hotelMsgPageInfo.setPageData(hotelMsgList, HOTELPAGESIZE, pageNum);
        model.addAttribute("hotelMsgPageInfo", hotelMsgPageInfo);
        return "ProductDT/Hotel";
    }
    /****************************攻略推荐模块**************** ******************/
}
