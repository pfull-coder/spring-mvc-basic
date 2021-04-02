package com.example.mvc;

import com.example.mvc.jdbc.basic.JdbcBasic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;

public class DbConnectTest {
    @Test
    @DisplayName("데이터 베이스 연결에 성공해야 한다.")
    void connect() {
        //db접속 설정정보 작성
        String userId = "hr";
        String userPw = "hr";
        String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe"; // db접속 위치
        String driverName = "oracle.jdbc.driver.OracleDriver"; // 드라이버 클래스이름

        try {
            // 드라이버 로딩
            Class.forName(driverName);

            // 연결정보 생성(Connection객체)
            Connection connection = DriverManager.getConnection(dbUrl, userId, userPw);
            System.out.println("연결 성공!");

        } catch (Exception e) {
            System.out.println("연결 실패!");
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("주어진 데이터를 jdbc_basic테이블에 잘 저장해야 한다.")
    void insertTest() {
        JdbcBasic jdbcBasic = new JdbcBasic();
        jdbcBasic.insertData(1, "안녕안녕", "김철수");
        jdbcBasic.insertData(2, "뽀롱뽀롱", "뽀로로");

    }

    @Test
    @DisplayName("테스트 데이터 삽입")
    void insertTestData() {

        JdbcBasic jdbcBasic = new JdbcBasic();

        for (int i = 1; i <= 10; i++) {
            jdbcBasic.insertData(i, "테스트내용" + i, "작성자" + i);
        }
    }

    @Test
    @DisplayName("전체 조회에 성공해야 한다.")
    void selectAllTest() {
        JdbcBasic jdbcBasic = new JdbcBasic();
        List<String[]> results = jdbcBasic.selectAll();

        System.out.println("========== 조회 결과 ==========");
        for (String[] result : results) {
            System.out.println(Arrays.toString(result));
        }
        System.out.println("==============================");
    }

}
