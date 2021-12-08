
public class AppController {
//	private static final int QUEUE_CAPACITY = 10;
	private Queue<Character> _queue;
	private int _inputChars; // �Էµ� ������ ����
	private int _addedChars; // ���Ե� ������ ����
	private int _ignoredChars; // ���õ� ������ ����

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

	// ������
	public AppController() {
		this.setQueue(new CircularlyLinkedQueue<Character>());
		this.setInputChars(0);
		this.setAddedChars(0);
		this.setIgnoredChars(0);
	}

	private void countInputChars() { // �Է¹��� Ƚ�����
		this.setInputChars(this.inputChars() + 1);
	}

	private void countIgnoredChars() { // ���ù��� Ƚ�����
		this.setIgnoredChars(this.ignoredChars() + 1);
	}

	private void countAddedChar() { // �߰����� Ƚ�����
		this.setAddedChars(this.addedChars() + 1);
	}

	// ť�� ��� ���Ҹ� Front ���� Rear���� ����Ѵ�
	private void showAllFromFront() {
		AppView.output("[Queue] <Front> ");
		Iterator<Character> queueIterator = this.queue().iterator(); // queue()�� �ݺ��� ����
		while (queueIterator.hasNext()) { // queueIterator�� next�� �ִ� ���� �ݺ�
			Character element = queueIterator.next(); // ������� element�� ����
			AppView.output(element.toString() + " "); // element�� ���ڿ� ���
		}
		AppView.outputLine("<Rear> ");
	}

	// ť�� ��� ���Ҹ� Rear ���� Front���� ����Ѵ�
	private void showAllFromRear() {
		AppView.output("[Queue] <Rear> ");
		for (int order = this.queue().size() - 1; order >= 0; order--) {
			AppView.output(this.queue().elementAt(order).toString() + " ");
		}
		AppView.outputLine("<Front> ");
	}

	private void showFrontElement() { // Front ť�� ���
		if (this.queue().isEmpty()) {
			AppView.outputLine("[Front.Empty] ť�� �� �� �� ���Ұ� �������� �ʽ��ϴ�.");
		} else {
			AppView.outputLine("[Front] ť�� �� �� ���Ҵ� " + this.queue().front().toString() + " �Դϴ�.");
		}
	}

	private void showRearElement() { // Rear ť�� ���
		if (this.queue().isEmpty()) {
			AppView.outputLine("[Rear.Empty] ť�� �� �� �� ���Ұ� �������� �ʽ��ϴ�.");
		} else {
			AppView.outputLine("[Rear] ť�� �� �� ���Ҵ� " + this.queue().rear().toString() + " �Դϴ�.");
		}
	}

	private void showQueueSize() { // Queue ������ ���
		AppView.outputLine("[Size] ť�� ������ ������  " + this.queue().size() + "�� �Դϴ�.");
	}

	private void addToQueue(Character anElement) {
		if (!this.queue().enQueue(anElement)) { // ť�� ���� �� ���
			AppView.outputLine("[EnQ.Full] ť�� �� ���� �� �̻� ���� ���� �����ϴ�.");
		} else { // enQueue�� ������ ���
			AppView.outputLine("[EnQ] ���Ե� ���Ҵ� '" + anElement.charValue() + "'�Դϴ�.");
			this.countAddedChar();
		}
	}

	private void removeOne() {
		if (this.queue().isEmpty()) { // ť�� ����ִ� ���
			AppView.outputLine("[DeQ.Empty] ť�� ������ ���Ұ� �����ϴ�.");
		} else {
			Character removedChar = this.queue().deQueue();
			if (removedChar == null) { // deQueue�� ������ ���
				AppView.outputLine("(����) ť���� �����ϴ� ���ȿ� ������ �߻��Ͽ����ϴ�. ");
			} else { // deQueue�� ������ ���
				AppView.outputLine("[DeQ] ������ ���Ҵ� '" + removedChar.charValue() + "'�Դϴ�.");
			}
		}
	}

