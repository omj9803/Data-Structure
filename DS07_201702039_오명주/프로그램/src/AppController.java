
public class AppController {

	// 상수
	private static final int VALID_MAX_SCORE = 100;
	private static final int VALID_MIN_SCORE = 0;
	private static final int BAN_CAPACITY = 10;

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
	}

	// 입력받은 학생 점수가 유효한지 확인하는 함수
	private static boolean scoreIsValid(int aScore) {
		return (aScore >= AppController.VALID_MIN_SCORE && aScore <= AppController.VALID_MAX_SCORE);
	}

	// Student 생성하여 score 저장하는 함수
	private static Student inputStudent() {
		int score = AppView.inputScore(); // 학생 점수를 입력받는다.
		while (!AppController.scoreIsValid(score)) { // 유효한 점수가 입력될때까지 반복
			AppView.outputLine("[오류]" + AppController.VALID_MIN_SCORE + " 보다 작거나 " + AppController.VALID_MAX_SCORE
					+ " 보다 커서, 정상적인 점수가 아닙니다.");
			score = AppView.inputScore();
		}
		Student student = new Student(); // student 객체 생성
		student.setScore(score); // 점수 입력
		return student;
	}

	private void inputAndStoreStudents() {
		AppView.outputLine("");
		boolean storingAStudentWasSuccessful = true; // 정상적으로 처리가 되었는지 확인하는 변수
		while (storingAStudentWasSuccessful && AppView.doesContinueToInputStudent()) { // 정상처리 &&'Y'입력확인
			Student student = AppController.inputStudent();
			if (!this.ban().add(student)) {
				AppView.outputLine("(경고) 입력에 오류가 있습니다. 학급에 더이상 학생을 넣을 공간이 없습니다.");
				storingAStudentWasSuccessful = false; // 제대로 입력안되면 false 저장
			}
		}
		AppView.outputLine("! 성적 입력을 마칩니다.");
	}

	// 학급 성적 통계를 출력해주는 함수
	private void showStatistics() {
		AppView.outputLine("");
		AppView.outputLine("[학급 성적 통계]");

		AppView.outputNumberOfStudents(this.ban().size()); // 학급 학생 수 출력
		AppView.outputHighestScore(this.ban().highest().score()); // 학급 최고점 출력
		AppView.outputLowestScore(this.ban().lowest().score()); // 학급 최저점 출력
		AppView.outputAverageScore(this.ban().average()); // 학급 평균점수 출력
		AppView.outputNumberOfStudentsAboveAverage(this.ban().numberOfStudentsAboveAverage()); // 평균이상인 학생 수 출력
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
		this.ban().sortByScore(); // sortByScore을 통해 정렬
		
		Iterator<Student> iterator = this.ban().iterator(); // 반복자 생성
		Student student = null;
		while (iterator.hasNext()) { // hasnext()가 null이 아닌동안 반복
			student = iterator.next(); // 다음 student를 저장
			AppView.outputScore(student.score()); // student의 score 출력
		}
	}

	public void run() {
		AppView.outputLine("");
		AppView.outputLine("<<< 학급 성적 처리를 시작합니다 >>>");

		this.setBan(new Ban(AppController.BAN_CAPACITY)); // capacity 설정
		this.inputAndStoreStudents(); // 성적 입력받아서 Student 객체로 저장
		if (this.ban().isEmpty()) {
			AppView.outputLine("");
			AppView.outputLine("(경고) 입력된 성적이 없습니다.");
		} else {
			this.showStatistics();
			this.showGradeCounts();
			this.showStudentSortedByScore();
		}
		AppView.outputLine("");
		AppView.outputLine("<<< 학급 성적 처리를 종료합니다 >>>");
	}
}
