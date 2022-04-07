package day4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import day4.dto.MemberDto;
import day4.mapper.MemberMapper;

@Component
public class MemberService {
	private MemberMapper mapper;
	
	// 의존관계 주입 : ByType(타입 일치하는것)
	@Autowired
	public MemberService(MemberMapper mapper) {		// 생성자에서는 생략 가능
		this.mapper = mapper;
	}
	
	public void insert(MemberDto dto) {
		mapper.insert(dto);
	}
}
