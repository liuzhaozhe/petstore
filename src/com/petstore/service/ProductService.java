package com.petstore.service;

import com.petstore.entity.Category;
import com.petstore.entity.Product;
import com.petstore.persistence.CategoryDao;
import com.petstore.persistence.ProductDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public List<String> getProductNameList(String search){
        return ProductDao.getInstance().getProductNameList(search);
    }

    public Map<String, String> getStockAndPrice(String productId){
        Object[] o = ProductDao.getInstance().getProduct(productId);
        Product product = (Product) o[0];
        Map<String, String> info = null;
        if (product != null){
            info = new HashMap<String, String>();
            info.put("stock", String.valueOf(product.getAmount()));
            info.put("price", String.valueOf(product.getPrice()));
        }
        return info;
    }

    public int getStock(String productId){
        return ProductDao.getInstance().getStock(productId);
    }

}
