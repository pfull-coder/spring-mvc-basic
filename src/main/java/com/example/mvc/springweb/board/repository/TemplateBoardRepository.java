package com.example.mvc.springweb.board.repository;

import com.example.mvc.springweb.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Repository("templateBoardRepo")
public class TemplateBoardRepository implements BoardRepository {

    private final JdbcTemplate template;

    @Override
    public List<Board> getArticles() {
        String sql = "SELECT * FROM tbl_board ORDER BY board_no DESC";
        return template.query(sql, new BoardMapper());
    }

    @Override
    public void insertArticle(Board article) {
        String sql = "INSERT INTO tbl_board VALUES (seq_board.nextval, ?, ?, ?)";
        template.update(sql, article.getWriter(), article.getTitle(), article.getContent());
    }

    @Override
    public void deleteArticle(int boardNo) {
        String sql = "DELETE FROM tbl_board WHERE board_no=?";
        template.update(sql, boardNo);
    }

    @Override
    public Board getContent(int boardNo) {
        String sql = "SELECT * FROM tbl_board WHERE board_no = ?";
        return template.queryForObject(sql, new BoardMapper(), boardNo);
    }

    @Override
    public void modifyArticle(Board article) {
        String sql = "UPDATE tbl_board " +
                "SET writer=?, title=?, content=? " +
                "WHERE board_no=?";
        template.update(sql, article.getWriter(), article.getTitle()
                , article.getContent(), article.getBoardNo());
    }

    class BoardMapper implements RowMapper<Board> {

        @Override
        public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Board(
                    rs.getInt("board_no"),
                    rs.getString("writer"),
                    rs.getString("title"),
                    rs.getString("content")
            );
        }
    }
}