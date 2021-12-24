
public enum MainMenu {
	Error,

	DoesContain, ElementAt, First, Last, OrderOf,

	AddTo, AddToFirst, AddToLast, Add,

	RemoveFrom, RemoveFirst, RemoveLast, RemoveAny,

	ReplaceAt,

	EndOfRun;

	public static final int END_OF_RUN = 99; // ���α׷� ����

	public static MainMenu value(int menuNumber) {
		if (menuNumber == END_OF_RUN) { // 99�� ������
			return MainMenu.EndOfRun; // ���α׷� ����
		} else if (menuNumber < DoesContain.ordinal() || menuNumber > ReplaceAt.ordinal()) {
			// ordinal() �Լ��� Enum ���� ����� ������ ��´�
			return MainMenu.Error; // Enum�� ������� ���� ���̸� Erroró��
		} else {
			// values() �Լ��� ��� enum ������ �� ������� �迭�� ������ش�
			return MainMenu.values()[menuNumber];
		}
	}
}
