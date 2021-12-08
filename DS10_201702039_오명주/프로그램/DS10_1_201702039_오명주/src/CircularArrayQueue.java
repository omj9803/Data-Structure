public class CircularArrayQueue<E> implements Queue<E> {
	private static final int DEFAULT_CAPACITY = 100;

	private int _maxLength; // capacity + 1
	private int _frontPosition;
	private int _rearPosition;
	private E[] _elements;

	// 생성자
	@SuppressWarnings("unchecked")
	public CircularArrayQueue(int givenCapacity) {
		this.setMaxLength(givenCapacity + 1);
		this.setFrontPosition(0);
		this.setRearPosition(0);
		this.setElements((E[]) new Object[this.maxLength()]);
	}

	public CircularArrayQueue() {
		this(CircularArrayQueue.DEFAULT_CAPACITY);
	}

	// Getters/Setters
	private int maxLength() {
		return this._maxLength;
	}

	private void setMaxLength(int newMaxlength) {
		this._maxLength = newMaxlength;
	}

	private E[] elements() {
		return this._elements;
	}

	public void setElements(E[] newElements) {
		this._elements = newElements;
	}

	public int capacity() {
		return (this.maxLength() - 1);
	}

	public int frontPosition() {
		return this._frontPosition;
	}

	private void setFrontPosition(int newFrontPosition) {

		this._frontPosition = newFrontPosition;
	}

	public int rearPosition() {
		return this._rearPosition;
	}

	private void setRearPosition(int newRearPosition) {
		this._rearPosition = newRearPosition;
		if (newRearPosition == maxLength()) {
			_rearPosition = 0;
		}
	}

	@Override
	public int size() {
		if (this.rearPosition() >= this.frontPosition()) { // frontPosition보가 rearPosition이 더 크면
			return (this.rearPosition() - this.frontPosition()); // 둘 사이의 차를 구한다
		} else { // maxLength를 넘어온 경우
			return (this.rearPosition() - this.frontPosition() + this.maxLength()); // maxLength를 더해주면 된다.
		}
	}

	@Override
	public boolean isFull() { // Queue가 가득 찼는지 확인
		int nextRearPosition = (this.rearPosition() + 1) % this.maxLength(); // 다음 삽입 위치
		return (nextRearPosition == this.frontPosition()); // 다음 삽입 위치가 맨 앞과 같으면 가득 찬 것
	}

	@Override
	public boolean isEmpty() { // Queue가 비어있는지 확인
		return (this.frontPosition() == this.rearPosition());
	}

	@Override
	public E front() { // front 원소 반환
		E frontElement = null;
		if (!this.isEmpty()) {
			frontElement = this.elements()[this.frontPosition() + 1];
		}
		return frontElement;
	}

	@Override
	public E rear() { // rear 원소 반환
		E rearElement = null;
		if (!this.isEmpty()) {
			rearElement = this.elements()[this.rearPosition()];
		}
		return rearElement;
	}

	@Override
	public boolean enQueue(E anElement) { // 큐 rear 위치에 원소 추가
		if (this.isFull()) { // 큐가 가득 찼다면
			return false; // false를 반환
		} else {
			if (this.isEmpty()) { // 만약 큐가 비어있을 경우
				this.elements()[this.rearPosition()] = anElement; // rear 현재 위치에 원소를 추가
				this.setFrontPosition((this.frontPosition() + this.maxLength() - 1) % this.maxLength()); // front를 하나 전으로 밀고 rear을 현재 위치에 삽입
				return true;
			} // 만약 큐에 원소가 있다면
			this.setRearPosition((this.rearPosition() + 1) % this.maxLength()); // rear을 다음 위치로 설정
			this.elements()[this.rearPosition()] = anElement; // rear위치에 원소를 추가
			return true;
		}
	}

	@Override
	public E deQueue() { // 큐 front 위치 원소 삭제
		E frontElement = null; // 비어있다면 null을 반환
		if (!this.isEmpty()) { // 비어있지않다면
			this.setFrontPosition((this.frontPosition() + 1) % this.maxLength()); // front를 다음 위치로 설정
			frontElement = this.elements()[this.frontPosition()]; // frontPosition의 원소를 변수에 저장
			this.elements()[this.frontPosition()] = null; // 삭제
		}
		return frontElement; // 삭제된 원소 반환
	}

	@Override
	public void clear() {
		this.setFrontPosition(0); // Front = 0으로
		this.setRearPosition(0); // Rear = 0으로
		for (int i = 0; i < this.maxLength(); i++) {
			this.elements()[i] = null; // 모든 큐를 null로
		}
	}

	@Override
	public E elementAt(int anOrder) { // anOrder 번 째 E 반환
		if (anOrder < 0 || anOrder > this.maxLength() - 1) { // anOrder이 범위 밖이면
			return null; // null 반환
		} else {
			return this.elements()[anOrder]; // anOrder번째 배열 반환
		}
	}

	@Override
	public Iterator<E> iterator() {
		return (new CircularArrayQueueIterator());
	}

	private class CircularArrayQueueIterator implements Iterator<E> {
		private int _nextOrder;

		private int nextOrder() {
			return this._nextOrder;
		}

		private void setNextOrder(int newNextPosition) {
			this._nextOrder = newNextPosition;
		}

		private CircularArrayQueueIterator() {
			this.setNextOrder((frontPosition() + 1) % maxLength());
		}

		@Override
		public boolean hasNext() {
			return (this.nextOrder() < CircularArrayQueue.this.rearPosition() + 1);
		}

		@Override
		public E next() {
			E nextElement = null;
			if (this.hasNext()) {
				nextElement = CircularArrayQueue.this.elements()[this.nextOrder()];
				this.setNextOrder(this.nextOrder() + 1);
			}
			return nextElement;
		}
	}
}
