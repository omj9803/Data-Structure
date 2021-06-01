
public class AppController {

	// ���
	private static final int VALID_MAX_SCORE = 100;
	private static final int VALID_MIN_SCORE = 0;
	private static final int VALID_STUDENTID_LENGTH = 9;

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
		setBan(new Ban());
	}

	// �Է¹��� �л� ������ ��ȿ���� Ȯ���ϴ� �Լ�
	private static boolean scoreIsValid(int aScore) {
		return (aScore >= AppController.VALID_MIN_SCORE && aScore <= AppController.VALID_MAX_SCORE);
	}

	// �Է¹��� �й��� ��ȿ���� Ȯ���ϴ� �Լ�
	private static boolean studentIDIsValid(String aStudentId) {
		return (aStudentId.length() <= AppController.VALID_STUDENTID_LENGTH);
	}

	// Student �����Ͽ� score �����ϴ� �Լ�
	private static DictionaryElement<String, Student> inputStudent() {
		DictionaryElement<String, Student> element = new DictionaryElement<String, Student>(); // ������ ������ element ����
		String studentId = AppView.inputStudentId(); // �й� �Է�
		int score = AppView.inputScore(); // �л� ���� �Է�

		if (AppController.studentIDIsValid(studentId) && AppController.scoreIsValid(score)) { // �Է¹��� studentId, score ��� true���
			Student student = new Student(); // student ��ü ����
			student.setScore(score); // ���� ����
			element.setKey(studentId); // �й� ����
			element.setObject(student);
		}
		if (!AppController.studentIDIsValid(studentId)) { // �й��� ���̸� Ȯ���Ͽ� ������ ���
			AppView.outputLine("(����) �й��� ���̰� �ʹ� ��ϴ�. �ִ� " + AppController.VALID_STUDENTID_LENGTH + " �Դϴ�.");
		}
		if (!AppController.scoreIsValid(score)) { // ���� ���ڸ� Ȯ���Ͽ� ������ ���
			AppView.outputLine("(����) ������ " + AppController.VALID_MIN_SCORE + " ���� �۰ų� " + AppController.VALID_MAX_SCORE
					+ " ���� Ŀ��, �������� ������ �ƴմϴ�.");

		}
		return element;
	}

	private void inputAndStoreStudents() {
		AppView.outputLine("");
		boolean storingAStudentWasSuccessful = true; // ���������� ó���� �Ǿ����� Ȯ���ϴ� ����
		while (storingAStudentWasSuccessful && AppView.doesContinueToInputStudent()) { // ����ó�� &&'Y'�Է�Ȯ��
			DictionaryElement<String, Student> element = AppController.inputStudent(); // �Է� �޾ƿ� element ������ ����
			this.ban().addKeyAndObject(element.key(), element.object()); // ban�� element�� key�� object�� ����
			AppView.outputLine("");
		}
		AppView.outputLine("<���� �Է��� ��Ĩ�ϴ�.>");
	}

	// �б� ���� ��踦 ������ִ� �Լ�
	private void showStatistics() {
		AppView.outputLine("");
		AppView.outputLine("[�б� ���� ó�� ���]");

		AppView.outputTotalNumberOfStudents(this.ban().size()); // ��ü �л� �� ���
		AppView.outputAverageScore(this.ban().average()); // �б� ������� ���
		AppView.outputNumberOfStudentsAboveAverage(this.ban().numberOfStudentsAboveAverage()); // ����̻��� �л� �� ���
		AppView.outputHighestScore(this.ban().highest().score()); // �б� �ְ��� ���
		AppView.outputLowestScore(this.ban().lowest().score()); // �б� ������ ���

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

		DictionaryElement<String, Student> element = new DictionaryElement<String, Student>();
		Student student = null;
		Student students[] = this.ban().studentsSortedByScore(); // �л� ���� ������������ ����
		for (int i = students.length - 1; i >= 0; i--) {
			Iterator<DictionaryElement<String, Student>> iterator = this.ban().iterator(); // �ݺ��� ����
			while (iterator.hasNext()) { // iterator�� hasNext�� �����Ѵٸ�
				element = iterator.next(); // element�� ���� ����
				student = element.object(); // element�� object�� student ������ ����
				if (students[i].score() == student.score()) { // ���� ���Ҹ� ã�´ٸ�
					AppView.outputStudentInfo(element.key(), student.score(), Ban.scoreToGrade(student.score())); // ���
				}
			}
		}
	}

	private void showStudentList() {
		AppView.outputLine("");
		AppView.outputLine("[�л� ���]");

		Iterator<DictionaryElement<String, Student>> iterator = this.ban().iterator(); // �ݺ��� ����
		DictionaryElement<String, Student> element = new DictionaryElement<String, Student>();
		Student student = null;
		while (iterator.hasNext()) { // hasNext()�� null�� �ƴѵ��� �ݺ�
			element = iterator.next();
			student = element.object(); // ���� student�� ����
			AppView.outputStudentList(element.key(), student.score());
		}
	}

	public void run() {
		AppView.outputLine("");
		AppView.outputLine("<<< ���� ó���� �����մϴ� >>>");

		this.inputAndStoreStudents(); // ���� �Է¹޾Ƽ� element ��ü�� ����
		if (this.ban().isEmpty()) {
			AppView.outputLine("");
			AppView.outputLine("(����) �л� ������ ���� �Էµ��� �ʾҽ��ϴ�.");
		} else {
			this.showStudentList(); // �л� ����Ʈ ���
			this.showStatistics(); // ������� ���
			this.showGradeCounts(); // �������� �л� �� ���
			this.showStudentSortedByScore(); // ���������� �л� ���
		}
		AppView.outputLine("");
		AppView.outputLine("<<< ���� ó���� �����մϴ� >>>");
	}
}
