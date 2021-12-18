
public class AppController {

	// 상수
	private static final char END_OF_CALCULATION = '!'; // 종료조건
	private static final boolean DEBUG_MODE = true; // 디버깅모드 여부 결정

	// 비공개 변수들
	private Calculator _calculator;

	// Getters/Setters
	private Calculator calculator() {
		return this._calculator;
	}

	private void setCalculator(Calculator newCalculator) {
		this._calculator = newCalculator;
	}

	// 생성자
	public AppController() {
		this.setCalculator(new Calculator());
		AppView.setDebugMode(AppController.DEBUG_MODE); // AppView에게 debug모드인지 알려줌
	}

	// 비공개 함수
	// 수식을 입력받는 함수
	private String inputExpression() {
		AppView.outputLine("");
		AppView.output("? 계산할 수식을 입력하시오 (종료하려면 " + END_OF_CALCULATION + " 를 입력하시오): ");
		return AppView.inputLine();
	}

	// error 코드에 맞는 메시지를 출력
	private void showCalculatorErrorMessage(CalculatorError anError) {
		switch (anError) {
		case InfixError_NoExpression:
			AppView.outputLine("[오류] 중위 계산식이 주어지지 않았습니다.");
			break;
		case InfixError_TooLongExpression:
			AppView.outputLine("[오류] 중위 계산식이 너무 길어 처리할 수 없습니다.");
			break;
		case InfixError_MissingLeftParen:
			AppView.outputLine("[오류] 왼쪽 괄호가 빠졌습니다.");
			break;
		case InfixError_MissingRightParen:
			AppView.outputLine("[오류] 오른쪽 괄호가 빠졌습니다.");
			break;
		case InfixError_UnknownOperator:
			AppView.outputLine("[오류] 중위 계산식에 알 수 없는 연산자가 있습니다.");
			break;
		case PostfixError_NoExpression:
			AppView.outputLine("[오류] 후위 계산식이 주어지지 않았습니다.");
			break;
		case PostfixError_TooLongExpression:
			AppView.outputLine("[오류] 후위 계산식이 너무 길어 처리할 수 없습니다.");
			break;
		case PostfixError_TooFewValues:
			AppView.outputLine("[오류] 연산자에 비해 연산값의 수가 적습니다.");
			break;
		case PostfixError_TooManyValues:
			AppView.outputLine("[오류] 연산자에 비해 연산값의 수가 많습니다..");
			break;
		case PostfixError_DivideByZero:
			AppView.outputLine("[오류] 나눗셈의 분모가 0입니다.");
			break;
		case PostfixError_UnknownOperator:
			AppView.outputLine("[오류] 후위 계산식에 알 수 없는 연산자가 있습니다.");
			break;
		default:
			; // Nothing to do
		}
	}

	public void run() {
		AppView.outputLine("<<< 계산기 프로그램을 시작합니다 >>>");

		String infixExpression = this.inputExpression(); // 사용자로부터 수식을 입력받음
		while (infixExpression.charAt(0) != AppController.END_OF_CALCULATION) { // 입력받은 수식이 '!'이 아닌동안 반복
			try {
				int result = this.calculator().evaluate(infixExpression); // Calculator을 통해 결과값을 받아옴
				AppView.outputLine("> 계산값: " + result); // 결과값을 출력
			} catch (CalculatorException exception) { // 예외처리 - evaluate() 실행 중 오류 발생하면 객체 throw
				this.showCalculatorErrorMessage(exception.error());
			}
			infixExpression = this.inputExpression(); // 반복
		}
		AppView.outputLine("");
		AppView.outputLine("<<< 계산기 프로그램을 종료합니다 >>>");
	}
}