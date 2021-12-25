
public class AppController {
	// 공개 상수
	public static final int MIN_ORDER = 3;	// 마방진을 만들 수 있는 가장 작은 수
	public static final int MAX_ORDER = 99;	// 마방진을 만들 수 있는 가장 큰 수

	// 비공개 변수들
	private MagicSquare _magicSqure;

	// 생성자
	public AppController() {
		this._magicSqure = new MagicSquare(AppController.MAX_ORDER);
	}

	// 공개함수
	public void run() {
		AppView.outputLine("<<< 마방진 풀이를 시작합니다 >>>");
		AppView.outputLine("");
		AppView.output("? 마방진 차수를 입력하시오(음수를 입력하면 종류합니다): ");

		int currentOrder = AppView.inputOrder(); // 메시지를 내보내고 차수를 입력받음
		OrderValidity currentValidity = OrderValidity.validityOf(currentOrder);
		while (currentValidity != OrderValidity.EndOfRun) { // 차수가 음수이면 프로그램 종료
			if (currentValidity == OrderValidity.Valid) { 	// 차수가 유효한지 검사
				AppView.outputTitleWithOrder(currentOrder);
				Board solvedBoard = this._magicSqure.solve(currentOrder);
				// _magicSquare 객체에게 주어진 차수의 마방진을 풀도록 시킨다.
				// 결과로 마방진 판을 얻는다
				this.showBoard(solvedBoard); // 마방진을 화면에 보여준다
			} else {
				this.showOrderValidityErrorMessage(currentValidity);
			}
			AppView.outputLine("");
			AppView.output("? 마방진 차수를 입력하시오(음수를 입력하면 종류합니다): ");
			currentOrder = AppView.inputOrder(); // 다음 마방진을 위해 차수를 입력받음
			currentValidity = OrderValidity.validityOf(currentOrder);
		}
		AppView.outputLine("");
		AppView.outputLine("<<< 마방진 풀이를 종료합니다 >>>");
	}

	// 차수가 유효한지 알려주는 함수
	private void showOrderValidityErrorMessage(OrderValidity orderValidity) {
		// enum에서 설정한 유효성을 바탕으로 오류검출
		switch (orderValidity) {
		case TooSmall:
			AppView.outputLine("[오류]차수가 너무 작습니다." + AppController.MIN_ORDER + "보다 크거나 같아야 합니다.");
			break;
		case TooLarge:
			AppView.outputLine("[오류]차수가 너무 큽니다." + AppController.MAX_ORDER + "보다 작거나 같아야 합니다.");
			break;
		case NotOddNumber:
			AppView.outputLine("[오류]차수가 짝수입니다. 홀수이어야 합니다.");
			break;
		default:
			break;
		}
	}

	// 마방진을 보여주는 함수
	private void showBoard(Board board) {
		CellLocation currentLoc = new CellLocation();
		this.showTitleForColumnIndexes(board.order());
		for (int row = 0; row < board.order(); row++) {
			AppView.outputRowNumber(row);				// AppView의 마방진 줄 출력함수를 이용하여 출력
			for (int col = 0; col < board.order(); col++) {
				currentLoc.setRow(row);
				currentLoc.setCol(col);
				AppView.outputCellValue(board.cellValue(currentLoc));	// AppView의 마방진 수 출력함수 이용하여 출력
			}
			AppView.outputLine("");
		}
	}

	// 마방진의 행과 열을 보여주는 함수 [ 0]과 같은.
	private void showTitleForColumnIndexes(int order) {
		AppView.output("      ");	// 처음 공백
		for (int col = 0; col < order; col++) {
			AppView.output(String.format(" [%3d]", col));
		}
		AppView.outputLine("");
	}

}
