package com.koreait.idev.controller;

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
	public String newdo(Model model, @SessionAttribute("member") Member member) {		// 현재 로그인한 사용자의 스케줄 리스트 가져와서 출력
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
}
