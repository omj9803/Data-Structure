
public class MagicSquare {
	private static final int DEFAULT_MAX_ORDER = 99;
	private int _maxOrder;

	// Getters/Setters
	public int maxOrder() {
		return this._maxOrder;
	}

	private void setMaxOrder(int newMaxOrder) {
		this._maxOrder = newMaxOrder;
	}

	// �⺻ ������
	public MagicSquare() {
		this.setMaxOrder(MagicSquare.DEFAULT_MAX_ORDER);
	}

	// �ִ� ������ ����ڰ� �����ϴ� ������
	public MagicSquare(int givenMaxOrder) {
		this.setMaxOrder(givenMaxOrder);
	}

	public Board solve(int anOrder) {
		if (OrderValidity.validityOf(anOrder) != OrderValidity.Valid) {
			return null;
		} else {
			Board board = new Board(anOrder);
			// ������ �Բ� Board ��ü �����ڸ� call �Ͽ� , Board ��ü�� �����Ѵ�
			CellLocation currentLoc = new CellLocation(0, anOrder / 2);
			// ��� ��ġ ���� �� �� ���� �� ��� �� ������ ��ġ�� ����
			CellLocation nextLoc = new CellLocation();
			board.setCellValue(currentLoc, 1);
			// ������ ��� ��ġ �� 1 �� ä���
			int lastValue = anOrder * anOrder;
			for (int cellValue = 2; cellValue <= lastValue; cellValue++) {
				// �ܰ� 1: <���� ��ġ>�κ��� <���� ��ġ>�� "������ ��" ��ġ�� ����Ѵ�
				// ���� "������ ��" ��ġ�� �־��� ��� �� �� ����ٸ� �ٽ� �����Ѵ�
				if (currentLoc.row() - 1 < 0 && currentLoc.col() + 1 >= anOrder) {
					nextLoc.setRow(currentLoc.row() - 1 + anOrder);
					nextLoc.setCol(currentLoc.col() + 1 - anOrder);
				}
				// ���� "������ ��" ��ġ�� �־��� ���� ����ٸ� ���� �Ʒ������� ��ġ�� �ٽ� �����Ѵ�
				else if (currentLoc.row() - 1 < 0) {
					nextLoc.setRow(currentLoc.row() - 1 + anOrder);
					nextLoc.setCol(currentLoc.col() + 1);
				}
				// ���� "������ ��" ��ġ�� �־��� ���� ����ٸ� ���� �������� ��ġ�� �ٽ� �����Ѵ�.
				else if (currentLoc.col() + 1 >= anOrder) {
					nextLoc.setRow(currentLoc.row() - 1);
					nextLoc.setCol(currentLoc.col() + 1 - anOrder);
				}
				// ���� "������ ��" ��ġ�� �־��� ��� ���� ����� �ʴ´ٸ� ��ǥ�� �����Ѵ�
				else {
					nextLoc.setRow(currentLoc.row() - 1);
					nextLoc.setCol(currentLoc.col() + 1);
				}
				// �ܰ� 2: <���� ��ġ>�� ä���� ������
				// <���� ��ġ>�� <���� ��ġ>�� �ٷ� �� �� �Ʒ� ĭ ��ġ�� �����Ѵ�
				if (!board.cellIsEmpty(nextLoc)) {
					nextLoc.setRow(currentLoc.row() + 1);
					nextLoc.setCol(currentLoc.col());
				}
				// �ܰ� 3: <���� ��ġ>�� ���ο� ���� ��ġ �� �Ѵ�
				currentLoc.setRow(nextLoc.row());
				currentLoc.setCol(nextLoc.col());
				// �ܰ� 4: ���ο� ���� ��ġ �� number ���� �ִ´�
				board.setCellValue(currentLoc, cellValue);
			}
			return board;
		}

	}
}
