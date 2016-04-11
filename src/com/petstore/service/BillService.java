package com.petstore.service;

import com.petstore.entity.Bill;
import com.petstore.entity.Item;
import com.petstore.persistence.BillDao;
import com.petstore.persistence.ItemDao;
import com.petstore.persistence.JDBCUtil;
import com.petstore.persistence.ProductDao;

import java.sql.Connection;
import java.sql.SQLException;
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
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            for (Item item : billItemList
                    ) {
                result = ItemDao.getInstance().addBillAndProduct(item, bill.getBillId(), conn);
                if (!result){
                    // 操作失败，回滚操作
                    conn.rollback();
                    break;
                }
                result = ProductDao.getInstance().update(item.getProductId(), item.getAmount(), conn);
                if (!result){
                    // 操作失败，回滚操作
                    conn.rollback();
                    break;
                }
            }
            if (result){
                result = BillDao.getInstance().add(bill, username, conn);
            }
            if (!result){
                // 操作失败，回滚操作
                conn.rollback();
            }
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // 操作失败，回滚操作
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
