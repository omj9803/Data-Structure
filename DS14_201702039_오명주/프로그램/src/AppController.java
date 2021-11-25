
public class AppController implements VisitDelegate<Integer, Integer> {

	// Constants
	private static final int DEFAULT_DATA_SIZE = 10;
	private Dictionary<Integer, Integer> _dictionary;
	private Integer[] _list;

	// Getters/Setters
	private Dictionary<Integer, Integer> dictionary() {
		return this._dictionary;
	}
	private void setDictionary(Dictionary<Integer, Integer> newDictionary) {
		this._dictionary = newDictionary;
	}
	private Integer[] list() {
		return this._list;
	}
	private void setList(Integer[] newList) {
		this._list = newList;
	}

	@Override
	public void visitForSortedOrder(Integer aKey, Integer anObj, int aLevel) { // VisitDelegate()���� ������ �Լ�
		AppView.outputLine(String.format("%3d (%2d)", aKey, anObj)); // Visit���� �� �� ���� ����ڰ� ����
	}

	@Override
	public void visitForReverseOfSortedOrder(Integer aKey, Integer anObj, int aLevel) { // VisitDelegate()���� ������ �Լ�
		if (aLevel == 1) { // aLevel�� ���� ����� �ٸ��� �ϰ� ����
			AppView.output(String.format("%7s", "Root: "));
		} else {
			AppView.output(String.format("%7s", ""));
		}
		for (int i = 1; i < aLevel; i++) {
			AppView.output(String.format("%7s", ""));
		}
		AppView.outputLine(String.format("%3d (% 2d)", aKey, anObj));
	}

	public void run() {
		AppView.outputLine("<<< �����˻�Ʈ���� ������ ���������� ���԰� ���� >>>");
		AppView.outputLine("");
		this.setDictionary(new DictionaryByBinarySearchTree<Integer, Integer>()); // DictionaryBST����
		this.dictionary().setVisitDelegate(this); // visitDelegate���� ��ü�� �������� �˷��ش�
		this.setList(DataGenerator.randomListWithoutDuplication(DEFAULT_DATA_SIZE)); // ��������
		this.addToDictionaryAndShowShape(); // addTo���� + ���԰��� ���
		this.showDictionaryInSortedOrderByCallBack(); // ��ͷ� SortedOrder ���
		this.showDictionaryInSortedOrderByIterator(); // �ݺ��ڷ� SortedOrder ���
		this.setList(DataGenerator.randomListWithoutDuplication(DEFAULT_DATA_SIZE)); // ��������
		this.removeFromDictionaryAndShowShape(); // removeFrom����
		AppView.outputLine("<<< ���� >>>");
	}

	private void showDictionary(String aTitlePrefix) {
		AppView.outputLine("> " + aTitlePrefix + "�����˻�Ʈ�� ���� :");
		if (this.dictionary().isEmpty()) {
			AppView.outputLine("  EMPTY");
		} else {
			this.dictionary().scanInReverseOfSortedOrder();
		}
		AppView.outputLine("");
	}

	private void addToDictionaryAndShowShape() { // addTo����
		AppView.outputLine("[���� ���������� �����˻�Ʈ�� ������ ��ȭ ]");
		this.showDictionary("������ �����ϱ� ���� ");
		for (int i = 0; i < this.list().length; i++) {
			Integer currentKey = this.list()[i];
			Integer currentObj = Integer.valueOf(i);
			this.dictionary().addKeyAndObject(currentKey, currentObj);
			this.showDictionary(String.format("Key=%d (Object=%d) ���Ҹ� ������ ���� ", currentKey, currentObj));
		}

	}

	private void removeFromDictionaryAndShowShape() { // removeFrom����
		AppView.outputLine("[���� ���������� �����˻�Ʈ�� ������ ��ȭ ]");
		this.showDictionary("������ �����ϱ� ���� ");
		for (int i = 0; i < this.list().length; i++) {
			Integer currentKey = this.list()[i];
			Integer currentObj = this.dictionary().removeObjectForKey(currentKey);
			this.showDictionary(String.format("Key=%d (Object=%d) ���Ҹ� ������ ���� ", currentKey, currentObj));
		}
	}

	private void showDictionaryInSortedOrderByCallBack() {
		AppView.outputLine("[\"Call Back\"�� ����Ͽ� ������ ������ ���� ]");
		this.dictionary().scanInSortedOrder(); // Visit�� �����ϴ� callBack �Լ� ȣ��
		AppView.outputLine("");
	}

	private void showDictionaryInSortedOrderByIterator() {
		AppView.outputLine("[\"Iterator\"�� ����Ͽ� ������ ������ ���� ]");
		Iterator<DictionaryElement<Integer, Integer>> iterator = this.dictionary().iterator(); // �ݺ��� ����
		while (iterator.hasNext()) { // hasNext-> next ���Ұ� �ִٸ�
			DictionaryElement<Integer, Integer> dictionaryElement = iterator.next();
			AppView.outputLine(String.format("%3d (%2d)", dictionaryElement.key(), dictionaryElement.object()));
		}
		AppView.outputLine("");
	}
}
