package com.csu.carefree.Persistence;

import com.csu.carefree.Model.ProductDT.Supplier;

import java.util.List;

public interface SupplierMapper {
    List<Supplier> getSupplierList();

    //例如当点击产品当中的供应商信息时候，要返回相应的信息
    Supplier getSupplierByName(String name);

}
