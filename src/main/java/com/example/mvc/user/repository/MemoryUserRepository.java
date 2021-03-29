package com.example.mvc.user.repository;

import com.example.mvc.user.domain.User;

import java.util.ArrayList;
import java.util.List;

// 메모리에 유저정보를 잘 관리하면 됨.
public class MemoryUserRepository implements UserRepository{

// 이 리스트를 DB 대용으로 메모리에서 테스트함
private static final List<User> USER_DB = new ArrayList<>();

    @Override
    public void save(User user) {
        USER_DB.add(user);
    }

    @Override
    public List<User> findAllUsers() {
        return USER_DB;
    }
}
