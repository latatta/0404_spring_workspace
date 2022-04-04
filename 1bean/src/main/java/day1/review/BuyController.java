package day1.review;

public class BuyController {
	
	// private int cnt;		// 기본데이터 타입 참고
	private BuyService service;		// private 접근권한의 전역변수 : 클래스의 프로퍼티
									// 프로퍼티가 클래스일때 의존관계가 생깁니다.
									// BuyService 클래스에 대해 의존관계가 있다고 합니다 
									// BuyController가 BuyService라는 클래스를 사용한다 => 의존관계가 있다.
	// 프로퍼티에 값을 대입 또는 참조할 때 	1) 생성자		2) setter 	2가지 
	// -> 클래스 타입일 때는 의것을 "의존관계 주입(Dependency Injection) 이라고 합니다.
	
	public BuyController(BuyService service) {
		System.out.println("BuyController 생성자 *********");
		this.service = service;
	}
	
	public void setService(BuyService service) {
		this.service = service;
	}

	// --------------------------------------------------------------------------------
	
	
	public void buy() {
		System.out.println("controller buy() ~~~~ ");
		service.buy();
	}
}
