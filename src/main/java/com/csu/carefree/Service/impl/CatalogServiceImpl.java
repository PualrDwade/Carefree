package com.csu.carefree.Service.impl;

import com.csu.carefree.Model.ProductDT.*;
import com.csu.carefree.Model.TraverAsk.TraverNote;
import com.csu.carefree.Model.TraverMsg.CityMsg;
import com.csu.carefree.Model.TraverMsg.ProvinceMsg;
import com.csu.carefree.Model.TraverMsg.ScenicMsg;
import com.csu.carefree.Persistence.*;
import com.csu.carefree.Service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *   Great by WLX
 */
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    private HotelMsgMapper hotelMsgMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CityMsgMapper cityMsgMapper;

    @Autowired
    private ScenicMsgMapper scenicMsgMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private Product_CityMsgMapper product_cityMsgMapper;

    @Autowired
    private Product_ScenicMsgMapper product_scenicMsgMapper;

    @Autowired
    private ProvinceMsgMapper provinceMsgMapper;

    @Autowired
    private StrategyMsgMapper strategyMsgMapper;

    @Autowired
    private TicketMsgMapper ticketMsgMapper;

    @Autowired
    private TraverNoteMapper traverNoteMapper;
  
  
    /*******酒店信息******/
    @Override//通过销量获取热门酒店列表
    public List<HotelMsg> getHotelList() {
        return hotelMsgMapper.getHotelMsgList();
    }


    @Override//获取酒店具体信息
    public HotelMsg getHotelMsg(String hotelId) {
        return hotelMsgMapper.getHotelMsg(hotelId);
    }


    @Override//通过关键词搜索获取酒店信息
    public List<HotelMsg> getHotelListByKeyword(String keyword) {
        return hotelMsgMapper.searchHotelMsgList("%" + keyword + "%");
    }

    @Override
    public List<HotelMsg> getHotelListByDestination(String destination) {
        return hotelMsgMapper.getHotelListByDestination(destination);
    }
    /********************************************************************************/


    /******产品信息******/
    @Override
    public List<ProductMsg> getProductList() {      //获取所有产品列表-倒序
        return productMapper.getProductList();
    }

    @Override
    public ProductMsg getProductById(String id) {    //通过产品ID获取产品信息
        return productMapper.getProductById(id);
    }

    /*****************通过筛选条件获取产品****************/
    @Override
    public List<ProductMsg> getProductListByTraver_days(String traver_days) {    //行程天数
        return productMapper.getProductListByTraver_days(traver_days);
    }

    @Override
    public List<ProductMsg> getProductListByProduct_type(String product_type) {    //产品类型
        return productMapper.getProductListByProduct_type(product_type);
    }

    @Override
    public List<ProductMsg> getProductListBySupplier_id(String supplier_id) {    //供应商
        return productMapper.getProductListBySupplier_id(supplier_id);
    }

    @Override
    public List<ProductMsg> getProductListByName(String product_name) {    //产品名称
        return productMapper.getProductListByName("%" + product_name + "%");
    }

    @Override
    public List<ProductMsg> getProductListByThree(String traver_days, String product_type, String supplier_id) {
        //三个筛选条件都有
        return productMapper.getProductListByThree(traver_days, product_type, supplier_id);
    }

    //*****************通过推荐条件来排序、推荐*****************/
    //注意：推荐条件是在筛选条件的基础上进行的，所以不能直接对数据库进行查找
    //推荐（排序）条件：score, sell_num, comments_num, product_grade


    /*******供应商信息*****/
    @Override
    public List<Supplier> getSupplierList() {    //获取供应商列表
        return supplierMapper.getSupplierList();
    }

    @Override
    public Supplier getSupplierByName(String name) {    //例如当点击产品当中的供应商信息时候，要返回相应的信息
        return supplierMapper.getSupplierByName(name);
    }

    /*******城市信息*******/
    @Override
    public ProvinceMsg getProvinceMsgById(String provinceId) {    //通过省份ID获取省份信息
        return cityMsgMapper.getProvinceMsgById(provinceId);
    }

    @Override

    public CityMsg getCityMsgById(String cityId) {    //通过城市ID获取城市信息
        return cityMsgMapper.getCityMsgById(cityId);
    }

    @Override
    public List<CityMsg> searchCityMsgByName(String cityName){    //通过城市名称获取城市信息(模糊搜索)
        return cityMsgMapper.searchCityMsgByName("%"+cityName+"%");
    }
  
    @Override
    public List<ScenicMsg> getScenicMsgListById(String cityId) {    //通过城市ID获取城市所有景点信息
        return cityMsgMapper.getScenicMsgListById(cityId);
    }

    @Override
    public List<ScenicMsg> getScenicMsgListByCityName(String cityName) {    //通过城市名字获取城市所有景点信息
        return cityMsgMapper.getScenicMsgListByCityName(cityName);
    }

    /***********景点信息*********/
    @Override

    public ScenicMsg getScenicMsgById(String scenicId) {    //通过景点ID获取景点信息
        return scenicMsgMapper.getScenicMsgById(scenicId);
    }

    @Override
    public List<ScenicMsg> getScenicMsgListByName(String name) {    //通过景点名称获取景点信息
        return scenicMsgMapper.getScenicMsgListByName("%" + name + "%");
    }

    /*************产品城市信息*****/
    @Override
    public List<Product_CityMsg> getProduct_CityList(){    //获得产品的所有有关城市信息
        return product_cityMsgMapper.getProduct_CityList();
    }

    @Override//根据产品ID找出产品的所有出发城市以及相应的起价
    public List<Product_CityMsg> getAllDepartCityListByProductId(String product_id){
        return product_cityMsgMapper.getAllDepartCityListByProductId(product_id);
    }

    @Override//根据产品ID以及出发城市名字查找相应的起价
    public Product_CityMsg getADepartCity_Price(String Product_id, String cityName){
        return product_cityMsgMapper.getADepartCity_Price(Product_id,cityName);
    }

    /*****************产品景点信息******/
    @Override//根据景点名字查找产品列表
    public List<Product_ScenicMsg> getProductIdsByScenicName(String scenicName){
        return product_scenicMsgMapper.getProductIdsByScenicName(scenicName);
    }

    @Override//根据产品ID查找景点名字列表
    public List<Product_ScenicMsg> getScenicNamesByProductId(String ProductId){
        return product_scenicMsgMapper.getScenicNamesByProductId(ProductId);
    }

    /*****************省份信息*********/
    @Override//根据省份ID获取城市信息
    public ProvinceMsg getProvinceMsgById(String provinceId){
        return provinceMsgMapper.getProvinceMsgById(provinceId);
    }

    @Override//根据省份名称名称获取城市信息
    public List<ProvinceMsg> searchProvinceMsgByName(String provinceName){
        return provinceMsgMapper.searchProvinceMsgByName("%"+provinceName+"%");
    }

    @Override//根据省份ID获取省份所有城市信息
    public List<CityMsg> getCityListById(String provinceId){
        return provinceMsgMapper.getCityListById(provinceId);
    }

    /***************攻略信息****/
    @Override// 得到攻略信息
    public List<StrategyMsg> getStrategyList(){
        return strategyMsgMapper.getStrategyList();
    }

    @Override// 根据景点得到
    public StrategyMsg getStrategyByScenic_name(String scenic_name){
        return strategyMsgMapper.getStrategyByScenic_name(scenic_name);
    }

    /*****************门票信息0******/
    @Override//得到所有门票信息
    public List<TicketMsg> getTicketList(){
        return ticketMsgMapper.getTicketList();
    }

    @Override//门票查询筛选信息有：景点名字、城市名字
    public List<TicketMsg> getTicketListByScenic_name(String scenic_name){
        return ticketMsgMapper.getTicketListByScenic_name(scenic_name);
    }

    @Override
    public List<TicketMsg> getTicketListByCity_id(String city_id){
        return ticketMsgMapper.getTicketListByCity_id(city_id);
    }

    /**************游记信息****/
    @Override//获取所有游记信息
    public List<TraverNote> getTraverNoteList(){
        return traverNoteMapper.getTraverNoteList();
    }

    @Override//通过关键词搜索游记信息
    public List<TraverNote> searchTraverNoteList(String keyword){
        return traverNoteMapper.searchTraverNoteList("%"+keyword+"%");
    }

    @Override//通过游记ID获取游记信息
    public TraverNote getTraverNoteById(String traverNoteId){
        return traverNoteMapper.getTraverNoteById(traverNoteId);
    }

    @Override//存储用户游记信息
    public void insertTraverNote(TraverNote traverNote){
        traverNoteMapper.insertTraverNote(traverNote);
    }
}
