
public class LinkedList<T> {

	// 비공개 인스턴스 변수
	private int _size; // 리스트가 가지고 있는 원소의 개수
	private LinkedNode<T> _head; // LinkedChain 의 맨 앞 노드

	// 순서가 유효한지 확인하는 함수
	private boolean anElementDoesExistAt(int order) {
		return ((order >= 0) && (order < this.size()));
	}

	// ListIterator 생성하여 얻기
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	// 생성자
	public LinkedList() {
		this._head = null;
		this._size = 0;
	}

	// 상태 알아보기
	// 리스트가 비어있는지 확인
	public boolean isEmpty() {
		return (this._head == null);
		// 또는  return (this._size == 0)
	}

	// 리스트가 가득 차있는지 확인
	public boolean isFull() {
		// 시스템 메모리가 모자라는 경우는 없다고 가정
		return false; // 언제나 full 이 아니다
	}

	// 리스트 크기를 반환
	public int size() {
		return this._size;
	}

	// 주어진 원소가 포함되어있는지 확인
	public boolean doesContain(T anElement) {
		return (orderOf(anElement) != -1); // orderOf : 존재하지않으면 -1을 반환
	}

	// 주어진 순서의 원소를 반환
	public T elementAt(int order) {
		if (this.anElementDoesExistAt(order)) { // 순서가 유효한지 확인
			LinkedNode<T> currentNode = this._head; // head를 current로 설정
			int nodeCount = 0;
			while (nodeCount < order) { // 순서만큼 반복
				currentNode = currentNode.next(); // 걸어나감
				nodeCount++;
			}
			return currentNode.element(); // 해당 순서의 원소 반환
		} else {
			return null; // 순서가 유효하지 않다면 null을 반환
		}
	}

	// 리스트 첫번째 원소를 반환
	public T first() {
		if (this.isEmpty()) {
			return null; // 마지막 원소가 존재할 수 없으므로
		} else {
			return elementAt(0);
			// 또는 이렇게 : return this._head.element()
		}
	}

	// 리스트 마지막 원소를 반환
	public T last() {
		if (this.isEmpty()) {
			return null; // 마지막 원소가 존재할 수 없으므로
		} else {
			return elementAt(this.size() - 1);
		}
	}

	// 주어진 원소의 순서를 반환
	public int orderOf(T anElement) { // 순차 검색
		int order = 0;
		LinkedNode<T> currentNode = this._head; // head를 current로 설정
		// 리스트 크기만큼 돌거나 원소를 찾을 때까지 반복
		while (currentNode != null && (!currentNode.element().equals(anElement))) {
			order++;
			currentNode = currentNode.next();
		}
		if (currentNode == null) { // Not Found
			return -1; // 존재하지 않으면 -1 을 돌려주기로 한다
		} else {
			return order;
		}
	}

	// 주어진 원소를 주어진 순서에 삽입
	public boolean addTo(T anElement, int order) {
		if ((order < 0) || (order > this.size())) { // order 가 유효한지 검사
			return false;
		} else if (this.isFull()) { // 가득 차 있는지 확인 -> LinkedList에서 무의미
			return false;
		} else {
			LinkedNode<T> nodeForAdd = new LinkedNode<T>(anElement, null);
			if (order == 0) { // 맨 앞 순서에 삽입 . 앞 (previous) 노드가 존재하지 않는다
				nodeForAdd.setNext(this._head);
				this._head = nodeForAdd;
			} else { // 순서가 맨 앞이 아니므로 , 반드시 앞 (previous) 노드가 존재한다
				LinkedNode<T> previousNode = this._head;
				for (int i = 1; i < order; i++) {
					previousNode = previousNode.next(); // 삽입할 위치의 앞 노드를 찾는다
				}
				nodeForAdd.setNext(previousNode.next());
				previousNode.setNext(nodeForAdd);
			}
			this._size++;
			return true;
		}
	}

	// 리스트 첫번째에 삽입
	public boolean addToFirst(T anElement) {
		return this.addTo(anElement, 0);
	}

	// 리스트 마지막에 삽입
	public boolean addToLast(T anElement) {
		return this.addTo(anElement, this.size());
	}

	// 임의의 순서에 삽입
	public boolean add(T anElement) {
		return this.addToFirst(anElement);
	}

	// 주어진 순서의 원소를 삭제
	public T removeFrom(int order) {
		if (!this.anElementDoesExistAt(order)) { // 삭제할 원소가 없거나 , 잘못된 위치
			return null;
		} else {
			// 리스트는 비어 있지 않으며 , 삭제할 원소가 있음
			LinkedNode<T> removedNode = null;
			if (order == 0) { // 삭제할 원소가 맨 앞 원소
				removedNode = this._head;
				this._head = this._head.next();
			} else { // 삭제할 원소의 위치는 맨 앞이 아니며 , 따라서 원소가 두 개 이상
				LinkedNode<T> previousNode = this._head;
				for (int i = 1; i < order; i++) {
					previousNode = previousNode.next(); // 삭제할 위치의 앞 노드를 찾는다
				}
				removedNode = previousNode.next();
				previousNode.setNext(removedNode.next());
			}
			this._size--; // 사이즈 감소
			return removedNode.element();
		}
	}

	// 첫번째 원소를 삭제
	public T removeFirst() {
		return (this.removeFrom(0));
	}

	// 마지막 원소를 삭제
	public T removeLast() {
		return (removeFrom(this.size() - 1));
	}

	// 임의의 원소를 삭제
	public T removeAny() {
		return this.removeFirst();
	}

	// 주어진 순서의 원소를 주어진 원소로 교체
	public boolean replaceAt(T anElement, int order) {
		if (!this.anElementDoesExistAt(order)) {
			// 대체할 노드가 없거나 , 잘못된 위치
			return false;
		} else {
			LinkedNode<T> currentNode = this._head; // head로 지정
			for (int i = 0; i < order; i++) { // 순서만큼 반복하여 위치 찾는다
				currentNode = currentNode.next();
				// 원소를 대체할 노드를 찾는다
			}
			currentNode.setElement(anElement);
			return true;
		}
	}

	// Inner Class "ListIterator"의 선언
	private class ListIterator implements Iterator<T> {

		private LinkedNode<T> _nextNode; // 연결 체인에서 다음 원소를 소유하는 노드

		// 생성자
		private ListIterator() {
			this._nextNode = LinkedList.this._head;
		}

		// 다음 원소가 존재하는지를 알아낸다
		@Override
		public boolean hasNext() {
			return (this._nextNode != null);
		}

		// 다음 원소를 얻어낸다. 없으면 null을 얻는다
		@Override
		public T next() {
			if (this._nextNode == null) {
				return null;
			} else {
				T nextElement = this._nextNode.element();
				this._nextNode = this._nextNode.next();
				return nextElement;
			}
		}

	}

}
