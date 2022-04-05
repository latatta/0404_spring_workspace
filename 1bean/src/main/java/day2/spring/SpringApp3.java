package day2.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import day2.review.MemberController;

public class SpringApp3 {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext3.xml");

		MemberController controller = (MemberController) context.getBean("memberController");

		controller.find(10);

	}

}
