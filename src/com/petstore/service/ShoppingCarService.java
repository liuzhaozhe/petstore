package com.petstore.service;

import com.petstore.entity.Item;
import com.petstore.persistence.ItemDao;

import java.util.List;

public class ShoppingCarService {

    public List<Item> getShoppingCarItems(String username) {
        return ItemDao.getInstance().getCarItemList(username);
    }

    public boolean checkCarItem(String productId, String username) {
        return ItemDao.getInstance().checkCarItem(productId, username);
    }

    public Item getCarItem(String productId, String username) {
        return ItemDao.getInstance().getCarItem(productId, username);
    }

    public boolean update(Item item, String username) {
        return ItemDao.getInstance().update(item, username);
    }

    public boolean delete(String username, String productId) {
        return ItemDao.getInstance().delete(username, productId);
    }
}
