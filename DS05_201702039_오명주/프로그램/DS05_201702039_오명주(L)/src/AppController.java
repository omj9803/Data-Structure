
public class AppController {

	//	private static final int LIST_CAPACITY = 5;
	//	LinkedList에서는 최대 크기가 무의미하다. 

	// Instance variables
	private LinkedList<Student> _list;

	// Getter/Setter
	private LinkedList<Student> list() {
		return this._list;
	}

	private void setList(LinkedList<Student> newList) {
		this._list = newList;
	}

	// Constructor
	public AppController() {
		this.setList(new LinkedList<Student>());
	}

	// 메뉴를 출력하는 함수
	private void showMenu() {
		AppView.outputLine("> 해야 할 작업의 번호를 선택해야 합니다:");
		AppView.outputLine("  DoesContain=1, ElementAt=2, First=3, Last=4, OrderOf=5, ");
		AppView.outputLine("  AddTo=6, AddToFirst=7, AddToLast=8, Add=9, ");
		AppView.outputLine("  RemoveFrom=10, RemoveFirst=11, RemoveLast=12, RemoveAny=13, ReplaceAt=14, EndOfRun=99");
		AppView.output("? 작업 번호를 입력하시오: ");
	}

	// Enum class를 이용하여 입출력을 관리 - enum을 하나의 type처럼 사용
	private MainMenu selectMenu() {
		AppView.outputLine("");
		this.showList();
		this.showMenu();
		// 정수가 아닌 입력포맷을 관리하는 try-catch문
		try {
			int selectedMenuNumber = AppView.inputInteger(); // 사용자로부터 수행할 메뉴 번호를 입력받음
			// "NumberFormatException" can occur. It will be caught.
			MainMenu selectedMenuValue = MainMenu.value(selectedMenuNumber);
			if (selectedMenuValue == MainMenu.Error) { // Enum class에 없는 메뉴 번호를 입력했을 경우
				AppView.outputLine("!오류: 작업 선택이 잘못되었습니다. (잘못된 메뉴 번호: " + selectedMenuNumber + ")"); // 오류 출력문 출력
			}
			return selectedMenuValue;

		} catch (NumberFormatException e) { // 정수가 아닌 잘못된 포맷을 입력했을경우
			AppView.outputLine("!오류: 입력된 메뉴 번호가 정수 숫자가 아닙니다. "); // 오류 출력
			return MainMenu.Error;
		}
	}

	// Public Methods
	public void run() {
		AppView.outputLine("<<< 리스트 기능 확인 프로그램을 시작합니다 >>>");

		MainMenu selectedMenuValue = this.selectMenu(); // enum을 하나의 type으로 사용하여 입력값 관리
		while (selectedMenuValue != MainMenu.EndOfRun) { // 프로그램 종료되는 '99'가 들어오지 않은 동안 반복
			switch (selectedMenuValue) {
			case DoesContain:
				this.doesContain();
				break;
			case ElementAt:
				this.elementAt();
				break;
			case First:
				this.firstElement();
				break;
			case Last:
				this.lastElement();
				break;
			case OrderOf:
				this.orderOf();
				break;
			case AddTo:
				this.addTo();
				break;
			case AddToFirst:
				this.addToFirst();
				break;
			case AddToLast:
				this.addToLast();
				break;
			case Add:
				this.add();
				break;
			case RemoveFrom:
				this.removeFrom();
				break;
			case RemoveFirst:
				this.removeFirst();
				break;
			case RemoveLast:
				this.removeLast();
				break;
			case RemoveAny:
				this.removeAny();
				break;
			case ReplaceAt:
				this.replaceAt();
				break;
			default:
				break;
			}
			selectedMenuValue = this.selectMenu(); // 반복하여 실행
		}
		this.showStatistics(); // 통계를 출력

		AppView.outputLine("");
		AppView.outputLine("<<< 리스트 기능 확인 프로그램을 종료합니다 >>>");
	}

	// 입력받은 점수가 존재하는지 여부를 알려주는 함수
	private void doesContain() {
		AppView.outputLine("");
		AppView.outputLine("! DoesContain 작업을 실행합니다 :");
		int score = this.inputScore(); // 점수를 입력받음
		if (this.list().doesContain(new Student(score))) { // 존재하면 true, 존재하지않으면 false를 반환
			AppView.outputLine("! 입력된 점수 (" + score + ")의 학생이 리스트에 존재합니다.");
		} else {
			AppView.outputLine("! 입력된 점수 (" + score + ")의 학생이 리스트에 존재하지 않습니다.");
		}
	}

