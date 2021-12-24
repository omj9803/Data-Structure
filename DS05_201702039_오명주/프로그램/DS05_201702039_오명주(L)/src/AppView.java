import java.util.Scanner;

public class AppView {

	private static Scanner scanner = new Scanner(System.in);

	// �����ڴ� private �̾���� - ������ ���ʿ�
	private AppView() {

	}

	// public OUTPUT methods
	public static void outputDebugMessage(String message) {

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
}
