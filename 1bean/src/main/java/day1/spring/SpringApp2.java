package day1.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import day1.review.ProduceService;

public class SpringApp2 {

	public static void main(String[] args) {
		// 빈 설정 파일을 읽어와서 빈(객체)를 생성합니다.
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:META-INF/spring/applicationContext2.xml");
		
		
		// 위에서 만들어진 bean(객체) 중에서 id가 controller인 bean을 가져와 참조합니다.
		// getBean 메소드는 리턴타입이 Object : 참조타입에 맞게 캐스팅이 필요합니다.
		ProduceService service = 
				(ProduceService)context.getBean("produceService");

		service.produce();
	}

}
