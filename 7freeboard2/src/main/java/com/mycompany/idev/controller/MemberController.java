package com.mycompany.idev.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mycompany.idev.mapper.MemberMapper;
import com.mycompany.idev.dto.Member;

@Controller
@RequestMapping(value = "/member")
//@SessionAttributes({"member"})  //배열로 애트리뷰트이름을 나열할 수 있습니다.
public class MemberController {
	private static final Logger logger 
	= LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberMapper mapper;		//dao 역할
	
	@GetMapping("/list.do")
	public String list(Model model) {  //매개변수로 선언된 Model 객체를 이용하여
					       //지정된 view(또는 redirect url) 로 데이터를 전달합니다. 
		List<Member> list = mapper.selectAll();
		model.addAttribute("list", list);		//view 에서는 el ${list} 로 데이터 가져오기
		return "member/MemberList";
	}
	
	@GetMapping("/join.do")
	public String join() {
		return "member/MemberForm";
	}
	
	@PostMapping("/join.do")
	public String insert(Member member) { //폼입력된 값의 name 속성과 Member객체가 매핑되어
											//데이터가 저장됩니다.
		//response.setCharacterEncoding("UTF-8");
		logger.info("[My]"+member);
		mapper.addMember(member);
		return "redirect:../";
	}  //회원가입 
	
	@GetMapping("/update.do")
	public String update(@SessionAttribute("member") Member member) {
		//model 객체 저장소에 저장된 것 중 세션애트리뷰트 "member" 데이터 가져오기
		return "member/MemberUpdate";
	}
	
	@PostMapping("/save.do")
	public void save(Member member,Model model,HttpServletResponse response) throws IOException {
		mapper.updateMember(member);
		model.addAttribute("member", member);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String url="./update.do"; String message="회원정보 수정되었습니다.";
		out.print("<script>alert('" +message +"');location.href='"+url+"'");
		out.print("</script>");
		//return "redirect:./update.do";
	}
	
	@GetMapping("/idCheck.do")
	public String idCheck(String email,Model model) {  //검사하기 위한 email은 요청파라미터입니다.
		String msg;
		if(mapper.checkEmail(email)==0)
			msg="사용할수 있는 이메일 입니다.";
		else msg ="사용할 수 없는 이메일 입니다.";
		model.addAttribute("email", email);
		model.addAttribute("msg", msg);
		return "member/idCheck";
	}
	
	
}
