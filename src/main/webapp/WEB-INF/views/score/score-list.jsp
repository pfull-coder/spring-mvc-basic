<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1>학생들의 전체 성적 조회</h1>

<c:forEach var = "score" items = "${scores}">
<p>
# 학번: ${score.stuNum}, 이름: ${score.name}, 국어: ${score.kor}점, 영어: ${score.eng}점,
 수학: ${score.math}점, 총점: ${score.total}점, 평균: ${score.average}점
 &nbsp;
 <a href="/score/delete?stuNum=${score.stuNum}">[삭제]</a>
</p>
</c:forEach>

<a href="/score/register">다른 점수 등록하기</a>

<script>
    const msg = "${msg}";

    if (msg === "delOk" {
        alert("점수 삭제 성공!");
    }
</script>


</body>
</html>
