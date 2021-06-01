
public class Ban extends DictionaryByBinarySearchTree<String, Student> {
	// Constructor
	public Ban() {
		super();
	}

	private DictionaryElement<String, Student> lowestRecursively(BinaryNode<DictionaryElement<String, Student>> aRoot) {
		DictionaryElement<String, Student> lowest = aRoot.element(); // aRoot�� element()�� lowest ������ ����
		if (aRoot.left() != null) { // aRoot�� left�� null�� �ƴ϶��
			DictionaryElement<String, Student> lowestOfLeftSubtree = this.lowestRecursively(aRoot.left()); // LeftSubtree�� ��ͷ� �˻�
			if (lowestOfLeftSubtree.object().score() < lowest.object().score()) { // LeftSubtree�� ���� ���� ���� aRoot�� ������ ��
				lowest = lowestOfLeftSubtree; // �� ���� ���� ����
			}
		}
		if (aRoot.right() != null) { // aRoot�� right�� null�� �ƴ϶��
			DictionaryElement<String, Student> lowestOfRightSubtree = this.lowestRecursively(aRoot.right()); // RightSubtree�� ��ͷ� �˻�
			if (lowestOfRightSubtree.object().score() < lowest.object().score()) { // RightSubtree�� ���� ���� ���� aRoot�� ������ ��
				lowest = lowestOfRightSubtree; // �� ���� ���� ����
			}
		}
		return lowest;
	}

	public Student lowest() {
		if (this.isEmpty()) {
			return null;
		} else {
			DictionaryElement<String, Student> lowest = this.lowestRecursively(this.root());
			return lowest.object();
		}
	}

	private DictionaryElement<String, Student> highestRecursively(
			BinaryNode<DictionaryElement<String, Student>> aRoot) {
		DictionaryElement<String, Student> highest = aRoot.element();
		if (aRoot.left() != null) {
			DictionaryElement<String, Student> highestOfLeftSubtree = this.highestRecursively(aRoot.left());
			if (highestOfLeftSubtree.object().score() > highest.object().score()) {
				highest = highestOfLeftSubtree;
			}
		}
		if (aRoot.right() != null) {
			DictionaryElement<String, Student> highestOfRightSubtree = this.highestRecursively(aRoot.right());
			if (highestOfRightSubtree.object().score() > highest.object().score()) {
				highest = highestOfRightSubtree;
			}
		}
		return highest;
	}

	public Student highest() {
		if (this.isEmpty()) {
			return null;
		} else {
			DictionaryElement<String, Student> highest = this.highestRecursively(this.root());
			return highest.object();
		}
	}

	private int sumOfScoresRecursively(BinaryNode<DictionaryElement<String, Student>> aRoot) {
		int sum = 0;
		sum += aRoot.element().object().score();
		if (aRoot.left() != null) {
			int leftChildsum = sumOfScoresRecursively(aRoot.left());
			sum += leftChildsum;
		}
		if (aRoot.right() != null) {
			int rightChildsum = sumOfScoresRecursively(aRoot.right());
			sum += rightChildsum;
		}
		return sum; // leftSum + rightSum�� ��ȯ�Ѵ�
	}

	private int sumOfScores() {
		return this.sumOfScoresRecursively(this.root());
	}

	public double average() {
		if (this.isEmpty()) {
			return 0;
		} else {
			return ((double) this.sumOfScores()) / ((double) this.size());
		}
	}

	public GradeCounter countGrades() {
		char grade;
		GradeCounter gradeCounter = new GradeCounter();
		Iterator<DictionaryElement<String, Student>> iterator = this.iterator();
		while (iterator.hasNext()) {
			grade = scoreToGrade(iterator.next().object().score());
			gradeCounter.count(grade);
		}
		return gradeCounter;
	}

	public Student[] studentsSortedByScore() {
//		@SuppressWarnings("unchecked")
		Student[] students = new Student[this.size()];
		Iterator<DictionaryElement<String, Student>> iterator = this.iterator();
		for (int i = 0; iterator.hasNext(); i++) {
			students[i] = iterator.next().object();
		}
		Sort<Student> quicksort = new QuickSort<Student>();
		quicksort.sort(students, students.length);
		return students;
	}

	// ������ �������� ȯ���ϴ� �Լ�
	public static char scoreToGrade(int aScore) {
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

	// ��պ��� ���� �л� �ĸ� ��ȯ�ϴ� �Լ�
	public int numberOfStudentsAboveAverage() {
		double average = this.average(); // average()�� ���� ��հ��� ���Ѵ�
		int numberOfStudentsAboveAverage = 0; // ����̻��л����� 0���� �ʱ�ȭ
		Iterator<DictionaryElement<String, Student>> iterator = this.iterator(); // �ݺ��� ��ü ����
		while (iterator.hasNext()) { // ���� �ݺ��ڰ� ������
			Student student = iterator.next().object(); // student�� �ݺ����� ������ �ȴ�.
			if (student.score() >= average) { // ��� �̻��̸�
				numberOfStudentsAboveAverage++; // �л��� + 1
			}
		}
		return numberOfStudentsAboveAverage; // ī��Ʈ �� �л����� ��ȯ
	}
}
