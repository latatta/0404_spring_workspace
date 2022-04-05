package day2.review;

public class MyMemberServiceImpl implements MemberService {
	private MemberDao dao;
	// 사용할 수 있는(의존관계 주입할 수 있는) 클래스를 MemberDao의 구현체로 합니다.
	// 해당 클래스는 Mybatis~Dao, Jdbc~Dao

	// MyMemberServiceImpl 커스텀생성자, 생성자 의존관계 주입으로 변경
	
	 public MyMemberServiceImpl(MemberDao dao) { this.dao=dao; }
	 

		/*
		 * public void setDao(MemberDao dao) { // 의존관계주입 setter this.dao = dao; }
		 * 
		 * public MyMemberServiceImpl() {
		 * System.out.println("MyMemberServiceImpl create ////////"); }
		 */

	@Override
	public void find(int idx) {
		// 예) 비지니스 로직 처리 : My 방식으로
		System.out.println("My Logic~~~~");
		dao.find(idx);
	}
}
