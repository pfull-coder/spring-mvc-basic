package com.example.mvc.springweb.board.repository;

import com.example.mvc.springweb.score.domain.Grade;
import com.example.mvc.springweb.score.domain.Score;
import com.example.mvc.springweb.score.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("templateScoreRepo")
@RequiredArgsConstructor
public class TemplateScoreRepository implements ScoreRepository {

    //스프링이 제공하는 JDBC템플릿 주입받아야 함
    private final JdbcTemplate template;

    @Override
    public void insertScore(Score score) {
        String sql = "INSERT INTO tbl_score " +
                "(stu_num, name, kor, eng, math, total, average) " +
                "VALUES (seq_score.nextval, ?, ?, ?, ?, ?, ?)";
        //insert, update, delete : update()
        //select : query()
        template.update(sql, score.getName(), score.getKor(), score.getEng()
                , score.getMath(), score.getTotal(), score.getAverage());
    }

    @Override
    public List<Score> selectAllScores() {
        String sql = "SELECT * FROM tbl_score";
        //query : 무조건 LIST로 가져옴
        return template.query(sql, new ScoreMapper());
    }

    @Override
    public Score selectOne(int stuNum) {
        String sql = "SELECT * FROM tbl_score WHERE stu_num=?";
        //queryForObject: 무조건 1행만 가져옴
        try {
            return template.queryForObject(sql, new ScoreMapper(), stuNum);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteScore(int stuNum) {
        String sql = "DELETE FROM tbl_score WHERE stu_num = ?";
        template.update(sql, stuNum);
    }

    //내부 클래스 (Inner class) : 이 클래스 안에서만 사용할 클래스
    class ScoreMapper implements RowMapper<Score> {

        @Override
        public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Score(
                    rs.getInt("stu_num"),
                    rs.getString("name"),
                    rs.getInt("kor"),
                    rs.getInt("eng"),
                    rs.getInt("math"),
                    rs.getInt("total"),
                    rs.getDouble("average"),
                    Grade.A
            );
        }
    }
}