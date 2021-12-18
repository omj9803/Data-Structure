
public class CalculatorException extends Exception {
/*���� ����ϴ� ���� ������ �߻��ϸ� CalculatorException ��ü�� throw �Ѵ�.*/
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; // �Ϸ� ��ȣ ���
	// Private Instance Variables
	private CalculatorError _error; // ���� �ڵ带 ������ instance variable

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
