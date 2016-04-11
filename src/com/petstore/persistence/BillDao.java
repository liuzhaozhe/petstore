package com.petstore.persistence;

import com.petstore.entity.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDao {
    public static BillDao instance = null;

    public static BillDao getInstance() {
        if(instance == null) {
            instance = new BillDao();
        }
        return instance;
    }

    public static String insertSql = "INSERT INTO bill VALUES (?,?,?,?,?,?,?)";
    public static String selectByUsernameSql = "SELECT * FROM bill WHERE username = ?";
    public static String selectByBillIdSql = "SELECT * FROM bill WHERE billId = ?";

    /**
     * 添加账单
     * @param bill
     * @param username
     * @return
     */
    public boolean add(Bill bill, String username){
        boolean result = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(insertSql);
            statement.setString(1, bill.getBillId());
            statement.setString(2, username);
            statement.setString(3, bill.getConsignee());
            statement.setString(4, bill.getConsigneeAddress());
            statement.setString(5, bill.getConsigneePhone());
            statement.setDouble(6, bill.getMoney());
            statement.setTimestamp(7, bill.getCreateTime());
            int i = statement.executeUpdate();
            if (i == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(statement);
            JDBCUtil.close(connection);
        }
        return result;
    }

    /**
     * 获取用户所有账单
     * @param username
     * @return
     */
    public List<Bill> getBillList(String username) {
        List<Bill> billList = new ArrayList<Bill>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(selectByUsernameSql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Bill bill = new Bill();
                bill.setBillId(resultSet.getString(1));
                bill.setConsignee(resultSet.getString(3));
                bill.setConsigneeAddress(resultSet.getString(4));
                bill.setConsigneePhone(resultSet.getString(5));
                bill.setMoney(resultSet.getDouble(6));
                bill.setCreateTime(resultSet.getTimestamp(7));
                billList.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(resultSet);
            JDBCUtil.close(statement);
            JDBCUtil.close(connection);
        }
        return billList;
    }

    /**
     * 通过id获取账单
     * @param id
     * @return
     */
    public Bill getBill(String id) {
        Bill bill = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(selectByBillIdSql);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                bill = new Bill();
                bill.setBillId(resultSet.getString(1));
                bill.setConsignee(resultSet.getString(3));
                bill.setConsigneeAddress(resultSet.getString(4));
                bill.setConsigneePhone(resultSet.getString(5));
                bill.setMoney(resultSet.getDouble(6));
                bill.setCreateTime(resultSet.getTimestamp(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(resultSet);
            JDBCUtil.close(statement);
            JDBCUtil.close(connection);
        }
        return bill;
    }
}
