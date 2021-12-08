public class CircularArrayQueue<E> implements Queue<E> {
	private static final int DEFAULT_CAPACITY = 100;

	private int _maxLength; // capacity + 1
	private int _frontPosition;
	private int _rearPosition;
	private E[] _elements;

	// ������
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
		if (this.rearPosition() >= this.frontPosition()) { // frontPosition���� rearPosition�� �� ũ��
			return (this.rearPosition() - this.frontPosition()); // �� ������ ���� ���Ѵ�
		} else { // maxLength�� �Ѿ�� ���
			return (this.rearPosition() - this.frontPosition() + this.maxLength()); // maxLength�� �����ָ� �ȴ�.
		}
	}

	@Override
	public boolean isFull() { // Queue�� ���� á���� Ȯ��
		int nextRearPosition = (this.rearPosition() + 1) % this.maxLength(); // ���� ���� ��ġ
		return (nextRearPosition == this.frontPosition()); // ���� ���� ��ġ�� �� �հ� ������ ���� �� ��
	}

	@Override
	public boolean isEmpty() { // Queue�� ����ִ��� Ȯ��
		return (this.frontPosition() == this.rearPosition());
	}

	@Override
	public E front() { // front ���� ��ȯ
		E frontElement = null;
		if (!this.isEmpty()) {
			frontElement = this.elements()[this.frontPosition() + 1];
		}
		return frontElement;
	}

	@Override
	public E rear() { // rear ���� ��ȯ
		E rearElement = null;
		if (!this.isEmpty()) {
			rearElement = this.elements()[this.rearPosition()];
		}
		return rearElement;
	}

	@Override
	public boolean enQueue(E anElement) { // ť rear ��ġ�� ���� �߰�
		if (this.isFull()) { // ť�� ���� á�ٸ�
			return false; // false�� ��ȯ
		} else {
			if (this.isEmpty()) { // ���� ť�� ������� ���
				this.elements()[this.rearPosition()] = anElement; // rear ���� ��ġ�� ���Ҹ� �߰�
				this.setFrontPosition((this.frontPosition() + this.maxLength() - 1) % this.maxLength()); // front�� �ϳ� ������ �а� rear�� ���� ��ġ�� ����
				return true;
			} // ���� ť�� ���Ұ� �ִٸ�
			this.setRearPosition((this.rearPosition() + 1) % this.maxLength()); // rear�� ���� ��ġ�� ����
			this.elements()[this.rearPosition()] = anElement; // rear��ġ�� ���Ҹ� �߰�
			return true;
		}
	}

	@Override
	public E deQueue() { // ť front ��ġ ���� ����
		E frontElement = null; // ����ִٸ� null�� ��ȯ
		if (!this.isEmpty()) { // ��������ʴٸ�
			this.setFrontPosition((this.frontPosition() + 1) % this.maxLength()); // front�� ���� ��ġ�� ����
			frontElement = this.elements()[this.frontPosition()]; // frontPosition�� ���Ҹ� ������ ����
			this.elements()[this.frontPosition()] = null; // ����
		}
		return frontElement; // ������ ���� ��ȯ
	}

	@Override
	public void clear() {
		this.setFrontPosition(0); // Front = 0����
		this.setRearPosition(0); // Rear = 0����
		for (int i = 0; i < this.maxLength(); i++) {
			this.elements()[i] = null; // ��� ť�� null��
		}
	}

	@Override
	public E elementAt(int anOrder) { // anOrder �� ° E ��ȯ
		if (anOrder < 0 || anOrder > this.maxLength() - 1) { // anOrder�� ���� ���̸�
			return null; // null ��ȯ
		} else {
			return this.elements()[anOrder]; // anOrder��° �迭 ��ȯ
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
