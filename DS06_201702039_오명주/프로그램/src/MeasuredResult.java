
public class MeasuredResult {
	// Instance variables
	private int size;
	private long _durationForAdd; // 시간 측정결과는 long
	private long _durationForMax;

	public int size() {
		return this.size;
	}

	public void setSize(int newSize) {
		this.size = newSize;
	}

	public long durationForAdd() {
		return this._durationForAdd;
	}

	public void setDurationForAdd(long newDurationForAdd) {
		this._durationForAdd = newDurationForAdd;
	}

	public long durationForMax() {
		return _durationForMax;
	}

	public void setDurationForMax(long newDurationForMax) {
		this._durationForMax = newDurationForMax;
	}

	public MeasuredResult() {
		this.setSize(0);
		this.setDurationForAdd(0);
		this.setDurationForMax(0);
	}

	public MeasuredResult(int givenSize, long givenDurationForAdd, long givenDurationForMax) {
		this.setSize(givenSize);
		this.setDurationForAdd(givenDurationForAdd);
		this.setDurationForMax(givenDurationForMax);
	}
}
