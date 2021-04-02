package com.example.mvc.springweb.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@AllArgsConstructor
@ToString
public class Board {

    private static int sequence;

    private int boardNo; //글번호
    private String writer; //작성자
    private String title; //글제목
    private String content; //글내용

    public Board() {
//        System.out.println("board constructor call!!");
        this.boardNo = ++sequence;
    }

    public Board(String writer, String title, String content) {
        this();
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}