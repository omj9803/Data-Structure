
public class ArrayList<E> implements Stack<E> {

	// Constant
	private static final int DEFAULT_CAPACITY = 1000;

	// private instance variables
	private int _capacity;
	private int _size;
	private E[] _elements;

	// Getters/Setters
	public int capacity() {
		return this._capacity;
	}

	private void setCapacity(int newCapacity) {
		this._capacity = newCapacity;
	}

	@Override
	public int size() {
		return this._size;
	}

	public void setSize(int newSize) {
		this._size = newSize;
	}

	private E[] elements() {
		return this._elements;
	}

	private void setElements(E[] newElements) {
		this._elements = newElements;
	}

	// Constructor
	public ArrayList() {
		this(ArrayList.DEFAULT_CAPACITY); // 다른 생성자 사용
	}

	@SuppressWarnings("unchecked")
	public ArrayList(int givenCapacity) {
		this.setCapacity(givenCapacity);
		this.setElements((E[]) new Object[this.capacity()]);
	}

	// Private Methods
	private void makeRoomAt(int aPosition) {
		for (int i = this.size(); i > aPosition; i--) { // size~aPosition반복
			this.elements()[i] = this.elements()[i - 1]; // i-1번째 원소를 i에 저장
		}
	}

	private void removeGapAt(int aPosition) {
		for (int i = aPosition + 1; i < this.size(); i++) { // aPosition+1~size반복
			this.elements()[i - 1] = this.elements()[i]; // i번째 원소를 i-1에 저장
		}
		this.elements()[this.size() - 1] = null; // 마지막 원소 삭제
	}

	// public Methods
	@Override
	public boolean isFull() { // 배열이 가득 찼는지 확인
		return (this.capacity() == this.size());
	}

	@Override
	public boolean isEmpty() { // 배열이 비어있는가 확인
		return (this.size() == 0);
	}

	public boolean doesContain(E anElement) { // 존재 유무 확인
		return (this.orderOf(anElement) >= 0); // anElement가 orderOf로 인해 있다는게 학인되면 true를 반환
	}

	public int orderOf(E anElement) {
		int order = -1; // 순서 변수 -1 로 선언
		for (int index = 0; index < this.size() && order < 0; index++) { // 0~사이즈 & order이 -1일때 반복
			if (this.elements()[index].equals(anElement)) { // index번째 배열과 anElement가 같으면
				order = index; // order 를 index로 설정
			}
		}
		return order; // 같은 배열을 찾은 순서인 order를 반환
	}

	public E elementAt(int anOrder) { // anOrder번째 원소 반환
		if (anOrder < 0 || anOrder >= this.size()) { // anOrder이 범위 밖이면
			return null; // null 반환
		} else {
			return this.elements()[anOrder]; // anOrder번째 원소 반환
		}
	}

	public void setElementsAt(int anOrder, E anElement) { // anOrder번째에 원소삽입
		if (anOrder < 0 || anOrder >= this.size()) { // 입력한 anOrder의 유효성 판단
			return;
		} else {
			this.elements()[anOrder] = anElement; // anOrder번째 배열 anElement로 설정
		}
	}

	public boolean addTo(E anElement, int anOrder) { // anOrder번째에 원소삽입
		if (this.isFull()) { // 배열이 가득 찼다면
			return false; // false를 반환
		} else if (anOrder < 0 || anOrder > this.size()) { // 입력한 anOrder의 유효성 판단
			return false; // 유효하지 않다면 false를 반환
		} else {
			this.makeRoomAt(anOrder); // anOrder 순서에 삽입할 자리 마련
			this.elements()[anOrder] = anElement; // 해당 순서에 원소 삽입
			this.setSize(this.size() + 1); // size set
			return true;
		}
	}

	public boolean addToFirst(E anElement) { // 배열 첫번째에 원소삽입
		return addTo(anElement, 0);
	}

	public boolean addToLast(E anElement) { // 배열 마지막에 원소삽입
		return addTo(anElement, this.size());
	}

	public E removeFrom(int anOrder) {
		if (anOrder < 0 || anOrder >= this.size()) { // 입력된 anOrder 유효성 판단
			return null; // 유효하지 않다면 null 반환
		} else {
			E removedElement = this.elements()[anOrder]; // 삭제할 원소를 removedElement에 저장
			this.removeGapAt(anOrder); // anOrder 이후 원소 한칸씩 앞으로 저장
			this.setSize(this.size() - 1); // size set
			return removedElement;
		}
	}

	public E removeFirst() { // 첫번째 원소 삭제
		return removeFrom(0);
	}

	public E removeLast() { // 마지막 원소 삭제
		return removeFrom(this.size() - 1);
	}

	@Override
	public boolean push(E anElement) {
		return this.addToLast(anElement); // 배열 마지막에 add
	}

	@Override
	public E pop() {
		return this.removeLast(); // 배열 마지막 원소 remove
	}

	@Override
	public E peek() {
		if (this.isEmpty()) { // 배열이 empty 라면
			return null; // null을 반환
		} else {
			return this.elementAt(this.size() - 1); // Last element
		}
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.size(); i++) { // 배열의 size만큼 반복
			this.elements()[i] = null; // 모든 배열을 null 처리
		}
		this.setSize(0); // size를 0으로 set
	}

}
