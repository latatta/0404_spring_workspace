package com.mycompany.idev.dto;



import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comments {
	private int idx;
	private int mref;
	private String name;
	private String content;
	private String ip;
	//private LocalDateTime  wdate;			//LocalDateTime : mysql에서만 하세요.
	private Timestamp wdate;		//Date타입은 날짜만 db에서 받아옵니다.(오라클)
	private int heart;
}
