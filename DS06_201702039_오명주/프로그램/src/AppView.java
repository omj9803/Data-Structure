
public class AppView {

	// ��� ���� �Լ�
	// ������ ����ϴ� �Լ� (������ �������)
	public static void outputLine(String aMessage) {
		System.out.println(aMessage);
	}

	// ������ ����ϴ� �Լ� (������ ��������ʴ´�)
	public void output(String aMessage) {
		System.out.print(aMessage);
	}

	// ���˿� �°� ����ϴ� �Լ�
	public static void outputResults(int size, long durationForAdd, long durationForMax) {
		System.out.println("[ũ�� : " + String.format("%5d", size) + "] " + "���� : " + String.format("%8d", durationForAdd)
				+ " , �ִ밪 : " + String.format("%8d ", durationForMax));
	}
}
