package com.example.mvc;

import com.example.mvc.springweb.score.domain.Score;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LombokTest {

    @Test
    void lombokTest() {

        Score score = new Score();
        score.setKor(100);
        score.getKor();
    }


}