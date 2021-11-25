
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
	public void visitForSortedOrder(Integer aKey, Integer anObj, int aLevel) { // VisitDelegate()에서 정의한 함수
		AppView.outputLine(String.format("%3d (%2d)", aKey, anObj)); // Visit했을 때 할 일을 사용자가 정의
	}

	@Override
	public void visitForReverseOfSortedOrder(Integer aKey, Integer anObj, int aLevel) { // VisitDelegate()에서 정의한 함수
		if (aLevel == 1) { // aLevel에 따른 출력을 다르게 하고 있음
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
		AppView.outputLine("<<< 이진검색트리로 구현된 사전에서의 삽입과 삭제 >>>");
		AppView.outputLine("");
		this.setDictionary(new DictionaryByBinarySearchTree<Integer, Integer>()); // DictionaryBST정의
		this.dictionary().setVisitDelegate(this); // visitDelegate에게 객체가 누구인지 알려준다
		this.setList(DataGenerator.randomListWithoutDuplication(DEFAULT_DATA_SIZE)); // 난수생성
		this.addToDictionaryAndShowShape(); // addTo실행 + 삽입과정 출력
		this.showDictionaryInSortedOrderByCallBack(); // 재귀로 SortedOrder 출력
		this.showDictionaryInSortedOrderByIterator(); // 반복자로 SortedOrder 출력
		this.setList(DataGenerator.randomListWithoutDuplication(DEFAULT_DATA_SIZE)); // 난수생성
		this.removeFromDictionaryAndShowShape(); // removeFrom실행
		AppView.outputLine("<<< 종료 >>>");
	}

	private void showDictionary(String aTitlePrefix) {
		AppView.outputLine("> " + aTitlePrefix + "이진검색트리 사전 :");
		if (this.dictionary().isEmpty()) {
			AppView.outputLine("  EMPTY");
		} else {
			this.dictionary().scanInReverseOfSortedOrder();
		}
		AppView.outputLine("");
	}

	private void addToDictionaryAndShowShape() { // addTo실행
		AppView.outputLine("[삽입 과정에서의 이진검색트리 사전의 변화 ]");
		this.showDictionary("삽입을 시작하기 전의 ");
		for (int i = 0; i < this.list().length; i++) {
			Integer currentKey = this.list()[i];
			Integer currentObj = Integer.valueOf(i);
			this.dictionary().addKeyAndObject(currentKey, currentObj);
			this.showDictionary(String.format("Key=%d (Object=%d) 원소를 삽입한 후의 ", currentKey, currentObj));
		}

	}

	private void removeFromDictionaryAndShowShape() { // removeFrom실행
		AppView.outputLine("[삭제 과정에서의 이진검색트리 사전의 변화 ]");
		this.showDictionary("삭제를 시작하기 전의 ");
		for (int i = 0; i < this.list().length; i++) {
			Integer currentKey = this.list()[i];
			Integer currentObj = this.dictionary().removeObjectForKey(currentKey);
			this.showDictionary(String.format("Key=%d (Object=%d) 원소를 삭제한 후의 ", currentKey, currentObj));
		}
	}

	private void showDictionaryInSortedOrderByCallBack() {
		AppView.outputLine("[\"Call Back\"을 사용하여 보여준 사전의 내용 ]");
		this.dictionary().scanInSortedOrder(); // Visit를 포함하는 callBack 함수 호출
		AppView.outputLine("");
	}

	private void showDictionaryInSortedOrderByIterator() {
		AppView.outputLine("[\"Iterator\"를 사용하여 보여준 사전의 내용 ]");
		Iterator<DictionaryElement<Integer, Integer>> iterator = this.dictionary().iterator(); // 반복자 생성
		while (iterator.hasNext()) { // hasNext-> next 원소가 있다면
			DictionaryElement<Integer, Integer> dictionaryElement = iterator.next();
			AppView.outputLine(String.format("%3d (%2d)", dictionaryElement.key(), dictionaryElement.object()));
		}
		AppView.outputLine("");
	}
}