	// 입력받은 순서에 학생이 존재하는지 여부를 알려주는 함수
	private void elementAt() {
		AppView.outputLine("");
		AppView.outputLine("!ElementAt 작업을 실행합니다 :");
		int order = this.inputOrder(); // 순서를 입력받음
		if (order < 0 || order > this.list().size() - 1) { // 해당 리스트에 유효하지 않은 정수면 예외처리
			AppView.outputLine("! 입력된 순서 [" + order + "]에 존재하는 학생은 없습니다.");
		} else { // 유효한 입력이라면 출력
			Student student = this.list().elementAt(order);
			AppView.outputLine("! 입력된 순서 [" + order + "]의 학생의 점수는 (" + student.score() + ") 입니다.");
		}
	}

	// 리스트의 첫번째 원소 반환하는 함수
	private void firstElement() {
		AppView.outputLine("");
		AppView.outputLine("! FirstElement 작업을 실행합니다 :");
		if (this.list().isEmpty()) { // 리스트가 비어있다면 예외처리
			AppView.outputLine("! [맨 앞] 학생이 존재하지 않습니다.");
		} else {
			Student student = this.list().first();
			AppView.outputLine("! [맨 앞] 학생의 점수는 (" + student.score() + ") 입니다.");
		}
	}

	// 리스트의 마지막 원소 반환하는 함수
	private void lastElement() {
		AppView.outputLine("");
		AppView.outputLine("!LastElement 작업을 실행합니다 :");
		if (this.list().isEmpty()) { // 리스트가 비어있다면 예외처리
			AppView.outputLine("! [맨 뒤] 학생이 존재하지 않습니다.");
		} else {
			Student student = this.list().last();
			AppView.outputLine("! [맨 뒤] 학생의 점수는 (" + student.score() + ") 입니다.");
		}
	}

	// 입력받은 점수의 학생 순서를 반환하는 함수
	private void orderOf() {
		AppView.outputLine("");
		AppView.outputLine("!OrderOf 작업을 실행합니다 :");
		int score = this.inputScore(); // 점수를 입력받음
		int order = this.list().orderOf(new Student(score)); // 입력받은 정수의 순서 확인
		if (order < 0) { // 입력받은 점수가 존재하면 순서를 반환, 존재하지않으면 -1을 반환
			AppView.outputLine("! 입력된 점수 (" + score + ")의 학생이 리스트에 존재하지 않습니다.");
		} else {
			AppView.outputLine("! 입력된 점수 (" + score + ")의 학생의 순서는 [" + order + "] 입니다.");
		}
	}

	// 입력받은 순서에 입력받은 점수를 삽입하는 함수
	private void addTo() {
		AppView.outputLine("");
		AppView.outputLine("!AddTo 작업을 실행합니다 :");
		if (this.list().isFull()) { // 리스트가 가득 찼다면 예외처리
			AppView.outputLine("! 리스트가 꽉 차 있어서 삽입 작업을 할 수 없습니다.");
		} else {
			int order = this.inputOrder(); // 순서를 입력받음
			if (order < 0 || order > this.list().size()) { // 입력받은 순서가 리스트에 유효한지 확인
				AppView.outputLine("! 입력된 순서 [" + order + "]가 정상 범위 [0.." + this.list().size() + "]에 있지 않습니다.");
			} else { // 순서가 유효하다면
				int score = this.inputScore(); // 점수를 입력받음
				if (this.list().addTo(new Student(score), order)) {
					AppView.outputLine("! 입력된 순서 [" + order + "]에 입력된 점수 (" + score + ")의 학생을 삽입하는 작업을 성공하였습니다.");
				} else {
					AppView.outputLine("! 입력된 순서 [" + order + "]에 입력된 점수 (" + score + ")의 학생을 삽입하는 작업을 실패하였습니다.");
				}
			}
		}
	}

