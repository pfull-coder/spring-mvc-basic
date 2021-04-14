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


<h1>게시글 목록</h1>

<table border="1">
	<tr>
		<td>번호</td>
		<td>작성자</td>
		<td>제목</td>
		<td>비고</td>
	</tr>

</table>


<p>
	<a href="#">게시글 작성하기</a>
</p>


<script>
	function makeArticleDOM(articles) {
		if (articles === null || articles.length <= 0) {
			alert('게시글이 없습니다!');
		} else {
			const $frag = document.createDocumentFragment();
			for (let article of articles) {
				const {boardNo, writer, title} = article;
				const $tr = document.createElement('tr');
				let datas = '';
				datas += '<td>' + boardNo + '</td>';
				datas += '<td>' + writer + '</td>';
				datas += '<td><a href="/api/board/detail?boardNo='+ boardNo +'">' + title + '</a></td>';
				datas += '<td><a href="#">[삭제]</a></td>';

				$tr.innerHTML = datas;
				$frag.appendChild($tr);
			}
			document.querySelector('table').appendChild($frag);
		}
	}
	function getList() {
		fetch('/api/board/')
			.then(res => res.json())
			.then(articles => {
				makeArticleDOM(articles);
			});
	}
	(function() {
		getList();
	}());
</script>

</body>
</html>