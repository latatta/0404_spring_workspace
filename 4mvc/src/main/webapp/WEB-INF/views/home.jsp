<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<h3>Index</h3>
<a href="./">현재 프로젝트 경로(컨텍스트)의 url : http://localhost:8085/app/</a><br>		<!-- 메인 소스폴더에 패키지 이름이 com.idev.app이므로 뒤에 app가 자동으로 붙음 -->
<a href="test">http://localhost:8085/app/test</a><br>
<a href="hello">http://localhost:8085/app/hello</a><br>
<a href="spring">http://localhost:8085/app/spring</a><br>
<h5>파라미터 테스트</h5>
<a href="search?name=모모">http://localhost:8085/app/search?name=홍길동&age=23</a><br>
<h5>모델객체를 이용한 파라미터 데이터 전달(폼 양식의 데이터)</h5>
<a href="order">http://localhost:8085/app/order</a><br>
</body>
</html>
