package com.petstore.service;

import com.petstore.entity.Category;
import com.petstore.persistence.CategoryDao;
import com.petstore.persistence.ProductDao;

import java.util.List;
import java.util.Map;

public class ProductService {

    public List<String[]> getProductInfoByName(String search){
        return ProductDao.getInstance().getProductInfoByName(search);
    }

    public Object[] getProduct(String productId){
        return ProductDao.getInstance().getProduct(productId);
    }

    public Category getCategory(String categoryId){
        return CategoryDao.getInstance().getCategory(categoryId);
    }

    public List<String[]> getProductInfo(String category2Id){
        return ProductDao.getInstance().getProductInfo(category2Id);
    }

    public Map<String, String> getCategory2(String category){
        return CategoryDao.getInstance().getCategory2(category);
    }

}
