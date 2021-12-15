import java.util.Scanner;

public class AppView {
	private static Scanner scanner = new Scanner(System.in);

	// 생성자
	public AppView() {}

	// 출력 관련 함수
	// 한줄을 출력하는 함수 (한줄이 띄워지지않는다)
	public static void output(String message) {
		System.out.print(message); // 입력받은 message를 출력한다
	}
	// 한줄을 출력하는 함수 (한줄이 띄워진다)
	public static void outputLine(String message) {
		System.out.println(message); // 입력받은 message를 출력한다
	}

	// 입력 관련 함수
	public static char inputChar() {
		String line = AppView.scanner.nextLine().trim();
		while(line.equals("")) {
			line = AppView.scanner.nextLine().trim();
		}
		return line.charAt(0);
	}
}
