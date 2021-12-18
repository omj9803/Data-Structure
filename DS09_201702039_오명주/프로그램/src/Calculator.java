import java.util.Arrays;

public class Calculator {
	private static final int MAX_EXPRESSION_LENGHT = 10; // �ִ����

	private Stack<Character> _operatorStack;
	private String _infixExpression;
	private String _postfixExpression;
	private PostfixCalculator _postfixCalculator;

	// Getters/Setters
	private String infixExpression() {
		return this._infixExpression;
	}

	private void setInfixExpression(String newInfixExpression) {
		this._infixExpression = newInfixExpression;
	}

	private String postfixExpression() {
		return this._postfixExpression;
	}

	private void setPostfixExpression(String newPostfixExpression) {
		this._postfixExpression = newPostfixExpression;
	}

	private PostfixCalculator postfixCalculator() {
		return this._postfixCalculator;
	}

	private void setPostfixCalculator(PostfixCalculator newPostfixCalculator) {
		this._postfixCalculator = newPostfixCalculator;
	}

	private Stack<Character> operatorStack() {
		return this._operatorStack;
	}

	private void setOperatorStack(Stack<Character> newOperatorStack) {
		this._operatorStack = newOperatorStack;
	}

	// ������
	public Calculator() {
		this.setOperatorStack(new ArrayList<Character>(Calculator.MAX_EXPRESSION_LENGHT)); // ũ�Ⱑ 100���� set
		this.setPostfixCalculator(new PostfixCalculator(Calculator.MAX_EXPRESSION_LENGHT)); // ũ�Ⱑ 100���� set
	}

	// ������ ������ �����ִ� �Լ�
	private void showOperatorStack(String anOperationLabel) {
		AppView.outputDebugMessage(anOperationLabel + " OperatorStack <Bottom> "); // anOperationLabel(pushed/popped..) ��¹�
		for (int i = 0; i < this.operatorStack().size(); i++) { // operatorStack().size()��ŭ �ݺ�
			AppView.outputDebugMessage(((ArrayList<Character>) this.operatorStack()).elementAt(i).charValue() + " ");
		}
		AppView.outputLineDebugMessage("<Top>"); // ����
	}

	private void showTokenAndPostfixExpression(char aToken, char[] aPostfixExpressionArray) {
		AppView.outputDebugMessage(aToken + " : (Postfix �������� ���) ");
		AppView.outputLineDebugMessage(new String(aPostfixExpressionArray));
	}

	private void showTokenAndMessage(char aToken, String aMessage) {
		AppView.outputLineDebugMessage(aToken + " : (" + aMessage + ") ");
	}

	// ���ÿ� �� �� �켱������ �����ϴ� �Լ�
	private int inComingPrecedence(Character aToken) {
		switch (aToken.charValue()) {
		case '(':
			return 20;
		case ')':
			return 19;
		case '^':
			return 17;
		case '*':
			return 13;
		case '/':
			return 13;
		case '%':
			return 13;
		case '+':
			return 12;
		case '-':
			return 12;
		default:
			return -1;
		}
	}

	// ���� �ȿ����� �켱������ �����ϴ� �Լ�
	private int inStackPrecedence(Character aToken) {
		switch (aToken.charValue()) {
		case '(':
			return 0;
		case ')':
			return 19;
		case '^':
			return 16;
		case '*':
			return 13;
		case '/':
			return 13;
		case '%':
			return 13;
		case '+':
			return 12;
		case '-':
			return 12;
		default:
			return -1;
		}
	}

