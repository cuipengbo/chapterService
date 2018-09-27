package com.hytx.chapterService.dao;

import java.util.List;

import com.hytx.chapterService.model.UserDomain;

public interface UserDao {

    int insert(UserDomain record);

    List<UserDomain> selectUsers();
}