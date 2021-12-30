<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<style>
	.loginForm {
		border : 1px solid black;
		margin : 30px;
		padding : 20px;
	}
	.loginTitle {
		font-size : 150%;
	}
</style>


<div class="loginForm">
	<h3><span class="loginTitle">로그인</span></h3>
	<form action="${cp}/user?cmd=login" method="post">
		<div class="form-group">
			<input type="text" class="form-control" placeholder="Enter userId" name="userId" value="" required="required"/>
		</div>
		
		<div class="form-group">
			<input type="password" class="form-control" placeholder="Enter password" name="password" required="required"/>
		</div>
		<button type="submit" class="btn btn-primary">로그인</button>
	</form>
</div>	

<%@ include file="../layout/footer.jsp"%>