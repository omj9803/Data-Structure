public class CircularlyLinkedQueue<E> implements Queue<E> {

	private int _size;
	private LinkedNode<E> _rearNode;

	// 생성자
	public CircularlyLinkedQueue() {
		this.setSize(0);
		this.setRearNode(null);
	}

	// Getters/Setters
	@Override
	public int size() {
		return this._size;
	}

	private void setSize(int newSize) {
		this._size = newSize;
	}

	public LinkedNode<E> rearNode() {
		return this._rearNode;
	}

	private void setRearNode(LinkedNode<E> newNode) {
		this._rearNode = newNode;
	}

	@Override
	public boolean isFull() { // 연결체인이 가득차는 경우는 없다
		return false;
	}

	@Override
	public boolean isEmpty() { // 큐가 비어있는지 확인
		return (this.rearNode() == null);
	}

	public E front() { // 큐의 front 원소 반환
		E frontElement = null;
		if (!this.isEmpty()) {
			frontElement = this.rearNode().next().element(); // rear의 다음번째가 첫번째 큐
		}
		return frontElement;
	}

	@Override
	public E rear() { // 큐의 rear 원소 반환
		E frontElement = null;
		if (!this.isEmpty()) {
			frontElement = this.rearNode().element(); // rear번째 큐
		}
		return frontElement;
	}

	@Override
	public boolean enQueue(E anElement) { // anElement 큐를 rear에 추가
		LinkedNode<E> newRearNode = new LinkedNode<E>(anElement, null); // 새로운 Node생성
		if (this.isEmpty()) { // 만약 비어있다면
			newRearNode.setNext(newRearNode); // newRearNode의 다음을 newRearNode로 지정
		} else { // 비어있지 않다면
			newRearNode.setNext(this.rearNode().next()); // newRearNode의 다음을 현 rearNode의 다음 노드로 지정
			this.rearNode().setNext(newRearNode); // rearNode의 다음을 newRearNode로 지정
		}
		this.setRearNode(newRearNode); // newRearNode로 rearNode 설정
		this.setSize(this.size() + 1); // size ++
		return true;

	}

	@Override
	public E deQueue() { // front의 원소를 삭제
		E frontElement = null;
		if (!this.isEmpty()) { // 비어있지않다면
			frontElement = this.rearNode().next().element(); // front노드는 rear의 next 노드
			if (this.rearNode() == this.rearNode().next()) { // 큐에 노드가 1개라면
				this.setRearNode(null); // 해당 노드를 삭제
			} else { // 노드가 2개 이상
				this.rearNode().setNext(this.rearNode().next().next()); // front노드를 삭제
			}
			this.setSize(this.size() - 1);
		}
		return frontElement;
	}

	@Override
	public void clear() {
		this.setRearNode(null);
		this.setSize(0);
	}

	@Override
	public E elementAt(int anOrder) { // anOrder 번 째 E 반환
		LinkedNode<E> frontNode = this.rearNode().next();
		if (anOrder < 0 || anOrder > this.size() - 1) { // anOrder이 범위 밖이면
			return null; // null 반환
		} else {
			for (int order = 0; order < anOrder; order++) {
				frontNode = frontNode.next();
			}
			return frontNode.element(); // frontNode 반환
		}
	}

	@Override
	public Iterator<E> iterator() {
		return (new CircularlyLinkedQueueIterator());
	}

	private class CircularlyLinkedQueueIterator implements Iterator<E> { // 반복자 내부 클래스
		private LinkedNode<E> _nextNode;
		private int _count;

		private int count() {
			return this._count;
		}

		private void setCount(int newCount) {
			this._count = newCount;
		}

		private LinkedNode<E> nextNode() {
			return this._nextNode;
		}

		private void setNextNode(LinkedNode<E> newNextNode) {
			this._nextNode = newNextNode;
		}

		private CircularlyLinkedQueueIterator() {
			this.setNextNode(CircularlyLinkedQueue.this.rearNode());
			this.setCount(CircularlyLinkedQueue.this.size());
		}

		@Override
		public boolean hasNext() {
			return (this.count() > 0);
		}

		@Override
		public E next() {
			if (this.hasNext()) {
				this.setNextNode(this.nextNode().next());
				E nextElement = this.nextNode().element();
				this.setCount(this.count() - 1);
				return nextElement;
			} else {
				return null;
			}
		}
	}

}
