package com.mycompany.idev.dto;

import java.sql.Date;

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
	private Date wdate;
	private int heart;
	private String ip;
}
