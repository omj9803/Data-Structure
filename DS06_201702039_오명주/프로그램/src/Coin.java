
public class Coin implements Comparable<Coin> {

	private static final int DEFAULT_VALUE = 0; // Default 금액
	private int _value; // 동전 금액

	public Coin() {
		this._value = DEFAULT_VALUE; // 값을 받지 못한 Coin의 동전금액은 DEFAULT = 0
	}

	public Coin(int givenValue) {
		this._value = givenValue; // 값을 받은 Coin의 동전금액은 주어진 값
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
