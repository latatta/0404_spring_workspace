// 작성자 : 정세은

package com.koreait.bookclub.mapper;

import java.util.List;

import com.koreait.bookclub.dto.BookDto;
import com.koreait.bookclub.dto.BookRentDto;


public interface BookrentMapper {
	List<BookDto> selectBooks();
	int countByBcode(String bcode);
	int countRentByBcode(String bcode);
	void insertRent(BookRentDto dto);
	List<BookRentDto> getRentList();
	void updateDelays();
}
