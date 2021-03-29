package com.example.mvc.basic;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//서블릿: 웹사이트의 요청을 처리해주는 클래스
@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    //http요청이 들어왔을 때 WAS에 의해 자동 호출되는 메서드
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/hello request!!");

        //요청 정보중에 클라이언트가 보내준 쿼리스트링(name=kim)의 값을 가져옴
        String userName = request.getParameter("name");
        System.out.println("userName = " + userName);

        //웹 브라우저에게 텍스트를 응답
        response.setContentType("text/plain");  //문서 타입 설정
        response.setCharacterEncoding("utf-8"); //문서 인코딩 설정
        PrintWriter w = response.getWriter(); //HTML을 그릴 수 있는 객체

        w.write("hello~~ " + userName);
    }
}