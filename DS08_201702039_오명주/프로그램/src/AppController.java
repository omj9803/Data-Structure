
public class AppController {

	// ���
	private static final int STACK_CAPACITY = 5;

	// ����� ������
	private ArrayList<Character> _stack;
	private int _inputChars; // �Էµ� ������ ����
	private int _pushedChars; // ���Ե� ������ ����
	private int _ignoredChars; // ���õ� ������ ����

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

	// ������
	public AppController() {
		this.setStack(new ArrayList<Character>(AppController.STACK_CAPACITY));
		this.setInputChars(0);
		this.setPushedChars(0);
		this.setIgnoredChars(0);
	}

	// ����� �Լ�
	// Ƚ�� ���
	private void countInputChar() { // �Էµ� ���� + 1 count
		this.setInputChars(this.inputChars() + 1);
	}

	private void countIgnoredChar() { // ���õ� ���� + 1 count
		this.setIgnoredChars(this.ignoredChars() + 1);
	}

	private void countPushedChar() { // ���Ե� ���� + 1 count
		this.setPushedChars(this.pushedChars() + 1);
	}

	// ���� ���� ����
	private void pushToStack(char aCharForPush) { // ���� Top�� ���Ҹ� �����ϴ� �Լ�
		if (this.stack().isFull()) { // ������ ���� ���ִٸ�
			AppView.outputLine("(����) ������ �� ����, �� �̻� ���� �� �����ϴ�."); // ���� ��¹� ���
		} else {
			Character charObjectForAdd = Character.valueOf(aCharForPush); // ���ڸ� ���� ��ü�� ��ȯ
			if (this.stack().push(charObjectForAdd)) { // ���ÿ� push
				AppView.outputLine("[Push] ���Ե� ���Ҵ� '" + aCharForPush + "' �Դϴ� .");
			} else {
				AppView.outputLine("(����) ���ÿ� �ִ� ���ȿ� ������ �߻��Ͽ����ϴ�.");
			}
		}

	}

	private void popOne() { // ���� Top�� ���Ҹ� �ϳ� �����Ͽ� return�ϴ� �Լ�
		if (this.stack().isEmpty()) { // ������ ����ִٸ�
			AppView.outputLine("[Pop.Empty] ���ÿ� ������ ���Ұ� �����ϴ�."); // ���Ұ� ���ٴ� ��¹� ���
		} else {
			Character poppedChar = this.stack().pop(); // pop�� ����� poppedChar�� ����
			if (poppedChar == null) { // poppedChar�� ���� null �˻�
				AppView.outputLine("(����) ���ÿ��� �����ϴ� ���ȿ� ������ �߻��Ͽ����ϴ�.");
			} else {
				AppView.outputLine("[Pop] ������ ���Ҵ� ' " + poppedChar + " ' �Դϴ�.");
			}
		}
	}

	private void popN(int numberOfCharsToBePopped) { // ���� Top�� ���Ҹ� numberOfCharsToBePopped��ŭ �����Ͽ� return�ϴ� �Լ�
		if (numberOfCharsToBePopped == 0) { // ���� �Է¹��� ���� 0�̶��
			AppView.outputLine("[Pops] ������ ������ ������ 0 �� �Դϴ�."); // ����Ƚ���� 0
		} else {
			int count = 0;
			while (count < numberOfCharsToBePopped && (!this.stack().isEmpty())) { // �Է¹��� �� ��ŭ �ݺ�
				Character poppedChar = this.stack().pop(); // �ݺ��Ͽ� pop�� ����
				if (poppedChar == null) {
					AppView.outputLine("(����) ���ÿ��� �����ϴ� ���ȿ� ������ �߻��Ͽ����ϴ�.");

				} else {
					AppView.outputLine("[Pops] ������ ���Ҵ� ' " + poppedChar + " '�Դϴ�.");
				}
				count++;
			}
			if (count < numberOfCharsToBePopped) { // ���ÿ� �ִ� ������ ������ �Է¹��� ���ڰ� �� ū ���
				AppView.outputLine("[Pops.Empty] ���ÿ� �� �̻� ������ ���Ұ� �����ϴ�."); // ������ ���
			}
		}
	}

