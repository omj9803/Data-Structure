
public class Coin implements Comparable<Coin> {

	private static final int DEFAULT_VALUE = 0; // Default �ݾ�
	private int _value; // ���� �ݾ�

	public Coin() {
		this._value = DEFAULT_VALUE; // ���� ���� ���� Coin�� �����ݾ��� DEFAULT = 0
	}

	public Coin(int givenValue) {
		this._value = givenValue; // ���� ���� Coin�� �����ݾ��� �־��� ��
	}

	public int value() {
		return this._value; // getter of value
	}

	public void setValue(int newValue) {
		this._value = newValue; // setter of value
	}

	public interface Comparable {
		public int compareTo(Coin aCoin);

	}

	@Override
	public int compareTo(Coin aCoin) {
		if (this.value() < aCoin.value()) {
			return -1;
		} else if (this.value() > aCoin.value()) {
			return 1;
		} else {
			return 0;
		}
	}
}
