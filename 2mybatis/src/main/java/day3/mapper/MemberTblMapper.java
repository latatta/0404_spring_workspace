package day3.mapper;

import java.util.List;

import day3.dto.Member;

public interface MemberTblMapper {
	
	void insert(Member member);
	List<Member> selectAll();
}
