
public class SortedLinkedList<E extends Comparable<E>> {
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
	public SortedLinkedList(int givenSize) {
		this.setCapacity(givenSize);
		this.setHead(null);
	}

	// 리스트에 원소를 삽입하는 함수
	public boolean add(E anElement) {
		if (this.isFull()) { // size = capacity라면 false 반환
			return false;
		} else {
			LinkedNode<E> nodeForAdd = new LinkedNode<E>(anElement, null); // 주어진 anElement를 가진 노드생성
			if (this.isEmpty()) { // 리스트가 비어있다면
				this.setHead(nodeForAdd); // head에 삽입
			} else {
				LinkedNode<E> current = this.head(); // 현재 비교하는 노드
				LinkedNode<E> previous = null; // current의 앞 노드 삽입을 하려면 , 앞 노드를 알아야 한다
				while (current != null) { // 리스트의 끝에 도달할 때 까지 비교 검색한다
					if (current.element().compareTo(anElement) > 0) {
						break; // 삽입할 위치를 찾은 것이므로 비교 검색 중지
					}
					previous = current;
					current = current.next();
				}
				if (previous == null) {
					nodeForAdd.setNext(this.head());
					this.setHead(nodeForAdd);
				} else {
					nodeForAdd.setNext(current);
					previous.setNext(nodeForAdd);
				}
			}
			this.setSize(this.size() + 1);
			return true;
		}
	}

	// max를 반환하는 함수
	public E max() {
		if (this.isEmpty()) {
			return null;
		} else {
			LinkedNode<E> currentNode = this._head; // 현재노드 생성 후 헤드로 설정
			while (currentNode.next() != null) { // 리스트 마지막까지 반복
				currentNode = currentNode.next();
			}
			return currentNode.element(); // 리스트 마지막 노드 element 반환
		}
	}
}
