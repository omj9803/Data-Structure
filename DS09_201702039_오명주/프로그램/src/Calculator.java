import java.util.Arrays;

public class Calculator {
	private static final int MAX_EXPRESSION_LENGHT = 10; // 최대길이

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

	// 생성자
	public Calculator() {
		this.setOperatorStack(new ArrayList<Character>(Calculator.MAX_EXPRESSION_LENGHT)); // 크기가 100으로 set
		this.setPostfixCalculator(new PostfixCalculator(Calculator.MAX_EXPRESSION_LENGHT)); // 크기가 100으로 set
	}

	// 연산자 스택을 보여주는 함수
	private void showOperatorStack(String anOperationLabel) {
		AppView.outputDebugMessage(anOperationLabel + " OperatorStack <Bottom> "); // anOperationLabel(pushed/popped..) 출력문
		for (int i = 0; i < this.operatorStack().size(); i++) { // operatorStack().size()만큼 반복
			AppView.outputDebugMessage(((ArrayList<Character>) this.operatorStack()).elementAt(i).charValue() + " ");
		}
		AppView.outputLineDebugMessage("<Top>"); // 종료
	}

	private void showTokenAndPostfixExpression(char aToken, char[] aPostfixExpressionArray) {
		AppView.outputDebugMessage(aToken + " : (Postfix 수식으로 출력) ");
		AppView.outputLineDebugMessage(new String(aPostfixExpressionArray));
	}

	private void showTokenAndMessage(char aToken, String aMessage) {
		AppView.outputLineDebugMessage(aToken + " : (" + aMessage + ") ");
	}

	// 스택에 들어갈 때 우선순위를 지정하는 함수
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

	// 스택 안에서의 우선순위를 지정하는 함수
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
		char[] postfixExpressionArray = new char[this.infixExpression().length()]; // postfixExpressionArray 배열 생성
		Arrays.fill(postfixExpressionArray, ' '); // 공백으로 선언하여 초기화

		Character currentToken, poppedToken, topToken; // 현재토큰, pop한토큰, top토큰 변수 선언
		this.operatorStack().clear(); // 연산자 스택 초기화
		int p = 0;
		for (int i = 0; i < this.infixExpression().length(); i++) { // infixExpression의 길이만큼 반복
			currentToken = this.infixExpression().charAt(i); // currentToken에 0-length순서로 반복해서 저장
			if (Character.isDigit(currentToken.charValue())) { // 만약 currentToken이 숫자라면
				postfixExpressionArray[p++] = currentToken; // postfixExpressionArray에 currentToken 삽입
				this.showTokenAndPostfixExpression(currentToken, postfixExpressionArray); // 출력
			} else { // currentToken이 연산자라면
				if (currentToken == ')') { // currentToken이 ')' 이면
					this.showTokenAndMessage(currentToken, "왼쪽 괄호가 나타날 때까지 스택에서 꺼내어 출력");
					poppedToken = this.operatorStack().pop(); // pop하여 poppedToken에 저장
					while (poppedToken != null && poppedToken.charValue() != '(') { // poppedToken이 null이 아니고 '('가 아니라면
						postfixExpressionArray[p++] = poppedToken.charValue(); // pop()된 객체를 char로 형변환 후 postfixExpressionArray에 넣어준다.
						this.showOperatorStack("Popped"); // 하나씩 꺼내면서 스택의 변화를 보여준다
						this.showTokenAndPostfixExpression(poppedToken, postfixExpressionArray); // 출력
						if (this.operatorStack().isEmpty()) { // 만약 모두 pop된다면
							return CalculatorError.InfixError_MissingLeftParen; // '('가 없는 에러
						} else {
							poppedToken = this.operatorStack().pop();
						} // 다시 pop()해서 객체를 받는다
					} // while 탈출
					if (poppedToken == null) { // poppedToken이 null이면
						return CalculatorError.InfixError_MissingLeftParen; // 에러
					}
					this.showOperatorStack("Popped"); // 스택 출력 후 상태 확인
				} // End of if ')'

				else { // currentToken 이 ')'외 연산자
					int inComingPrecedence = this.inComingPrecedence(currentToken.charValue()); // currentToken을 char형으로 형변환 후 우선순위 저장
					if (inComingPrecedence < 0) { // default 연산자라면
						AppView.outputLineDebugMessage(currentToken + " : (Unknown Operator)");
						return CalculatorError.InfixError_UnknownOperator; // 알 수 없는 연산자 에러 =
					}

					this.showTokenAndMessage(currentToken, "입력 연산자보다 순위가 높지 않은 연산자를 스택에서 꺼내어 출력"); // currentToken 출력
					topToken = this.operatorStack().peek(); // topToken은 operatorStack().peek()을 통해 Top의 토큰을 받는다.
					while (topToken != null && this.inStackPrecedence(topToken) >= inComingPrecedence) { // topToken이 존재하고, 스택 안 연산자가 우선순위가 높은 경우
						poppedToken = this.operatorStack().pop(); // topToken이 pop()되고 poppedToken에 저장
						postfixExpressionArray[p++] = poppedToken; // poppedToken은 배열에 들어간다.
						this.showOperatorStack("Popped"); // 출력
						this.showTokenAndPostfixExpression(poppedToken, postfixExpressionArray); // 출력
						topToken = this.operatorStack().peek(); // topToken을 다시 peek()해서 저장
					} // topToken이 존재하지않거나 topToken의 스택우선순위가 currentToken의 inComingPrecedence보다 작을 경우 while 탈출

					if (this.operatorStack().isFull()) { // operatorStack이 가득 차있다면
						this.showOperatorStack("Fulled"); // Fulled하고 스택 출력
						return CalculatorError.InfixError_TooLongExpression; // 에러
					}

					this.operatorStack().push(currentToken); // currentToken을 스택에 넣어준다.
					this.showOperatorStack("Pushed"); // Pushed하고 스택 출력
				}
			}
		} // end For

