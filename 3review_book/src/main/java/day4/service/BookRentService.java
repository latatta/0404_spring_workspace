package day4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import day4.dto.BookRentDto;
import day4.mapper.BookRentMapper;

@Component		// bean 태그를 쓰지 않아도 자동으로 bean 생성해줌
public class BookRentService {
	private BookRentMapper mapper;
	
	@Autowired
	public BookRentService(BookRentMapper mapper) {
		this.mapper=mapper;
	}

	// 트랜잭션 : 하나의 세트(집합, 그룹)로 실행되는 sql 명령들. 특히 insert, update, delete
	// 예시로, 사나 -> 모모 계좌이체 (사나 잔액 -, 모모 잔액 + 되는 sql명령 실행)
	public int insert(BookRentDto dto) {
		System.out.println("insert 전 dto rent_no : " + dto.getRent_no());
		int result = mapper.insert(dto);
		System.out.println("insert 후 dto rent_no : " + dto.getRent_no());
		mapper.updateExpDate(dto.getRent_no());
		// update에 오류가 있으면 insert는 rollback
		return result;
	}
}
