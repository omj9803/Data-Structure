
public class CellLocation {

	// ���
	private static final int UNDEFINED_INDEX = -1;

	// private instance variables
	private int _row;
	private int _col;

	// �⺻ ������ : Cell ��ǥ�� �־����� �ʴ´�
	public CellLocation() {
		// Cell��ǥ�� �־����� ������ (-1,-1)�� �����ϱ�� �Ѵ�
		this.setRow(UNDEFINED_INDEX);
		this.setCol(UNDEFINED_INDEX);
	}

	// Cell ��ǥ�� �־����� ������
	public CellLocation(int givenRow, int givenCol) {
		this.setRow(givenRow);
		this.setCol(givenCol);
	}

	// Getter / Setter
	// ��ġ�� row ��ǥ�� �Է¹޾� �����Ѵ�
	public void setRow(int newRow) {
		this._row = newRow;
	}
	// ��ġ�� row ��ǥ�� ��ȯ�Ѵ�
	public int row() {
		return this._row;
	}
	// ��ġ�� col ��ǥ�� �Է¹޾� �����Ѵ�
	public void setCol(int newCol) {
		this._col = newCol;
	}
	// ��ġ�� col ��ǥ�� ��ȯ�Ѵ�
	public int col() {
		return this._col;
	}
}
