<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<h3> Index</h3>
<hr>
<a href="./">현재 경로</a><br>
<a href="test">test</a><br>				<!-- HomeController.java에 메소드를 하나 생성하여 @RequestMapping(value="test")를 생성해야 함
												생성하지 않았을 시, 404오류 -->
<a href="hello">hello</a><br>						
<a href="search?name=홍길동&age=22">search</a>		<!-- url의 파라미터(홍길동, 22)는 HomeController의 리퀘스트 매퍼가 있는 
															value="search"인 메소드의 매개변수로 들어감 (매개변수와 파라미터이름은 일치해야 한다) -->
</body>
</html>
