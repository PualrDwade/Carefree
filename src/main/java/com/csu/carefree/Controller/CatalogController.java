package com.csu.carefree.Controller;


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
        List<FullProductInfo> hotProductList = catalogService.getHotProductList(location);
        FullProductInfo product1 = hotProductList.get(0);
        String product1Price = (catalogService.getDepartCityPrice(product1.getId(), (String) session.getAttribute("location"))).getProduct_price();
        product1.setPrice(product1Price);//设置价格
        FullProductInfo product2 = hotProductList.get(1);
        String product2Price = (catalogService.getDepartCityPrice(product2.getId(), (String) session.getAttribute("location"))).getProduct_price();
        product2.setPrice(product2Price);
        //2热门产品信息存入model
        model.addAttribute("product1", product1);
        model.addAttribute("product2", product2);

        /***************************热门游记推荐*********************************/
        //得到两篇热门游记
        List<TraverNote> hotTraverNoteList = catalogService.getHotTraverNoteList(3);
        System.out.println("热门游记个数：" + hotProductList.size());
        model.addAttribute("hotTraverNoteList", hotTraverNoteList);
        /****************************热门酒店推荐********************************/
        //热门酒店推荐这个城市排名最高个酒店
        //1.价格最便宜的酒店
        List<HotelMsg> hotHotelList_01 = catalogService.getHotHotelListByCityName((String) session.getAttribute("location"), 1).subList(0, 4);
        //2.评分最高的酒店
        List<HotelMsg> hotHotelList_02 = catalogService.getHotHotelListByCityName((String) session.getAttribute("location"), 2).subList(0, 4);
        //存储数据到前端页面
        //1. 酒店信息
        model.addAttribute("hotHotelList_01", hotHotelList_01);
        model.addAttribute("hotHotelList_02", hotHotelList_02);


        /***************************热门问答推荐**********************************/
        List<UserAsk> askList = traverAskService.getUserAskList();//获得所有Ask
        //循环,通过每一个提问获取提问的回答
        for (UserAsk userAsk : askList) {
            //通过提问获取所有的回答
            List<UserAnswer> userAnswerList = traverAskService.getUserAnswerByAsk(userAsk.getId());
            userAsk.setAnswer_num(userAnswerList.size());
        }
        //排序关键词:评论数
        askList.sort(Comparator.reverseOrder());
        model.addAttribute("askList", askList);

        //最后显示主页
        return "index";
    }


    //不填写表单直接跳转到目的地界面
    @GetMapping("/Catalog/Mdd")
    public String ViewMdd(HttpSession session,Model model) {
        /*****************************景点推荐*********************************/
        List<ScenicMsg> recommendScenicList = catalogService.getRecommendScenicList(session);
        session.setAttribute("recommendScenicList", recommendScenicList);


        /*****************************酒店推荐*********************************/
        List<HotelMsg> recommendHotelList = catalogService.getRecommendHotelList(session);
        session.setAttribute("recommendHotelList", recommendHotelList);

        /*****************************门票推荐*********************************/
        ArrayList<StrategyMsg> recommendStrategyList = catalogService.getRecommendStrategyList(session);
        session.setAttribute("recommendStrategyList", recommendStrategyList);

        /*****************************游记推荐*********************************/
        String cityName;
        TraverMsg traverMsg = (TraverMsg) session.getAttribute("traverMsg");
        if(traverMsg.getEnd_city() == null){
            cityName = session.getAttribute("location")+"市";
        }else {
            cityName = traverMsg.getEnd_city()+"市";
        }
        List<TraverNote> recommendTraverNoteList = catalogService.getHotTraverNoteListByCity(4,cityName);
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

        /*****************************攻略推荐*********************************/
        List<StrategyMsg> recommendStrategyList = catalogService.getRecommendStrategyList(session);
        session.setAttribute("recommendStrategyList", recommendStrategyList);

        /*****************************游记推荐*********************************/
        String cityName;
        if(traverMsg.getEnd_city().equals("")){
            cityName = session.getAttribute("location")+"市";
        }else {
            cityName = traverMsg.getEnd_city()+"市";
        }
        List<TraverNote> recommendTraverNoteList = catalogService.getHotTraverNoteListByCity(4,cityName);
        model.addAttribute("recommendTraverNoteList", recommendTraverNoteList);


        return "ProductDT/Mdd";
    }


    @GetMapping("/Catalog/HotProductList")
    public String HotProductList(@RequestParam(defaultValue = "1") Integer pageNum, HttpServletRequest httpServletRequest, HttpSession session, Model model) {
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

    //搜索产品信息
    @GetMapping("/Product/searchProductList")
    public String searchProductListByKeyword(@RequestParam("keyword") String keyword,Model model){


        return "ProductDT/Product";
    }

    @GetMapping("/ProductDT/searchProductList")
    public String searchHotelListByKeyword(@RequestParam("keyword") String keyword,Model model){


        return "ProductDT/Hotel";
    }

    // 进入热门酒店的控制器url
    @GetMapping("/Catalog/HotHotelList")
    public String HotHotelListByConditions(HttpServletRequest httpServletRequest, HttpSession session, Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
        String supplierId = "0";
        String price = "0";
        String[] checkedStoreValues = httpServletRequest.getParameterValues("store");
        String[] checkedPriceValues = httpServletRequest.getParameterValues("price");
        String destination = session.getAttribute("location").toString();

        if (checkedStoreValues != null && checkedPriceValues != null) {
            supplierId = checkedStoreValues[0];
            price = checkedPriceValues[0];
        }

        List<HotelMsg> hotelMsgList = new ArrayList<HotelMsg>();
        if (supplierId.equals("0") && price.equals("0")) {
            hotelMsgList = catalogService.getHotelListByDestination(destination + "市");
        }
        if (!supplierId.equals("0") && price.equals("0")) {
            hotelMsgList = catalogService.getHotelListByDestinationAndStore(destination + "市", supplierId);
        }
        if (supplierId.equals("0") && !price.equals("0")) {
            hotelMsgList = catalogService.getHotelListByDestination(destination + "市");
            catalogUtils.setHotelListByPrices(hotelMsgList, price);
        }
        if (!supplierId.equals("0") && !price.equals("0")) {
            hotelMsgList = catalogService.getHotelListByDestinationAndStore(destination + "市", supplierId);
            catalogUtils.setHotelListByPrices(hotelMsgList, price);
        }
        /****分页模块***/
        PageInfo<HotelMsg> hotelMsgPageInfo = new PageInfo<>();
        hotelMsgPageInfo.setPageData(hotelMsgList, HOTELPAGESIZE, pageNum);
        model.addAttribute("hotelMsgPageInfo", hotelMsgPageInfo);
        return "ProductDT/Hotel";
    }
    /****************************攻略推荐模块**************** ******************/
}
