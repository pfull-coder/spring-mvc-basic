<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- jsp에서 자바 코드를 안쓰는 법(EL, JSTL) -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

    <%-- // jsp 전용 주석

        for (var : items) {}
        ${모델이름} -> ${userList} : 모델객체에서 userList를 사용함.
    --%>

    <c:forEach var = "user" items="%{userList}">
        <div> 이름: ${user.name}, 나이: ${user.age}세</div>
    </c:forEach>

        ==============================================
        <br>
        <a href="/new-form.jsp">다시 회원가입하기</a>
</body>
</html>