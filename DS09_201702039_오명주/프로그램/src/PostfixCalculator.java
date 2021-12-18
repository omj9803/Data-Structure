
public class PostfixCalculator {
	private static final int DEFAULT_MAX_EXPRESSION_LENGTH = 100;
	private int _maxExpressionLenght;
	private Stack<Integer> _valueStack;

	// Getters / Setters
	public int maxExpressionLength() {
		return this._maxExpressionLenght;
	}

	private void setMaxExpressionLength(int newMaxExpressionLength) {
		this._maxExpressionLenght = newMaxExpressionLength;
	}

	private Stack<Integer> valueStack() {
		return this._valueStack;
	}

	private void setValueStack(Stack<Integer> newValueStack) {
		this._valueStack = newValueStack;
	}

	// ������
	public PostfixCalculator() {
		this.setMaxExpressionLength(PostfixCalculator.DEFAULT_MAX_EXPRESSION_LENGTH);
	}

	public PostfixCalculator(int givenMaxExpressionLength) {
		this.setMaxExpressionLength(givenMaxExpressionLength);
		this.setValueStack(new ArrayList<Integer>(this.maxExpressionLength()));
	}

	// stack�� �̿��Ͽ� postfix ���� ����Ͽ� �� ����� ��� �Լ�
	public int evaluate(String aPostfixExpression) throws CalculatorException {
		if (aPostfixExpression == null || aPostfixExpression.length() == 0) { // �Է¹��� aPostfixExpression�� �ƹ��͵� ���ٸ�
			throw new CalculatorException(CalculatorError.PostfixError_NoExpression); // ������ ���
		}
		this.valueStack().clear(); // ������ �ʱ�ȭ
		char token; // token ����
		for (int current = 0; current < aPostfixExpression.length(); current++) { // ���ڿ� ���̸�ŭ �ݺ�
			token = aPostfixExpression.charAt(current); // ��ū�� String�� current��° ����(�ݺ�)
			if (Character.isDigit(token)) { // token�� ���ڰ� ���ڶ��
				int tokenValue = Character.getNumericValue(token); // token�� digit�� int�� ��ȯ
				if (this.valueStack().isFull()) { // value ������ ���� ���ִٸ�
					throw new CalculatorException(CalculatorError.PostfixError_TooLongExpression); // ������ ���
				} else { // �׷��� �ʴٸ�
					this.valueStack().push(Integer.valueOf(tokenValue)); // Integer��ü�� ��ȯ �� valueStack�� push
				}
			} else { // token�� �����ڶ��
				CalculatorError error = this.executeBinaryOperator(token); // ������ token�� ��� �� error ��ȯ
				if (error != CalculatorError.PostfixError_None) { // None ������ �ƴ϶�� -> ��� �� ������ ����
					throw new CalculatorException(error); // error�� throw
				}
			}
			this.showTokenAndValueStack(token); // ��� �Ϸ� �� token�� ���
		} // end of For()
		if (this.valueStack().size() == 1) { // ������ 1���� ���̸�
			return (this.valueStack().pop().intValue()); // pop()�� ��ü�� int������ ��ȯ�ϰ� ��ȯ�Ѵ�. -> �Ի갪
		} else { // �׷��� �ʴٸ�
			throw new CalculatorException(CalculatorError.PostfixError_TooManyValues); // ������
		}
	}

	// Binary �����ڸ� ����. �����ڷ� valueStack�� ���갪�� ����ϴ� �Լ�
	private CalculatorError executeBinaryOperator(char anOperator) {
		if (this.valueStack().size() < 2) { // ������ 2������ ���ٸ� ����� �� ����
			return CalculatorError.PostfixError_TooFewValues; // ���� ��ȯ
		}
		// size�� 2���� ũ�ų� ���� ���
		int operand1 = this.valueStack().pop().intValue(); // ����� ù��° ��
		int operand2 = this.valueStack().pop().intValue(); // ����� �ι�° ��
		int calculated = 0; // calculated �ʱ�ȭ
		switch (anOperator) { // �������� ����� ��
		case '^': // ����
			calculated = (int) Math.pow((double) operand2, (double) operand1); // = operand2^operand1
			break;
		case '*': // ��
			calculated = operand2 * operand1;
			break;
		case '/': // ������
			if (operand1 == 0) { // �и� 0�̵Ǹ�
				AppView.outputLineDebugMessage(
						anOperator + " : (DivideByZero) " + operand2 + " " + anOperator + " " + operand1); // �������� ���
				return CalculatorError.PostfixError_DivideByZero; // ����
			} else {
				calculated = operand2 / operand1;
			}
			break;
		case '%': // ������
			if (operand1 == 0) {
				AppView.outputLineDebugMessage(
						anOperator + " : (DivideByZero) " + operand2 + " " + anOperator + " " + operand1); // �������� ���
				return CalculatorError.PostfixError_DivideByZero; // ����
			} else {
				calculated = operand2 % operand1;
			}
			break;
		case '+': // ��
			calculated = operand2 + operand1;
			break;
		case '-': // ��
			calculated = operand2 - operand1;
			break;
		default: // �� ��
			return CalculatorError.PostfixError_UnknownOperator; // ����
		}
		this.valueStack().push(Integer.valueOf(calculated)); // calculated�� Integer��ü�� ��ȯ�� push
		return CalculatorError.PostfixError_None; // ���� ������ ���� ��ȯ
	}

	// ����� ����. �־��� ��ū�� ���� ������ �����ִ� �Լ�
	private void showTokenAndValueStack(char aToken) {
		AppView.outputDebugMessage(aToken + " : ValueStack <Bottom> "); // ��¹�
		for (int i = 0; i < this.valueStack().size(); i++) { // ������ size��ŭ �ݺ�
			AppView.outputDebugMessage(((ArrayList<Integer>) this.valueStack()).elementAt(i).intValue() + " "); // ������ value���� ���
		}
		AppView.outputLineDebugMessage("<Top>");
	}
}
