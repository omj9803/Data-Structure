
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

	// 생성자
	public PostfixCalculator() {
		this.setMaxExpressionLength(PostfixCalculator.DEFAULT_MAX_EXPRESSION_LENGTH);
	}

	public PostfixCalculator(int givenMaxExpressionLength) {
		this.setMaxExpressionLength(givenMaxExpressionLength);
		this.setValueStack(new ArrayList<Integer>(this.maxExpressionLength()));
	}

	// stack을 이용하여 postfix 수식 계산하여 그 결과를 얻는 함수
	public int evaluate(String aPostfixExpression) throws CalculatorException {
		if (aPostfixExpression == null || aPostfixExpression.length() == 0) { // 입력받은 aPostfixExpression이 아무것도 없다면
			throw new CalculatorException(CalculatorError.PostfixError_NoExpression); // 오류문 출력
		}
		this.valueStack().clear(); // 스택을 초기화
		char token; // token 선언
		for (int current = 0; current < aPostfixExpression.length(); current++) { // 문자열 길이만큼 반복
			token = aPostfixExpression.charAt(current); // 토큰은 String의 current번째 문자(반복)
			if (Character.isDigit(token)) { // token의 문자가 숫자라면
				int tokenValue = Character.getNumericValue(token); // token의 digit를 int로 변환
				if (this.valueStack().isFull()) { // value 스택이 가득 차있다면
					throw new CalculatorException(CalculatorError.PostfixError_TooLongExpression); // 오류문 출력
				} else { // 그렇지 않다면
					this.valueStack().push(Integer.valueOf(tokenValue)); // Integer객체로 변환 후 valueStack에 push
				}
			} else { // token이 연산자라면
				CalculatorError error = this.executeBinaryOperator(token); // 연산자 token을 계산 후 error 반환
				if (error != CalculatorError.PostfixError_None) { // None 에러가 아니라면 -> 계산 중 오류가 있음
					throw new CalculatorException(error); // error를 throw
				}
			}
			this.showTokenAndValueStack(token); // 계산 완료 후 token을 출력
		} // end of For()
		if (this.valueStack().size() == 1) { // 스택이 1개가 쌓이면
			return (this.valueStack().pop().intValue()); // pop()한 객체를 int형으로 변환하고 반환한다. -> 게산값
		} else { // 그렇지 않다면
			throw new CalculatorException(CalculatorError.PostfixError_TooManyValues); // 오류문
		}
	}

	// Binary 연산자를 실행. 연산자로 valueStack의 연산값을 계산하는 함수
	private CalculatorError executeBinaryOperator(char anOperator) {
		if (this.valueStack().size() < 2) { // 스택이 2개보다 적다면 계산할 값 부족
			return CalculatorError.PostfixError_TooFewValues; // 오류 반환
		}
		// size가 2보다 크거나 같을 경우
		int operand1 = this.valueStack().pop().intValue(); // 계산할 첫번째 값
		int operand2 = this.valueStack().pop().intValue(); // 계산할 두번째 값
		int calculated = 0; // calculated 초기화
		switch (anOperator) { // 연산자의 경우의 수
		case '^': // 제곱
			calculated = (int) Math.pow((double) operand2, (double) operand1); // = operand2^operand1
			break;
		case '*': // 곱
			calculated = operand2 * operand1;
			break;
		case '/': // 나누기
			if (operand1 == 0) { // 분모가 0이되면
				AppView.outputLineDebugMessage(
						anOperator + " : (DivideByZero) " + operand2 + " " + anOperator + " " + operand1); // 오류구문 출력
				return CalculatorError.PostfixError_DivideByZero; // 에러
			} else {
				calculated = operand2 / operand1;
			}
			break;
		case '%': // 나머지
			if (operand1 == 0) {
				AppView.outputLineDebugMessage(
						anOperator + " : (DivideByZero) " + operand2 + " " + anOperator + " " + operand1); // 오류구문 출력
				return CalculatorError.PostfixError_DivideByZero; // 에러
			} else {
				calculated = operand2 % operand1;
			}
			break;
		case '+': // 합
			calculated = operand2 + operand1;
			break;
		case '-': // 차
			calculated = operand2 - operand1;
			break;
		default: // 그 외
			return CalculatorError.PostfixError_UnknownOperator; // 에러
		}
		this.valueStack().push(Integer.valueOf(calculated)); // calculated를 Integer객체로 변환후 push
		return CalculatorError.PostfixError_None; // 후위 계산식이 없음 반환
	}

	// 디버깅 목적. 주어진 토큰과 현재 스택을 보여주는 함수
	private void showTokenAndValueStack(char aToken) {
		AppView.outputDebugMessage(aToken + " : ValueStack <Bottom> "); // 출력문
		for (int i = 0; i < this.valueStack().size(); i++) { // 스택의 size만큼 반복
			AppView.outputDebugMessage(((ArrayList<Integer>) this.valueStack()).elementAt(i).intValue() + " "); // 스택의 value값을 출력
		}
		AppView.outputLineDebugMessage("<Top>");
	}
}
