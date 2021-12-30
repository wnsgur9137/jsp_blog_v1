<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<style>
	.joinForm {
		border : 1px solid black;
		margin : 30px;
		padding : 20px;
	}
	.joinTitle {
		font-size : 150%;
	}
</style>


<div class="container">
	<h3><span class="joinTitle">회원가입</span></h3><br>
	<form action="${cp}/user?cmd=join" method="post">
		<div class="form-group">
			<input type="text" class="form-control" placeholder="이름을 입력하십시오." name="userName" required="required"/>
		</div>
		
		<div class="form-group">
			<input type="text" class="form-control" placeholder="아이디를 입력하십시오." name="userId" required="required"/>
		</div>
		
		<div class="form-group">
			<input type="password" class="form-control" placeholder="비밀번호를 입력하십시오." name="password" required="required"/>
		</div>
		
		<div class="form-group">
			<input type="email" class="form-control" placeholder="email을 입력하십시오." name="email" required="required"/>
		</div>
		<button type="submit" class="btn btn-primary">회원가입</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>