import java.util.Scanner;

public class AppView {

	private static Scanner scanner = new Scanner(System.in);
	private static boolean debugMode = false;

	// 생성자
	public AppView() {

	}

	public static boolean debugMode() {
		return debugMode;
	}

	public static void setDebugMode(boolean newDebugMode) {
		debugMode = newDebugMode;
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

	public static void outputDebugMessage(String aMessage) {
		if (AppView.debugMode()) {
			System.out.print(aMessage);
		}
	}

	public static void outputLineDebugMessage(String aMessage) {
		if(AppView.debugMode()) {
			System.out.println(aMessage);
		}
	}

	// 입력 관련 함수
	public static String inputLine() {
		String line = AppView.scanner.nextLine().trim();
		while (line.equals("")) { // 문자들이 동일 순서라면 true를 얻음
			line = AppView.scanner.nextLine().trim(); // 한 줄의 앞뒤 공백은 제거
		}
		return line; // 입력받은 String을 반환
	}
}
