package com_korit.util;


import com_korit.config.DBconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    /*
    * 정적 초기화 블록
    * - 클래스가 처음으로 로딩이 될 때 단 한 번만 실행된다
    * - 요즘에는 mysql connector/j를 쓰면 대부분 자동 등록이 된다
    *
    * */

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    /*
    * DrverManger.getConnection()은 매번 새로운 연결을 만든다.
    * Connection 안에는 값이 아니라 DB와의 세션/자원 이므로 반드시 사용이 끝나면 close()
    * 맴버 변수로 등록 해두고 오래 들고 있으면 아니된다.
    *
    * SQLException = 연결 실패(잘못된 URL/계정/DB다운/방화벽 등의 문제)시 발생
    * */


    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DBconfig.URL, DBconfig.USERNAME, DBconfig.PASSWORD);

    }



}
