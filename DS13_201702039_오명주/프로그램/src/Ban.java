
public class Ban extends DictionaryByBinarySearchTree<String, Student> {
	// Constructor
	public Ban() {
		super();
	}

	private DictionaryElement<String, Student> lowestRecursively(BinaryNode<DictionaryElement<String, Student>> aRoot) {
		DictionaryElement<String, Student> lowest = aRoot.element(); // aRoot의 element()를 lowest 변수로 저장
		if (aRoot.left() != null) { // aRoot의 left가 null이 아니라면
			DictionaryElement<String, Student> lowestOfLeftSubtree = this.lowestRecursively(aRoot.left()); // LeftSubtree를 재귀로 검사
			if (lowestOfLeftSubtree.object().score() < lowest.object().score()) { // LeftSubtree의 가장 낮은 값과 aRoot의 점수를 비교
				lowest = lowestOfLeftSubtree; // 더 낮은 값을 저장
			}
		}
		if (aRoot.right() != null) { // aRoot의 right가 null이 아니라면
			DictionaryElement<String, Student> lowestOfRightSubtree = this.lowestRecursively(aRoot.right()); // RightSubtree를 재귀로 검사
			if (lowestOfRightSubtree.object().score() < lowest.object().score()) { // RightSubtree의 가장 낮은 값과 aRoot의 점수를 비교
				lowest = lowestOfRightSubtree; // 더 낮은 값을 저장
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
		return sum; // leftSum + rightSum을 반환한다
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

	// 점수를 학점으로 환산하는 함수
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

	// 평균보다 높은 학생 후를 반환하는 함수
	public int numberOfStudentsAboveAverage() {
		double average = this.average(); // average()를 통해 평균값을 구한다
		int numberOfStudentsAboveAverage = 0; // 평균이상학생수를 0으로 초기화
		Iterator<DictionaryElement<String, Student>> iterator = this.iterator(); // 반복자 객체 생성
		while (iterator.hasNext()) { // 다음 반복자가 있으면
			Student student = iterator.next().object(); // student가 반복자의 다음이 된다.
			if (student.score() >= average) { // 평균 이상이면
				numberOfStudentsAboveAverage++; // 학생수 + 1
			}
		}
		return numberOfStudentsAboveAverage; // 카운트 된 학생수를 반환
	}
}
