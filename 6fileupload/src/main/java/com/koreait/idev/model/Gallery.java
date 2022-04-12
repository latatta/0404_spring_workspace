package com.koreait.idev.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gallery {

	private String title;
	private List<MultipartFile> pics;		// MultipartFile은 form요소의 file을 전달받아 참조합니다.
	
}
