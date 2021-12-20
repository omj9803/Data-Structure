
public class AppController {

	// 상수
	private static final int STACK_CAPACITY = 5;

	// 비공개 변수들
	private ArrayList<Character> _stack;
	private int _inputChars; // 입력된 문자의 개수
	private int _pushedChars; // 삽입된 문자의 개수
	private int _ignoredChars; // 무시된 문자의 개수

	// Getters/Setters
	private ArrayList<Character> stack() {
		return this._stack;
	}

	private void setStack(ArrayList<Character> newStack) {
		this._stack = newStack;
	}

	private int inputChars() {
		return this._inputChars;
	}

	private void setInputChars(int newInputChars) {
		this._inputChars = newInputChars;
	}

	private int pushedChars() {
		return this._pushedChars;
	}

	private void setPushedChars(int newPushedChars) {
		this._pushedChars = newPushedChars;
	}

	private int ignoredChars() {
		return this._ignoredChars;
	}

	private void setIgnoredChars(int newIgnoredChars) {
		this._ignoredChars = newIgnoredChars;
	}

	// 생성자
	public AppController() {
		this.setStack(new ArrayList<Character>(AppController.STACK_CAPACITY));
		this.setInputChars(0);
		this.setPushedChars(0);
		this.setIgnoredChars(0);
	}

	// 비공개 함수
	// 횟수 계산
	private void countInputChar() { // 입력된 문자 + 1 count
		this.setInputChars(this.inputChars() + 1);
	}

	private void countIgnoredChar() { // 무시된 문자 + 1 count
		this.setIgnoredChars(this.ignoredChars() + 1);
	}

	private void countPushedChar() { // 삽입된 문자 + 1 count
		this.setPushedChars(this.pushedChars() + 1);
	}

	// 스택 수행 관련
	private void pushToStack(char aCharForPush) { // 스택 Top에 원소를 삽입하는 함수
		if (this.stack().isFull()) { // 스택이 가득 차있다면
			AppView.outputLine("(오류) 스택이 꽉 차서, 더 이상 넣을 수 없습니다."); // 오류 출력문 출력
		} else {
			Character charObjectForAdd = Character.valueOf(aCharForPush); // 문자를 문자 객체로 변환
			if (this.stack().push(charObjectForAdd)) { // 스택에 push
				AppView.outputLine("[Push] 삽입된 원소는 '" + aCharForPush + "' 입니다 .");
			} else {
				AppView.outputLine("(오류) 스택에 넣는 동안에 오류가 발생하였습니다.");
			}
		}

	}

	private void popOne() { // 스택 Top의 원소를 하나 삭제하여 return하는 함수
		if (this.stack().isEmpty()) { // 스택이 비어있다면
			AppView.outputLine("[Pop.Empty] 스택에 삭제할 원소가 없습니다."); // 원소가 없다는 출력문 출력
		} else {
			Character poppedChar = this.stack().pop(); // pop한 결과를 poppedChar에 저장
			if (poppedChar == null) { // poppedChar에 대한 null 검사
				AppView.outputLine("(오류) 스택에서 삭제하는 동안에 오류가 발생하였습니다.");
			} else {
				AppView.outputLine("[Pop] 삭제된 원소는 ' " + poppedChar + " ' 입니다.");
			}
		}
	}

	private void popN(int numberOfCharsToBePopped) { // 스택 Top의 원소를 numberOfCharsToBePopped만큼 삭제하여 return하는 함수
		if (numberOfCharsToBePopped == 0) { // 만약 입력받은 수가 0이라면
			AppView.outputLine("[Pops] 삭제할 원소의 개수가 0 개 입니다."); // 삭제횟수가 0
		} else {
			int count = 0;
			while (count < numberOfCharsToBePopped && (!this.stack().isEmpty())) { // 입력받은 수 만큼 반복
				Character poppedChar = this.stack().pop(); // 반복하여 pop을 수행
				if (poppedChar == null) {
					AppView.outputLine("(오류) 스택에서 삭제하는 동안에 오류가 발생하였습니다.");

				} else {
					AppView.outputLine("[Pops] 삭제된 원소는 ' " + poppedChar + " '입니다.");
				}
				count++;
			}
			if (count < numberOfCharsToBePopped) { // 스택에 있는 원소의 수보다 입력받은 숫자가 더 큰 경우
				AppView.outputLine("[Pops.Empty] 스택에 더 이상 삭제할 원소가 없습니다."); // 오류문 출력
			}
		}
	}

