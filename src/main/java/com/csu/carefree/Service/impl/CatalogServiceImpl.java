package com.csu.carefree.Service.impl;

import com.csu.carefree.Model.ProductDT.HotelMsg;
import com.csu.carefree.Model.ProductDT.ProductMsg;
import com.csu.carefree.Model.ProductDT.Supplier;
import com.csu.carefree.Model.TraverMsg.CityMsg;
import com.csu.carefree.Model.TraverMsg.ProvinceMsg;
import com.csu.carefree.Model.TraverMsg.ScenicMsg;
import com.csu.carefree.Persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *   Great by WLX
 */
@Service
public class CatalogServiceImpl {
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

    /*******酒店信息******/

    @Override//通过销量获取热门酒店列表
    public List<HotelMsg> getHotelList(){
        return hotelMsgMapper.getHotelMsgList();
    };

    @Override//获取酒店具体信息
    public HotelMsg getHotelMsg(String hotelId){
        return hotelMsgMapper.getHotelMsg(hotelId);
    };

    @Override//通过关键词搜索获取酒店信息
    public List<HotelMsg> getHotelListByKeyword(String keyword){
        return hotelMsgMapper.searchHotelMsgList("%"+keyword+"%");
    };

    @Override
    public List<HotelMsg> getHotelListByDestination(String destination){
        return hotelMsgMapper.getHotelListByDestination(destination);
    }


    /******产品信息******/
    @Override
    public List<ProductMsg> getProductList(){      //获取所有产品列表-倒序
        return productMapper.getProductList();
    }

    @Override
    public ProductMsg getProductById(String id){    //通过产品ID获取产品信息
        return productMapper.getProductById(id);
    }

    /*****************通过筛选条件获取产品****************/
    @Override
    public List<ProductMsg> getProductListByTraver_days(String traver_days){    //行程天数
        return productMapper.getProductListByTraver_days(traver_days);
    }

    @Override
    public List<ProductMsg> getProductListByProduct_type(String product_type){    //产品类型
        return productMapper.getProductListByProduct_type(product_type);
    }

    @Override
    public List<ProductMsg> getProductListBySupplier_id(String supplier_id){    //供应商
        return productMapper.getProductListBySupplier_id(supplier_id);
    }

    @Override
    public List<ProductMsg> getProductListByName(String product_name){    //产品名称
        return productMapper.getProductListByName("%"+product_name+"%");
    }

    @Override
    public List<ProductMsg> getProductListByThree(String traver_days, String product_type, String supplier_id){
        //三个筛选条件都有
        return productMapper.getProductListByThree(traver_days,product_type,supplier_id);
    }

    //*****************通过推荐条件来排序、推荐*****************/
    //注意：推荐条件是在筛选条件的基础上进行的，所以不能直接对数据库进行查找
    //推荐（排序）条件：score, sell_num, comments_num, product_grade


    /*******供应商信息*****/
    @Override
    public List<Supplier> getSupplierList(){    //获取供应商列表
        return supplierMapper.getSupplierList();
    }

    @Override
    Supplier getSupplierByName(String name){    //例如当点击产品当中的供应商信息时候，要返回相应的信息
        return supplierMapper.getSupplierByName(name);
    }

    /*******城市信息*******/
    @Override
    ProvinceMsg getProvinceMsgById(String provinceId){    //通过省份ID获取省份信息
        return cityMsgMapper.getProvinceMsgById(provinceId);
    }

    @Override
    CityMsg getCityMsgById(String cityId){    //通过城市ID获取城市信息
        return cityMsgMapper.getCityMsgById(cityId);
    }

    @Override
    List<CityMsg> searchCityMsgByName(String cityName){    //通过城市名称获取城市信息(模糊搜索)
        return cityMsgMapper.searchCityMsgByName("%"+cityName+"%");
    }

    @Override
    List<ScenicMsg> getScenicMsgListById(String cityId){    //通过城市ID获取城市所有景点信息
        return cityMsgMapper.getScenicMsgListById(cityId);
    }

    @Override
    List<ScenicMsg> getScenicMsgListByCityName(String cityName){    //通过城市名字获取城市所有景点信息
        return cityMsgMapper.getScenicMsgListByCityName(cityName);
    }

    /***********景点信息*********/
    @Override
    ScenicMsg getScenicMsgById(String scenicId){    //通过景点ID获取景点信息

        return scenicMsgMapper.getScenicMsgById(scenicId);
    }

    @Override
    List<ScenicMsg> getScenicMsgListByName(String name){    //通过景点名称获取景点信息
        return scenicMsgMapper.getScenicMsgListByName("%"+name+"%");
    }

}
