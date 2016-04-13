package com.petstore.service;

import com.petstore.entity.Log;
import com.petstore.persistence.LogDao;

import java.util.List;

public class LogService {
    private LogDao logDao = LogDao.getInstance();

    /**
     * 插入数据
     *
     * @param log
     * @return
     */
    public boolean insert(Log log) {
        return logDao.insert(log);
    }

    /**
     * 查找用户的日志
     *
     * @param username
     * @return
     */
    public List<Log> find(String username) {
        return logDao.find(username);
    }

}
