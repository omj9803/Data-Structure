import java.util.Scanner;

public class AppView {
	// ����� ���, ����
	private static Scanner scanner = new Scanner(System.in); // scanner import�Ͽ� �Է¹���

	// ������ : ��ü ������ �� ����
	private AppView() {

	}

	// �Է� ���� �Լ�
	public static int inputOrder() {
		int inputValue = scanner.nextInt(); // scanner�� �̿��Ͽ� ������ �Է¹޴´�
		return inputValue; 					// �Է¹��� ������ return�Ѵ�
	}

	// ��� ���� �Լ�
	// ������ ����ϴ� �Լ� (������ ��������ʴ´�)
	public static void output(String message) {
		System.out.print(message);			// �Է¹��� message�� ����Ѵ�
	}

	// ������ ����ϴ� �Լ� (������ �������)
	public static void outputLine(String message) {
		System.out.println(message);		// �Է¹��� message�� ����Ѵ�
	}

	public static void outputTitleWithOrder(int order) {
	}

	// �������� ���� ��Ÿ���� �Լ�
	public static void outputRowNumber(int number) {
		System.out.printf("[%3d] ", number);	// number�� �Է¹޾� �־��� �������� ����Ѵ�
	}

	// ������ ���� ä��� �Լ�
	public static void outputCellValue(int value) {
		System.out.printf("  %3d ", value);		// value�� �Է¹޾� ������ �־��� ��ġ�� ���� ����Ѵ�
	}
}
