
public class ArrayList<T> {
	// Constants
	// 리스트 최대크기를 지정하지 않았을 경우 Default값
	private static final int DEFAULT_CAPACITY = 100;

	// Private Instance Variables
	private int _capacity; // 리스트 최대크기
	private int _size; // 현재 리스트 크기
	private T[] _elements; // 리스트 요소

	// ListIterator 생성하여 얻기
	public Iterator<T> iterator() {
		return (new ListIterator());
	}

	// Getters/Setters
	public int capacity() {
		return this._capacity;
	}

	public void setCapacity(int newCapacity) {
		this._capacity = newCapacity;
	}

	public int size() {
		return this._size;
	}

// 이번 과제에서는 불필요한 함수
//	private void setSize(int newSize) {
//		this._size = newSize;
//	}

	private T[] elements() {
		return this._elements;
	}

	private void setElements(T[] newElements) {
		this._elements = newElements;
	}

	// Constructor
	@SuppressWarnings("unchecked")
	public ArrayList(int givenCapacity) {
		this.setCapacity(givenCapacity);
		this.setElements((T[]) new Object[this.capacity()]);
	}

	public ArrayList() {
		this(ArrayList.DEFAULT_CAPACITY);
	}

	// 리스트가 비었는지 확인
	public boolean isEmpty() {
		return (this.size() == 0);
	}

	// 리스트가 가득 차있는지 확인
	public boolean isFull() {
		return (this.size() == this.capacity());
	}

	// 리스트에 원소가 있는지 확인
	public boolean doesContain(T anElement) {
		return (this.orderOf(anElement) != -1); // orderOf : 존재하지 않으면 -1 반환
	}

	// 주어진 순서에 있는 원소를 반환
	public T elementAt(int order) {
		if (this.anElementDoesExistAt(order)) { // 유효한 순서인지 확인
			int position = order;
			return this._elements[position];
		} else {
			return null;
		}
	}

	// 원소가 리스트에 유효한지 확인
	private boolean anElementDoesExistAt(int order) {
		return ((order >= 0) && (order < this.size()));
	}

	// 리스트의 첫번째 원소 반환
	public T first() {
		if (this.isEmpty()) { // 비어있다면 null반환
			return null;
		} else {
			return this.elementAt(0); // 0번째 원소 반환
		}
	}

	// 리스트의 마지막 원소 반환
	public T last() {
		if (this.isEmpty()) { // 비어있다면 null반환
			return null;
		} else {
			return this._elements[this.size() - 1]; // size-1 번째 원소 반환
		}
	}

	// 원소 anElement 가 리스트 안에 존재하면 해당 위치를 돌려준다
	// 존재하지 않으면 -1 을 돌려준다
	public int orderOf(T anElement) {
		for (int order = 0; order < this.size(); order++) {
			if (this._elements[order].equals(anElement)) {
				return order;
			}
		}
		return -1; // 주어진 원소 anElement 가 리스트 안에 없다
	}

	// 원소 삽입
	public boolean addTo(T anElement, int order) {
		if (this.isFull()) { // 리스트가 꽉 찼다면 false 반환
			return false;
		} else {
			if ((order >= 0) && (order <= this.size())) { // size크기: 마지막에 원소를 넣겠다는 의미
				this.makeRoomAt(order); // 삽입할 공간을 확보
				this._elements[order] = anElement; // 삽입
				this._size++; // 사이즈 증가
				return true;
			} else {
				return false; // 잘못된 삽입 위치
			}
		}
	}

	// 삽입할 공간을 만든다
	private void makeRoomAt(int position) {
		for (int i = this.size(); i > position; i--) { // size 크기부터 시작
			this._elements[i] = this._elements[i - 1]; // 한칸씩 뒤로 저장
		}
	}

	// 리스트 첫번째에 원소 삽입
	public boolean addToFirst(T anElement) {
		return this.addTo(anElement, 0);
	}

	// 리스트 마지막에 원소 삽입
	public boolean addToLast(T anElement) {
		return this.addTo(anElement, this.size());
	}

	// 임의의 위치에 원소 삽입
	public boolean add(T anElement) {
		return this.addToLast(anElement); // 가장 효과적인 곳에 삽입
	}

	// 주어진 순서의 원소를 삭제
	public T removeFrom(int order) {
		// 주어진 순서 order 에 원소가 없으면 null 을 return 한다
		// 원소가 있으면 리스트에서 제거하여 return 한다
		T removedElement = null;
		if (this.anElementDoesExistAt(order)) {
			// 리스트가 empty 이면 이 조건은 false 를 얻는다
			// 따라서 , 별도의 empty 검사를 하지 않아도 안전하다
			removedElement = this._elements[order];
			this.removeGapAt(order); // 빈 공간 제거
			this._size--; // 사이즈 감소
		}
		return removedElement;
	}

	// 삭제된 빈 공간을 제거
	private void removeGapAt(int position) {
		// 리스트는 empty 가 아님 : 언제나 (this.size() > 0)
		// position 은 valid: 언제나 (0 <= position < this.size())
		for (int i = position + 1; i < this.size(); i++) {
			this._elements[i - 1] = this._elements[i];
		}
		this._elements[this.size() - 1] = null;
	}

	// 첫번째 원소 삭제
	public T removeFirst() {
		return removeFrom(0);
	}

	// 마지막 원소 삭제
	public T removeLast() {
		return removeFrom(this._size - 1);
	}

	// 임의의 원소 삭제
	public T removeAny() {
		return removeLast();
	}

	// 리스트 특정 순서의 원소 교체
	public boolean replaceAt(T anElement, int order) {
		if (this.anElementDoesExistAt(order)) { // 순서가 리스트에서 유효한지 확인
			this._elements[order] = anElement; // 교체 진행
			return true;
		} else {
			return false;
		}
	}

	// Inner Class "ListIterator"의 선언
	private class ListIterator implements Iterator<T> {

		private int _nextPosition; // 배열에서의 다음 원소 위치

		// 다음 위치 반환하는 getter
		private int nextPosition() {
			return this._nextPosition;
		}

		// 다음 위치 설정하는 setter
		private void setNextPosition(int newNextPosition) {
			this._nextPosition = newNextPosition;
		}

		// 생성자
		private ListIterator() {
			this.setNextPosition(0);
		}

		// 다음 원소가 존재하는지를 알아낸다
		@Override
		public boolean hasNext() {
			return (this.nextPosition() < ArrayList.this.size());
		}

		// 다음 원소를 얻어낸다. 없으면 null을 얻는다
		@Override
		public T next() {
			T nextElement = null;
			if (this.hasNext()) { // 다음 원소가 존재하는 동안 반복
				nextElement = ArrayList.this.elements()[this.nextPosition()];
				this.setNextPosition(this.nextPosition() + 1); // 걸어나간다
			}
			return nextElement;
		}

	}

}
