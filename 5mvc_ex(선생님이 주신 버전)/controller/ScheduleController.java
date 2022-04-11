package com.koreait.idev.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("schedule/")
public class ScheduleController {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

	
	@GetMapping("/new.do")
	public String newdo() {
		return "schedule/schedule";
	}

	@PostMapping("/save")
	public String save() {
		return "redirect:schedule/new.do";
	}
}
