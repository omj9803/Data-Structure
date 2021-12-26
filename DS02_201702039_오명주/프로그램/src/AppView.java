import java.util.Scanner;

public class AppView {
	// ����� ���, ����
	private static Scanner scanner = new Scanner(System.in); // scanner import�Ͽ� �Է¹���

	// ������ : ��ü ������ �� ����
	private AppView() {

	}

	// �Է� ���� �Լ�
	// ������ �޴��� ���� �Է¹޴� �Լ�
	public static int inputMenuNumber() {
		AppView.outputLine("");
		AppView.output("? �����Ϸ��� �ϴ� �޴� ��ȣ�� �����Ͻÿ� (add:1, remove:2, search:3, frequency:4, exit:9): ");
		int inputValue = scanner.nextInt(); // scanner�� �̿��Ͽ� ������ �Է¹޴´�
		return inputValue; 					// �Է¹��� ������ return�Ѵ�
	}
	
	// �ִ� Bag�� ũ�⸦ �Է¹޴� �Լ�
	public static int inputCapacityOfCoinBag() {
		AppView.outputLine("");
		AppView.output("? ���� ������ ũ��, �� ���濡 �� ������ �ִ� ������ �Է��Ͻÿ�: ");
		int inputValue = scanner.nextInt(); // scanner�� �̿��Ͽ� ������ �Է¹޴´�
		return inputValue; 					// �Է¹��� ������ return�Ѵ�
	}
	
	// ������ ���� �Է¹޴� �Լ�
	public static int inputCoinValue() {
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

}
