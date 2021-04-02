package com.example.mvc.jdbc.basic;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcBasic {

    // 설정정보 필드 등록
    private String userId = "hr";
    private String userPw = "hr";
    private String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe"; //db접속 위치
    private String driverName = "oracle.jdbc.driver.OracleDriver"; //드라이버 클래스이름

    // insert 방법
    public void insertData(int id, String text, String writer) {

        Connection connection = null;
        try {
            // 드라이버 클래스 로딩
            Class.forName(driverName);

            // DB 연결정보 생성
            connection = DriverManager.getConnection(dbUrl, userId, userPw);

            // SQL 작성
            String sql = "INSERT INTO jdbc_basic VALUES (?, ?, ?)";

            // SQL을 실행할 객체 PreparedStatement 사용
            PreparedStatement statement = connection.prepareStatement(sql);
            // '?'값 채우기
            statement.setInt(1, id);
            statement.setString(2, text);
            statement.setString(3, writer);

            // sql 실행명령 (insert, update, delete) : executeUpdate()
            statement.executeUpdate();

            System.out.println("데이터 입력 성공!");

        } catch (Exception e) {
            System.out.println("데이터 입력 실패!");
        } finally {
            try {
                // DM 접속 해제
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    // update 방법
    public void updateData(int id, String text, String writer) {

        Connection connection = null;
        try {
            // 드라이버 클래스 로딩
            Class.forName(driverName);

            // DB 연결정보 생성
            connection = DriverManager.getConnection(dbUrl, userId, userPw);

            // SQL 작성
            String sql = "UPDATE jdbc_basic SET text=?, writer=? WHERE id=?";

            // SQL을 실행할 객체 PreparedStatement 사용
            PreparedStatement statement = connection.prepareStatement(sql);
            // '?'값 채우기
            statement.setString(1, text);
            statement.setString(2, writer);
            statement.setInt(3, id);

            // sql 실행명령 (insert, update, delete) : executeUpdate()
            statement.executeUpdate();

            System.out.println("데이터 수정 성공!");

        } catch (Exception e) {
            System.out.println("데이터 수정 실패!");
        } finally {
            try {
                // DB 접속 해제
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    // delete 방법
    public void deleteData(int id) {

        Connection connection = null;
        try {
            // 드라이버 클래스 로딩
            Class.forName(driverName);

            // DB 연결정보 생성
            connection = DriverManager.getConnection(dbUrl, userId, userPw);

            // SQL 작성
            String sql = "DELETE FROM jdbc_basic WHERE id=?";

            // SQL을 실행할 객체 PreparedStatement 사용
            PreparedStatement statement = connection.prepareStatement(sql);
            // '?'값 채우기
            statement.setInt(1, id);

            // sql 실행명령 (insert, update, delete) : executeUpdate()
            statement.executeUpdate();

            System.out.println("데이터 삭제 성공!");

        } catch (Exception e) {
            System.out.println("데이터 삭제 실패!");
        } finally {
            try {
                // DB 접속 해제
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    // select
    public List<String[]> selectAll() {

        // 조회결과를 담아둘 리스트
        List<String[]> resultList = new ArrayList<>();

        Connection connection = null;
        try {
            // 드라이버 클래스 로딩
            Class.forName(driverName);

            // DB연결정보 생성
            connection = DriverManager.getConnection(dbUrl, userId, userPw);

            // SQL 작성
            String sql = "SELECT * FROM jdbc_basic";

            // SQL을 실행할 객체 PreparedStatement 사용
            PreparedStatement statement = connection.prepareStatement(sql);
            // '?'값 채우기

            // sql 실행명령 (select) : executeQuery()
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String text = resultSet.getString("text");
                String writer = resultSet.getString("writer");
                String[] results = {String.valueOf(id), text, writer};

                resultList.add(results);
            }

            System.out.println("데이터 조회 성공!");

        } catch (Exception e) {
            System.out.println("데이터 조회 실패!");
        } finally {
            try {
                // DB 접속 해제
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return resultList;
    }

}