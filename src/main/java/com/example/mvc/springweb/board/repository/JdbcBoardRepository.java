package com.example.mvc.springweb.board.repository;

import com.example.mvc.springweb.board.domain.Board;
import com.example.mvc.springweb.score.domain.Grade;
import com.example.mvc.springweb.score.domain.Score;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("jdbcBoardRepo")
public class JdbcBoardRepository implements BoardRepository {

    //설정정보 필드 등록
    private String userId = "java_web1";
    private String userPw = "202104";
    private String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe"; //db접속 위치
    private String driverName = "oracle.jdbc.driver.OracleDriver"; //드라이버 클래스이름

    @Override
    public List<Board> getArticles() {
        List<Board> boardList = new ArrayList<>();

        Connection connection = null;

        try {
            Class.forName(driverName);

            connection = DriverManager.getConnection(dbUrl, userId, userPw);

            String sql = "SELECT * FROM tbl_board";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Board findArticle = new Board(
                        resultSet.getInt("board_no"),
                        resultSet.getString("writer"),
                        resultSet.getString("title"),
                        resultSet.getString("content")
                );

                boardList.add(findArticle);
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

        return boardList;
    }

    @Override
    public void insertArticle(Board article) {
        Connection connection = null;

        try {
            Class.forName(driverName);

            connection = DriverManager.getConnection(dbUrl, userId, userPw);

            String sql = "INSERT INTO tbl_board VALUES (seq_board.nextval, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, article.getWriter());
            statement.setString(2, article.getTitle());
            statement.setString(3, article.getContent());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void deleteArticle(int boardNo) {
        Connection connection = null;

        try {
            Class.forName(driverName);

            connection = DriverManager.getConnection(dbUrl, userId, userPw);

            String sql = "DELETE FROM tbl_board WHERE board_no = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, boardNo);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public Board getContent(int boardNo) {
        Connection connection = null;

        try {
            Class.forName(driverName);

            connection = DriverManager.getConnection(dbUrl, userId, userPw);

            String sql = "SELECT * FROM tbl_board WHERE board_no = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, boardNo);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Board(
                        resultSet.getInt("board_no"),
                        resultSet.getString("writer"),
                        resultSet.getString("title"),
                        resultSet.getString("content")
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
    public void modifyArticle(Board article) {
        Connection connection = null;

        try {
            Class.forName(driverName);

            connection = DriverManager.getConnection(dbUrl, userId, userPw);

            String sql = "UPDATE tbl_board " +
                    "SET writer=?, title=?, content=? " +
                    "WHERE board_no=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, article.getWriter());
            statement.setString(2, article.getTitle());
            statement.setString(3, article.getContent());
            statement.setInt(4, article.getBoardNo());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}