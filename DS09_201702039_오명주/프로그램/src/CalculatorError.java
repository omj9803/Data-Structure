
public enum CalculatorError {
	Undefined, // Error�� �߻��ϱ� �� ���·� �ʱ�ȭ�ϴ� ����
	
	// Infix ������ ����ϴ� ���� �߻��� �� �ִ� ���� �ڵ�
	InfixError_None,
	InfixError_NoExpression,
	InfixError_TooLongExpression,
	InfixError_MissingLeftParen,
	InfixError_MissingRightParen,
	InfixError_UnknownOperator,
	
	// Postfix ������ ����ϴ� ���� �߻��� �� �ִ� ���� �ڵ�
	PostfixError_None,
	PostfixError_NoExpression,
	PostfixError_TooLongExpression,
	PostfixError_TooFewValues,
	PostfixError_TooManyValues,
	PostfixError_DivideByZero,
	PostfixError_UnknownOperator,
	

}
