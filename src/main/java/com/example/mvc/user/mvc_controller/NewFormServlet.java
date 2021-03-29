package com.example.mvc.user.mvc_controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 단순히 외부접근이 불가능한 'WEB-INF' 폴더 내부에 있는 mvc-form.jsp를 열어주는 역할.
@WebServlet(urlPatterns = "/new-form")
public class NewFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //내부에서 접근차단된 jsp파일을 열려면 forward기능 이용해야 함
        RequestDispatcher dispatcher
                = req.getRequestDispatcher("/WEB-INF/views/mvc-form.jsp");
        dispatcher.forward(req, resp);
    }
}
