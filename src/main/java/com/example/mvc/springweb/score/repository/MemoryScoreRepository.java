package com.example.mvc.springweb.score.repository;


import com.example.mvc.springweb.score.domain.Score;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryScoreRepository implements ScoreRepository {

    //학생들의 점수정보 객체(Score)를 저장할 Map 생성(메모리DB 역할)
    // key: 학번, value: 점수정보 객체
    private static Map<Integer, Score> scoreMap = new HashMap<>();

    static {
        scoreMap.put(1, new Score("김철수", 99, 78, 67));
        scoreMap.put(2, new Score("박영희", 85, 66, 95));
        scoreMap.put(3, new Score("고길동", 100, 88, 92));
    }

    @Override
    public void insertScore(Score score) {
        scoreMap.put(score.getStuNum(), score);
    }

    @Override
    public List<Score> selectAllScores() {
        //map을 반복문으로 돌려서 value만 싹 빼서 리스트에 담아서 리턴
        List<Score> scoreList = new ArrayList<>();
        for (int key : scoreMap.keySet()) {
            Score score = scoreMap.get(key);
            scoreList.add(score);
        }
        return scoreList;
    }

    @Override
    public Score selectOne(int stuNum) {
        return scoreMap.get(stuNum);
    }

    @Override
    public void deleteScore(int stuNum) {
        scoreMap.remove(stuNum);
    }
}