	// 입력받은 점수를 첫번째에 삽입하는 함수
	private void addToFirst() {
		AppView.outputLine("");
		AppView.outputLine("!AddToFirst 작업을 실행합니다 :");
		if (this.list().isFull()) { // 리스트가 가득 찼다면 예외처리
			AppView.outputLine("! 리스트가 꽉 차 있어서 삽입 작업을 할 수 없습니다.");
		} else {
			int score = this.inputScore(); // 점수를 입력받음
			if (this.list().addToFirst(new Student(score))) {
				AppView.outputLine("! 입력된 점수 (" + score + ")의 학생을 [맨 앞]에 삽입하는 작업을 성공하였습니다.");
			} else {
				AppView.outputLine("! 입력된 점수 (" + score + ")의 학생을 [맨 앞]에 삽입하는 작업을 실패하였습니다.");
			}
		}
	}

	// 입력받은 점수를 마지막에 삽입하는 함수
	private void addToLast() {
		AppView.outputLine("");
		AppView.outputLine("!AddToLast 작업을 실행합니다 :");
		if (this.list().isFull()) { // 리스트가 가득 찼다면 예외처리
			AppView.outputLine("! 리스트가 꽉 차 있어서 삽입 작업을 할 수 없습니다.");
		} else {
			int score = this.inputScore(); // 점수를 입력받음
			if (this.list().addToLast(new Student(score))) {
				AppView.outputLine("! 입력된 점수 (" + score + ")의 학생을 [맨 뒤]에 삽입하는 작업을 성공하였습니다.");
			} else {
				AppView.outputLine("! 입력된 점수 (" + score + ")의 학생을 [맨 뒤]에 삽입하는 작업을 실패하였습니다.");
			}
		}
	}

	// 입력받은 점수를 임의의 위치에 삽입하는 함수
	private void add() {
		AppView.outputLine("");
		AppView.outputLine("!Add 작업을 실행합니다 :");
		if (this.list().isFull()) {
			AppView.outputLine("! 리스트가 꽉 차 있어서 삽입 작업을 할 수 없습니다.");
		} else {
			int score = this.inputScore(); // 점수를 입력받음
			if (this.list().add(new Student(score))) {
				AppView.outputLine("! 입력된 점수 (" + score + ")의 학생을 [임의의 순서]에 삽입하는 작업을 성공하였습니다.");
			} else {
				AppView.outputLine("! 입력된 점수 (" + score + ")의 학생을 [임의의 순서]에 삽입하는 작업을 실패하였습니다.");
			}
		}
	}

	// 입력받은 순서의 점수를 삭제하는 함수
	private void removeFrom() {
		AppView.outputLine("");
		AppView.outputLine("!RemoveFrom 작업을 실행합니다 :");
		if (this.list().isEmpty()) { // 리스트가 비어있다면 예외처리
			AppView.outputLine("! 리스트가 비어 있어서 삭제 작업을 할 수 없습니다.");
		} else {
			int order = this.inputOrder(); // 순서를 입력받음
			if (order < 0 || order >= this.list().size()) { // 순서가 리스트 내에서 유효한지 확인
				AppView.outputLine("! 입력된 순서 [" + order + "]가 정상 범위 [0.." + (this.list().size() - 1) + "]에 있지 않습니다.");
			}
			Student student = this.list().removeFrom(order); // 삭제한 객체를 반환하여 저장
			if (student != null) { // 삭제한 객체가 있다면 실행
				AppView.outputLine("! 입력된 순서 [" + order + "]에서 삭제된 학생의 성적은 (" + student.score() + ") 입니다.");
			} else {
				AppView.outputLine("! 입력된 순서 [" + order + "]에서 학생을 삭제하는 작업을 실패하였습니다.");
			}

		}
	}

	// 맨 앞의 점수를 삭제하는 함수
	private void removeFirst() {
		AppView.outputLine("");
		AppView.outputLine("!RemoveFirst 작업을 실행합니다 :");
		if (this.list().isEmpty()) { // 리스트가 비어있다면 예외처리
			AppView.outputLine("! 리스트가 비어 있어서 삭제 작업을 할 수 없습니다.");
		} else {
			Student student = this.list().removeFirst(); // 삭제한 객체를 반환하여 저장
			if (student != null) { // 삭제한 객체가 있다면 실행
				AppView.outputLine("! 삭제된 [맨 앞] 학생의 성적은 (" + student.score() + ") 입니다.");
			} else {
				AppView.outputLine("! [맨 앞] 학생을 삭제하는 작업을 실패하였습니다.");
			}
		}
	}

