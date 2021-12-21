
public class UnsortedArrayList<E extends Comparable<E>> {
	// 비공개 상수, 변수들
	private static final int DEFAULT_CAPACITY = 100;

	private int _capacity;
	private int _size;
	private E[] _elements;

	// Getter/Setter
	private int capacity() { // getter of capacity
		return this._capacity;
	}

	private void setCapacity(int newCapacity) { // setter of capacity
		this._capacity = newCapacity;
	}

	public int size() { // getter of size
		return this._size;
	}

	private void setSize(int newSize) { // setter of size
		this._size = newSize;
	}

	private E[] elements() { // getter of elements
		return this._elements;
	}

	private void setElements(E[] newElements) { // setter of elements
		this._elements = newElements;
	}

	// 생성자
	public UnsortedArrayList() {
		this.setCapacity(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public UnsortedArrayList(int givenCapacity) {
		this.setCapacity(givenCapacity);
		this.setElements((E[]) new Comparable[this.capacity()]);
	}

	public boolean isEmpty() { // 비어있으면 true
		return (this.size() == 0);
	}

	public boolean isFull() { // 꽉찼으면 true
		return (this.size() == this._capacity);
	}

	public E elementAt(int anOrder) { // anOrder번째 배열 반환
		if (anOrder < 0 || anOrder >= this.size()) { // anOrder이 범위 밖이면
			return null; // null 반환
		} else {
			return this.elements()[anOrder]; // anOrder번째 배열 반환
		}
	}

	protected void setElementsAt(int anOrder, E anElement) {
		if (anOrder < 0 || anOrder >= this.size()) { // anOrder 범위 밖이라면
			return; // 진행
		} else { // 범위 안이라면
			this.elements()[anOrder] = anElement; // anOrder번째 배열 anElement로 설정
		}
	}

	public boolean doesContain(E anElement) { // 존재 유무 확인
		return (this.orderOf(anElement) >= 0); // anElement가 orderOf로 인해 있다는게 학인되면
												// true를 반환
	}

	public int orderOf(E anElement) {
		int order = -1; // 순서 변수 -1 로 선언
		for (int index = 0; index < this.size() && order < 0; index++) { // 0~사이즈 &
			// order이 -1일때 반복
			if (this.elements()[index].equals(anElement)) { // index번째 배열과 anElement가 같으면
				order = index; // order 를 index로 설정
			}
		}
		return order; // 같은 배열을 찾은 순서인 order를 반환
	}

	private void makeRoomAt(int aPosition) {
		for (int i = this.size(); i > aPosition; i--) { // this.size()부터 aPosition까지 반복
			this.elements()[i] = this.elements()[i - 1]; // i번째 배열을 i - 1 번째 배열로 설정
															// 한 칸씩 미는 것
		}
	}

	public boolean addToFirst(E anElement) {
		if (this.isFull()) { // 꽉차면 추가를 못하니
			return false; // false 반환
		} else {
			this.makeRoomAt(0); // 0번째부터 한칸씩 민다
			this.elements()[0] = anElement; // 0번째 배열에 anElement 대입
			this.setSize(this.size() + 1); // 사이즈 + 1
			return true; // true반환
		}
	}

	public boolean addToLast(E anElement) {
		if (this.isFull()) { // 꽉차면 추가를 못하니
			return false; // false 반환
		} else {
			this.elements()[this.size()] = anElement; // this.size()번째 배열에 anElement 대입
			this.setSize(this.size() + 1); // 사이즈 + 1
			return true; // true 반환
		}
	}

	public boolean add(E anElement) { // 용이한 위치에 add
		return this.addToLast(anElement); // 마지막 위치에 추가하는 것이 용이함
	}

	public Iterator<E> iterator() {
		return (new ListIterator());
	}

	private class ListIterator implements Iterator<E> {
		private int _nextPosition;

		private int nextPosition() {
			return this._nextPosition;
		}

		private void setNextPosition(int newNextPosition) {
			this._nextPosition = newNextPosition;
		}

		private ListIterator() {
			this.setNextPosition(0);
		}

		@Override
		public boolean hasNext() {
			return (this.nextPosition() < UnsortedArrayList.this.size());
		}

		@Override
		public E next() {
			E nextElement = null;
			if (this.hasNext()) {
				nextElement = UnsortedArrayList.this.elements()[this.nextPosition()];
				this.setNextPosition(this.nextPosition() + 1);
			}
			return nextElement;
		}
	}

}
