import java.util.Scanner;

public class AppView {
	private static Scanner scanner = new Scanner(System.in);

	// 생성자
	public AppView() {}

	// 출력 관련 함수
	public static void output(String message) { // 한줄을 출력하는 함수 (한줄이 띄워지지않는다)
		System.out.print(message); // 입력받은 message를 출력한다
	}
	public static void outputLine(String message) { // 한줄을 출력하는 함수 (한줄이 띄워진다)
		System.out.println(message); // 입력받은 message를 출력한다
	}
	public static void outputTotalNumberOfStudents(int numberOfStudents) {
		System.out.println("전체 학생 수 : " + numberOfStudents);
	}
	public static void outputHighestScore(int aScore) {
		System.out.println("학급 최고 점수 : " + aScore);
	}
	public static void outputLowestScore(int aScore) {
		System.out.println("학급 최저 점수 : " + aScore);
	}
	public static void outputAverageScore(double average) {
		System.out.println("학급 평균 점수 : " + average);
	}
	public static void outputNumberOfStudentsAboveAverage(int numberOfStudents) {
		System.out.println("평균 이상인 학생 수 : " + numberOfStudents);
	}
	public static void outputNumberOfStudentsForGrade(char aGrade, int numberOfStudenst) {
		System.out.println(aGrade + " 학점은 모두 " + numberOfStudenst + "명 입니다.");
	}
	public static void outputStudentList(String aStudentID, int aScore) {
		System.out.println("학번 : " + aStudentID + ", 점수 : " + aScore);
	}
	public static void outputStudentInfo(String aStudentID, int aScore, char aGrade) {
		System.out.println("학번 : " + aStudentID + ", 점수 : " + aScore + ", 학점 : " + aGrade);
	}

	// 입력 관련 함수
	public static int inputInt() throws NumberFormatException {
		return Integer.parseInt(AppView.scanner.next());
	}
	public static boolean doesContinueToInputStudent() {
		AppView.output("? 성적을 입력하려면 'Y'또는 'y'를, 종료하려면 다른 아무 키나 치시오: ");
		String line = null;
		do { // 빈 줄이 아닐때까지 입력받는다
			line = AppView.scanner.nextLine();
		} while (line.equals(""));
		char answer = line.charAt(0);
		return ((answer == 'Y') || (answer == 'y'));
	}
	public static String inputStudentId() {
		while (true) {
			AppView.output("- 학번을 입력하시오 : ");
			String studentId = scanner.next();
			return studentId;
		}
	}
	public static int inputScore() {
		while (true) {
			try {
				AppView.output("- 점수를 입력하시오 : ");
				int score = AppView.inputInt();
				return score;
			} catch (NumberFormatException e) {
				AppView.outputLine("(오류) 정수가 입력되지 않았습니다");
			}
		}
	}
}
