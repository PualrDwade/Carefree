package com.csu.carefree.Persistence;

import com.csu.carefree.Model.ProductDT.ProductMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {

    /*****************通过筛选条件获取产品*****************/


    List<ProductMsg> getProductList();

    ProductMsg getProductById(@Param("id") String id);

    //筛选条件：traver_days, product_type, supplier_id, cityname

    //城市名
    List<ProductMsg> getProductListByCityName(@Param("cityname") String cityname);

    //行程天数
    List<ProductMsg> getProductListByTraverdays(@Param("traver_days") String traverDays, @Param("cityname") String cityname);

    //产品类型
    List<ProductMsg> getProductListByProductType(@Param("product_type") String productType, @Param("cityname") String cityname);

    //供应商
    List<ProductMsg> getProductListBySupplierId(@Param("supplier_id") String supplierId, @Param("cityname") String cityname);

    //产品名称,模糊查询,查询产品内容
    List<ProductMsg> getProductListByName(@Param("product_name") String productName);

    //三个筛选条件都有
    List<ProductMsg> getProductListByThree(@Param("traver_days") String traverDays, @Param("product_type") String productType,
                                           @Param("suppliper_id") String supplierId, @Param("cityname") String cityname);

    //行程天数和供应商
    List<ProductMsg> getProductListByDaysAndStore(@Param("traver_days") String traverDays,
                                                  @Param("suppliper_id") String supplierId, @Param("cityname") String cityname);

    //行程天数和产品类型
    List<ProductMsg> getProductListByDaysAndType(@Param("traver_days") String traverDays,
                                                 @Param("product_type") String productType, @Param("cityname") String cityname);


    //产品类型和供应商
    List<ProductMsg> getProductListByTypeAndStore(@Param("product_type") String productType,
                                                  @Param("suppliper_id") String supplierId, @Param("cityname") String cityname);

    //通过关键词获得产品信息
    List<ProductMsg> searchProductListByKeyword(String keyword);


    //*****************通过推荐条件来排序、推荐*****************/
    //注意：推荐条件是在筛选条件的基础上进行的，所以不能直接对数据库进行查找
    //推荐（排序）条件：score, sell_num, comments_num, product_grade
}
