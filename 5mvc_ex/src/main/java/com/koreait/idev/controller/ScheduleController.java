package com.koreait.idev.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.koreait.idev.mapper.ScheduleMapper;
import com.koreait.idev.model.Member;
import com.koreait.idev.model.Schedule;

@Controller
@RequestMapping("schedule/")
@SessionAttributes("member")
public class ScheduleController {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

	@Autowired
	ScheduleMapper mapper;
	
	@GetMapping("/new.do")
	public String newdo(Model model, @SessionAttribute(value="member", required=false) Member member) {		// 현재 로그인한 사용자의 스케줄 리스트 가져와서 출력
																	// required=false로 지정하면 member가 필수요소가 아니게 됩니다.(400오류 해결)
		// 로그인 상태가 아닐 때 : 400오류 (Missing session attribute 'member' of type Member)
		//	-> @SessionAttribute에 대해 필수적인 값을 false로 설정 (required=false로 설정)
		model.addAttribute("list", mapper.getSchedules(member.getMno()));
		return "schedule/schedule";
	}

	@GetMapping("/save.do")
	public String save(Schedule sch, Model model) {
		logger.info("[My]" + sch.getSdate() + " : " + sch.getStime());
		String sdate = sch.getSdate() + " " + sch.getStime();
		sch.setSdate(sdate);
		mapper.insert(sch);
		return "redirect:new.do";
	}
	
	@GetMapping("/delete.do")
	public void delete(int idx, @SessionAttribute("member") Member member, 
			HttpServletResponse response) throws IOException {
		String message;
		if (mapper.checkMno(idx) != member.getMno())		// mno 비교하기
			message = "삭제할 수 없는 idx입니다.";
		else {
			message = "삭제하였습니다.";
			mapper.delete(idx);
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String url = "./new.do";
		out.print("<script>alert('" +message +"');location.href='"+url+"'");
		out.print("</script>");
	}
	
}
