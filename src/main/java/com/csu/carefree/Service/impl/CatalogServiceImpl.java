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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 *   Great by WLX
 */
@Service
@Transactional
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
    private ProductCityMsgMapper productCityMsgMapper;

    @Autowired
    private ProductScenicMsgMapper productscenicMsgMapper;

    @Autowired
    private ProvinceMsgMapper provinceMsgMapper;

    @Autowired
    private StrategyMsgMapper strategyMsgMapper;

    @Autowired
    private TicketMsgMapper ticketMsgMapper;

    @Autowired
    private TraverNoteMapper traverNoteMapper;

    @Override
    public List<HotelMsg> getHotelMsgList() {
        return hotelMsgMapper.getHotelMsgList();
    }

    @Override
    public HotelMsg getHotelMsg(String id) {
        return hotelMsgMapper.getHotelMsg(id);
    }

    @Override
    public List<HotelMsg> searchHotelMsgList(String keyword) {
        return searchHotelMsgList(keyword);
    }

    @Override
    public List<HotelMsg> getHotelListByDestination(String destination) {
        return hotelMsgMapper.getHotelListByDestination(destination);
    }

    @Override
    public List<ProductMsg> getProductList() {
        return productMapper.getProductList();
    }

    @Override
    public ProductMsg getProductById(String id) {
        return productMapper.getProductById(id);
    }

    @Override
    public List<ProductMsg> getProductListByTraverdays(String traverDays) {
        return productMapper.getProductListByTraverdays(traverDays);
    }

    @Override
    public List<ProductMsg> getProductListByProductType(String productType) {
        return productMapper.getProductListByProductType(productType);
    }

    @Override
    public List<ProductMsg> getProductListBySupplierId(String supplierId) {
        return productMapper.getProductListBySupplierId(supplierId);
    }

    @Override
    public List<ProductMsg> getProductListByName(String productName) {
        return productMapper.getProductListByName(productName);
    }

    @Override
    public List<ProductMsg> getProductListByThree(String traverDays, String productType, String supplierId) {
        return productMapper.getProductListByThree(traverDays, productType, supplierId);
    }

    @Override
    public List<Supplier> getSupplierList() {
        return supplierMapper.getSupplierList();
    }

    @Override
    public Supplier getSupplierByName(String name) {
        return supplierMapper.getSupplierByName(name);
    }

    @Override
    public ProvinceMsg getProvinceMsgById(String provinceId) {
        return provinceMsgMapper.getProvinceMsgById(provinceId);
    }

    @Override
    public CityMsg getCityMsgById(String cityId) {
        return cityMsgMapper.getCityMsgById(cityId);
    }

    @Override
    public List<CityMsg> searchCityMsgByName(String cityName) {
        return cityMsgMapper.searchCityMsgByName(cityName);
    }

    @Override
    public List<ScenicMsg> getScenicMsgListByCityId(String cityId) {
        return cityMsgMapper.getScenicMsgListById(cityId);
    }

    @Override
    public List<ScenicMsg> getScenicMsgListByCityName(String cityName) {
        return scenicMsgMapper.getScenicMsgListByCityName(cityName);
    }

    @Override
    public ScenicMsg getScenicMsgById(String scenicId) {
        return scenicMsgMapper.getScenicMsgById(scenicId);
    }

    @Override
    public List<ScenicMsg> getScenicMsgListByName(String name) {
        return scenicMsgMapper.getScenicMsgListByName(name);
    }

    @Override
    public List<ProductCityMsg> getProductCityList() {
        return productCityMsgMapper.getProductCityList();
    }

    @Override
    public List<ProductCityMsg> getAllDepartCityListByProductId(String productId) {
        return productCityMsgMapper.getAllDepartCityListByProductId(productId);
    }

    @Override
    public ProductCityMsg getDepartCityPrice(String productId, String cityName) {
        return productCityMsgMapper.getDepartCityPriceByProductId(productId, cityName);
    }

    @Override
    public List<ProductScenicMsg> getProductIdsByScenicName(String scenicName) {
        return productscenicMsgMapper.getProductIdsByScenicName(scenicName);
    }

    @Override
    public List<ProductScenicMsg> getScenicNamesByProductId(String productId) {
        return productscenicMsgMapper.getScenicNamesByProductId(productId);
    }

    @Override
    public List<StrategyMsg> getStrategyList() {
        return strategyMsgMapper.getStrategyList();
    }

    @Override
    public StrategyMsg getStrategyByScenicName(String scenicName) {
        return strategyMsgMapper.getStrategyByScenicName(scenicName);
    }

    @Override
    public List<TicketMsg> getTicketList() {
        return ticketMsgMapper.getTicketList();
    }

    @Override
    public List<TicketMsg> getTicketListByScenicName(String scenicName) {
        return ticketMsgMapper.getTicketListByScenicName(scenicName);
    }

    @Override
    public List<TicketMsg> getTicketListByCityName(String cityId) {
        return ticketMsgMapper.getTicketListByCityName(cityId);
    }

    @Override
    public List<TraverNote> getTraverNoteList() {
        return traverNoteMapper.getTraverNoteList();
    }

    @Override
    public List<TraverNote> searchTraverNoteList(String keyword) {
        return traverNoteMapper.searchTraverNoteList(keyword);
    }

    @Override
    public TraverNote getTraverNoteById(String traverNoteId) {
        return traverNoteMapper.getTraverNoteById(traverNoteId);
    }
}
