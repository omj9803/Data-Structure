
public enum MainMenu {
	Error,

	DoesContain, ElementAt, First, Last, OrderOf,

	AddTo, AddToFirst, AddToLast, Add,

	RemoveFrom, RemoveFirst, RemoveLast, RemoveAny,

	ReplaceAt,

	EndOfRun;

	public static final int END_OF_RUN = 99; // 프로그램 종료

	public static MainMenu value(int menuNumber) {
		if (menuNumber == END_OF_RUN) { // 99가 들어오면
			return MainMenu.EndOfRun; // 프로그램 종료
		} else if (menuNumber < DoesContain.ordinal() || menuNumber > ReplaceAt.ordinal()) {
			// ordinal() 함수는 Enum 값이 선언된 순서를 얻는다
			return MainMenu.Error; // Enum에 선언되지 않은 값이면 Error처리
		} else {
			// values() 함수는 모든 enum 값들을 그 순서대로 배열로 만들어준다
			return MainMenu.values()[menuNumber];
		}
	}
}
