import java.util.Scanner;

public class AppView {
	// 비공개 상수, 변수
	private static Scanner scanner = new Scanner(System.in); // scanner import하여 입력받음

	// 생성자 : 객체 생성할 일 없음
	private AppView() {

	}

	// 입력 관련 함수
	public static int inputOrder() {
		int inputValue = scanner.nextInt(); // scanner을 이용하여 정수를 입력받는다
		return inputValue; 					// 입력받은 정수를 return한다
	}

	// 출력 관련 함수
	// 한줄을 출력하는 함수 (한줄이 띄워지지않는다)
	public static void output(String message) {
		System.out.print(message);			// 입력받은 message를 출력한다
	}

	// 한줄을 출력하는 함수 (한줄이 띄워진다)
	public static void outputLine(String message) {
		System.out.println(message);		// 입력받은 message를 출력한다
	}

	public static void outputTitleWithOrder(int order) {
	}

	// 마방진에 줄을 나타내는 함수
	public static void outputRowNumber(int number) {
		System.out.printf("[%3d] ", number);	// number을 입력받아 주어진 형식으로 출력한다
	}

	// 마방진 수를 채우는 함수
	public static void outputCellValue(int value) {
		System.out.printf("  %3d ", value);		// value를 입력받아 마방진 주어진 위치에 수를 출력한다
	}
}
