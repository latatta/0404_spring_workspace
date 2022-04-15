package com.mycompany.idev.mapper;

import java.util.List;
import java.util.Map;

import com.mycompany.idev.dto.Freeboard;

public interface FreeboardMapper {
	List<Freeboard> getPagelist(Map<String, Integer> map);
	int getCount();
	void insert(Freeboard dto);
	void update(Freeboard dto);
	Freeboard getOne(int idx);
	void readCount(int idx);		
	// resultType이 없는데 리턴타입이 int로 되나요? -> 필요한 경우에는 insert,update,delete 가능합니다. 반영된 행의 갯수가 리턴되어요

}
