<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import = "com.example.mvc.user.domain.User;" %>
<%@ page import = "com.example.mvc.user.repository.*;" %>

<%
        // 자바코드 작성 가능
        UserRepository repository = new MemoryUserRepository();

        // 클라이언트가 준 데이터를 읽는다.
        String name = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        //데이터를 객체로 래핑한다.
        User newUser = new User(name, age);

        // 이 데이터를 DB로 보낸다.
        repository.save(newUser);

        // 회원 전체 목록 조회 요청을 해야함. (/show-users)
        response.sendRedirect("/jsp/show-users.jsp");
%>