import java.util.Scanner;

public class AppView {

	private static Scanner scanner = new Scanner(System.in);

	// 생성자는 private 이어야함 - 생성자 불필요
	private AppView() {

	}

	// public OUTPUT methods
	public static void outputDebugMessage(String message) {

	}

	// 출력 관련 함수
	// 한줄을 출력하는 함수 (한줄이 띄워지지않는다)
	public static void output(String message) {
		System.out.print(message); // 입력받은 message를 출력한다
	}

	// 한줄을 출력하는 함수 (한줄이 띄워진다)
	public static void outputLine(String message) {
		System.out.println(message); // 입력받은 message를 출력한다
	}

	// 정수가 아닌 경우의 에러 처리를 보완할 것 : exception throws
	public static int inputInteger() throws NumberFormatException {
		return Integer.parseInt(AppView.scanner.next());
	}
}
