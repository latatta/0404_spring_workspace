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

import com.mycompany.idev.dto.Member;
import com.mycompany.idev.mapper.MemberMapper;

@Controller
@RequestMapping(value = "member/")
@SessionAttributes("member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberMapper mapper;		// dao 역할
	
	
	@GetMapping("/list.do")
	public String list(Model model) {
		List<Member> list = mapper.selectAll();
		model.addAttribute("list", list);
		return "member/MemberList";
	}
	
	//	여기부터-----------------------------------------------------------
	@GetMapping("/join.do")
	public String join() {
		return "member/MemberForm";
	}
	
	@PostMapping("/insert.do")
	public String insert(Member member) {	//	폼 입력된 값의 name 속성과 Mapper 객체를 이용하여
											//		지정된 view(또는 redirect url)로 데이터를 전달합니다.
		// response.setCharacterEncoding("UTF-8");
		logger.info("[My]" + member);
		mapper.addMember(member);
		return "redirect:../";		// 전단계로 이동
	}		// 여기까지 회원가입 끝 ------------------------------------------
	
	@GetMapping("/update.do")
	public String update(@SessionAttribute("member") Member member) {
		return "member/MemberUpdateForm";
	}
	
	@PostMapping("/save.do")
	public void save(Member member, Model model, HttpServletResponse response) throws IOException {
		mapper.updateMember(member);
		model.addAttribute("member", member);
		response.setContentType("text/html;UTF-8");
		PrintWriter out = response.getWriter();
		String url = "./update.do";
		String message = "회원정보 수정되었습니다.";
		out.print("<script>alert('" +message +"');location.href='"+url+"'");
		out.print("</script>");
		// return "redirect:../";
	}
	
	@GetMapping("/idCheck.do")
	public String idCheck(String email, Model model) {		// 검사하기 위한 email은 요청파라미터로 받아옵니다.
		String msg;
		if (mapper.checkEmail(email)==0) {
			msg="사용할 수 있는 이메일 입니다.";
		} else {
			msg="사용할 수 없는 이메일 입니다.";
		}
		model.addAttribute("email", email);
		model.addAttribute("msg", msg);
		return "member/idCheck";		// 뒤에 확장자는 붙이지 않는다
	}
	
	
	
}
