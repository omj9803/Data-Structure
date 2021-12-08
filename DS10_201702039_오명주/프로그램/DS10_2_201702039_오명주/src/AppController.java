
public class AppController {
//	private static final int QUEUE_CAPACITY = 10;
	private Queue<Character> _queue;
	private int _inputChars; // 입력된 문자의 개수
	private int _addedChars; // 삽입된 문자의 개수
	private int _ignoredChars; // 무시된 문자의 개수

	// Getters/Setters
	private Queue<Character> queue() {
		return this._queue;
	}

	private void setQueue(Queue<Character> newQueue) {
		this._queue = newQueue;
	}

	private int inputChars() {
		return this._inputChars;
	}

	private void setInputChars(int newInputChars) {
		this._inputChars = newInputChars;
	}

	private int addedChars() {
		return this._addedChars;
	}

	private void setAddedChars(int newAddedChars) {
		this._addedChars = newAddedChars;
	}

	private int ignoredChars() {
		return this._ignoredChars;
	}

	private void setIgnoredChars(int newIgnoredChars) {
		this._ignoredChars = newIgnoredChars;
	}

	// 생성자
	public AppController() {
		this.setQueue(new CircularlyLinkedQueue<Character>());
		this.setInputChars(0);
		this.setAddedChars(0);
		this.setIgnoredChars(0);
	}

	private void countInputChars() { // 입력문자 횟수계산
		this.setInputChars(this.inputChars() + 1);
	}

	private void countIgnoredChars() { // 무시문자 횟수계산
		this.setIgnoredChars(this.ignoredChars() + 1);
	}

	private void countAddedChar() { // 추가문자 횟수계산
		this.setAddedChars(this.addedChars() + 1);
	}

	// 큐의 모든 원소를 Front 부터 Rear까지 출력한다
	private void showAllFromFront() {
		AppView.output("[Queue] <Front> ");
		Iterator<Character> queueIterator = this.queue().iterator(); // queue()의 반복자 생성
		while (queueIterator.hasNext()) { // queueIterator의 next가 있는 동안 반복
			Character element = queueIterator.next(); // 순서대로 element에 저장
			AppView.output(element.toString() + " "); // element의 문자열 출력
		}
		AppView.outputLine("<Rear> ");
	}

	// 큐의 모든 원소를 Rear 부터 Front까지 출력한다
	private void showAllFromRear() {
		AppView.output("[Queue] <Rear> ");
		for (int order = this.queue().size() - 1; order >= 0; order--) {
			AppView.output(this.queue().elementAt(order).toString() + " ");
		}
		AppView.outputLine("<Front> ");
	}

	private void showFrontElement() { // Front 큐를 출력
		if (this.queue().isEmpty()) {
			AppView.outputLine("[Front.Empty] 큐가 비어서 맨 앞 원소가 존재하지 않습니다.");
		} else {
			AppView.outputLine("[Front] 큐의 맨 앞 원소는 " + this.queue().front().toString() + " 입니다.");
		}
	}

	private void showRearElement() { // Rear 큐를 출력
		if (this.queue().isEmpty()) {
			AppView.outputLine("[Rear.Empty] 큐가 비어서 맨 뒤 원소가 존재하지 않습니다.");
		} else {
			AppView.outputLine("[Rear] 큐의 맨 뒤 원소는 " + this.queue().rear().toString() + " 입니다.");
		}
	}

	private void showQueueSize() { // Queue 사이즈 출력
		AppView.outputLine("[Size] 큐의 원소의 개수는  " + this.queue().size() + "개 입니다.");
	}

	private void addToQueue(Character anElement) {
		if (!this.queue().enQueue(anElement)) { // 큐가 가득 찬 경우
			AppView.outputLine("[EnQ.Full] 큐가 꽉 차서 더 이상 넣을 수가 없습니다.");
		} else { // enQueue가 성공한 경우
			AppView.outputLine("[EnQ] 삽입된 원소는 '" + anElement.charValue() + "'입니다.");
			this.countAddedChar();
		}
	}

