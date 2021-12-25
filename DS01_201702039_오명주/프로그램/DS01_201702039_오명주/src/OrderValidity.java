// 프로그램의 유효성을 판단하는 enum
public enum OrderValidity {
	EndOfRun, Valid, TooSmall, TooLarge, NotOddNumber;

	public static OrderValidity validityOf(int order) {
		// 만약 입력받은 차수가 음수라면 EndOfRun으로 설정
		if (order < 0) {
			return OrderValidity.EndOfRun;
		}
		// 만약 입력받은 차수가 설정한 최소값보다 작으면 TooSmall로 설정
		else if (order < AppController.MIN_ORDER) {
			return OrderValidity.TooSmall;
		}
		// 만약 입력받은 차수가 설정한 최댓값보다 크면 TooLarge로 설정
		else if (order > AppController.MAX_ORDER) {
			return OrderValidity.TooLarge;
		}
		// 만약 입력받은 차수가 짝수라면 NotOddNumber로 설정
		else if ((order % 2) == 0) {
			return OrderValidity.NotOddNumber;
		}
		// 만약 유효하다면 Valid로 설정
		else {
			return OrderValidity.Valid;
		}
	}
}
