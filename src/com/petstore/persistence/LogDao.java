package com.petstore.persistence;

import com.petstore.entity.Log;
import com.petstore.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogDao {
    public static LogDao instance = null;

    public static LogDao getInstance() {
        if (instance == null) {
            instance = new LogDao();
        }
        return instance;
    }

    private static String insertSql = "INSERT INTO log(username,operation,time) VALUES (?,?,?)";
    private static String findSql = "SELECT * FROM log WHERE username = ?";

    /**
     * 插入数据
     *
     * @param log
     * @return
     */
    public boolean insert(Log log) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(insertSql);
            statement.setString(1, log.getUsername());
            statement.setString(2, log.getOperation());
            statement.setTimestamp(3, log.getTime());

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
     * 查找用户的日志
     * @param username
     * @return
     */
    public List<Log> find(String username) {
        List<Log> logList = new ArrayList<Log>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(findSql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Log log = new Log();
                log.setId(resultSet.getInt(1));
                log.setUsername(resultSet.getString(2));
                log.setOperation(resultSet.getString(3));
                log.setTime(resultSet.getTimestamp(4));
                logList.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(resultSet);
            JDBCUtil.close(statement);
            JDBCUtil.close(connection);
        }
        return logList;
    }

    public static void main(String[] args) {
        LogDao logDao = LogDao.getInstance();
        Log log = new Log();
        log.setUsername("hezhujun");
        log.setOperation("登录");
        log.setTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(logDao.insert(log));
        List<Log> logList = logDao.find("hezhujun");
        System.out.println(logList.size());
    }

}
