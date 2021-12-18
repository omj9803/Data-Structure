
public class CalculatorException extends Exception {
/*수식 계산하는 동안 오류가 발생하면 CalculatorException 객체를 throw 한다.*/
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; // 일련 번호 사용
	// Private Instance Variables
	private CalculatorError _error; // 오류 코드를 저장할 instance variable

	// Getters/Setters
	public CalculatorError error() {
		return this._error;
	}

	public void setError(CalculatorError newError) {
		this._error = newError;
	}

	// Constructors
	public CalculatorException() {
		this.setError(CalculatorError.Undefined);
	}
	public CalculatorException(CalculatorError givenError) {
		this.setError(givenError);
	}

}
