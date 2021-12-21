
public class Ban extends UnsortedArrayList<Student> {

	// Constructor
	public Ban() {
		super();
	}

	public Ban(int givenCapacity) {
		super(givenCapacity);
	}
	
	// ������ �������� ȯ���ϴ� �Լ�
	private static char scoreToGrade(int aScore) {
		if (aScore >= 90) {
			return 'A';
		} else if (aScore >= 80) {
			return 'B';
		} else if (aScore >= 70) {
			return 'C';
		} else if (aScore >= 60) {
			return 'D';
		} else {
			return 'F';
		}
	}

	private Student lowestRecursively(int left, int right) {
		if (left == right) { // ��� ���� �� ���� ���
			return this.elementAt(left); // left�� ��ȯ
		} else {
			Student lowestFromRights = lowestRecursively(left + 1, right); // left�� left+1, left+2, left+3 .. �� ��� ��
			if (lowestFromRights.compareTo(this.elementAt(left)) <= 0) { // �� ���� ���� ��ȯ
				return lowestFromRights; // lowestFromRights�� ������ �̰��� ��ȯ
			} else {
				return this.elementAt(left); // left��°�� �۴ٸ� �̰��� ��ȯ
			}
		}
	}

	// ������ ���� ���� �л��� ��´�
	public Student lowest() {
		if (this.isEmpty()) { // ���������
			return null; // null
		} else {
			return this.lowestRecursively(0, this.size() - 1); // ��͸� �̿��Ͽ� ���� ���� ���� �л� ��ȯ
		}
	}

	private Student highestRecursively(int left, int right) {
		if (left == right) { // ��� ���� �� ���� ���
			return this.elementAt(left); // left�� ��ȯ
		} else {
			Student highestFromRights = highestRecursively(left + 1, right); // left�� left+1, left+2 .. ��
			if (highestFromRights.compareTo(this.elementAt(left)) >= 0) { // �� ū ���� ��ȯ
				return highestFromRights; // highestFromRights �� ���ų� ũ�ٸ� ��ȯ
			} else {
				return this.elementAt(left); // left ���� �� ũ�ٸ� ��ȯ
			}
		}
	}

	// ������ ���� ���� �л��� ��´�
	public Student highest() {
		if (this.isEmpty()) { // ���������
			return null; // null
		} else {
			return this.highestRecursively(0, this.size() - 1); // ��͸� �̿��Ͽ� ���� ���� ���� �л� ��ȯ
		}
	}

	private int sumOfScoreRecursively(int left, int right) {
		int mid = (left + right) / 2; // �߰������� ����
		if (left == right) { // ó���� ���� ������
			return this.elementAt(left).score(); // left�� ���ھ ��ȯ
		} else {
			int leftSum = this.sumOfScoreRecursively(left, mid); // leftSum�� left ~ mid �� ��������� ���� ��
			int rightSum = this.sumOfScoreRecursively(mid + 1, right); // rightSum�� mid + 1 ~ right�� ��������� ���� ��
			return (leftSum + rightSum); // leftSum + rightSum�� ��ȯ�Ѵ�
		}
	}

	// �б��� �л����� ������ �հ踦 ��´�
	public int sum() {
		if (this.isEmpty()) { // ����ִٸ�
			return 0; // ���� 0
		} else {
			return this.sumOfScoreRecursively(0, this.size() - 1); // ��͸� �̿��Ͽ� 0~(size-1)�� ��ȯ
		}
	}

	// �б��� ���� ��� ������ ��´�
	public double average() {
		if (this.isEmpty()) {
			return 0;
		} else {
			return ((double) this.sum()) / ((double) this.size());
		}
	}
	
	// ��պ��� ���� �л� �ĸ� ��ȯ�ϴ� �Լ�
	public int numberOfStudentsAboveAverage() {
		double average = this.average(); // average()�� ���� ��հ��� ���Ѵ�
		int numberOfStudentsAboveAverage = 0; // ����̻��л����� 0���� �ʱ�ȭ
		Iterator<Student> iterator = this.iterator(); // �ݺ��� ��ü ����
		while (iterator.hasNext()) { // ���� �ݺ��ڰ� ������
			Student student = iterator.next(); // student�� �ݺ����� ������ �ȴ�.
			if (student.score() >= average) { // ��� �̻��̸�
				numberOfStudentsAboveAverage++; // �л��� + 1
			}
		}
		return numberOfStudentsAboveAverage; // ī��Ʈ �� �л����� ��ȯ
	}

