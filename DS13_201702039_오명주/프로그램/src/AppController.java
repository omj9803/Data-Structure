
public class AppController {

	// 상수
	private static final int VALID_MAX_SCORE = 100;
	private static final int VALID_MIN_SCORE = 0;
	private static final int VALID_STUDENTID_LENGTH = 9;

	// 비공개 인스턴스 변수들
	private Ban _ban;
	private GradeCounter _gradeCounter;

	// Getters/Setters
	private Ban ban() {
		return this._ban;
	}

	private void setBan(Ban newBan) {
		this._ban = newBan;
	}

	private GradeCounter gradeCounter() {
		return this._gradeCounter;
	}

	private void setGradeCounter(GradeCounter newGradeCounter) {
		this._gradeCounter = newGradeCounter;
	}

	// 생성자
	public AppController() {
		setBan(new Ban());
	}

	// 입력받은 학생 점수가 유효한지 확인하는 함수
	private static boolean scoreIsValid(int aScore) {
		return (aScore >= AppController.VALID_MIN_SCORE && aScore <= AppController.VALID_MAX_SCORE);
	}

	// 입력받은 학번이 유효한지 확인하는 함수
	private static boolean studentIDIsValid(String aStudentId) {
		return (aStudentId.length() <= AppController.VALID_STUDENTID_LENGTH);
	}

	// Student 생성하여 score 저장하는 함수
	private static DictionaryElement<String, Student> inputStudent() {
		DictionaryElement<String, Student> element = new DictionaryElement<String, Student>(); // 정보를 저장할 element 선언
		String studentId = AppView.inputStudentId(); // 학번 입력
		int score = AppView.inputScore(); // 학생 점수 입력

		if (AppController.studentIDIsValid(studentId) && AppController.scoreIsValid(score)) { // 입력받은 studentId, score 모두 true라면
			Student student = new Student(); // student 객체 생성
			student.setScore(score); // 점수 저장
			element.setKey(studentId); // 학번 저장
			element.setObject(student);
		}
		if (!AppController.studentIDIsValid(studentId)) { // 학번의 길이를 확인하여 오류문 출력
			AppView.outputLine("(오류) 학번의 길이가 너무 깁니다. 최대 " + AppController.VALID_STUDENTID_LENGTH + " 입니다.");
		}
		if (!AppController.scoreIsValid(score)) { // 성적 숫자를 확인하여 오류문 출력
			AppView.outputLine("(오류) 성적이 " + AppController.VALID_MIN_SCORE + " 보다 작거나 " + AppController.VALID_MAX_SCORE
					+ " 보다 커서, 정상적인 점수가 아닙니다.");

		}
		return element;
	}

	private void inputAndStoreStudents() {
		AppView.outputLine("");
		boolean storingAStudentWasSuccessful = true; // 정상적으로 처리가 되었는지 확인하는 변수
		while (storingAStudentWasSuccessful && AppView.doesContinueToInputStudent()) { // 정상처리 &&'Y'입력확인
			DictionaryElement<String, Student> element = AppController.inputStudent(); // 입력 받아온 element 변수에 저장
			this.ban().addKeyAndObject(element.key(), element.object()); // ban에 element의 key와 object를 삽입
			AppView.outputLine("");
		}
		AppView.outputLine("<성적 입력을 마칩니다.>");
	}

	// 학급 성적 통계를 출력해주는 함수
	private void showStatistics() {
		AppView.outputLine("");
		AppView.outputLine("[학급 성적 처리 결과]");

		AppView.outputTotalNumberOfStudents(this.ban().size()); // 전체 학생 수 출력
		AppView.outputAverageScore(this.ban().average()); // 학급 평균점수 출력
		AppView.outputNumberOfStudentsAboveAverage(this.ban().numberOfStudentsAboveAverage()); // 평균이상인 학생 수 출력
		AppView.outputHighestScore(this.ban().highest().score()); // 학급 최고점 출력
		AppView.outputLowestScore(this.ban().lowest().score()); // 학급 최저점 출력

	}

	// 학점별로 학생수를 출력해주는 함수
	private void showGradeCounts() {
		AppView.outputLine("");
		AppView.outputLine("[학점별 학생수]");
		this.setGradeCounter(this.ban().countGrades());
		AppView.outputNumberOfStudentsForGrade('A', this.gradeCounter().numberOfA()); // A 학생 수 출력
		AppView.outputNumberOfStudentsForGrade('B', this.gradeCounter().numberOfB()); // B 학생 수 출력
		AppView.outputNumberOfStudentsForGrade('C', this.gradeCounter().numberOfC()); // C 학생 수 출력
		AppView.outputNumberOfStudentsForGrade('D', this.gradeCounter().numberOfD()); // D 학생 수 출력
		AppView.outputNumberOfStudentsForGrade('F', this.gradeCounter().numberOfF()); // F 학생 수 출력

	}

	// 학생들의 성적을 순서대로 출력하는 함수
	private void showStudentSortedByScore() {
		AppView.outputLine("");
		AppView.outputLine("[학생들의 성적순 목록]");

		DictionaryElement<String, Student> element = new DictionaryElement<String, Student>();
		Student student = null;
		Student students[] = this.ban().studentsSortedByScore(); // 학생 성적 오름차순으로 정렬
		for (int i = students.length - 1; i >= 0; i--) {
			Iterator<DictionaryElement<String, Student>> iterator = this.ban().iterator(); // 반복자 생성
			while (iterator.hasNext()) { // iterator의 hasNext가 존재한다면
				element = iterator.next(); // element에 원소 저장
				student = element.object(); // element의 object를 student 변수에 저장
				if (students[i].score() == student.score()) { // 만약 원소를 찾는다면
					AppView.outputStudentInfo(element.key(), student.score(), Ban.scoreToGrade(student.score())); // 출력
				}
			}
		}
	}

	private void showStudentList() {
		AppView.outputLine("");
		AppView.outputLine("[학생 목록]");

		Iterator<DictionaryElement<String, Student>> iterator = this.ban().iterator(); // 반복자 생성
		DictionaryElement<String, Student> element = new DictionaryElement<String, Student>();
		Student student = null;
		while (iterator.hasNext()) { // hasNext()가 null이 아닌동안 반복
			element = iterator.next();
			student = element.object(); // 다음 student를 저장
			AppView.outputStudentList(element.key(), student.score());
		}
	}

	public void run() {
		AppView.outputLine("");
		AppView.outputLine("<<< 성적 처리를 시작합니다 >>>");

		this.inputAndStoreStudents(); // 성적 입력받아서 element 객체로 저장
		if (this.ban().isEmpty()) {
			AppView.outputLine("");
			AppView.outputLine("(오류) 학생 정보가 전혀 입력되지 않았습니다.");
		} else {
			this.showStudentList(); // 학생 리스트 출력
			this.showStatistics(); // 통계정보 출력
			this.showGradeCounts(); // 학점별로 학생 수 출력
			this.showStudentSortedByScore(); // 성적순으로 학생 출력
		}
		AppView.outputLine("");
		AppView.outputLine("<<< 성적 처리를 종료합니다 >>>");
	}
}
