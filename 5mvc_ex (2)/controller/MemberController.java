package com.koreait.idev.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "member/")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@GetMapping("/list.do")
	public String list() {
		return "member/MemberList";
	}
	
	@GetMapping("/join.do")
	public String join() {
		return "member/MemberForm";
	}
	
	@PostMapping("/insert.do")
	public String insert() {
		return "redirect:member/login.do?success=y";
	}
	
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