	private void removeN(int numberOfCharsToBeRemoved) {
		if (numberOfCharsToBeRemoved == 0) { // ������ ������ 0�ΰ��
			AppView.outputLine("[DeQs] ������ ������ ������ 0���Դϴ�.");
		} else {
			int count = 0; // ������ Ƚ��
			while (count < numberOfCharsToBeRemoved && (!this.queue().isEmpty())) { // queue�� ��ų� count�� ���ں��� Ŀ���� Ż��
				Character removedChar = this.queue().deQueue();
				if (removedChar == null) {
					AppView.outputLine("(����) ť���� �����ϴ� ���ȿ� ������ �߻��Ͽ����ϴ�.");
				} else {
					AppView.outputLine("[DeQs] ������ ���Ҵ� '" + removedChar + "'�Դϴ�.");
					count++;
				}
			}
			if (count < numberOfCharsToBeRemoved) {
				AppView.outputLine("[DeQs.Empty] ť�� �� �̻� ������ ���Ұ� �����ϴ�.");
			}
		}
	}

	private void quitQueueProcessing() {
		AppView.outputLine("");
		AppView.outputLine("<ť�� ���� ����� �����մϴ�>");
		this.showAllFromFront(); // Front���� Rear���� ���
		this.removeN(this.queue().size()); // Queue�� size��ŭ �����Ѵ�.
	}

	private void showStatistics() { // ��� ���
		AppView.outputLine("");
		AppView.outputLine("<ť ��� ���>");
		AppView.outputLine("- �Էµ� ���ڴ�  " + this.inputChars() + "�� �Դϴ�.");
		AppView.outputLine("- ����ó���� ���ڴ�  " + (this.inputChars() - this.ignoredChars()) + "�� �Դϴ�.");
		AppView.outputLine("- ���õ� ���ڴ�  " + this.ignoredChars() + "�� �Դϴ�.");
		AppView.outputLine("- ���Ե� ���ڴ�  " + this.addedChars() + "�� �Դϴ�.");

	}

	private char inputChar() {
		AppView.output("? ���ڸ� �Է��Ͻÿ� : ");
		return AppView.inputChar();
	}

	public void run() {
		AppView.outputLine("<<< ť ��� Ȯ�� ���α׷��� �����մϴ� >>>");
		AppView.outputLine("");
		char input = this.inputChar(); // input �Է¹ޱ�
		while (input != '!') { // !�� ����
			this.countInputChars();
			if ((Character.isAlphabetic(input))) { // ���� �Է� ���ڰ� ���ĺ��̸�
				this.addToQueue(Character.valueOf(input)); // Queue�� ����
			} else if (Character.isDigit(input)) { // ���� �Է� ���ڰ� ���ڸ�
				this.removeN(Character.getNumericValue(input)); // �Է¹��ڸ�ŭ ����
			} else if (input == '-') { // - �� 1�� ����
				this.removeOne();
			} else if (input == '#') { // # �� ũ�� ���
				this.showQueueSize();
			} else if (input == '/') { // / �� front���� rear ���� ���
				this.showAllFromFront();
			} else if (input == '\\') { // \ �� rear���� front ���� ���
				this.showAllFromRear();
			} else if (input == '<') { // < �� front���� ���
				this.showFrontElement();
			} else if (input == '>') { // > �� rear���� ���
				this.showRearElement();
			} else { // �� �ܿ�
				AppView.outputLine("[Ignored] �ǹ� ���� ���ڰ� �ԷµǾ����ϴ�.");
				this.countIgnoredChars();
			}
			input = this.inputChar();
		}
		this.quitQueueProcessing();
		this.showStatistics();
		AppView.outputLine("");
		AppView.outputLine("<<< ť ��� Ȯ�� ���α׷��� �����մϴ� >>>");
	}

}
