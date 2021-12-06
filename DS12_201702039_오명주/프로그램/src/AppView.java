import java.util.Scanner;

public class AppView {
	private static Scanner scanner = new Scanner(System.in);

	// ������
	public AppView() {}

	// ��� ���� �Լ�
	// ������ ����ϴ� �Լ� (������ ��������ʴ´�)
	public static void output(String message) {
		System.out.print(message); // �Է¹��� message�� ����Ѵ�
	}
	// ������ ����ϴ� �Լ� (������ �������)
	public static void outputLine(String message) {
		System.out.println(message); // �Է¹��� message�� ����Ѵ�
	}

	// �Է� ���� �Լ�
	public static char inputChar() {
		String line = AppView.scanner.nextLine().trim();
		while(line.equals("")) {
			line = AppView.scanner.nextLine().trim();
		}
		return line.charAt(0);
	}
}
