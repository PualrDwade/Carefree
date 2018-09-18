package com.csu.carefree.Persistence;

import com.csu.carefree.Model.ProductDT.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierMapper {
    //得到所有的供应商
    List<Supplier> getSupplierList();

    //例如当点击产品当中的供应商信息时候，要返回相应的信息
    Supplier getSupplierByName(@Param("name") String name);

    //通过供应商id得到供应商
    Supplier getSupplierById(@Param("id") String id);

}
