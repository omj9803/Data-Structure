import java.util.Scanner;

public class AppView {

	private static Scanner scanner = new Scanner(System.in);

	// 생성자
	public AppView() {

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

	// 학급 학생 수 출력
	public static void outputNumberOfStudents(int aNumberOfStudents) {
		System.out.println("학급 학생 수: " + aNumberOfStudents);
	}

	// 학급 최고 점수 출력
	public static void outputHighestScore(int aScore) {
		System.out.println("학급 최고 점수: " + aScore);
	}

	// 학급 최저 점수 출력
	public static void outputLowestScore(int aScore) {
		System.out.println("학급 최저 점수: " + aScore);
	}

	// 평균값 출력
	public static void outputAverageScore(double anAverageScore) {
		System.out.println("학급 평균: " + anAverageScore);
	}

	// 평균 이상인 학생수 출력
	public static void outputNumberOfStudentsAboveAverage(int aNumberOfStudents) {
		System.out.println("평균 이상인 학생 수: " + aNumberOfStudents);
	}

	// 각 학점에 대한 학생 수 출력
	public static void outputNumberOfStudentsForGrade(char aGrade, int aNumberOfStudents) {
		System.out.println(aGrade + " 학점의 학생 수는 " + aNumberOfStudents + " 입니다.");
	}

	// 학생들의 점수 출력
	public static void outputScore(int aScore) {
		System.out.println("점수: " + aScore);
	}

	// 입력관련함수
	// 성적 입력받아서 예외처리
	public static int inputInt() throws NumberFormatException {
		// 입력값이 숫자가 아니면 예외처리
		return Integer.parseInt(AppView.scanner.nextLine());
	}

	// 성적 입력받아서 예외처리
	public static int inputScore() {
		while (true) {
			try {
				AppView.output("- 점수를 입력하시오 (0..100): ");
				int score = AppView.inputInt();
				return score;
			} catch (NumberFormatException e) {
				AppView.outputLine("(오류) 정수가 입력되지 않았습니다");
			}
		}
	}

	// Y또는y가 입력되었는지 확인
	public static boolean doesContinueToInputStudent() {
		AppView.output("? 성적을 입력하려면 'Y'또는 'y'를, 종료하려면 다른 아무 키나 치시오: ");
		String line = null;
		do { // 빈 줄이 아닐때까지 입력받는다
			line = AppView.scanner.nextLine();
		} while (line.equals(""));
		char answer = line.charAt(0);
		return ((answer == 'Y') || (answer == 'y'));
	}
}
