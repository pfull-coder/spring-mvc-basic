package com.example.mvc.user.repository;

import com.example.mvc.user.domain.User;

import java.util.List;

public interface UserRepository {

    // 회원 저장 기능
    void save(User user);

    // 회원 전체 목록 조회 기능
    List<User> findAllUsers();

}
