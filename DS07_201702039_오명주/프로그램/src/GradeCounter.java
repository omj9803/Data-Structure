
public class GradeCounter {
	private int _numberOfA, _numberOfB, _numberOfC, _numberOfD, _numberOfF;

	public int numberOfA() {
		return _numberOfA;
	}

	public int numberOfB() {
		return _numberOfB;
	}

	public int numberOfC() {
		return _numberOfC;
	}

	public int numberOfD() {
		return _numberOfD;
	}

	public int numberOfF() {
		return _numberOfF;
	}

	private void setNumberOfA(int newA) {
		this._numberOfA = newA;
	}

	private void setNumberOfB(int newB) {
		this._numberOfB = newB;
	}

	private void setNumberOfC(int newC) {
		this._numberOfC = newC;
	}

	private void setNumberOfD(int newD) {
		this._numberOfD = newD;
	}

	private void setNumberOfF(int newF) {
		this._numberOfF = newF;
	}

	// Constructor
	public GradeCounter() {
		this.setNumberOfA(0);
		this.setNumberOfB(0);
		this.setNumberOfC(0);
		this.setNumberOfD(0);
		this.setNumberOfF(0);
	}

	public void count(char aGrade) {
		switch (aGrade) {
		case 'A':
			this.setNumberOfA(this.numberOfA() + 1);
			break;
		case 'B':
			this.setNumberOfB(this.numberOfB() + 1);
			break;
		case 'C':
			this.setNumberOfC(this.numberOfC() + 1);
			break;
		case 'D':
			this.setNumberOfD(this.numberOfD() + 1);
			break;
		case 'F':
			this.setNumberOfF(this.numberOfF() + 1);
			break;
		}
	}

}
