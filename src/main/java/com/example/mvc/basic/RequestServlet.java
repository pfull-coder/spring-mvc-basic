package com.example.mvc.basic;

import com.example.mvc.user.domain.User;
import com.example.mvc.user.repository.MemoryUserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// DB에 회원가입을 시켜야한다.
@WebServlet(urlPatterns = "/req-param")
public class RequestServlet extends HttpServlet {

    private MemoryUserRepository repository = new MemoryUserRepository();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 클라이언트가 준 데이터를 읽는다.
        String name = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        //데이터를 객체로 래핑한다.
        User newUser = new User(name, age);

        // 이 데이터를 DB로 보낸다.
        repository.save(newUser);

        // 회원 전체 목록 조회 요청을 해야함. (/show-users)
        resp.sendRedirect("/show-users");

//        resp.setCharacterEncoding("utf-8");
//        resp.setContentType("text/html");
//
//        PrintWriter w = resp.getWriter();
//        w.println("<html>");
//        w.println("<head>");
//        w.println("</head>");
//        w.println("<body>");
//        w.println("  <div>");
//        for (int i = 0; i < 3; i++) {
//            w.println("     <p>");
//            w.println("         이름: " + name  + ", 나이: " + age + "세");
//            w.println("     </p>");
//        }
//        w.println("  </div>");
//        w.println("</body>");
//        w.println("</html>");

    }
}