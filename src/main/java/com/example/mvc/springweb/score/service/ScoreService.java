package com.example.mvc.springweb.score.service;

import com.example.mvc.springweb.score.domain.Grade;
import com.example.mvc.springweb.score.domain.Score;
import com.example.mvc.springweb.score.mapper.ScoreMyBatisMapper;
import com.example.mvc.springweb.score.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final ScoreMyBatisMapper scoreMapper;

    @Autowired
    public ScoreService(@Qualifier("jdbcScoreRepo") ScoreRepository scoreRepository, ScoreMyBatisMapper scoreMapper) {
        this.scoreRepository = scoreRepository;
        this.scoreMapper = scoreMapper;
    }

    //점수를 저장하기 전에 총점,평균 계산처리
    public void insertService(Score score) {
        score.calcTotalAvg();
//        scoreRepository.insertScore(score);
        scoreMapper.insertScore(score);
    }

    //점수를 조회한 후 컨트롤러에게 학점정보를 추가 리턴한다.
    public List<Score> addGradeService() {
//        List<Score> scoreList = scoreRepository.selectAllScores();
        List<Score> scoreList = scoreMapper.selectAllScores();

        for (Score score : scoreList) {
            if (score.getAverage() >= 90) {
                score.setGrade(Grade.A);
            } else if (score.getAverage() >= 80) {
                score.setGrade(Grade.B);
            } else if (score.getAverage() >= 70) {
                score.setGrade(Grade.C);
            } else if (score.getAverage() >= 60) {
                score.setGrade(Grade.D);
            } else {
                score.setGrade(Grade.F);
            }
        }

        return scoreList;
    }

}