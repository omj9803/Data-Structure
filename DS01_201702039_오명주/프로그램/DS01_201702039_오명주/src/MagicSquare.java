
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

	// 기본 생성자
	public MagicSquare() {
		this.setMaxOrder(MagicSquare.DEFAULT_MAX_ORDER);
	}

	// 최대 차수를 사용자가 지정하는 생성자
	public MagicSquare(int givenMaxOrder) {
		this.setMaxOrder(givenMaxOrder);
	}

	public Board solve(int anOrder) {
		if (OrderValidity.validityOf(anOrder) != OrderValidity.Valid) {
			return null;
		} else {
			Board board = new Board(anOrder);
			// 차수와 함께 Board 객체 생성자를 call 하여 , Board 객체를 생성한다
			CellLocation currentLoc = new CellLocation(0, anOrder / 2);
			// 출발 위치 보드 의 맨 윗줄 한 가운데 를 현재의 위치로 설정
			CellLocation nextLoc = new CellLocation();
			board.setCellValue(currentLoc, 1);
			// 보드의 출발 위치 에 1 을 채운다
			int lastValue = anOrder * anOrder;
			for (int cellValue = 2; cellValue <= lastValue; cellValue++) {
				// 단계 1: <현재 위치>로부터 <다음 위치>인 "오른쪽 위" 위치를 계산한다
				// 만약 "오른쪽 위" 위치가 주어진 행과 열 을 벗어난다면 다시 조정한다
				if (currentLoc.row() - 1 < 0 && currentLoc.col() + 1 >= anOrder) {
					nextLoc.setRow(currentLoc.row() - 1 + anOrder);
					nextLoc.setCol(currentLoc.col() + 1 - anOrder);
				}
				// 만약 "오른쪽 위" 위치가 주어진 행을 벗어난다면 가장 아래쪽으로 위치를 다시 조정한다
				else if (currentLoc.row() - 1 < 0) {
					nextLoc.setRow(currentLoc.row() - 1 + anOrder);
					nextLoc.setCol(currentLoc.col() + 1);
				}
				// 만약 "오른쪽 위" 위치가 주어진 열을 벗어난다면 가장 좌측으로 위치를 다시 조정한다.
				else if (currentLoc.col() + 1 >= anOrder) {
					nextLoc.setRow(currentLoc.row() - 1);
					nextLoc.setCol(currentLoc.col() + 1 - anOrder);
				}
				// 만약 "오른쪽 위" 위치가 주어진 행과 열을 벗어나지 않는다면 좌표를 설정한다
				else {
					nextLoc.setRow(currentLoc.row() - 1);
					nextLoc.setCol(currentLoc.col() + 1);
				}
				// 단계 2: <다음 위치>가 채워져 있으면
				// <다음 위치>를 <현재 위치>의 바로 한 줄 아래 칸 위치로 수정한다
				if (!board.cellIsEmpty(nextLoc)) {
					nextLoc.setRow(currentLoc.row() + 1);
					nextLoc.setCol(currentLoc.col());
				}
				// 단계 3: <다음 위치>를 새로운 현재 위치 로 한다
				currentLoc.setRow(nextLoc.row());
				currentLoc.setCol(nextLoc.col());
				// 단계 4: 새로운 현재 위치 에 number 값을 넣는다
				board.setCellValue(currentLoc, cellValue);
			}
			return board;
		}

	}
}
