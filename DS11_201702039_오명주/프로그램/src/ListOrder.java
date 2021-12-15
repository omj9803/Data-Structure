
public enum ListOrder {
	// 이번 실험에서는 , 3 가지 유형의 데이터 리스트를 구분하고 있다
	// 이 구분을 표현할 목적으로 Enum "ListOrder" 를 사용한다
	Ascending, // 오름차순 데이터 리스트의 유형을 표현
	Descending, // 내림차순 데이터 리스트의 유형을 표현
	Random; // 무작위 데이터 리스트의 유형을 표현
	// Enum은 Class 의 특수한 경우로 간주된다
	// 따라서 아래와 같이 상수를 선언할 수 있다

	public static final String[] ORDER_NAMES = { "오름차순 ", "내림차순 ", "무작위 " };

	// 또한 아래와 같이 member method 역시 선언할 수 있다
	// 즉 Enum 안에 선언된 값들은 Enum 의 객체 인스턴스로 인식된다
	// Ascending, Descending, Random 각각이 객체 인스턴스이다
	public String orderName() {
		return ListOrder.ORDER_NAMES[this.ordinal()];
		// "ordinal()" 은 모든 Enum 에 미리 정의되어 있는 함수로
		// 선언된 값의 Enum 안에서의 순서를 정수로 얻을 수 있다
		// 즉, Ascending.ordinal()은 0, Descending.ordinal() 은 1,
		// Random.ordinal()은 2 를 얻는다
	}

}
