package com.petstore.service;

import com.petstore.entity.Bill;
import com.petstore.entity.Item;
import com.petstore.persistence.BillDao;
import com.petstore.persistence.ItemDao;
import com.petstore.persistence.ProductDao;

import java.util.List;

public class BillService {

    public List<Bill> getBillList(String username) {
        return BillDao.getInstance().getBillList(username);
    }

    public List<Item> getBillItemList(String billId) {
        return ItemDao.getInstance().getBillItemList(billId);
    }

    public Bill getBill(String billId) {
        return BillDao.getInstance().getBill(billId);
    }

    public boolean addBill(Bill bill, List<Item> billItemList, String username) {
        boolean result = false;
        for (Item item : billItemList
                ) {
            result = ItemDao.getInstance().addBillAndProduct(item, bill.getBillId());
            if (!result){
                break;
            }
            result = ProductDao.getInstance().update(item.getProductId(), item.getAmount());
            if (!result){
                break;
            }
        }
        if (result){
            result = BillDao.getInstance().add(bill, username);
        }
        return result;
    }

}
