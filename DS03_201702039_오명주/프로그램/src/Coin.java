
public class Coin {
	// 상수
	private static final int DEFAULT_VALUE = 0;

	// private instance variables
	private int _value; // 동전의 금액

	// 생성자
	// 객체 생성시 주어진 값이 없는 경우
	public Coin() {
		// default 값은 0으로 설정
		this._value = DEFAULT_VALUE;
	}

	// 객체 생성시 주어진 값이 있는 경우
	public Coin(int givenValue) {
		this._value = givenValue;
	}

	// 공개 함수
	// getter / setter
	public int value() {
		return this._value; // 동전 값 반환
	}

	public void setValue(int newValue) {
		this._value = newValue;	// 입력받은 값으로 값 설정
	}

	// 객체 값을 비교하는 함수
	@Override
	public boolean equals(Object otherCoin) {
		if (otherCoin.getClass() != Coin.class) { // 주어진 객체의 클래스 정보를 받아와 Coin 클래스와 동일한지 확인
			return false;
		} else { // Coin의 class를 안전하게 Coin class로 형변환 가능
			return (this.value() == ((Coin) otherCoin).value()); // class가 동일하기 때문에 해당 객체의 값을 비교
		}
	}
}
