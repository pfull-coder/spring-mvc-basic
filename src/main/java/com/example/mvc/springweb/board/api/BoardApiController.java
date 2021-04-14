package com.example.mvc.springweb.board.api;

import com.example.mvc.springweb.board.domain.Board;
import com.example.mvc.springweb.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/board/*")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardMapper boardMapper;

    //게시물 목록 페이지 열기
    @GetMapping("/list")
    public ModelAndView listPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("board-api/list");
        return mv;
    }
    //게시물 상세보기 페이지 열기
    @GetMapping("/detail")
    public ModelAndView contentPage(int boardNo) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("board-api/content");
        mv.addObject("bno", boardNo);
        return mv;
    }

    //게시물 전체 조회
    @GetMapping("/")
    public List<Board> list() {
        System.out.println("/api/board/ GET!");
        return boardMapper.getArticles();
    }

    //게시물 개별 조회
    @GetMapping("/{boardNo}")
    public Board findOne(@PathVariable int boardNo) {
        System.out.println("/api/board/" + boardNo + " GET!");
        return boardMapper.getContent(boardNo);
    }

    //게시물 등록
    @PostMapping("/")
    public String register(@RequestBody Board board) {
        System.out.println("/api/board/ POST! - " + board);
        boardMapper.insertArticle(board);
        return "regSuccess";
    }

    //게시물 수정
    @PutMapping("/{boardNo}")
    public String update(@PathVariable int boardNo, @RequestBody Board board) {
        board.setBoardNo(boardNo);
        System.out.println("/api/board/ PUT! - " + board);
        boardMapper.modifyArticle(board);
        return "modSuccess";
    }

    //게시물 삭제
    @DeleteMapping("/{boardNo}")
    public String delete(@PathVariable int boardNo) {
        System.out.println("/api/board/" + boardNo + " DELETE!");
        boardMapper.deleteArticle(boardNo);
        return "delSuccess";
    }
}