		AppView.outputLineDebugMessage("(End of infix expression : 스택에서 모든 연산자를 꺼내어 출력)");

		while (!this.operatorStack().isEmpty()) { // 비어있지않다면 반복
			poppedToken = this.operatorStack().pop(); // pop()된 객체가 poppedToken에 저장
			this.showOperatorStack("Popped"); // Popped하여 스택 상태 확인
			if (poppedToken == '(') { // poppedToken이 '('이면
				return CalculatorError.InfixError_MissingRightParen; // 에러
			}
			postfixExpressionArray[p++] = poppedToken; // 아니면 poppedToken은 배열에 들어가고
			this.showTokenAndPostfixExpression(poppedToken, postfixExpressionArray); // 출력
		} // while 탈출
		AppView.outputLineDebugMessage(""); // 줄바꿈
		this.setPostfixExpression(new String(postfixExpressionArray).trim()); // 공백을 없앤 후 String으로 set
		return CalculatorError.InfixError_None; // 에러가 없다
	}

	public int evaluate(String anInfixExpression) throws CalculatorException { // 계산 함수
		this.setInfixExpression(anInfixExpression); // infixExpression을 입력받은 인자로 set
		AppView.outputLineDebugMessage("[Infix to Postfix] " + anInfixExpression);
		if (this.infixExpression() == null || this.infixExpression().length() == 0) { // 중위계산식이 null이거나 중위계산식의 길이가 0이면
			throw new CalculatorException(CalculatorError.InfixError_NoExpression); // 에러
		}
		CalculatorError infixError = this.infixToPostfix(); // infixExpression을 infixToPostfix()를 통해 CalculatorError 객체로 반환
		if (infixError == CalculatorError.InfixError_None) { // infixError가 infixerror_none 이면 -> 더 이상 변경할 infixExpression이 없다 -> 후위 계산식 완성
			AppView.outputLineDebugMessage("[Evaluate Postfix] " + this.postfixExpression()); // 후위계산식을 계산한다는 구문 출력
			return this.postfixCalculator().evaluate(this.postfixExpression()); // 후위계산기의 evaluate를 통해 계산한 값을 반환한다.
		} else { // 그렇지 않다면
			throw new CalculatorException(infixError); // infixError에 대한 에러를 throw
		}
	}

}
