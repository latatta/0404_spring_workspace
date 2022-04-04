package day1.review;

public class ProduceDao {
	private int cnt;
	
	public ProduceDao() {
		System.out.println("dao produce() ~~~~~~~~~~");
		System.out.println("ProduceDao 기본 생성자**********");
	
	}
	public void setCnt(int cnt) {		// 기본 데이터타입
		this.cnt = cnt;
	}
	
	public void produce() {
		System.out.println("상품을 " + cnt + " 수량으로 생산합니다.");
	}
}
