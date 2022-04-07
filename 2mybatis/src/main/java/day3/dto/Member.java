package day3.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor		// 기본생성자
@AllArgsConstructor		// 모든 프로퍼티가 인자인 생성자
@ToString

public class Member {

	private int custNo;
	private String custName;
	private String phone;
	private String address;
	private Date joinDate;
	private String grade;
	private String city; 
}
