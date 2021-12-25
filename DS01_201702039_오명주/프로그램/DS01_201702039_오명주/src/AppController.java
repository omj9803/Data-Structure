
public class AppController {
	// ���� ���
	public static final int MIN_ORDER = 3;	// �������� ���� �� �ִ� ���� ���� ��
	public static final int MAX_ORDER = 99;	// �������� ���� �� �ִ� ���� ū ��

	// ����� ������
	private MagicSquare _magicSqure;

	// ������
	public AppController() {
		this._magicSqure = new MagicSquare(AppController.MAX_ORDER);
	}

	// �����Լ�
	public void run() {
		AppView.outputLine("<<< ������ Ǯ�̸� �����մϴ� >>>");
		AppView.outputLine("");
		AppView.output("? ������ ������ �Է��Ͻÿ�(������ �Է��ϸ� �����մϴ�): ");

		int currentOrder = AppView.inputOrder(); // �޽����� �������� ������ �Է¹���
		OrderValidity currentValidity = OrderValidity.validityOf(currentOrder);
		while (currentValidity != OrderValidity.EndOfRun) { // ������ �����̸� ���α׷� ����
			if (currentValidity == OrderValidity.Valid) { 	// ������ ��ȿ���� �˻�
				AppView.outputTitleWithOrder(currentOrder);
				Board solvedBoard = this._magicSqure.solve(currentOrder);
				// _magicSquare ��ü���� �־��� ������ �������� Ǯ���� ��Ų��.
				// ����� ������ ���� ��´�
				this.showBoard(solvedBoard); // �������� ȭ�鿡 �����ش�
			} else {
				this.showOrderValidityErrorMessage(currentValidity);
			}
			AppView.outputLine("");
			AppView.output("? ������ ������ �Է��Ͻÿ�(������ �Է��ϸ� �����մϴ�): ");
			currentOrder = AppView.inputOrder(); // ���� �������� ���� ������ �Է¹���
			currentValidity = OrderValidity.validityOf(currentOrder);
		}
		AppView.outputLine("");
		AppView.outputLine("<<< ������ Ǯ�̸� �����մϴ� >>>");
	}

	// ������ ��ȿ���� �˷��ִ� �Լ�
	private void showOrderValidityErrorMessage(OrderValidity orderValidity) {
		// enum���� ������ ��ȿ���� �������� ��������
		switch (orderValidity) {
		case TooSmall:
			AppView.outputLine("[����]������ �ʹ� �۽��ϴ�." + AppController.MIN_ORDER + "���� ũ�ų� ���ƾ� �մϴ�.");
			break;
		case TooLarge:
			AppView.outputLine("[����]������ �ʹ� Ů�ϴ�." + AppController.MAX_ORDER + "���� �۰ų� ���ƾ� �մϴ�.");
			break;
		case NotOddNumber:
			AppView.outputLine("[����]������ ¦���Դϴ�. Ȧ���̾�� �մϴ�.");
			break;
		default:
			break;
		}
	}

	// �������� �����ִ� �Լ�
	private void showBoard(Board board) {
		CellLocation currentLoc = new CellLocation();
		this.showTitleForColumnIndexes(board.order());
		for (int row = 0; row < board.order(); row++) {
			AppView.outputRowNumber(row);				// AppView�� ������ �� ����Լ��� �̿��Ͽ� ���
			for (int col = 0; col < board.order(); col++) {
				currentLoc.setRow(row);
				currentLoc.setCol(col);
				AppView.outputCellValue(board.cellValue(currentLoc));	// AppView�� ������ �� ����Լ� �̿��Ͽ� ���
			}
			AppView.outputLine("");
		}
	}

	// �������� ��� ���� �����ִ� �Լ� [ 0]�� ����.
	private void showTitleForColumnIndexes(int order) {
		AppView.output("      ");	// ó�� ����
		for (int col = 0; col < order; col++) {
			AppView.output(String.format(" [%3d]", col));
		}
		AppView.outputLine("");
	}

}
