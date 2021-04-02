package com.example.mvc.springweb.score.repository;

import com.example.mvc.springweb.score.domain.Grade;
import com.example.mvc.springweb.score.domain.Score;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("jdbcScoreRepo")
public class JdbcScoreRepository implements ScoreRepository {

    //설정정보 필드 등록
    private String userId = "java_web1";
    private String userPw = "202104";
    private String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe"; //db접속 위치
    private String driverName = "oracle.jdbc.driver.OracleDriver"; //드라이버 클래스이름

    @Override
    public void insertScore(Score score) {
        Connection connection = null;
        try {
            //드라이버 클래스 로딩
            Class.forName(driverName);

            //DB연결정보 생성
            connection = DriverManager.getConnection(dbUrl, userId, userPw);

            //SQL 작성
            String sql = "INSERT INTO tbl_score " +
                    "(stu_num, name, kor, eng, math, total, average) " +
                    "VALUES (seq_score.nextval, ?, ?, ?, ?, ?, ?)";

            //SQL을 실행할 객체 PreparedStatement 사용
            PreparedStatement statement = connection.prepareStatement(sql);
            // ?값 채우기
            statement.setString(1, score.getName());
            statement.setInt(2, score.getKor());
            statement.setInt(3, score.getEng());
            statement.setInt(4, score.getMath());
            statement.setInt(5, score.getTotal());
            statement.setDouble(6, score.getAverage());

            //sql 실행명령 (insert, update, delete) : executeUpdate()
            statement.executeUpdate();

            System.out.println("데이터 입력 성공!");

        } catch (Exception e) {
            System.out.println("데이터 입력 실패!");
        } finally {
            try {
                //db접속 해제
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public List<Score> selectAllScores() {

        List<Score> scoreList = new ArrayList<>();

        Connection connection = null;

        try {
            Class.forName(driverName);

            connection = DriverManager.getConnection(dbUrl, userId, userPw);

            String sql = "SELECT * FROM tbl_score";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Score findScore = new Score(
                        resultSet.getInt("stu_num"),
                        resultSet.getString("name"),
                        resultSet.getInt("kor"),
                        resultSet.getInt("eng"),
                        resultSet.getInt("math"),
                        resultSet.getInt("total"),
                        resultSet.getDouble("average"),
                        Grade.A
                );

                scoreList.add(findScore);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return scoreList;
    }

    @Override
    public Score selectOne(int stuNum) {

        Connection connection = null;

        try {
            Class.forName(driverName);

            connection = DriverManager.getConnection(dbUrl, userId, userPw);

            String sql = "SELECT * FROM tbl_score WHERE stu_num = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, stuNum);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Score(
                        resultSet.getInt("stu_num"),
                        resultSet.getString("name"),
                        resultSet.getInt("kor"),
                        resultSet.getInt("eng"),
                        resultSet.getInt("math"),
                        resultSet.getInt("total"),
                        resultSet.getDouble("average"),
                        Grade.A
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void deleteScore(int stuNum) {
        Connection connection = null;
        try {
            //드라이버 클래스 로딩
            Class.forName(driverName);

            //DB연결정보 생성
            connection = DriverManager.getConnection(dbUrl, userId, userPw);

            //SQL 작성
            String sql = "DELETE FROM tbl_score WHERE stu_num=?";

            //SQL을 실행할 객체 PreparedStatement 사용
            PreparedStatement statement = connection.prepareStatement(sql);
            // ?값 채우기
            statement.setInt(1, stuNum);

            //sql 실행명령 (insert, update, delete) : executeUpdate()
            statement.executeUpdate();

            System.out.println("데이터 삭제 성공!");

        } catch (Exception e) {
            System.out.println("데이터 삭제 실패!");
        } finally {
            try {
                //db접속 해제
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}