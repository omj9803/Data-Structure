
public class LinkedBag<E> {

	// 비공개 인스턴스 변수
	private int _size; // 가방이 가지고 있는 원소의 갯수(연결된 노드의 갯수)
	private LinkedNode<E> _head; // 연결 체인의 맨 앞 노드를 소유한다

	// 생성자
	public LinkedBag() {
		setSize(0);
		setHead(null);
	}

	// getter / setter
	// Bag에 들어있는 개수를 확인한다
	public int size() {
		return this._size;
	}

	private void setSize(int newSize) {
		this._size = newSize;
	}

	private LinkedNode<E> head() {
		return this._head;
	}

	private void setHead(LinkedNode<E> newHead) {
		this._head = newHead;
	}

	// Bag이 비어있는지 확인한다
	public boolean isEmpty() {
		return (this.size() == 0);
	}

	public boolean isFull() {
		return false;
		// 원소 저장 개수에 영향 받지 않으므로
		// 시스템 메모리 부족 오류는 없다고 가정한다
	}

	// Bag 안에 주어진 원소가 존재하는지 확인한다
	public boolean doesContain(E anElement) {
		LinkedNode<E> currentNode = this.head(); // head를 currentNode로 지정
		while (currentNode != null) { // currentNode가 null이 아닌동안 반복
			if (currentNode.element().equals(anElement)) {
				// 만약 찾는 원소가 있다면 true 반환
				return true;
			}
			currentNode = currentNode.next(); // 다음 노드 반복
		}
		return false;
	}

	// Bag 안에 주어진 원소가 몇 개 있는지 확인한다
	public int frequencyOf(E anElement) {
		int frequencyCount = 0; // 원소를 count할 변수 초기화
		LinkedNode<E> currentNode = this._head; // head를 currentNode로 지정
		while (currentNode != null) { // currentNode가 null이 아닌동안 반복
			if (currentNode.element().equals(anElement)) {
				// 주어진 원소가 있다면 count증가
				frequencyCount++;
			}
			currentNode = currentNode.next(); // 다음 노드 반복
		}
		return frequencyCount;
	}

	// Bag 안에 주어진 순서의 원소를 얻는다
	// 맨 앞이 order 0, 맨 뒤가 order size()-1
	// 주어진 순서가 적정 범위를 벗어나 있으면 null을 얻는다
	public E elementAt(int anOrder) {
		if ((anOrder < 0) || (anOrder >= this.size())) {
			return null; // anOrder 가 적정 범위를 벗어나 있다
		} else { // anOrder가 적정 범위 안에 있다
			LinkedNode<E> currentNode = this.head();
			for (int i = 0; i < anOrder; i++) {
				currentNode = currentNode.next();
			}
			return currentNode.element();
		}
	}

	// Bag에 원소를 추가한다
	public boolean add(E anElement) {
		// LinkedList에서는 가득 차는 개념이 없으므로 무의미한 확인.
		if (this.isFull()) {
			return false;
		} else {
			LinkedNode<E> newNode = new LinkedNode<E>(); // 새로운 노드 생성
			newNode.setElement(anElement); // 새로운 노드에 입력받은 값을 넣는다
			newNode.setNext(this.head()); // 새로운 head로 지정한다
			this.setHead(newNode); // head 저장
			this.setSize(this.size() + 1); // size 증가했음을 저장
			return true;
		}
	}

	// Bag에서 원소를 삭제한다
	public boolean remove(E anElement) {
		if (this.isEmpty()) { // 비어있는지 확인한다
			return false; // 비어있다면 false 반환
		} else {
			LinkedNode<E> previousNode = null; // 이전노드를 나타낼 변수
			LinkedNode<E> currentNode = this._head; // 현재 노드를 head로 지정한다
			boolean found = false; // 찾았는지 여부를 저장할 변수
			
			// 첫 번째 단계 : 삭제할 위치 찾기
			while (currentNode != null && !found) { // 노드가 끝날때까지 반복
				if (currentNode.element().equals(anElement)) { // 찾고자 하는 값의 노드를 발견하면
					found = true; // 발견여부를 저장한다
				} else {
					// 찾지 못한경우 다음 노드로 이동
					previousNode = currentNode;
					currentNode = currentNode.next();
				}
			}

			// 두 번째 단계 : 삭제하기
			if (!found) { // 찾지 못한 경우는 false를 반환
				return false;
			} else { // 삭제할 대상을 찾은 경우,
				if (currentNode == this.head()) { // 삭제할 대상이 head인 경우
					this.setHead(this.head().next()); // head를 next로 바꾸어 head를 없앤다
				}
				else {
					previousNode.setNext(currentNode.next()); // 이전노드의 다음을 현재노드의 다음으로 저장하여 현재노드를 삭제한다.
				}
				this.setSize(this.size() - 1); // size는 하나 줄여 저장한다
				return true;
			}
		}
	}

	// Bag에서 원소 하나를 무작위로 삭제한다-> head 노드 삭제
	public E removeAny() {
		if (this.isEmpty()) { // 비어있는 경우 null 반환
			return null;
		} else {
			E removedElement = this.head().element(); // head에 있는 element를 저장
			this.setHead(this.head().next()); // head 다음 노드를 head로 지정
			this.setSize(this.size() - 1); // size를 하나 감소한다
			return removedElement; // 저장할 element를 반환
		}
	}

	// Bag을 초기화한다. 모든 원소 삭제한다
	public void clear() {
		this.setSize(0);
		this.setHead(null);
	}
}
