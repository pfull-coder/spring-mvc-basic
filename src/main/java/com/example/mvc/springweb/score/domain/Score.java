package com.example.mvc.springweb.score.domain;

import lombok.*;

//학생 성적정보를 저장할 도메인 객체
@Setter
@Getter
@AllArgsConstructor //모든 필드 초기화 생성자
//@NoArgsConstructor //기본 생성자
@ToString
public class Score {

    private static int sequence; //순차적인 학번을 만들어주는 필드

    private int stuNum; //학번
    private String name;
    private int kor;
    private int eng;
    private int math;
    private int total;
    private double average;
    private Grade grade; //학점

    public Score() {
        this.stuNum = ++sequence;
    }

    public Score(String name, int kor, int eng, int math) {
        this();
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }

    //총점, 평균을 구하는 메서드
    public void calcTotalAvg() {
        this.total = kor + eng + math;
        this.average = Math.round((this.total / 3.0) * 100) / 100.0;
    }
}