	private CalculatorError infixToPostfix() {
		char[] postfixExpressionArray = new char[this.infixExpression().length()]; // postfixExpressionArray �迭 ����
		Arrays.fill(postfixExpressionArray, ' '); // �������� �����Ͽ� �ʱ�ȭ

		Character currentToken, poppedToken, topToken; // ������ū, pop����ū, top��ū ���� ����
		this.operatorStack().clear(); // ������ ���� �ʱ�ȭ
		int p = 0;
		for (int i = 0; i < this.infixExpression().length(); i++) { // infixExpression�� ���̸�ŭ �ݺ�
			currentToken = this.infixExpression().charAt(i); // currentToken�� 0-length������ �ݺ��ؼ� ����
			if (Character.isDigit(currentToken.charValue())) { // ���� currentToken�� ���ڶ��
				postfixExpressionArray[p++] = currentToken; // postfixExpressionArray�� currentToken ����
				this.showTokenAndPostfixExpression(currentToken, postfixExpressionArray); // ���
			} else { // currentToken�� �����ڶ��
				if (currentToken == ')') { // currentToken�� ')' �̸�
					this.showTokenAndMessage(currentToken, "���� ��ȣ�� ��Ÿ�� ������ ���ÿ��� ������ ���");
					poppedToken = this.operatorStack().pop(); // pop�Ͽ� poppedToken�� ����
					while (poppedToken != null && poppedToken.charValue() != '(') { // poppedToken�� null�� �ƴϰ� '('�� �ƴ϶��
						postfixExpressionArray[p++] = poppedToken.charValue(); // pop()�� ��ü�� char�� ����ȯ �� postfixExpressionArray�� �־��ش�.
						this.showOperatorStack("Popped"); // �ϳ��� �����鼭 ������ ��ȭ�� �����ش�
						this.showTokenAndPostfixExpression(poppedToken, postfixExpressionArray); // ���
						if (this.operatorStack().isEmpty()) { // ���� ��� pop�ȴٸ�
							return CalculatorError.InfixError_MissingLeftParen; // '('�� ���� ����
						} else {
							poppedToken = this.operatorStack().pop();
						} // �ٽ� pop()�ؼ� ��ü�� �޴´�
					} // while Ż��
					if (poppedToken == null) { // poppedToken�� null�̸�
						return CalculatorError.InfixError_MissingLeftParen; // ����
					}
					this.showOperatorStack("Popped"); // ���� ��� �� ���� Ȯ��
				} // End of if ')'

				else { // currentToken �� ')'�� ������
					int inComingPrecedence = this.inComingPrecedence(currentToken.charValue()); // currentToken�� char������ ����ȯ �� �켱���� ����
					if (inComingPrecedence < 0) { // default �����ڶ��
						AppView.outputLineDebugMessage(currentToken + " : (Unknown Operator)");
						return CalculatorError.InfixError_UnknownOperator; // �� �� ���� ������ ���� =
					}

					this.showTokenAndMessage(currentToken, "�Է� �����ں��� ������ ���� ���� �����ڸ� ���ÿ��� ������ ���"); // currentToken ���
					topToken = this.operatorStack().peek(); // topToken�� operatorStack().peek()�� ���� Top�� ��ū�� �޴´�.
					while (topToken != null && this.inStackPrecedence(topToken) >= inComingPrecedence) { // topToken�� �����ϰ�, ���� �� �����ڰ� �켱������ ���� ���
						poppedToken = this.operatorStack().pop(); // topToken�� pop()�ǰ� poppedToken�� ����
						postfixExpressionArray[p++] = poppedToken; // poppedToken�� �迭�� ����.
						this.showOperatorStack("Popped"); // ���
						this.showTokenAndPostfixExpression(poppedToken, postfixExpressionArray); // ���
						topToken = this.operatorStack().peek(); // topToken�� �ٽ� peek()�ؼ� ����
					} // topToken�� ���������ʰų� topToken�� ���ÿ켱������ currentToken�� inComingPrecedence���� ���� ��� while Ż��

					if (this.operatorStack().isFull()) { // operatorStack�� ���� ���ִٸ�
						this.showOperatorStack("Fulled"); // Fulled�ϰ� ���� ���
						return CalculatorError.InfixError_TooLongExpression; // ����
					}

					this.operatorStack().push(currentToken); // currentToken�� ���ÿ� �־��ش�.
					this.showOperatorStack("Pushed"); // Pushed�ϰ� ���� ���
				}
			}
		} // end For

		AppView.outputLineDebugMessage("(End of infix expression : ���ÿ��� ��� �����ڸ� ������ ���)");

		while (!this.operatorStack().isEmpty()) { // ��������ʴٸ� �ݺ�
			poppedToken = this.operatorStack().pop(); // pop()�� ��ü�� poppedToken�� ����
			this.showOperatorStack("Popped"); // Popped�Ͽ� ���� ���� Ȯ��
			if (poppedToken == '(') { // poppedToken�� '('�̸�
				return CalculatorError.InfixError_MissingRightParen; // ����
			}
			postfixExpressionArray[p++] = poppedToken; // �ƴϸ� poppedToken�� �迭�� ����
			this.showTokenAndPostfixExpression(poppedToken, postfixExpressionArray); // ���
		} // while Ż��
		AppView.outputLineDebugMessage(""); // �ٹٲ�
		this.setPostfixExpression(new String(postfixExpressionArray).trim()); // ������ ���� �� String���� set
		return CalculatorError.InfixError_None; // ������ ����
	}

	public int evaluate(String anInfixExpression) throws CalculatorException { // ��� �Լ�
		this.setInfixExpression(anInfixExpression); // infixExpression�� �Է¹��� ���ڷ� set
		AppView.outputLineDebugMessage("[Infix to Postfix] " + anInfixExpression);
		if (this.infixExpression() == null || this.infixExpression().length() == 0) { // ���������� null�̰ų� ���������� ���̰� 0�̸�
			throw new CalculatorException(CalculatorError.InfixError_NoExpression); // ����
		}
		CalculatorError infixError = this.infixToPostfix(); // infixExpression�� infixToPostfix()�� ���� CalculatorError ��ü�� ��ȯ
		if (infixError == CalculatorError.InfixError_None) { // infixError�� infixerror_none �̸� -> �� �̻� ������ infixExpression�� ���� -> ���� ���� �ϼ�
			AppView.outputLineDebugMessage("[Evaluate Postfix] " + this.postfixExpression()); // ���������� ����Ѵٴ� ���� ���
			return this.postfixCalculator().evaluate(this.postfixExpression()); // ���������� evaluate�� ���� ����� ���� ��ȯ�Ѵ�.
		} else { // �׷��� �ʴٸ�
			throw new CalculatorException(infixError); // infixError�� ���� ������ throw
		}
	}

}
