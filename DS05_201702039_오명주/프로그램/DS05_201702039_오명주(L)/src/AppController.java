
public class AppController {

	//	private static final int LIST_CAPACITY = 5;
	//	LinkedList������ �ִ� ũ�Ⱑ ���ǹ��ϴ�. 

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

	// �޴��� ����ϴ� �Լ�
	private void showMenu() {
		AppView.outputLine("> �ؾ� �� �۾��� ��ȣ�� �����ؾ� �մϴ�:");
		AppView.outputLine("  DoesContain=1, ElementAt=2, First=3, Last=4, OrderOf=5, ");
		AppView.outputLine("  AddTo=6, AddToFirst=7, AddToLast=8, Add=9, ");
		AppView.outputLine("  RemoveFrom=10, RemoveFirst=11, RemoveLast=12, RemoveAny=13, ReplaceAt=14, EndOfRun=99");
		AppView.output("? �۾� ��ȣ�� �Է��Ͻÿ�: ");
	}

	// Enum class�� �̿��Ͽ� ������� ���� - enum�� �ϳ��� typeó�� ���
	private MainMenu selectMenu() {
		AppView.outputLine("");
		this.showList();
		this.showMenu();
		// ������ �ƴ� �Է������� �����ϴ� try-catch��
		try {
			int selectedMenuNumber = AppView.inputInteger(); // ����ڷκ��� ������ �޴� ��ȣ�� �Է¹���
			// "NumberFormatException" can occur. It will be caught.
			MainMenu selectedMenuValue = MainMenu.value(selectedMenuNumber);
			if (selectedMenuValue == MainMenu.Error) { // Enum class�� ���� �޴� ��ȣ�� �Է����� ���
				AppView.outputLine("!����: �۾� ������ �߸��Ǿ����ϴ�. (�߸��� �޴� ��ȣ: " + selectedMenuNumber + ")"); // ���� ��¹� ���
			}
			return selectedMenuValue;

		} catch (NumberFormatException e) { // ������ �ƴ� �߸��� ������ �Է��������
			AppView.outputLine("!����: �Էµ� �޴� ��ȣ�� ���� ���ڰ� �ƴմϴ�. "); // ���� ���
			return MainMenu.Error;
		}
	}

	// Public Methods
	public void run() {
		AppView.outputLine("<<< ����Ʈ ��� Ȯ�� ���α׷��� �����մϴ� >>>");

		MainMenu selectedMenuValue = this.selectMenu(); // enum�� �ϳ��� type���� ����Ͽ� �Է°� ����
		while (selectedMenuValue != MainMenu.EndOfRun) { // ���α׷� ����Ǵ� '99'�� ������ ���� ���� �ݺ�
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
			selectedMenuValue = this.selectMenu(); // �ݺ��Ͽ� ����
		}
		this.showStatistics(); // ��踦 ���

		AppView.outputLine("");
		AppView.outputLine("<<< ����Ʈ ��� Ȯ�� ���α׷��� �����մϴ� >>>");
	}

	// �Է¹��� ������ �����ϴ��� ���θ� �˷��ִ� �Լ�
	private void doesContain() {
		AppView.outputLine("");
		AppView.outputLine("! DoesContain �۾��� �����մϴ� :");
		int score = this.inputScore(); // ������ �Է¹���
		if (this.list().doesContain(new Student(score))) { // �����ϸ� true, �������������� false�� ��ȯ
			AppView.outputLine("! �Էµ� ���� (" + score + ")�� �л��� ����Ʈ�� �����մϴ�.");
		} else {
			AppView.outputLine("! �Էµ� ���� (" + score + ")�� �л��� ����Ʈ�� �������� �ʽ��ϴ�.");
		}
	}

	// �Է¹��� ������ �л��� �����ϴ��� ���θ� �˷��ִ� �Լ�
	private void elementAt() {
		AppView.outputLine("");
		AppView.outputLine("!ElementAt �۾��� �����մϴ� :");
		int order = this.inputOrder(); // ������ �Է¹���
		if (order < 0 || order > this.list().size() - 1) { // �ش� ����Ʈ�� ��ȿ���� ���� ������ ����ó��
			AppView.outputLine("! �Էµ� ���� [" + order + "]�� �����ϴ� �л��� �����ϴ�.");
		} else { // ��ȿ�� �Է��̶�� ���
			Student student = this.list().elementAt(order);
			AppView.outputLine("! �Էµ� ���� [" + order + "]�� �л��� ������ (" + student.score() + ") �Դϴ�.");
		}
	}

