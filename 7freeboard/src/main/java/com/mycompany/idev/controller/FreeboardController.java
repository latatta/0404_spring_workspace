package com.mycompany.idev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.idev.dto.Comments;
import com.mycompany.idev.dto.Freeboard;
import com.mycompany.idev.dto.PageDto;
import com.mycompany.idev.mapper.CommentsMapper;
import com.mycompany.idev.mapper.FreeboardMapper;

@Controller
@RequestMapping("/community")
public class FreeboardController {
	private static final Logger logger = LoggerFactory.getLogger(FreeboardController.class);

	@Autowired
	FreeboardMapper mapper;

	@Autowired
	CommentsMapper cmtmapper;

	@RequestMapping("/list")
	public String list(@RequestParam(required = false, defaultValue = "1") int pageNo, Model model) {
		PageDto page = new PageDto(pageNo, 10, mapper.getCount());

		Map<String, Integer> map = new HashMap<>();
		map.put("startNo", page.getStartNo());
		map.put("endNo", page.getEndNo());
		List<Freeboard> list = mapper.getPagelist(map);

		model.addAttribute("page", page);
		model.addAttribute("list", list);

		return "community/list";
//		return "community/list2";		// pageNo를 form
	}

	@RequestMapping("/insert")
	public String insert(int pageNo, Model model) {
		model.addAttribute("page", pageNo);
		return "community/insert";
	}

	@PostMapping("/insert")
	public String save(Freeboard dto) {
		mapper.insert(dto);
		return "redirect:list";

	}

	@GetMapping("/detail")
	public String detail(int idx, int pageNo, Model model) {
		// 조회수 증가 먼저
		mapper.readCount(idx);
		Freeboard dto = mapper.getOne(idx); // sql 실행
		model.addAttribute("bean", dto);
		model.addAttribute("page", pageNo);

		return "community/detail";
	}

	@PostMapping("comment")
	public String comment_save(Comments dto, int pageNo, Model model) {
		// 댓글 입력요소 값들 db에 저장 -> detail (글 상세보기)
		cmtmapper.insert(dto);
		
		// mref값이 freeboard 테이블의 idx입니다.(dto가 Comments이기 때문)
		cmtmapper.commentCountUp(dto.getMref());
		
		model.addAttribute("idx", dto.getMref());
		model.addAttribute("pageNo", pageNo);

		return "redirect:detail";
	}
}