	private void quitStackProcessing() { // 스택 비우고 종료하는 함수
		AppView.outputLine("");
		AppView.outputLine("<스택을 비우고 사용을 종료합니다>");
		this.showAllFromBottom(); // 스택 Bottom-Top 순서로 출력
		this.popN(this.stack().size()); // 모든 원소 pop 삭제
	}

	// 출력 관련
	private void showAllFromBottom() { // 스택의 모든 원소를 Bottom 부터 Top까지 출력한다
		AppView.output("[Stack] <Bottom> ");
		for (int order = 0; order < this.stack().size(); order++) {
			AppView.output(this.stack().elementAt(order).toString() + " ");
		}
		AppView.outputLine(" <Top>");
	}

	private void showAllFromTop() { // 스택의 모든 원소를 Top 부터 Bottom까지 출력한다
		AppView.output("[Stack] <Top> ");
		for (int order = this.stack().size() - 1; order >= 0; order--) {
			AppView.output(this.stack().elementAt(order).toString() + " ");
		}
		AppView.outputLine(" <Bottom>");
	}

	private void showTopElement() {
		if (this.stack().isEmpty()) {
			AppView.outputLine("[Top.Empty] 스택이 비어서 Top 원소가 존재하지 않습니다.");
		} else {
			AppView.outputLine("[Top] 스택의 Top 원소는 '" + this.stack().peek() + "' 입니다.");
		}

	}

	private void showStackSize() { // 스택 사이즈를 출력하는 함수
		AppView.outputLine("[Size] 스택에는 현재 " + this.stack().size() + " 개의 원소가 있습니다.");
	}

	private void showStatistics() { // 통계를 출력하는 함수
		AppView.outputLine("");
		AppView.outputLine("<스택 사용 통계>");
		AppView.outputLine("- 입력된 문자는 " + this.inputChars() + " 개 입니다.");
		AppView.outputLine("- 정상 처리된 문자는 " + (this.inputChars() - this.ignoredChars()) + " 개 입니다.");
		AppView.outputLine("- 무시된 문자는 " + this.ignoredChars() + " 개 입니다.");
		AppView.outputLine("- 삽입된 문자는 " + this.pushedChars() + " 개 입니다.");
	}

	// 입력 관련
	private char inputChar() {
		AppView.output("? 문자를 입력하시오: ");
		return AppView.inputChar();
	}

	public void run() {
		AppView.outputLine("<<< 스택 기능 확인 프로그램을 시작합니다 >>>");
		AppView.outputLine("");

		char input = this.inputChar(); // char 문자 입력받음
		while (input != '!') { // '!'를 입력받으면 종료
			this.countInputChar(); // 입력된문자 + 1
			if (Character.isAlphabetic(input)) { // 알파벳인지 검사
				this.pushToStack(input);
				this.countPushedChar(); // 삽입된문자 + 1
			} else if ((Character.isDigit(input))) { // 숫자 문자인지 검사
				this.popN(Character.getNumericValue(input)); // 숫자 문자를 정수값으로 변환
			} else if (input == '-') { // '-'문자 입력받으면 popOne 실행
				this.popOne();
			} else if (input == '#') { // '#'문자 입력받으면 스택크기 출력
				this.showStackSize();
			} else if (input == '/') { // '/'문자 입력받으면 Bottom-Top까지의 스택 출력
				this.showAllFromBottom();
			} else if (input == '\\') { // '\'문자 입력받으면 Top-Bottom까지의 스택 출력
				this.showAllFromTop();
			} else if (input == '^') { // '^'문자 입력받으면 Top원소 peek하여 출력
				this.showTopElement();
			} else {
				AppView.outputLine("[Ignore] 의미 없은 문자가 입력되었습니다."); // 오류처리
				this.countIgnoredChar(); // 무시된문자 + 1
			}
			input = this.inputChar(); // 반복해서 실행
		}
		this.quitStackProcessing(); // 스택 비우는 함수
		this.showStatistics();
		AppView.outputLine("");
		AppView.outputLine("<<< 스택 기능 확인 프로그램을 종료합니다 >>> ");
	}
}
