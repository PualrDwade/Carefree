package com.csu.carefree.Service;


import com.csu.carefree.Model.ProductDT.*;
import com.csu.carefree.Model.TraverAsk.TraverNote;
import com.csu.carefree.Model.TraverMsg.CityMsg;
import com.csu.carefree.Model.TraverMsg.ProvinceMsg;
import com.csu.carefree.Model.TraverMsg.ScenicMsg;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public interface CatalogService {

    /*******************根据行程推荐景点、酒店、攻略、游记等**********/
    ArrayList<ScenicMsg> getRecommendScenicList(HttpSession session);

    ArrayList<HotelMsg> getRecommendHotelList(HttpSession session);

    ArrayList<StrategyMsg> getRecommendStrategyList(HttpSession session);

    ArrayList<TraverNote> getRecommendTraverNoteList(HttpSession session);

    void sortScenicList(List<ScenicMsg> recommendScenicList);

    void sortHotelList(List<HotelMsg> sortHotelList);

    void sortStrategyList(List<StrategyMsg> recommendStrategyList);

    void sortTraverNoteList(List<TraverNote> recommendTraverNoteList);

    /*******************获取所在地热门信息**********/
    ArrayList<FullProductInfo> getHotProductList(String location, int number);

    //通过城市名获取热门酒店信息,按照给定的指标进行筛选
    ArrayList<HotelMsg> getHotHotelListByCityName(String cityName, int orderType);

    ArrayList<TraverNote> getHotTraverNoteList(int traverNoteNum);

    /*******************酒店信息**********/
    //获取所有酒店信息
    List<HotelMsg> getHotelMsgList();

    //通过酒店ID获取酒店具体信息
    HotelMsg getHotelMsg(String id);

    //通过关键字搜索酒店
    List<HotelMsg> searchHotelMsgList(String keyword);

    //根据目的地获得酒店信息
    List<HotelMsg> getHotelListByDestination(String destination);

    //目的地和供应商
    List<HotelMsg> getHotelListByDestinationAndStore(String destination, String supplierId);


    /*****************通过筛选条件获取产品*****************/

    List<ProductMsg> getProductList();

    ProductMsg getProductById(String id);

    //筛选条件：traver_days, product_type, supplier_id, cityname

    //城市名
    List<ProductMsg> getProductListByCityName(String cityname);

    //行程天数
    List<ProductMsg> getProductListByTraverdays(String traverDays, String cityname);

    //产品类型
    List<ProductMsg> getProductListByProductType(String productType, String cityname);

    //供应商
    List<ProductMsg> getProductListBySupplierId(String supplierId, String cityname);

    //产品名称,模糊查询,查询产品内容
    List<ProductMsg> getProductListByName(String productName);

    //三个筛选条件都有
    List<ProductMsg> getProductListByThree(String traverDays, String productType,
                                           String supplierId, String cityname);

    //行程天数和供应商
    List<ProductMsg> getProductListByDaysAndStore(String traverDays,
                                                  String supplierId, String cityname);

    //行程天数和产品类型
    List<ProductMsg> getProductListByDaysAndType(String traverDays,
                                                 String productType, String cityname);


    //产品类型和供应商
    List<ProductMsg> getProductListByTypeAndStore(String productType,
                                                  String supplierId, String cityname);

    //通过关键词获得产品信息
    List<ProductMsg> searchProductListByKeyword(String keyword);

    //*****************通过推荐条件来排序、推荐*****************/
    //注意：推荐条件是在筛选条件的基础上进行的，所以不能直接对数据库进行查找
    //推荐（排序）条件：score, sell_num, comments_num, product_grade

    /*******供应商信息*********************************/
    //获取供应商列表
    List<Supplier> getSupplierList();

    //例如当点击产品当中的供应商信息时候，要返回相应的信息
    Supplier getSupplierByName(String name);

    //通过供应商id得到供应商
    Supplier getSupplierById(String id);

    /*******城市省份信息*******/
    //通过省份ID获取省份信息
    ProvinceMsg getProvinceMsgById(String provinceId);

    //获取城市信息
    List<String> getCityNameList();

    //通过城市ID获取城市信息
    CityMsg getCityMsgById(String cityId);

    //通过城市名称获取城市信息(模糊搜索)
    List<CityMsg> searchCityMsgByName(String cityName);

    //通过城市ID获取城市所有景点信息
    List<ScenicMsg> getScenicMsgListByCityId(String cityId);

    //通过城市名字获取城市所有景点信息
    List<ScenicMsg> getScenicMsgListByCityName(String cityName);


    /***********景点信息*********/
    //通过景点ID获取景点信息
    ScenicMsg getScenicMsgById(String scenicId);

    //通过景点名称获取景点信息
    List<ScenicMsg> getScenicMsgListByName(String name);

    /*************产品城市信息*****/
    //获得产品的所有有关城市信息
    List<ProductCityMsg> getProductCityList();

    //根据产品ID找出产品的所有出发城市以及相应的起价
    List<ProductCityMsg> getAllDepartCityListByProductId(String productId);

    //根据产品ID以及出发城市名字查找相应的起价
    ProductCityMsg getDepartCityPrice(String productId, String cityName);

    /*****************产品景点信息******/
    //根据景点名字查找产品列表
    List<ProductScenicMsg> getProductIdsByScenicName(String scenicName);

    //根据产品ID查找景点名字列表
    List<ProductScenicMsg> getScenicNamesByProductId(String productId);

    /***************攻略信息****/
    // 得到攻略信息
    List<StrategyMsg> getStrategyList();

    // 根据景点得到
    StrategyMsg getStrategyByScenicName(String scenicName);

    /*****************门票信息******/
    //得到所有门票信息
    List<TicketMsg> getTicketList();

    //门票查询筛选信息有：景点名字、城市名字
    List<TicketMsg> getTicketListByScenicName(String scenicName);

    List<TicketMsg> getTicketListByCityName(String cityName);

    /**************游记信息*******/
    //获取所有游记信息
    List<TraverNote> getTraverNoteList();

    //通过关键词搜索游记信息
    List<TraverNote> searchTraverNoteList(String keyword);

    //通过游记ID获取游记信息
    TraverNote getTraverNoteById(String traverNoteId);

    //通过城市名获取游记
    List<TraverNote> getTraverNoteListByCityName(String cityName);

    //插入游记信息
    void insertTraverNote(TraverNote traverNote);

    //获得所有traverNote
    List<TraverNote> getAllTraverNoteList();

    /***********************************************/
    //通过地点获得游记
    ArrayList<TraverNote> getHotTraverNoteListByCity(int traverNoteNum, String cityName);

}
