package com.example.mvc.user.controller;

import com.example.mvc.user.domain.User;
import com.example.mvc.user.repository.MemoryUserRepository;
import com.example.mvc.user.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// 유저 저장소에게 연락해서 회원 목록 조회를 처리해서 화면에 응답해야 함.
@WebServlet(urlPatterns = "/show-users")
public class UserListServlet extends HttpServlet {

    private UserRepository repository = new MemoryUserRepository();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //DB에서 모든 정보를 조회해서 가져옴.
        List<User> userList = repository.findAllUsers();

        //해당 정보를 화면에 HTML로 응답
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        PrintWriter w = resp.getWriter();
        w.println("<html>");
        w.println("<head>");
        w.println("</head>");
        w.println("<body>");

        w.println("<h1>회원 가입 정보</h1>");

        for (User user : userList) {
            w.println("<div># 이름: " + user.getName() + ", 나이: " + user.getAge() + "세</div>");
        }
        w.println("====================================");

        w.println("<a href=\"/basic/post-form.html\">다시 회원가입하기</a>");

        w.println("</body>");
        w.println("</html>");

    }
}