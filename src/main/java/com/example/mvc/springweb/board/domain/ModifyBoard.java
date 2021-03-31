package com.example.mvc.springweb.board.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ModifyBoard {
    private int boardNo;
    private String writer;
    private String title;
    private String content;
}