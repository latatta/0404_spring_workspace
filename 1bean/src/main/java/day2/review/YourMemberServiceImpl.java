package day2.review;

public class YourMemberServiceImpl implements MemberService{
	private MemberDao dao;
	// 사용할 수 있는(의존관계 주입할 수 있는) 클래스를 MemberDao의 구현체로 합니다.
	// 해당 클래스는 Mybatis~Dao, Jdbc~Dao
	
	public void setDao(MemberDao dao) {		// 의존관계주입 setter
		this.dao = dao;
	}
	
	public YourMemberServiceImpl() {
		System.out.println("MyMemberServiceImpl create ////////");
	}
	
	@Override
	public void find(int idx) {
		// 예) 검색 비즈니스 로직 처리 : Your 방식으로
		System.out.println("Your Logic~~~~~");
		dao.find(idx);
	}
}
