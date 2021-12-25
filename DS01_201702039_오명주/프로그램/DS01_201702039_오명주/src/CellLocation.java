
public class CellLocation {

	// 상수
	private static final int UNDEFINED_INDEX = -1;

	// private instance variables
	private int _row;
	private int _col;

	// 기본 생성자 : Cell 좌표가 주어지지 않는다
	public CellLocation() {
		// Cell좌표가 주어지지 않으면 (-1,-1)로 설정하기로 한다
		this.setRow(UNDEFINED_INDEX);
		this.setCol(UNDEFINED_INDEX);
	}

	// Cell 좌표가 주어지는 생성자
	public CellLocation(int givenRow, int givenCol) {
		this.setRow(givenRow);
		this.setCol(givenCol);
	}

	// Getter / Setter
	// 위치의 row 좌표를 입력받아 설정한다
	public void setRow(int newRow) {
		this._row = newRow;
	}
	// 위치의 row 좌표를 반환한다
	public int row() {
		return this._row;
	}
	// 위치의 col 좌표를 입력받아 설정한다
	public void setCol(int newCol) {
		this._col = newCol;
	}
	// 위치의 col 좌표를 반환한다
	public int col() {
		return this._col;
	}
}
