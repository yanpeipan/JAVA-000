package com.example.demo.service;

import com.example.demo.model.User;import java.util.List;

public interface UserService {


    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int insertOrUpdate(User record);

    int insertOrUpdateSelective(User record);

    int updateBatch(List<User> list);

    int updateBatchSelective(List<User> list);

    int batchInsert(List<User> list);
}


