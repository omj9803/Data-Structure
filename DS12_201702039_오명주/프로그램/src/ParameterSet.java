public class ParameterSet {
	private int _startingSize;
	private int _numberOfSizeIncreasingSteps;
	private int _incrementSize;

// public Getters & Setters
	public int startingSize() {
		return this._startingSize;
	}
	public void setStartingSize(int newStaringSize) {
		this._startingSize = newStaringSize;
	}
	public int numberOfSizeIncreasingSteps() {
		return this._numberOfSizeIncreasingSteps;
	}
	public void setNumberOfSizeIncreasingSteps(int newNumberOfSizeIncreasingSteps) {
		this._numberOfSizeIncreasingSteps = newNumberOfSizeIncreasingSteps;
	}
	public int incrementSize() {
		return this._incrementSize;
	}
	public void setIncrementSize(int newIncrementSize) {
		this._incrementSize = newIncrementSize;
	}
	public int maxDataSize() {
		return (this.startingSize() + (this.incrementSize() * (this.numberOfSizeIncreasingSteps() - 1)));
	}

	// »ý¼ºÀÚ
	public ParameterSet(int givenStartingSize, int givenNumberOfSizeIncreasingSteps, int givenIncrementSize) {
		this.setIncrementSize(givenIncrementSize);
		this.setNumberOfSizeIncreasingSteps(givenNumberOfSizeIncreasingSteps);
		this.setStartingSize(givenStartingSize);
	}
}