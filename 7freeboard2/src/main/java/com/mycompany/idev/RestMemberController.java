package com.mycompany.idev;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.idev.dto.Member;
import com.mycompany.idev.mapper.MemberMapper;

@RestController			//비동기통신 요청을 처리할 컨트롤러
public class RestMemberController {
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	
	//데이터베이스 sql 실행 
	private final MemberMapper mapper;
	public RestMemberController(MemberMapper mapper) {
		this.mapper=mapper;
	}
	
	@ResponseBody		//리턴이 데이터 자체일 떄 설정
	@RequestMapping(value = "/ajaxex" , method = RequestMethod.GET
	, produces = "application/json;charset=utf-8") // 응답으로 보내는 데이터의 MIME
	public String getList() {
		List<Member> list = mapper.selectAll();
		
		//자바객체를 json으로 변경하기 : jackson-databind 라이브러리 추가
		//json 과 닮은 자바 객체는 Map 입니다. -> 결과를 Map에 저장합니다.
		Map<String,Object> map = new HashMap<>();
		map.put("members",list);
		ObjectMapper objmapper = new ObjectMapper();
	//	objmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//Map 객체에 저장된 결과를 json으로 변환
		String json_result=null;
		try {
			json_result = objmapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json_result;		//리턴은 기존컨트롤러는 view 이름, 여기서는 json 데이터
	}
	
	
	@RequestMapping(value="/ajaxex/{mno}",method=RequestMethod.GET
			,produces = "application/json;charset=utf-8")   //path(url)로 전달되는 변수 : {mno}
	@ResponseBody
	public String getOne(@PathVariable int mno) {
		Member vo = mapper.selectByMno(mno);
		
		//자바객체를 json으로 변경하기 : jackson-databind 라이브러리 추가
		//json 과 닮은 자바 객체는 Map 입니다. -> 결과를 Map에 저장합니다.
		Map<String,Object> map = new HashMap<>();
		map.put("member",vo);
		ObjectMapper objmapper = new ObjectMapper();
	//	objmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//Map 객체에 저장된 결과를 json으로 변환
		String json_result=null;
		try {
			json_result = objmapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json_result;
	}
	
}
/*
 * pom.xml에 추가하기
	<dependency>
	    <groupId>com.fasterxml.jackson.core</groupId>
	    <artifactId>jackson-databind</artifactId>
	    <version>2.13.2.2</version>
	</dependency>
 * 
 * 
 */









