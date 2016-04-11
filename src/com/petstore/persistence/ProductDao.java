package com.petstore.persistence;

import com.petstore.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hezhujun on 2016/3/18.
 */
public class ProductDao {
    public static ProductDao instance = null;

    public static ProductDao getInstance() {
        if(instance == null) {
            instance = new ProductDao();
        }
        return instance;
    }

    public static String selectByProductId = "SELECT * FROM product WHERE productId = ?";
    public static String selectByCategoryId = "SELECT productId,productName,url FROM product WHERE categoryId = ?";
    public static String selectByName = "SELECT productId,productName,url FROM product WHERE productName LIKE ?";
    public static String update = "UPDATE product SET amount = ?, sellCount = ? WHERE productId = ?";
    public static String selectProductStock = "SELECT amount FROM product WHERE productId = ?";
    public static String selectProductName = "SELECT productName FROM product WHERE productName LIKE ?";


    /**
     * 获取商品
     * @param id
     * @return o[]: product, categoryId
     */
    public Object[] getProduct(String id) {
        Object[] o = new Object[2];
        Product product = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(selectByProductId);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                product = new Product();
                product.setProductId(resultSet.getString(1));
                o[1] = resultSet.getString(2);
                product.setProductName(resultSet.getString(3));
                product.setDescription(resultSet.getString(4));
                product.setPrice(resultSet.getDouble(5));
                product.setAmount(resultSet.getInt(6));
                product.setSellCount(resultSet.getInt(7));
                product.setUrl(resultSet.getString(8));
                o[0] = product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(resultSet);
            JDBCUtil.close(statement);
            JDBCUtil.close(connection);
        }
        return o;
    }

    /**
     * 通过类别获取商品简要信息
     * @param categoryId
     * @return String[3]:productId,productName,url
     */
    public List<String[]> getProductInfo(String categoryId) {
        List<String[]> productInfoList = new ArrayList<String[]>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(selectByCategoryId);
            statement.setString(1, categoryId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String[] productInfo = new String[3];
                productInfo[0] = resultSet.getString(1);
                productInfo[1] = resultSet.getString(2);
                productInfo[2] = resultSet.getString(3);
                productInfoList.add(productInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(resultSet);
            JDBCUtil.close(statement);
            JDBCUtil.close(connection);
        }
        return productInfoList;
    }

    /**
     * 通过名字获取商品简要信息
     * @param name
     * @return String[3]:productId,productName,url
     */
    public List<String[]> getProductInfoByName(String name) {
        List<String[]> productInfoList = new ArrayList<String[]>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(selectByName);
            statement.setString(1, name + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String[] productInfo = new String[3];
                productInfo[0] = resultSet.getString(1);
                productInfo[1] = resultSet.getString(2);
                productInfo[2] = resultSet.getString(3);
                productInfoList.add(productInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(resultSet);
            JDBCUtil.close(statement);
            JDBCUtil.close(connection);
        }
        return productInfoList;
    }

    /**
     * 更新商品数量
     * @param id
     * @param amount
     * @return
     */
    public boolean update(String id, int amount){
        boolean result = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            Object[] o = getProduct(id);
            Product product = (Product) o[0];
            if (product.getAmount() < amount){
                return false;
            }
            product.setAmount(product.getAmount() - amount);
            product.setSellCount(product.getSellCount() + amount);
            statement = connection.prepareStatement(update);
            statement.setInt(1, product.getAmount());
            statement.setInt(2, product.getSellCount());
            statement.setString(3, id);
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
     * 获取商品库存
     * @param productId
     * @return
     */
    public int getStock(String productId) {
        int stock = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(selectProductStock);
            statement.setString(1, productId);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                stock = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(resultSet);
            JDBCUtil.close(statement);
            JDBCUtil.close(connection);
        }
        return stock;
    }

    /**
     * 通过名字的模糊查询获取商品名字
     * @param productName
     * @return
     */
    public List<String> getProductNameList(String productName){
        List<String> productNameList = new ArrayList<String>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(selectProductName);
            statement.setString(1, productName + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                productNameList.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(resultSet);
            JDBCUtil.close(statement);
            JDBCUtil.close(connection);
        }
        return productNameList;
    }
}
