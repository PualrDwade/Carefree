package com.csu.carefree.Controller;


import com.csu.carefree.Util.PageInfo;
import com.csu.carefree.Model.TraverMsg.ScenicMsg;
import com.csu.carefree.Model.TraverMsg.TraverMsg;
import com.csu.carefree.Service.CatalogService;
import com.csu.carefree.Model.ProductDT.*;
import com.csu.carefree.Model.TraverAsk.TraverNote;
import com.csu.carefree.Util.CatalogUtils;
import com.csu.carefree.Util.LocationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<FullProductInfo> hotProductList = catalogService.getHotProductList(session);
        session.setAttribute("product1", hotProductList.get(0));
        session.setAttribute("product2", hotProductList.get(1));

        FullProductInfo product1 = session.getAttribute("product1")
                == null ? new FullProductInfo() : (FullProductInfo) session.getAttribute("product1");
        String product1Price = (catalogService.getDepartCityPrice(product1.getId(), (String) session.getAttribute("location"))).getProduct_price();
        product1.setPrice(product1Price);//设置价格
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
        //热门酒店推荐这个城市排名最高个酒店
        //1.价格最便宜的酒店
        List<HotelMsg> hotHotelList_01 = catalogService.getHotHotelListByCityName((String) session.getAttribute("location"), 1).subList(0, 4);
        //2.评分最高的酒店
        List<HotelMsg> hotHotelList_02 = catalogService.getHotHotelListByCityName((String) session.getAttribute("location"), 2).subList(0, 4);
        //存储数据到前端页面
        //1. 酒店信息
        model.addAttribute("hotHotelList_01", hotHotelList_01);
        model.addAttribute("hotHotelList_02", hotHotelList_02);
        //2. 热门产品信息
        model.addAttribute("product1", product1);
        model.addAttribute("product2", product2);
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


    @GetMapping("/Catalog/HotProductList")
    public String HotProductList(HttpServletRequest httpServletRequest, HttpSession session, Model model) {
        String traverDays = "0";
        String supplierId = "0";
        String productType = "0";
        String destination = session.getAttribute("location").toString();
        String[] checkedDaysValues = httpServletRequest.getParameterValues("days");
        String[] checkedStoreValues = httpServletRequest.getParameterValues("store");
        String[] checkedTypeValues = httpServletRequest.getParameterValues("type");
        if (checkedDaysValues != null && checkedStoreValues != null && checkedTypeValues != null) {
            System.out.println(checkedDaysValues[0]);
            System.out.println(checkedStoreValues[0]);
            System.out.println(checkedTypeValues[0]);
            traverDays = checkedDaysValues[0];
            supplierId = checkedStoreValues[0];
            productType = checkedTypeValues[0];
        }

        List<ProductMsg> productMsgList = new ArrayList<ProductMsg>();
        if (traverDays.equals("0") && supplierId.equals("0") && productType.equals("0")) {
            productMsgList = catalogService.getProductListByCityName(destination);
        }
        if (!traverDays.equals("0") && supplierId.equals("0") && productType.equals("0")) {
            productMsgList = catalogService.getProductListByTraverdays(traverDays);
        }
        if (traverDays.equals("0") && !supplierId.equals("0") && productType.equals("0")) {
            productMsgList = catalogService.getProductListBySupplierId(supplierId);
        }
        if (traverDays.equals("0") && supplierId.equals("0") && !productType.equals("0")) {
            productMsgList = catalogService.getProductListByProductType(productType);
        }
        if (!traverDays.equals("0") && !supplierId.equals("0") && productType.equals("0")) {
            productMsgList = catalogService.getProductListByDaysAndStore(traverDays, supplierId);
        }
        if (!traverDays.equals("0") && supplierId.equals("0") && !productType.equals("0")) {
            productMsgList = catalogService.getProductListByDaysAndType(traverDays, productType);
        }
        if (traverDays.equals("0") && !supplierId.equals("0") && !productType.equals("0")) {
            productMsgList = catalogService.getProductListByTypeAndStore(productType, supplierId);
        }
        if (!traverDays.equals("0") && !supplierId.equals("0") && !productType.equals("0")) {
            productMsgList = catalogService.getProductListByThree(traverDays, productType, supplierId);
        }

        System.out.println("找到符合条件的产品" + productMsgList.size() + "条");

        System.out.println("开始计算价格");
        Map<ProductMsg, String> map = new HashMap<>();
        for (ProductMsg productMsg : productMsgList) {
            map.put(productMsg, catalogService.getDepartCityPrice(productMsg.getId(), destination).getProduct_price());
        }
        System.out.println("计算完成");
        model.addAttribute("product_price_map", map);
        model.addAttribute("productMsgList", productMsgList);
        return "ProductDT/Product";
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
        Map<Integer, List<HotelMsg>> hotelMap = new HashMap<>();

        hotelMsgPageInfo.setTotal(hotelMsgList.size());
        System.out.println("页面大小" + hotelMsgPageInfo.getPageSize());
        System.out.println("list大小" + hotelMsgList.size());
        System.out.println("最多的界面数" + hotelMsgPageInfo.getMaxPage());
        //设置当前页码
        hotelMsgPageInfo.setCurrentPage(pageNum);
        if (hotelMsgList.size() / HOTELPAGESIZE == 0) {
            for (int i = 0; i < hotelMsgPageInfo.getMaxPage(); i++) {
                hotelMap.put(i + 1, hotelMsgList.subList(i * HOTELPAGESIZE, i * HOTELPAGESIZE + HOTELPAGESIZE));
            }
        } else {
            for (int i = 0; i < hotelMsgPageInfo.getMaxPage(); i++) {
                if (i == hotelMsgPageInfo.getMaxPage() - 1) {
                    hotelMap.put(i + 1, hotelMsgList.subList(i * HOTELPAGESIZE, i * HOTELPAGESIZE + hotelMsgList.size() % HOTELPAGESIZE));
                    break;
                }
                hotelMap.put(i + 1, hotelMsgList.subList(i * HOTELPAGESIZE, i * HOTELPAGESIZE + HOTELPAGESIZE));
            }
        }
        if (hotelMsgPageInfo.getCurrentPage() == 1)
            hotelMsgPageInfo.setFirstPage(true);
        else
            hotelMsgPageInfo.setFirstPage(false);
        if (hotelMsgPageInfo.getCurrentPage() == hotelMsgPageInfo.getMaxPage())
            hotelMsgPageInfo.setLastPage(true);
        else
            hotelMsgPageInfo.setLastPage(false);
        hotelMsgPageInfo.setPageData(hotelMap.get(pageNum));
        model.addAttribute("hotelMsgPageInfo", hotelMsgPageInfo);
        model.addAttribute("hotelMsgList", hotelMsgList);
        return "ProductDT/Hotel";
    }
    /****************************攻略推荐模块**************** ******************/
}
