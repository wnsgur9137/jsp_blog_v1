<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<style>
	.list {
		border : 1px solid black;
		margin : 30px;
		padding : 20px;
	}
</style>

<div class="container">

	<c:forEach var="board" items="${boards}">
	<!-- 게시글 아이템 시작 -->
	<div class="card gap_b_20">
		<div class="card-body">
			<h4 class="card-title">${board.title}</h4>
			<a style="font-size : 12px" >글 번호 : ${board.boardKey}  /  글 작성자 : ${board.userName}</a><br>
			<a href="${cp}/board?cmd=detail&boardKey=${board.boardKey}" class="btn btn-primary">상세보기</a>
		</div>
	</div>
	<!-- 게시글 아이템 종료 -->
	</c:forEach>
</div>

<div style="margin-left : 75px">
	<ul class="pagination">
		<c:choose>
			<c:when test="${param.page == 0}">
				<li class="page-item disablend"><a class="page-link" href="${cp}/board?cmd=list&page=${param.page}">이전</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="${cp}/board?cmd=list&page=${param.page - 1}">이전</a></li>
			</c:otherwise>
		</c:choose>
		
		<li class="page-item"><a class="page-link" href="${cp}/board?cmd=list&page=${param.page + 1}">다음</a></ul>
	</ul>
</div>

<%@ include file="../layout/footer.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>