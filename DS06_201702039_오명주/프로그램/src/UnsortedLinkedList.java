
public class UnsortedLinkedList<E extends Comparable<E>> {
	// 비공개 인스턴스 변수
	private int _size; // 리스트의 크기(원소의 개수)
	private LinkedNode<E> _head; // 연결체인의 맨 앞 노드를 소유
	private int _capacity; // 최대치

	// Getter/Setter
	public int size() { // getter of size
		return this._size;
	}

	private void setSize(int newSize) { // setter of size
		this._size = newSize;
	}

	private int capacity() { // setter of capacity
		return this._capacity;
	}

	private void setCapacity(int newCapacity) { // setter of capacity
		this._capacity = newCapacity;
	}

	private LinkedNode<E> head() { // getter of head
		return this._head;
	}

	private void setHead(LinkedNode<E> newHead) { // setter of head
		this._head = newHead;
	}

	// 리스트가 비었는지 확인하는 함수
	public boolean isEmpty() {
		return (this.size() == 0); // _size가 0이면 true를 반환
	}

	// 리스트가 꽉 차 있는지 확인하는 함수
	public boolean isFull() {
		return (this.size() == this.capacity()); // _size가 _capacity와 같으면 true를 반환
	}

	// 생성자
	public UnsortedLinkedList(int givensize) {
		this.setCapacity(givensize);
		this.setHead(null);
	}

	// 상태 알아보기
	// 최대값 반환하는 함수
	public E max() {
		if (this.isEmpty()) { // 비어있다면 null 반환
			return null;
		} else {
			LinkedNode<E> currentNode = this.head(); // 현재노드 생성 후 헤드로 설정
			LinkedNode<E> maxNode = new LinkedNode<E>(this.head().element(), null);
			while (currentNode != null) { // 마지막 노드까지 반복
				if (maxNode.element().compareTo(currentNode.element()) < 0) {
					maxNode.setElement(currentNode.element());
				}
				currentNode = currentNode.next();
			}
			return maxNode.element();
		}
	}

	// 노드 추가하는 함수
	public void add(E anElement) {
		// head에 노드 추가하는 코드
		LinkedNode<E> nodeForAdd = new LinkedNode<E>(anElement, null);
		nodeForAdd.setNext(this.head());
		this.setHead(nodeForAdd);
		this.setSize(this.size() + 1);
	}

}
