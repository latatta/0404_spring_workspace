package day2.annotation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import day2.review.MemberDao;
import day2.review.MemberService;

 @Component("myService")		// value 속성 생략, bean_id 'myService'로 지정
public class MyMemberServiceImpl implements MemberService {
	// @Autowired 
	// @Qualifier("jdbcMemberDao")
	private MemberDao dao;
	// 사용할 수 있는(의존관계 주입할 수 있는) 클래스를 MemberDao의 구현체로 합니다.
	// 해당 클래스는 Mybatis~Dao, Jdbc~Dao

	// 커스텀 생성자
	public MyMemberServiceImpl( @Qualifier("jdbcMemberDao") MemberDao dao) {
		System.out.println("MyMemberServiceImpl create///////");
		this.dao = dao;
	}

	public void setDao(MemberDao dao) { // 의존관계주입 setter
		this.dao = dao;
	}

	@Override
	public void find(int idx) {
		// 예) 비지니스 로직 처리 : My 방식으로
		System.out.println("My Logic~~~~");
		dao.find(idx);
	}
}
