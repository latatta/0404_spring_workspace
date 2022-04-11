package com.koreait.idev.mapper;

import java.util.List;
import java.util.Map;

import com.koreait.idev.model.Member;

public interface MemberMapper {
	
	int getCount();
	List<Member> selectAll();
	Member selectByMno(int mno);
	Member login(Map<String, String> map);
	void changePassw();
	int checkEmail(String email);
	void addMember(Member mem);
	void updateMember(Member mem);
	
}
