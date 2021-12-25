// ���α׷��� ��ȿ���� �Ǵ��ϴ� enum
public enum OrderValidity {
	EndOfRun, Valid, TooSmall, TooLarge, NotOddNumber;

	public static OrderValidity validityOf(int order) {
		// ���� �Է¹��� ������ ������� EndOfRun���� ����
		if (order < 0) {
			return OrderValidity.EndOfRun;
		}
		// ���� �Է¹��� ������ ������ �ּҰ����� ������ TooSmall�� ����
		else if (order < AppController.MIN_ORDER) {
			return OrderValidity.TooSmall;
		}
		// ���� �Է¹��� ������ ������ �ִ񰪺��� ũ�� TooLarge�� ����
		else if (order > AppController.MAX_ORDER) {
			return OrderValidity.TooLarge;
		}
		// ���� �Է¹��� ������ ¦����� NotOddNumber�� ����
		else if ((order % 2) == 0) {
			return OrderValidity.NotOddNumber;
		}
		// ���� ��ȿ�ϴٸ� Valid�� ����
		else {
			return OrderValidity.Valid;
		}
	}
}