	private void removeOne() {
		if (this.queue().isEmpty()) { // 큐가 비어있는 경우
			AppView.outputLine("[DeQ.Empty] 큐에 삭제할 원소가 없습니다.");
		} else {
			Character removedChar = this.queue().deQueue();
			if (removedChar == null) { // deQueue가 실패한 경우
				AppView.outputLine("(오류) 큐에서 삭제하는 동안에 오류가 발생하였습니다. ");
			} else { // deQueue가 성공한 경우
				AppView.outputLine("[DeQ] 삭제된 원소는 '" + removedChar.charValue() + "'입니다.");
			}
		}
	}

	private void removeN(int numberOfCharsToBeRemoved) {
		if (numberOfCharsToBeRemoved == 0) { // 삭제할 개수가 0인경우
			AppView.outputLine("[DeQs] 삭제할 원소의 개수가 0개입니다.");
		} else {
			int count = 0; // 삭제한 횟수
			while (count < numberOfCharsToBeRemoved && (!this.queue().isEmpty())) { // queue가 비거나 count가 인자보다 커지면 탈출
				Character removedChar = this.queue().deQueue();
				if (removedChar == null) {
					AppView.outputLine("(오류) 큐에서 삭제하는 동안에 오류가 발생하였습니다.");
				} else {
					AppView.outputLine("[DeQs] 삭제한 원소는 '" + removedChar + "'입니다.");
					count++;
				}
			}
			if (count < numberOfCharsToBeRemoved) {
				AppView.outputLine("[DeQs.Empty] 큐에 더 이상 삭제할 원소가 없습니다.");
			}
		}
	}

	private void quitQueueProcessing() {
		AppView.outputLine("");
		AppView.outputLine("<큐를 비우고 사용을 종료합니다>");
		this.showAllFromFront(); // Front부터 Rear까지 출력
		this.removeN(this.queue().size()); // Queue의 size만큼 삭제한다.
	}

	private void showStatistics() { // 통계 출력
		AppView.outputLine("");
		AppView.outputLine("<큐 사용 통계>");
		AppView.outputLine("- 입력된 문자는  " + this.inputChars() + "개 입니다.");
		AppView.outputLine("- 정상처리된 문자는  " + (this.inputChars() - this.ignoredChars()) + "개 입니다.");
		AppView.outputLine("- 무시된 문자는  " + this.ignoredChars() + "개 입니다.");
		AppView.outputLine("- 삽입된 문자는  " + this.addedChars() + "개 입니다.");

	}

	private char inputChar() {
		AppView.output("? 문자를 입력하시오 : ");
		return AppView.inputChar();
	}

	public void run() {
		AppView.outputLine("<<< 큐 기능 확인 프로그램을 시작합니다 >>>");
		AppView.outputLine("");
		char input = this.inputChar(); // input 입력받기
		while (input != '!') { // !는 종료
			this.countInputChars();
			if ((Character.isAlphabetic(input))) { // 만약 입력 문자가 알파벳이면
				this.addToQueue(Character.valueOf(input)); // Queue에 삽입
			} else if (Character.isDigit(input)) { // 만약 입력 문자가 숫자면
				this.removeN(Character.getNumericValue(input)); // 입력문자만큼 삭제
			} else if (input == '-') { // - 는 1개 삭제
				this.removeOne();
			} else if (input == '#') { // # 은 크기 출력
				this.showQueueSize();
			} else if (input == '/') { // / 는 front부터 rear 순서 출력
				this.showAllFromFront();
			} else if (input == '\\') { // \ 는 rear부터 front 순서 출력
				this.showAllFromRear();
			} else if (input == '<') { // < 는 front원소 출력
				this.showFrontElement();
			} else if (input == '>') { // > 는 rear원소 출력
				this.showRearElement();
			} else { // 그 외에
				AppView.outputLine("[Ignored] 의미 없는 문자가 입력되었습니다.");
				this.countIgnoredChars();
			}
			input = this.inputChar();
		}
		this.quitQueueProcessing();
		this.showStatistics();
		AppView.outputLine("");
		AppView.outputLine("<<< 큐 기능 확인 프로그램을 종료합니다 >>>");
	}

}
