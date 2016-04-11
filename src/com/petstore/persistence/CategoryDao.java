package com.petstore.persistence;

import com.petstore.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hezhujun on 2016/3/12.
 */
public class CategoryDao {

    public static CategoryDao instance = null;

    public static CategoryDao getInstance() {
        if(instance == null) {
            instance = new CategoryDao();
        }
        return instance;
    }

    public static String selectCategory = "SELECT categoryId, categoryName FROM category WHERE fatherId = ?";
    public static String selectByCategoryId = "SELECT * FROM category WHERE categoryId = ?";

    /**
     * 获取所有二级类别
     * @param id
     * @return
     */
    public Map<String, String> getCategory2(String id) {
        Map<String, String> categoryList = new HashMap<String, String>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(selectCategory);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                categoryList.put(resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(resultSet);
            JDBCUtil.close(statement);
            JDBCUtil.close(connection);
        }
        return categoryList;
    }

    /**
     * 获取一个类别所有信息
     * @param id
     * @return
     */
    public Category getCategory(String id) {
        Category category = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(selectByCategoryId);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                category = new Category();
                category.setCategoryId(resultSet.getString(1));
                category.setCategoryName(resultSet.getString(2));
                category.setFatherId(resultSet.getString(3));
                category.setFatherName(resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(resultSet);
            JDBCUtil.close(statement);
            JDBCUtil.close(connection);
        }
        return category;
    }
}