	// p��°�� q��° Student element�� ��ü
	private void swap(int p, int q) { 
		Student temp = this.elementAt(p); // Student temp�� p��° �迭
		this.setElementsAt(p, this.elementAt(q)); // p��° �迭�� q��° �л��� �ְ�
		this.setElementsAt(q, temp); // q��° �迭�� p���� �迭�̾��� temp�� �ִ´�.
	}

	private int partition(int left, int right) {
		int pivot = left; // pivot�� left�� ����
		int toRight = left; // ���������� �� toRight�� left ��ġ�� ����
		int toLeft = right + 1; // �������� �� toLeft�� right+1 ��ġ�� ����
		do {
			do {
				toRight++;
			} while (this.elementAt(toRight).score() < this.elementAt(pivot).score()); // Left���� Right�� �� score ��ġ ����
			do {
				toLeft--;
			} while (this.elementAt(toLeft).score() > this.elementAt(pivot).score()); // Right���� Left�� �� score ��ġ ����
			if (toRight < toLeft) { // toRight < toLeft���
				this.swap(toRight, toLeft); // �ΰ��� �迭�� �ٲ۴�.
			}
		} while (toRight < toLeft); // toRight > toLeft�� �Ǵ� ���� Ż��
		this.swap(pivot, toLeft); // pivot�� toLeft�� �ٲ۴�.
		return toLeft; // pivot ��ġ�� toLeft �̴�.
	}

	private void quicksortRecursively(int left, int right) { // ������
		if (left < right) { // left<right�̸�
			int mid = this.partition(left, right); // ��Ƽ�� ���� pivot ��ġ
			this.quicksortRecursively(left, mid - 1); // �������� ���� �ٽ� �������Ѵ�.
			this.quicksortRecursively(mid + 1, right); // �������� ���� �ٽ� �������Ѵ�.
		}
	}

	// �б��� �л����� ���� ������ �����Ѵ�
	public void sortByScore() {
		if (this.size() > 1) { // �迭�� 1���� ������
			int maxLoc = 0; // �ִ밪 ��ġ�� 0���� �ʱ�ȭ
			for (int i = 1; i < this.size(); i++) { // �迭 ������ �ݺ�
				if (this.elementAt(i).score() > this.elementAt(maxLoc).score()) { // i��° ������ maxLoc�� �������� ũ�ٸ�
					maxLoc = i; // maxLoc�� i�� �����Ѵ�
				}
			}
			this.swap(maxLoc, this.size() - 1); // �ִ밪�� maxLoc�� �� �ڷ� ������.
			this.quicksortRecursively(0, this.size() - 2); // �ִ밪�� �� 0 ~ this.size()-2 �� ������ �Ѵ�.
		}
	}

	// �б� �������� �л����� �����ϰ�, �� ����� ������ GradeCounter ��ü ��´�
	public GradeCounter countGrades() {
		// step 1 : GradeCounter ��ü ����
		// ������ counter�� ��� 0���� �ʱ�ȭ�ȴ�.
		GradeCounter gradeCounter = new GradeCounter(); // gradeCounter ����
		// step 2 : �л����� ������ ����
		// �л����� Iterator�� ����Ͽ� ���ʷ� ��´�.
		Iterator<Student> iterator = this.iterator(); // �ݺ��� ����
		while (iterator.hasNext()) { // �ݺ����� ������ �ִٸ�
			char grade; // grade ���ڿ� ����
			Student student = iterator.next(); // student�� �ݺ����� next�� ��
			grade = Ban.scoreToGrade(student.score()); // �л��� ������ �������� ��ȯ�ϰ� grade�� �ִ´�.
			gradeCounter.count(grade); // grade�� �޾� ī��Ʈ�� �Ѵ�.
		}
		return gradeCounter; // gradeCounter�� ��ȯ
	}
}