	// 마지막 순서의 점수를 삭제하는 함수
	private void removeLast() {
		AppView.outputLine("");
		AppView.outputLine("!RemoveLast 작업을 실행합니다 :");
		if (this.list().isEmpty()) { // 리스트가 비어있다면 예외처리
			AppView.outputLine("! 리스트가 비어 있어서 삭제 작업을 할 수 없습니다.");
		} else {
			Student student = this.list().removeLast(); // 삭제한 객체를 반환하여 저장
			if (student != null) { // 삭제한 객체가 있다면 실행
				AppView.outputLine("! 삭제된 [맨 뒤] 학생의 성적은 (" + student.score() + ") 입니다.");
			} else {
				AppView.outputLine("! [맨 뒤] 학생을 삭제하는 작업을 실패하였습니다.");
			}
		}
	}

	// 임의의 순서의 점수를 삭제하는 함수
	private void removeAny() {
		AppView.outputLine("");
		AppView.outputLine("!RemoveAny 작업을 실행합니다 :");
		if (this.list().isEmpty()) { // 리스트가 비어있다면 예외처리
			AppView.outputLine("! 리스트가 비어 있어서 삭제 작업을 할 수 없습니다.");
		} else {
			Student student = this.list().removeAny(); // 삭제한 객체를 반환하여 저장
			if (student != null) { // 삭제한 객체가 있다면 실행
				AppView.outputLine("! 삭제된 [임의]의 학생의 성적은 (" + student.score() + ") 입니다.");
			} else {
				AppView.outputLine("! [임의]의 학생을 삭제하는 작업을 실패하였습니다.");
			}
		}
	}

	// 입력받은 순서의 점수를 입력받은 점수로 교체하는 함수
	private void replaceAt() {
		AppView.outputLine("");
		AppView.outputLine("!ReplaceAt 작업을 실행합니다 :");
		if (this.list().isEmpty()) {
			AppView.outputLine("! 리스트가 비어 있어서 바꾸기 작업을 할 수 없습니다.");
		} else {
			int order = this.inputOrder(); // 순서를 입력받음
			if (order < 0 || order >= this.list().size()) { // 해당 순서가 리스트에 유효한지 확인
				AppView.outputLine("! 입력된 순서 [" + order + "]가 정상 범위 [0.." + (this.list().size() - 1) + "]에 있지 않습니다.");
			} else { // 유효하다면
				int score = this.inputScore(); // 점수를 입력받음
				if (this.list().replaceAt(new Student(score), order)) { // 교체 실행
					AppView.outputLine("! 주어진 순서 [" + order + "]의 학생의 점수가 (" + score + ")로 바뀌었습니다.");
				} else {
					AppView.outputLine("! 주어진 순서 [" + order + "]의 학생의 점수를 바꾸는 작업을 실패하였습니다.");
				}
			}
		}
	}

	// 현재 리스트를 보여주는 함수
	private void showList() {
		AppView.output("! 현재의 리스트 원소들: [");
		Student student = null;
		Iterator<Student> iterator = this.list().iterator(); // iterator 객체 사용
		while (iterator.hasNext()) { // 다음 원소가 존재하는 동안 반복
			student = iterator.next(); // 걸어 나가며 객체 저장
			AppView.output(" " + student.score()); // 저장한 객체 값을 출력
		}
		AppView.outputLine(" ]");
	}

	// 통계 및 정보를 보여주는 함수
	private void showStatistics() {
		AppView.outputLine("");
		AppView.outputLine("> 리스트 정보 입니다: ");
		AppView.outputLine("! 학생 수: " + this.list().size());
		this.showList();
	}

	// 점수를 입력받는 함수
	private int inputScore() {
		int score;
		while (true) {
			try {
				AppView.output("? 점수를 입력하시오: ");
				score = AppView.inputInteger();
				return score;
			} catch (NumberFormatException e) { // 정수 아닌 값을 입력받은 경우 예외처리
				AppView.outputLine("[오류] 입력된 점수는 정수가 아닙니다.");
			}
		}
	}

	// 순서를 입력받는 함수
	private int inputOrder() {
		int score;
		while (true) {
			try {
				AppView.output("? 리스트에서의 순서 번호를 입력하시오: ");
				score = AppView.inputInteger();
				return score;
			} catch (NumberFormatException e) { // 정수 아닌 값을 입력받은 경우 예외처리
				AppView.outputLine("[오류] 입력된 순서 번호는 정수가 아닙니다.");
			}
		}
	}
}
