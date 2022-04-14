package com.koreait.idev;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.idev.model.Order;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="test")
	public void test() {	// 내용이 없을 시, 요청 value값을 가진 view로 연결 
	}
	
	@RequestMapping(value="hello")
	public String hello() {
		return "test";		// String 값으로 리턴하여 요청 value="test"인 view로 연결	: 	hello 링크를 눌러도 test화면으로 넘어가게 됨
	}
	
	@RequestMapping(value="search")
	public void search(String name, int age, HttpServletResponse response) throws IOException {		// 메소드의 매개변수 = url의 파라미터(매개변수와 파라미터이름은 일치해야 한다)
										  // HttpServletResponse : 서버가 클라이언트로 보내는 응답정보를 처리하는 객체
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		/* 자바에서 웹으로 데이터를 출력하기 위해 PrintWriter를 사용한다. 서블릿 생명주기에 따라 생성된 요청객체와 응답객체는 doGet
		 * 메서드를 통해 html 콘텐츠를 생성하는데 이때 원하는 내용을 출력하기 위한 코드이다. */
		out.print(name+"<br>");
		out.print(age);
	}
	
	
	// orderForm.jsp를 화면에 띄우기
	@RequestMapping(value="order", method=RequestMethod.GET)
	public String form() {
		return "orderForm";		// 보여줄 view 파일(orderForm) 지정
	}
	
	// orderForm.jsp의 폼 양식에 입력된 값 가져오기
	@RequestMapping(value="order", method=RequestMethod.POST)
	public String order(Order order) {
		System.out.println(order);
		return "home";
	}
}