	// ����Ʈ�� ù��° ���� ��ȯ�ϴ� �Լ�
	private void firstElement() {
		AppView.outputLine("");
		AppView.outputLine("! FirstElement �۾��� �����մϴ� :");
		if (this.list().isEmpty()) { // ����Ʈ�� ����ִٸ� ����ó��
			AppView.outputLine("! [�� ��] �л��� �������� �ʽ��ϴ�.");
		} else {
			Student student = this.list().first();
			AppView.outputLine("! [�� ��] �л��� ������ (" + student.score() + ") �Դϴ�.");
		}
	}

	// ����Ʈ�� ������ ���� ��ȯ�ϴ� �Լ�
	private void lastElement() {
		AppView.outputLine("");
		AppView.outputLine("!LastElement �۾��� �����մϴ� :");
		if (this.list().isEmpty()) { // ����Ʈ�� ����ִٸ� ����ó��
			AppView.outputLine("! [�� ��] �л��� �������� �ʽ��ϴ�.");
		} else {
			Student student = this.list().last();
			AppView.outputLine("! [�� ��] �л��� ������ (" + student.score() + ") �Դϴ�.");
		}
	}

	// �Է¹��� ������ �л� ������ ��ȯ�ϴ� �Լ�
	private void orderOf() {
		AppView.outputLine("");
		AppView.outputLine("!OrderOf �۾��� �����մϴ� :");
		int score = this.inputScore(); // ������ �Է¹���
		int order = this.list().orderOf(new Student(score)); // �Է¹��� ������ ���� Ȯ��
		if (order < 0) { // �Է¹��� ������ �����ϸ� ������ ��ȯ, �������������� -1�� ��ȯ
			AppView.outputLine("! �Էµ� ���� (" + score + ")�� �л��� ����Ʈ�� �������� �ʽ��ϴ�.");
		} else {
			AppView.outputLine("! �Էµ� ���� (" + score + ")�� �л��� ������ [" + order + "] �Դϴ�.");
		}
	}

	// �Է¹��� ������ �Է¹��� ������ �����ϴ� �Լ�
	private void addTo() {
		AppView.outputLine("");
		AppView.outputLine("!AddTo �۾��� �����մϴ� :");
		if (this.list().isFull()) { // ����Ʈ�� ���� á�ٸ� ����ó��
			AppView.outputLine("! ����Ʈ�� �� �� �־ ���� �۾��� �� �� �����ϴ�.");
		} else {
			int order = this.inputOrder(); // ������ �Է¹���
			if (order < 0 || order > this.list().size()) { // �Է¹��� ������ ����Ʈ�� ��ȿ���� Ȯ��
				AppView.outputLine("! �Էµ� ���� [" + order + "]�� ���� ���� [0.." + this.list().size() + "]�� ���� �ʽ��ϴ�.");
			} else { // ������ ��ȿ�ϴٸ�
				int score = this.inputScore(); // ������ �Է¹���
				if (this.list().addTo(new Student(score), order)) {
					AppView.outputLine("! �Էµ� ���� [" + order + "]�� �Էµ� ���� (" + score + ")�� �л��� �����ϴ� �۾��� �����Ͽ����ϴ�.");
				} else {
					AppView.outputLine("! �Էµ� ���� [" + order + "]�� �Էµ� ���� (" + score + ")�� �л��� �����ϴ� �۾��� �����Ͽ����ϴ�.");
				}
			}
		}
	}

	// �Է¹��� ������ ù��°�� �����ϴ� �Լ�
	private void addToFirst() {
		AppView.outputLine("");
		AppView.outputLine("!AddToFirst �۾��� �����մϴ� :");
		if (this.list().isFull()) { // ����Ʈ�� ���� á�ٸ� ����ó��
			AppView.outputLine("! ����Ʈ�� �� �� �־ ���� �۾��� �� �� �����ϴ�.");
		} else {
			int score = this.inputScore(); // ������ �Է¹���
			if (this.list().addToFirst(new Student(score))) {
				AppView.outputLine("! �Էµ� ���� (" + score + ")�� �л��� [�� ��]�� �����ϴ� �۾��� �����Ͽ����ϴ�.");
			} else {
				AppView.outputLine("! �Էµ� ���� (" + score + ")�� �л��� [�� ��]�� �����ϴ� �۾��� �����Ͽ����ϴ�.");
			}
		}
	}

