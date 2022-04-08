package com.idev.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idev.app.model.Order;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * @RequestMapping : url 요청을 처리할 메소드를 지정하는 어노테이션
	 * 				   : 브라우저에 출력할 view파일은 리턴값으로 설정
	 * 				   : value 속성값은 요청이름
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
	public void test() {
		
	}

	@RequestMapping(value="hello")
	public void hello() {
		
	}
	
	@RequestMapping(value="spring")
	public String spring() {
		return "home";			// view 파일이 home.jsp가 됩니다.
	}
	
	// 결론 : url 요청이름과 view 파일명은 같도록 하는 것이 기본설정입니다.
	//		(url 요청이름은 프로젝트에 맞게 직접 설계합니다.)
	//		view파일을 변경하고 싶으면 return "파일명"으로 합니다.
	
	@RequestMapping(value="search")
	public void search(String name, int age) {		// 메소드의 매개변수는 파라미터를 전달받게 됩니다.
		// 메소드의 매개변수는 url 요청파라미터(쿼리스트링)를 전달받게 됩니다. 
		// 중요 : 파라미터 이름과 변수명은 일치하도록 합니다.
		// int값은 파라미터는 기본이 String이므로 null일 때, int변환 못하고 오류
		System.out.println("name : " + name);
		System.out.println("age : " + age);
	}
	
	
	
	// orderForm.jsp를 화면에 띄웁니다.
	@RequestMapping(value="order", method = RequestMethod.GET)
	public String form() {
		return "orderForm";			// view 파일이름(확장자 제외) 지정
	}
	
	// 폼 양식에 입력된 값을 가져옵니다.
	@RequestMapping(value="order", method = RequestMethod.POST)
	public String order(Order order) {
		System.out.println(order);
		return "home";
	}
	
	
}
