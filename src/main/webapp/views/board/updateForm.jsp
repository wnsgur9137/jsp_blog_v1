<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
	<form action="${cp}/board?cmd=update" method="post">
		<div class="form-group">
			<input type="text" class="form-control" placeholder="Enter title" name="title" required="required"/>
		</div>
		
		<div class="form-group">
			<textarea id="summernot" class="form-control" rows="5" cols="" name="content">
			</textarea>
		</div>
		<button type="submit" class="btn btn-primary">수정</button>
	</form>
</div>

<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			height : 300
		});
	});
</script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>