	private void quitStackProcessing() { // ���� ���� �����ϴ� �Լ�
		AppView.outputLine("");
		AppView.outputLine("<������ ���� ����� �����մϴ�>");
		this.showAllFromBottom(); // ���� Bottom-Top ������ ���
		this.popN(this.stack().size()); // ��� ���� pop ����
	}

	// ��� ����
	private void showAllFromBottom() { // ������ ��� ���Ҹ� Bottom ���� Top���� ����Ѵ�
		AppView.output("[Stack] <Bottom> ");
		for (int order = 0; order < this.stack().size(); order++) {
			AppView.output(this.stack().elementAt(order).toString() + " ");
		}
		AppView.outputLine(" <Top>");
	}

	private void showAllFromTop() { // ������ ��� ���Ҹ� Top ���� Bottom���� ����Ѵ�
		AppView.output("[Stack] <Top> ");
		for (int order = this.stack().size() - 1; order >= 0; order--) {
			AppView.output(this.stack().elementAt(order).toString() + " ");
		}
		AppView.outputLine(" <Bottom>");
	}

	private void showTopElement() {
		if (this.stack().isEmpty()) {
			AppView.outputLine("[Top.Empty] ������ �� Top ���Ұ� �������� �ʽ��ϴ�.");
		} else {
			AppView.outputLine("[Top] ������ Top ���Ҵ� '" + this.stack().peek() + "' �Դϴ�.");
		}

	}

	private void showStackSize() { // ���� ����� ����ϴ� �Լ�
		AppView.outputLine("[Size] ���ÿ��� ���� " + this.stack().size() + " ���� ���Ұ� �ֽ��ϴ�.");
	}

	private void showStatistics() { // ��踦 ����ϴ� �Լ�
		AppView.outputLine("");
		AppView.outputLine("<���� ��� ���>");
		AppView.outputLine("- �Էµ� ���ڴ� " + this.inputChars() + " �� �Դϴ�.");
		AppView.outputLine("- ���� ó���� ���ڴ� " + (this.inputChars() - this.ignoredChars()) + " �� �Դϴ�.");
		AppView.outputLine("- ���õ� ���ڴ� " + this.ignoredChars() + " �� �Դϴ�.");
		AppView.outputLine("- ���Ե� ���ڴ� " + this.pushedChars() + " �� �Դϴ�.");
	}

	// �Է� ����
	private char inputChar() {
		AppView.output("? ���ڸ� �Է��Ͻÿ�: ");
		return AppView.inputChar();
	}

	public void run() {
		AppView.outputLine("<<< ���� ��� Ȯ�� ���α׷��� �����մϴ� >>>");
		AppView.outputLine("");

		char input = this.inputChar(); // char ���� �Է¹���
		while (input != '!') { // '!'�� �Է¹����� ����
			this.countInputChar(); // �Էµȹ��� + 1
			if (Character.isAlphabetic(input)) { // ���ĺ����� �˻�
				this.pushToStack(input);
				this.countPushedChar(); // ���Եȹ��� + 1
			} else if ((Character.isDigit(input))) { // ���� �������� �˻�
				this.popN(Character.getNumericValue(input)); // ���� ���ڸ� ���������� ��ȯ
			} else if (input == '-') { // '-'���� �Է¹����� popOne ����
				this.popOne();
			} else if (input == '#') { // '#'���� �Է¹����� ����ũ�� ���
				this.showStackSize();
			} else if (input == '/') { // '/'���� �Է¹����� Bottom-Top������ ���� ���
				this.showAllFromBottom();
			} else if (input == '\\') { // '\'���� �Է¹����� Top-Bottom������ ���� ���
				this.showAllFromTop();
			} else if (input == '^') { // '^'���� �Է¹����� Top���� peek�Ͽ� ���
				this.showTopElement();
			} else {
				AppView.outputLine("[Ignore] �ǹ� ���� ���ڰ� �ԷµǾ����ϴ�."); // ����ó��
				this.countIgnoredChar(); // ���õȹ��� + 1
			}
			input = this.inputChar(); // �ݺ��ؼ� ����
		}
		this.quitStackProcessing(); // ���� ���� �Լ�
		this.showStatistics();
		AppView.outputLine("");
		AppView.outputLine("<<< ���� ��� Ȯ�� ���α׷��� �����մϴ� >>> ");
	}
}
