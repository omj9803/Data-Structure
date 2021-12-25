
public class Board {

	// ���
	private static int EMPTY_CELL = -1;

	// private instance variables
	private int _order;
	private int[][] _cells;

	// Getters / Setters
	public int order() { // ������ ������ ��´�
		return this._order;
	}

	private void setOrder(int newOrder) { // ������ ������ �־��� ������ �����Ѵ�
		this._order = newOrder;
	}

	private int[][] cells() { // _cells ��ü�� ��´�
		return this._cells;
	}

	private void setCells(int[][] newCells) { // _cells�� �־�ģ ��ü�� �����Ѵ�
		this._cells = newCells;
	}

	// �⺻ ������
	public Board(int givenOrder) {
		this.setOrder(givenOrder);
		this.setCells(new int[givenOrder][givenOrder]);
		for(int row = 0;row<givenOrder;row++) {
			for(int col = 0;col<givenOrder;col++) {
				this.setCellValue(row, col, Board.EMPTY_CELL);
			}
		}
	}

	// ���� �Լ� (public methods)
	public boolean cellIsEmpty(CellLocation location) { // "Cell is empty?"
		// �־��� ��ġ�� cell�� ��� �ִ��� ���θ� �˷��ش�
		// ��� ������ true, �ƴϸ� false�� ��´�
		return (this.cellValue(location) == EMPTY_CELL);

	}

	public int cellValue(CellLocation location) {
		// �־��� location�� cell���� ��´�
		return this.cells()[location.row()][location.col()];
	}

	public void setCellValue(CellLocation location, int newCellValue) {
		// �־��� location�� cell�� �־��� value�� �ִ´�
		this.cells()[location.row()][location.col()] = newCellValue;
	}

	private void setCellValue(int row, int col, int newCellValue) {
		// �� method�� class ���ο����� ����Ѵ�
		// �־��� ��ġ (row, col)�� cell�� �־��� �� value�� �ִ´�
		this.cells()[row][col] = newCellValue;
	}

}
