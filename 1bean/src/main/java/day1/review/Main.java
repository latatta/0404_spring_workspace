package day1.review;

public class Main {

	public static void main(String[] args) {
		// controller 클래스 테스트
		BuyDao dao = new BuyDao(null);
		BuyService service = new BuyService(dao);
		BuyController shop = new BuyController(service);
		shop.buy();
		
		// 애플리케이션 실행은 여러 환경에서 실행하게 됩니다. 그리고 데이터베이스 연결도 다른서버 다른계정들을
		// 		사용하면서 실행이 됩니다.
		// controller에서 사용하는 service 클래스를 변경해야한다거나
		//		service에서 사용하는 dao클래스를 변경해야하는 상황들일 많이 생기는데
		//		이럴때 어떻게 변경?
		
		// spring 프레임웍은 의존관계 주입을 spring에서 관리하는 방법으로 합니다. : IoC
		// spring에서는 객체를 bean(빈)이라고 부릅니다.
		// 		BuyDao = new BuyDao("Mybatis");
		//		-> BuyDao는 클래스
		//		-> new 연산으로 객체를 만듭니다.
	}

}
