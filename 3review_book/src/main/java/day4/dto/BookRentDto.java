package day4.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder	// 생성자 대신 프로퍼티값을 설정 : 빌더 디자인 패턴

public class BookRentDto {
	private int rent_no;		// sequence 자동생성
	private int mem_idx;
	private String bcode;		// 대여날짜 : sysdate
	private Date rent_date;
	private Date exp_date;
	private Date return_date;
	private int diff_days;
}