	// �Է¹��� ������ �������� �����ϴ� �Լ�
	private void addToLast() {
		AppView.outputLine("");
		AppView.outputLine("!AddToLast �۾��� �����մϴ� :");
		if (this.list().isFull()) { // ����Ʈ�� ���� á�ٸ� ����ó��
			AppView.outputLine("! ����Ʈ�� �� �� �־ ���� �۾��� �� �� �����ϴ�.");
		} else {
			int score = this.inputScore(); // ������ �Է¹���
			if (this.list().addToLast(new Student(score))) {
				AppView.outputLine("! �Էµ� ���� (" + score + ")�� �л��� [�� ��]�� �����ϴ� �۾��� �����Ͽ����ϴ�.");
			} else {
				AppView.outputLine("! �Էµ� ���� (" + score + ")�� �л��� [�� ��]�� �����ϴ� �۾��� �����Ͽ����ϴ�.");
			}
		}
	}

	// �Է¹��� ������ ������ ��ġ�� �����ϴ� �Լ�
	private void add() {
		AppView.outputLine("");
		AppView.outputLine("!Add �۾��� �����մϴ� :");
		if (this.list().isFull()) {
			AppView.outputLine("! ����Ʈ�� �� �� �־ ���� �۾��� �� �� �����ϴ�.");
		} else {
			int score = this.inputScore(); // ������ �Է¹���
			if (this.list().add(new Student(score))) {
				AppView.outputLine("! �Էµ� ���� (" + score + ")�� �л��� [������ ����]�� �����ϴ� �۾��� �����Ͽ����ϴ�.");
			} else {
				AppView.outputLine("! �Էµ� ���� (" + score + ")�� �л��� [������ ����]�� �����ϴ� �۾��� �����Ͽ����ϴ�.");
			}
		}
	}

	// �Է¹��� ������ ������ �����ϴ� �Լ�
	private void removeFrom() {
		AppView.outputLine("");
		AppView.outputLine("!RemoveFrom �۾��� �����մϴ� :");
		if (this.list().isEmpty()) { // ����Ʈ�� ����ִٸ� ����ó��
			AppView.outputLine("! ����Ʈ�� ��� �־ ���� �۾��� �� �� �����ϴ�.");
		} else {
			int order = this.inputOrder(); // ������ �Է¹���
			if (order < 0 || order >= this.list().size()) { // ������ ����Ʈ ������ ��ȿ���� Ȯ��
				AppView.outputLine("! �Էµ� ���� [" + order + "]�� ���� ���� [0.." + (this.list().size() - 1) + "]�� ���� �ʽ��ϴ�.");
			}
			Student student = this.list().removeFrom(order); // ������ ��ü�� ��ȯ�Ͽ� ����
			if (student != null) { // ������ ��ü�� �ִٸ� ����
				AppView.outputLine("! �Էµ� ���� [" + order + "]���� ������ �л��� ������ (" + student.score() + ") �Դϴ�.");
			} else {
				AppView.outputLine("! �Էµ� ���� [" + order + "]���� �л��� �����ϴ� �۾��� �����Ͽ����ϴ�.");
			}

		}
	}

	// �� ���� ������ �����ϴ� �Լ�
	private void removeFirst() {
		AppView.outputLine("");
		AppView.outputLine("!RemoveFirst �۾��� �����մϴ� :");
		if (this.list().isEmpty()) { // ����Ʈ�� ����ִٸ� ����ó��
			AppView.outputLine("! ����Ʈ�� ��� �־ ���� �۾��� �� �� �����ϴ�.");
		} else {
			Student student = this.list().removeFirst(); // ������ ��ü�� ��ȯ�Ͽ� ����
			if (student != null) { // ������ ��ü�� �ִٸ� ����
				AppView.outputLine("! ������ [�� ��] �л��� ������ (" + student.score() + ") �Դϴ�.");
			} else {
				AppView.outputLine("! [�� ��] �л��� �����ϴ� �۾��� �����Ͽ����ϴ�.");
			}
		}
	}

	// ������ ������ ������ �����ϴ� �Լ�
	private void removeLast() {
		AppView.outputLine("");
		AppView.outputLine("!RemoveLast �۾��� �����մϴ� :");
		if (this.list().isEmpty()) { // ����Ʈ�� ����ִٸ� ����ó��
			AppView.outputLine("! ����Ʈ�� ��� �־ ���� �۾��� �� �� �����ϴ�.");
		} else {
			Student student = this.list().removeLast(); // ������ ��ü�� ��ȯ�Ͽ� ����
			if (student != null) { // ������ ��ü�� �ִٸ� ����
				AppView.outputLine("! ������ [�� ��] �л��� ������ (" + student.score() + ") �Դϴ�.");
			} else {
				AppView.outputLine("! [�� ��] �л��� �����ϴ� �۾��� �����Ͽ����ϴ�.");
			}
		}
	}

