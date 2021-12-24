
public class Student {
	// Constants
	private static final int DEFAULT_SCORE = 0;
	// Private Instance Variables
	private int _score;

	// Getters/Setters
	public int score() {
		return this._score;
	}

	public void setScore(int newScore) {
		this._score = newScore;
	}

	// Constructor
	public Student() {
		this.setScore(Student.DEFAULT_SCORE);
	}

	public Student(int givenScore) {
		this.setScore(givenScore);
	}

	@Override
	public boolean equals(Object aStudent) {
		if (aStudent.getClass() != Student.class) {
			return false;
		} else {
			return (this.score() == ((Student) aStudent).score());
		}
	}
}
