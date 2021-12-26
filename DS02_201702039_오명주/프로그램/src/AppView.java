import java.util.Scanner;

public class AppView {
	// 비공개 상수, 변수
	private static Scanner scanner = new Scanner(System.in); // scanner import하여 입력받음

	// 생성자 : 객체 생성할 일 없음
	private AppView() {

	}

	// 입력 관련 함수
	// 수행할 메뉴의 값을 입력받는 함수
	public static int inputMenuNumber() {
		AppView.outputLine("");
		AppView.output("? 수행하려고 하는 메뉴 번호를 선택하시오 (add:1, remove:2, search:3, frequency:4, exit:9): ");
		int inputValue = scanner.nextInt(); // scanner을 이용하여 정수를 입력받는다
		return inputValue; 					// 입력받은 정수를 return한다
	}
	
	// 최대 Bag의 크기를 입력받는 함수
	public static int inputCapacityOfCoinBag() {
		AppView.outputLine("");
		AppView.output("? 동전 가방의 크기, 즉 가방에 들어갈 동전의 최대 개수를 입력하시오: ");
		int inputValue = scanner.nextInt(); // scanner을 이용하여 정수를 입력받는다
		return inputValue; 					// 입력받은 정수를 return한다
	}
	
	// 동전의 값을 입력받는 함수
	public static int inputCoinValue() {
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

}
