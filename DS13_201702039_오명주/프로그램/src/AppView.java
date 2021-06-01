import java.util.Scanner;

public class AppView {
	private static Scanner scanner = new Scanner(System.in);

	// ������
	public AppView() {}

	// ��� ���� �Լ�
	public static void output(String message) { // ������ ����ϴ� �Լ� (������ ��������ʴ´�)
		System.out.print(message); // �Է¹��� message�� ����Ѵ�
	}
	public static void outputLine(String message) { // ������ ����ϴ� �Լ� (������ �������)
		System.out.println(message); // �Է¹��� message�� ����Ѵ�
	}
	public static void outputTotalNumberOfStudents(int numberOfStudents) {
		System.out.println("��ü �л� �� : " + numberOfStudents);
	}
	public static void outputHighestScore(int aScore) {
		System.out.println("�б� �ְ� ���� : " + aScore);
	}
	public static void outputLowestScore(int aScore) {
		System.out.println("�б� ���� ���� : " + aScore);
	}
	public static void outputAverageScore(double average) {
		System.out.println("�б� ��� ���� : " + average);
	}
	public static void outputNumberOfStudentsAboveAverage(int numberOfStudents) {
		System.out.println("��� �̻��� �л� �� : " + numberOfStudents);
	}
	public static void outputNumberOfStudentsForGrade(char aGrade, int numberOfStudenst) {
		System.out.println(aGrade + " ������ ��� " + numberOfStudenst + "�� �Դϴ�.");
	}
	public static void outputStudentList(String aStudentID, int aScore) {
		System.out.println("�й� : " + aStudentID + ", ���� : " + aScore);
	}
	public static void outputStudentInfo(String aStudentID, int aScore, char aGrade) {
		System.out.println("�й� : " + aStudentID + ", ���� : " + aScore + ", ���� : " + aGrade);
	}

	// �Է� ���� �Լ�
	public static int inputInt() throws NumberFormatException {
		return Integer.parseInt(AppView.scanner.next());
	}
	public static boolean doesContinueToInputStudent() {
		AppView.output("? ������ �Է��Ϸ��� 'Y'�Ǵ� 'y'��, �����Ϸ��� �ٸ� �ƹ� Ű�� ġ�ÿ�: ");
		String line = null;
		do { // �� ���� �ƴҶ����� �Է¹޴´�
			line = AppView.scanner.nextLine();
		} while (line.equals(""));
		char answer = line.charAt(0);
		return ((answer == 'Y') || (answer == 'y'));
	}
	public static String inputStudentId() {
		while (true) {
			AppView.output("- �й��� �Է��Ͻÿ� : ");
			String studentId = scanner.next();
			return studentId;
		}
	}
	public static int inputScore() {
		while (true) {
			try {
				AppView.output("- ������ �Է��Ͻÿ� : ");
				int score = AppView.inputInt();
				return score;
			} catch (NumberFormatException e) {
				AppView.outputLine("(����) ������ �Էµ��� �ʾҽ��ϴ�");
			}
		}
	}
}
