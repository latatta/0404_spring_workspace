package com.koreait.idev;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.idev.mapper.MemberMapper;
import com.koreait.idev.model.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("member")	// **Model 저장소에 저장된 애트리뷰트 중에 member는 세션 scope 값이라는 설정입니다.
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	MemberMapper mapper;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, @ModelAttribute("success") String success) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@GetMapping("/login.do")
//	public String login(@ModelAttribute("success") String success) {
	public String login(String success, Model model) {
		model.addAttribute("success", success);		// url에 요청 파라미터가 남아있습니다. (alert가 새로고침할 때마다 뜸)
		return "login";
	}
	
	@PostMapping("login.do")
	public String loginProc(@RequestParam Map<String, String> map, Model model, RedirectAttributes rdattr) {
		Member member = mapper.login(map);
		String url;
		if (member != null) {
			// 성공 : 메인화면으로, session 객체에 로그인 정보를 저장했습니다.
			model.addAttribute("member", member);
			model.addAttribute("success", "y");
			url = "/";
		} else {	// 실패 : 다시 로그인 하러각. (미션) alert 메시지 띄우기 "로그인 정보가 틀립니다."
//			rdattr.addFlashAttribute("success", "n");		// url 표시 안하고 보내기
			url = "login.do?success=n";
			// 요청방식이 post일때만 RedirectAttributes 객체에 url에 표시되지 않도록 파라미터
			//	값을 전달할 수 있습니다. -> 여기서는 사용을 못합니다.
		}
		return "redirect:/"+url;
	}
	
	@GetMapping("/logout.do")
	public String logout(SessionStatus status) {		// 현재 세션 상태 객체
		status.setComplete();		// @SessionAttributes로 설정된 애트리뷰트 값을 clear한다.
		return "redirect:./";
	}
	
	@GetMapping("/logout")
	public String logout2(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.invalidate();
		// 서버가 JSESSIONID는 새로 부여해주지만 @SessionAttributes로 설정된 값은 남아있다.
		return "redirect:/";
	}
	
	// @SessionAttributes로 설정된 것은 SessionStatus로 지운다.
	// status.setComplete();		- JSESSIONID는 변하지 않고 @SessionAttributes로 설정된 
	//									애트리뷰트 값을 clear한다.
	//								- HttpSession의 removeAttribute() 메소드 동작과 매우 유사
	
	// jsp에서 로그아웃 : session.invalidae(); 		// JSESSIONID값을 새로운 값으로 합니다.
}
