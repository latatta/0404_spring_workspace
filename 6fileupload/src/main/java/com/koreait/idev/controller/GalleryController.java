package com.koreait.idev.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.idev.model.Gallery;

@Controller
public class GalleryController {

	private static final Logger logger = LoggerFactory.getLogger(GalleryController.class);

	@GetMapping(value = "gallery")
	public void view() {
	}

	@PostMapping("gallery")
	public String upload(Gallery vo, Model model) {
		// 서버에서 전송받은 파일을 저장할 폴더를 정합니다.
		String path = "C:\\dev1228\\upload"; // \는 출력 또는 문자열로 지정할 때는 두번 씁니다.

		// view(jsp)로 전달할 파일명 리스트
		List<String> list = new ArrayList<>();

		// 서버에 전송된 내용 확인 테스트
		logger.info("title : " + vo.getTitle());
		for (MultipartFile f : vo.getPics()) {
			logger.info("파일명 : " + f.getOriginalFilename()); // 전송받은 파일명
			logger.info("파일 사이즈 : " + f.getSize());
			logger.info("랜덤 문자열 : " + UUID.randomUUID().toString());

			// 서버에 저장할 파일 객체를 생성 : 생성자 인자는 파일객체의 경로와 파일명
			// File sfile = new File(path + "\\" + f.getOriginalFilename());
			if (f.getSize() != 0) {
				String temp = f.getOriginalFilename();
				String ext = temp.substring(temp.indexOf('.'), temp.length()); // 확장자만 추출
				String newfile = UUID.randomUUID().toString().substring(0, 8) + ext;
				// 새로운 파일명 - 중복을 최소화하려면 랜덤문자열길이를 늘리세요.
				File sfile = new File(path + "\\" + newfile);
				// f.getOriginalFilename()은 여러 접속자(사용자)가 중복된 이름을 사용할 수 있어요.
				// -> 파일명은 서버에서 임의로 정해줄 것 입니다.

				try {
					// MultipartFile f 로 전송받은 파일을 참조하고 있으므로ㅋ
					f.transferTo(sfile); // 서버에 저장할 파일객체로 전송합니다.(서버 업로드)
					list.add(newfile);
				} catch (IllegalStateException | IOException e) {
					logger.info("파일 전송 오류 : " + e.getMessage());
				}
			}
		}
		model.addAttribute("list", list);
		return "gallery"; // view파일이 gallery.jsp
	}

}
