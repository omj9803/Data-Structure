
public class AppController {

	// ���
	private static final int VALID_MAX_SCORE = 100;
	private static final int VALID_MIN_SCORE = 0;
	private static final int BAN_CAPACITY = 10;

	// ����� �ν��Ͻ� ������
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

	// ������
	public AppController() {
	}

	// �Է¹��� �л� ������ ��ȿ���� Ȯ���ϴ� �Լ�
	private static boolean scoreIsValid(int aScore) {
		return (aScore >= AppController.VALID_MIN_SCORE && aScore <= AppController.VALID_MAX_SCORE);
	}

	// Student �����Ͽ� score �����ϴ� �Լ�
	private static Student inputStudent() {
		int score = AppView.inputScore(); // �л� ������ �Է¹޴´�.
		while (!AppController.scoreIsValid(score)) { // ��ȿ�� ������ �Էµɶ����� �ݺ�
			AppView.outputLine("[����]" + AppController.VALID_MIN_SCORE + " ���� �۰ų� " + AppController.VALID_MAX_SCORE
					+ " ���� Ŀ��, �������� ������ �ƴմϴ�.");
			score = AppView.inputScore();
		}
		Student student = new Student(); // student ��ü ����
		student.setScore(score); // ���� �Է�
		return student;
	}

	private void inputAndStoreStudents() {
		AppView.outputLine("");
		boolean storingAStudentWasSuccessful = true; // ���������� ó���� �Ǿ����� Ȯ���ϴ� ����
		while (storingAStudentWasSuccessful && AppView.doesContinueToInputStudent()) { // ����ó�� &&'Y'�Է�Ȯ��
			Student student = AppController.inputStudent();
			if (!this.ban().add(student)) {
				AppView.outputLine("(���) �Է¿� ������ �ֽ��ϴ�. �б޿� ���̻� �л��� ���� ������ �����ϴ�.");
				storingAStudentWasSuccessful = false; // ����� �Է¾ȵǸ� false ����
			}
		}
		AppView.outputLine("! ���� �Է��� ��Ĩ�ϴ�.");
	}

	// �б� ���� ��踦 ������ִ� �Լ�
	private void showStatistics() {
		AppView.outputLine("");
		AppView.outputLine("[�б� ���� ���]");

		AppView.outputNumberOfStudents(this.ban().size()); // �б� �л� �� ���
		AppView.outputHighestScore(this.ban().highest().score()); // �б� �ְ��� ���
		AppView.outputLowestScore(this.ban().lowest().score()); // �б� ������ ���
		AppView.outputAverageScore(this.ban().average()); // �б� ������� ���
		AppView.outputNumberOfStudentsAboveAverage(this.ban().numberOfStudentsAboveAverage()); // ����̻��� �л� �� ���
	}

	// �������� �л����� ������ִ� �Լ�
	private void showGradeCounts() {
		AppView.outputLine("");
		AppView.outputLine("[������ �л���]");

		this.setGradeCounter(this.ban().countGrades());
		AppView.outputNumberOfStudentsForGrade('A', this.gradeCounter().numberOfA()); // A �л� �� ���
		AppView.outputNumberOfStudentsForGrade('B', this.gradeCounter().numberOfB()); // B �л� �� ���
		AppView.outputNumberOfStudentsForGrade('C', this.gradeCounter().numberOfC()); // C �л� �� ���
		AppView.outputNumberOfStudentsForGrade('D', this.gradeCounter().numberOfD()); // D �л� �� ���
		AppView.outputNumberOfStudentsForGrade('F', this.gradeCounter().numberOfF()); // F �л� �� ���

	}

	// �л����� ������ ������� ����ϴ� �Լ�
	private void showStudentSortedByScore() {
		AppView.outputLine("");
		AppView.outputLine("[�л����� ������ ���]");
		this.ban().sortByScore(); // sortByScore�� ���� ����
		
		Iterator<Student> iterator = this.ban().iterator(); // �ݺ��� ����
		Student student = null;
		while (iterator.hasNext()) { // hasnext()�� null�� �ƴѵ��� �ݺ�
			student = iterator.next(); // ���� student�� ����
			AppView.outputScore(student.score()); // student�� score ���
		}
	}

	public void run() {
		AppView.outputLine("");
		AppView.outputLine("<<< �б� ���� ó���� �����մϴ� >>>");

		this.setBan(new Ban(AppController.BAN_CAPACITY)); // capacity ����
		this.inputAndStoreStudents(); // ���� �Է¹޾Ƽ� Student ��ü�� ����
		if (this.ban().isEmpty()) {
			AppView.outputLine("");
			AppView.outputLine("(���) �Էµ� ������ �����ϴ�.");
		} else {
			this.showStatistics();
			this.showGradeCounts();
			this.showStudentSortedByScore();
		}
		AppView.outputLine("");
		AppView.outputLine("<<< �б� ���� ó���� �����մϴ� >>>");
	}
}
