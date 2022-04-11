package com.koreait.idev.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.idev.mapper.MemberMapper;
import com.koreait.idev.model.Member;

@Controller
@RequestMapping(value = "member/")
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
	public String update() {
		return "member/MemberUpdate";
	}
	
	@PostMapping("/save.do")
	public String save() {
		return "redirect:member/update.do";
	}
	
	@GetMapping("/idCheck.do")
	public String idCheck() {
		return "member/idCheck.jsp";
	}
	
	
	
}
