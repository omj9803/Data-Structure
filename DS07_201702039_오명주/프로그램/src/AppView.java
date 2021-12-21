import java.util.Scanner;

public class AppView {

	private static Scanner scanner = new Scanner(System.in);

	// ������
	public AppView() {

	}

	// ��� ���� �Լ�
	// ������ ����ϴ� �Լ� (������ ��������ʴ´�)
	public static void output(String message) {
		System.out.print(message); // �Է¹��� message�� ����Ѵ�
	}

	// ������ ����ϴ� �Լ� (������ �������)
	public static void outputLine(String message) {
		System.out.println(message); // �Է¹��� message�� ����Ѵ�
	}

	// ������ �ƴ� ����� ���� ó���� ������ �� : exception throws
	public static int inputInteger() throws NumberFormatException {
		return Integer.parseInt(AppView.scanner.next());
	}

	// �б� �л� �� ���
	public static void outputNumberOfStudents(int aNumberOfStudents) {
		System.out.println("�б� �л� ��: " + aNumberOfStudents);
	}

	// �б� �ְ� ���� ���
	public static void outputHighestScore(int aScore) {
		System.out.println("�б� �ְ� ����: " + aScore);
	}

	// �б� ���� ���� ���
	public static void outputLowestScore(int aScore) {
		System.out.println("�б� ���� ����: " + aScore);
	}

	// ��հ� ���
	public static void outputAverageScore(double anAverageScore) {
		System.out.println("�б� ���: " + anAverageScore);
	}

	// ��� �̻��� �л��� ���
	public static void outputNumberOfStudentsAboveAverage(int aNumberOfStudents) {
		System.out.println("��� �̻��� �л� ��: " + aNumberOfStudents);
	}

	// �� ������ ���� �л� �� ���
	public static void outputNumberOfStudentsForGrade(char aGrade, int aNumberOfStudents) {
		System.out.println(aGrade + " ������ �л� ���� " + aNumberOfStudents + " �Դϴ�.");
	}

	// �л����� ���� ���
	public static void outputScore(int aScore) {
		System.out.println("����: " + aScore);
	}

	// �Է°����Լ�
	// ���� �Է¹޾Ƽ� ����ó��
	public static int inputInt() throws NumberFormatException {
		// �Է°��� ���ڰ� �ƴϸ� ����ó��
		return Integer.parseInt(AppView.scanner.nextLine());
	}

	// ���� �Է¹޾Ƽ� ����ó��
	public static int inputScore() {
		while (true) {
			try {
				AppView.output("- ������ �Է��Ͻÿ� (0..100): ");
				int score = AppView.inputInt();
				return score;
			} catch (NumberFormatException e) {
				AppView.outputLine("(����) ������ �Էµ��� �ʾҽ��ϴ�");
			}
		}
	}

	// Y�Ǵ�y�� �ԷµǾ����� Ȯ��
	public static boolean doesContinueToInputStudent() {
		AppView.output("? ������ �Է��Ϸ��� 'Y'�Ǵ� 'y'��, �����Ϸ��� �ٸ� �ƹ� Ű�� ġ�ÿ�: ");
		String line = null;
		do { // �� ���� �ƴҶ����� �Է¹޴´�
			line = AppView.scanner.nextLine();
		} while (line.equals(""));
		char answer = line.charAt(0);
		return ((answer == 'Y') || (answer == 'y'));
	}
}
