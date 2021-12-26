
public class Coin {
	// ���
	private static final int DEFAULT_VALUE = 0;

	// private instance variables
	private int _value; // ������ �ݾ�

	// ������
	// ��ü ������ �־��� ���� ���� ���
	public Coin() {
		// default ���� 0���� ����
		this._value = DEFAULT_VALUE;
	}

	// ��ü ������ �־��� ���� �ִ� ���
	public Coin(int givenValue) {
		this._value = givenValue;
	}

	// ���� �Լ�
	// getter / setter
	public int value() {
		return this._value; // ���� �� ��ȯ
	}

	public void setValue(int newValue) {
		this._value = newValue;	// �Է¹��� ������ �� ����
	}

	// ��ü ���� ���ϴ� �Լ�
	@Override
	public boolean equals(Object otherCoin) {
		if (otherCoin.getClass() != Coin.class) { // �־��� ��ü�� Ŭ���� ������ �޾ƿ� Coin Ŭ������ �������� Ȯ��
			return false;
		} else { // Coin�� class�� �����ϰ� Coin class�� ����ȯ ����
			return (this.value() == ((Coin) otherCoin).value()); // class�� �����ϱ� ������ �ش� ��ü�� ���� ��
		}
	}
}
