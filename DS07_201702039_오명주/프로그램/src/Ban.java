
public class Ban extends UnsortedArrayList<Student> {

	// Constructor
	public Ban() {
		super();
	}

	public Ban(int givenCapacity) {
		super(givenCapacity);
	}
	
	// 점수를 학점으로 환산하는 함수
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
		if (left == right) { // 모든 값을 다 비교한 경우
			return this.elementAt(left); // left를 반환
		} else {
			Student lowestFromRights = lowestRecursively(left + 1, right); // left와 left+1, left+2, left+3 .. 를 계속 비교
			if (lowestFromRights.compareTo(this.elementAt(left)) <= 0) { // 더 작은 값을 반환
				return lowestFromRights; // lowestFromRights가 작으면 이것을 반환
			} else {
				return this.elementAt(left); // left번째가 작다면 이것을 반환
			}
		}
	}

	// 성적이 가장 낮은 학생을 얻는다
	public Student lowest() {
		if (this.isEmpty()) { // 비어있으면
			return null; // null
		} else {
			return this.lowestRecursively(0, this.size() - 1); // 재귀를 이용하여 성적 가장 낮은 학생 반환
		}
	}

	private Student highestRecursively(int left, int right) {
		if (left == right) { // 모든 값을 다 비교한 경우
			return this.elementAt(left); // left를 반환
		} else {
			Student highestFromRights = highestRecursively(left + 1, right); // left를 left+1, left+2 .. 비교
			if (highestFromRights.compareTo(this.elementAt(left)) >= 0) { // 더 큰 값을 반환
				return highestFromRights; // highestFromRights 가 같거나 크다면 반환
			} else {
				return this.elementAt(left); // left 값이 더 크다면 반환
			}
		}
	}

	// 성적이 가장 높은 학생을 얻는다
	public Student highest() {
		if (this.isEmpty()) { // 비어있으면
			return null; // null
		} else {
			return this.highestRecursively(0, this.size() - 1); // 재귀를 이용하여 성적 가장 높은 학생 반환
		}
	}

	private int sumOfScoreRecursively(int left, int right) {
		int mid = (left + right) / 2; // 중간지점을 설정
		if (left == right) { // 처음과 끝이 같으면
			return this.elementAt(left).score(); // left의 스코어를 반환
		} else {
			int leftSum = this.sumOfScoreRecursively(left, mid); // leftSum은 left ~ mid 를 재귀적으로 합한 것
			int rightSum = this.sumOfScoreRecursively(mid + 1, right); // rightSum은 mid + 1 ~ right를 재귀적으로 합한 것
			return (leftSum + rightSum); // leftSum + rightSum을 반환한다
		}
	}

	// 학급의 학생들의 성적의 합계를 얻는다
	public int sum() {
		if (this.isEmpty()) { // 비어있다면
			return 0; // 합은 0
		} else {
			return this.sumOfScoreRecursively(0, this.size() - 1); // 재귀를 이용하여 0~(size-1)합 반환
		}
	}

	// 학급의 성적 평균 성적을 얻는다
	public double average() {
		if (this.isEmpty()) {
			return 0;
		} else {
			return ((double) this.sum()) / ((double) this.size());
		}
	}
	
	// 평균보다 높은 학생 후를 반환하는 함수
	public int numberOfStudentsAboveAverage() {
		double average = this.average(); // average()를 통해 평균값을 구한다
		int numberOfStudentsAboveAverage = 0; // 평균이상학생수를 0으로 초기화
		Iterator<Student> iterator = this.iterator(); // 반복자 객체 생성
		while (iterator.hasNext()) { // 다음 반복자가 있으면
			Student student = iterator.next(); // student가 반복자의 다음이 된다.
			if (student.score() >= average) { // 평균 이상이면
				numberOfStudentsAboveAverage++; // 학생수 + 1
			}
		}
		return numberOfStudentsAboveAverage; // 카운트 된 학생수를 반환
	}

	// p번째와 q번째 Student element를 교체
	private void swap(int p, int q) { 
		Student temp = this.elementAt(p); // Student temp는 p번째 배열
		this.setElementsAt(p, this.elementAt(q)); // p번째 배열에 q번째 학생을 넣고
		this.setElementsAt(q, temp); // q번째 배열에 p번재 배열이었던 temp를 넣는다.
	}

	private int partition(int left, int right) {
		int pivot = left; // pivot을 left로 설정
		int toRight = left; // 오른쪽으로 갈 toRight는 left 위치에 지정
		int toLeft = right + 1; // 왼쪽으로 갈 toLeft는 right+1 위치에 지정
		do {
			do {
				toRight++;
			} while (this.elementAt(toRight).score() < this.elementAt(pivot).score()); // Left에서 Right로 갈 score 위치 선정
			do {
				toLeft--;
			} while (this.elementAt(toLeft).score() > this.elementAt(pivot).score()); // Right에서 Left로 갈 score 위치 선정
			if (toRight < toLeft) { // toRight < toLeft라면
				this.swap(toRight, toLeft); // 두개의 배열을 바꾼다.
			}
		} while (toRight < toLeft); // toRight > toLeft가 되는 순간 탈출
		this.swap(pivot, toLeft); // pivot과 toLeft를 바꾼다.
		return toLeft; // pivot 위치가 toLeft 이다.
	}

	private void quicksortRecursively(int left, int right) { // 퀵정렬
		if (left < right) { // left<right이면
			int mid = this.partition(left, right); // 파티션 후의 pivot 위치
			this.quicksortRecursively(left, mid - 1); // 나누어진 반을 다시 퀵정렬한다.
			this.quicksortRecursively(mid + 1, right); // 나누어진 반을 다시 퀵정렬한다.
		}
	}

	// 학급의 학생들을 성적 순으로 정렬한다
	public void sortByScore() {
		if (this.size() > 1) { // 배열이 1개라도 있으면
			int maxLoc = 0; // 최대값 위치를 0으로 초기화
			for (int i = 1; i < this.size(); i++) { // 배열 끝까지 반복
				if (this.elementAt(i).score() > this.elementAt(maxLoc).score()) { // i번째 성적이 maxLoc의 성적보다 크다면
					maxLoc = i; // maxLoc은 i로 설정한다
				}
			}
			this.swap(maxLoc, this.size() - 1); // 최대값인 maxLoc을 맨 뒤로 보낸다.
			this.quicksortRecursively(0, this.size() - 2); // 최대값을 뺀 0 ~ this.size()-2 를 퀵정렬 한다.
		}
	}

	// 학급 학점별로 학생수를 세게하고, 그 결과를 가지는 GradeCounter 객체 얻는다
	public GradeCounter countGrades() {
		// step 1 : GradeCounter 객체 생성
		// 학점별 counter는 모두 0으로 초기화된다.
		GradeCounter gradeCounter = new GradeCounter(); // gradeCounter 선언
		// step 2 : 학생들의 학점을 센다
		// 학생들은 Iterator를 사용하여 차례로 얻는다.
		Iterator<Student> iterator = this.iterator(); // 반복자 선언
		while (iterator.hasNext()) { // 반복자의 다음이 있다면
			char grade; // grade 문자열 선언
			Student student = iterator.next(); // student는 반복자의 next가 됨
			grade = Ban.scoreToGrade(student.score()); // 학생의 성적을 학점으로 반환하고 grade에 넣는다.
			gradeCounter.count(grade); // grade를 받아 카운트를 한다.
		}
		return gradeCounter; // gradeCounter를 반환
	}
}
