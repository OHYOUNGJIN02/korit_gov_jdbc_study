package com_korit;

import com_korit.dao.UserDao;
import com_korit.entity.User;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = UserDao.getInstance();

        User user = User.builder()
                .username("trg9956123")
                .password("1q2w3e4r")
                .age(24)
                .build();

        int count = userDao.addUser(user);
        System.out.println("추가된 행 갯수:" + count);
        System.out.println("추가된 유저 정보:" + user);

        }
    }
