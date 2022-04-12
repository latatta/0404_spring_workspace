<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일업로드 테스트</title>
</head>
<body>
	<h3>파일업로드 테스트</h3>
	<hr>
	<form action="gallery" method="post" enctype="multipart/form-data">
		<input type="text" placeholder="제목을 입력하세요."><br> 
		이미지 파일을 선택하세요.(최대 3개)<br> 
		<input type="file" name="pics" accept="image/*"><br>
		<input type="file" name="pics" accept="image/*"><br> 
		<input type="file" name="pics" accept="image/*"><br>
		<button>전송</button>
	</form>
</body>
</html>