	// ������ ������ ������ �����ϴ� �Լ�
	private void removeAny() {
		AppView.outputLine("");
		AppView.outputLine("!RemoveAny �۾��� �����մϴ� :");
		if (this.list().isEmpty()) { // ����Ʈ�� ����ִٸ� ����ó��
			AppView.outputLine("! ����Ʈ�� ��� �־ ���� �۾��� �� �� �����ϴ�.");
		} else {
			Student student = this.list().removeAny(); // ������ ��ü�� ��ȯ�Ͽ� ����
			if (student != null) { // ������ ��ü�� �ִٸ� ����
				AppView.outputLine("! ������ [����]�� �л��� ������ (" + student.score() + ") �Դϴ�.");
			} else {
				AppView.outputLine("! [����]�� �л��� �����ϴ� �۾��� �����Ͽ����ϴ�.");
			}
		}
	}

	// �Է¹��� ������ ������ �Է¹��� ������ ��ü�ϴ� �Լ�
	private void replaceAt() {
		AppView.outputLine("");
		AppView.outputLine("!ReplaceAt �۾��� �����մϴ� :");
		if (this.list().isEmpty()) {
			AppView.outputLine("! ����Ʈ�� ��� �־ �ٲٱ� �۾��� �� �� �����ϴ�.");
		} else {
			int order = this.inputOrder(); // ������ �Է¹���
			if (order < 0 || order >= this.list().size()) { // �ش� ������ ����Ʈ�� ��ȿ���� Ȯ��
				AppView.outputLine("! �Էµ� ���� [" + order + "]�� ���� ���� [0.." + (this.list().size() - 1) + "]�� ���� �ʽ��ϴ�.");
			} else { // ��ȿ�ϴٸ�
				int score = this.inputScore(); // ������ �Է¹���
				if (this.list().replaceAt(new Student(score), order)) { // ��ü ����
					AppView.outputLine("! �־��� ���� [" + order + "]�� �л��� ������ (" + score + ")�� �ٲ�����ϴ�.");
				} else {
					AppView.outputLine("! �־��� ���� [" + order + "]�� �л��� ������ �ٲٴ� �۾��� �����Ͽ����ϴ�.");
				}
			}
		}
	}

	// ���� ����Ʈ�� �����ִ� �Լ�
	private void showList() {
		AppView.output("! ������ ����Ʈ ���ҵ�: [");
		Student student = null;
		Iterator<Student> iterator = this.list().iterator(); // iterator ��ü ���
		while (iterator.hasNext()) { // ���� ���Ұ� �����ϴ� ���� �ݺ�
			student = iterator.next(); // �ɾ� ������ ��ü ����
			AppView.output(" " + student.score()); // ������ ��ü ���� ���
		}
		AppView.outputLine(" ]");
	}

	// ��� �� ������ �����ִ� �Լ�
	private void showStatistics() {
		AppView.outputLine("");
		AppView.outputLine("> ����Ʈ ���� �Դϴ�: ");
		AppView.outputLine("! �л� ��: " + this.list().size());
		this.showList();
	}

	// ������ �Է¹޴� �Լ�
	private int inputScore() {
		int score;
		while (true) {
			try {
				AppView.output("? ������ �Է��Ͻÿ�: ");
				score = AppView.inputInteger();
				return score;
			} catch (NumberFormatException e) { // ���� �ƴ� ���� �Է¹��� ��� ����ó��
				AppView.outputLine("[����] �Էµ� ������ ������ �ƴմϴ�.");
			}
		}
	}

	// ������ �Է¹޴� �Լ�
	private int inputOrder() {
		int score;
		while (true) {
			try {
				AppView.output("? ����Ʈ������ ���� ��ȣ�� �Է��Ͻÿ�: ");
				score = AppView.inputInteger();
				return score;
			} catch (NumberFormatException e) { // ���� �ƴ� ���� �Է¹��� ��� ����ó��
				AppView.outputLine("[����] �Էµ� ���� ��ȣ�� ������ �ƴմϴ�.");
			}
		}
	}
}
