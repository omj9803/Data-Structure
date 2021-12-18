import java.util.Scanner;

public class AppView {

	private static Scanner scanner = new Scanner(System.in);
	private static boolean debugMode = false;

	// ������
	public AppView() {

	}

	public static boolean debugMode() {
		return debugMode;
	}

	public static void setDebugMode(boolean newDebugMode) {
		debugMode = newDebugMode;
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

	// �Է� ���� �Լ�
	public static String inputLine() {
		String line = AppView.scanner.nextLine().trim();
		while (line.equals("")) { // ���ڵ��� ���� ������� true�� ����
			line = AppView.scanner.nextLine().trim(); // �� ���� �յ� ������ ����
		}
		return line; // �Է¹��� String�� ��ȯ
	}
}
