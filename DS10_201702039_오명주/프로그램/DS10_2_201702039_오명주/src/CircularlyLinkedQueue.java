public class CircularlyLinkedQueue<E> implements Queue<E> {

	private int _size;
	private LinkedNode<E> _rearNode;

	// ������
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
	public boolean isFull() { // ����ü���� �������� ���� ����
		return false;
	}

	@Override
	public boolean isEmpty() { // ť�� ����ִ��� Ȯ��
		return (this.rearNode() == null);
	}

	public E front() { // ť�� front ���� ��ȯ
		E frontElement = null;
		if (!this.isEmpty()) {
			frontElement = this.rearNode().next().element(); // rear�� ������°�� ù��° ť
		}
		return frontElement;
	}

	@Override
	public E rear() { // ť�� rear ���� ��ȯ
		E frontElement = null;
		if (!this.isEmpty()) {
			frontElement = this.rearNode().element(); // rear��° ť
		}
		return frontElement;
	}

	@Override
	public boolean enQueue(E anElement) { // anElement ť�� rear�� �߰�
		LinkedNode<E> newRearNode = new LinkedNode<E>(anElement, null); // ���ο� Node����
		if (this.isEmpty()) { // ���� ����ִٸ�
			newRearNode.setNext(newRearNode); // newRearNode�� ������ newRearNode�� ����
		} else { // ������� �ʴٸ�
			newRearNode.setNext(this.rearNode().next()); // newRearNode�� ������ �� rearNode�� ���� ���� ����
			this.rearNode().setNext(newRearNode); // rearNode�� ������ newRearNode�� ����
		}
		this.setRearNode(newRearNode); // newRearNode�� rearNode ����
		this.setSize(this.size() + 1); // size ++
		return true;

	}

	@Override
	public E deQueue() { // front�� ���Ҹ� ����
		E frontElement = null;
		if (!this.isEmpty()) { // ��������ʴٸ�
			frontElement = this.rearNode().next().element(); // front���� rear�� next ���
			if (this.rearNode() == this.rearNode().next()) { // ť�� ��尡 1�����
				this.setRearNode(null); // �ش� ��带 ����
			} else { // ��尡 2�� �̻�
				this.rearNode().setNext(this.rearNode().next().next()); // front��带 ����
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
	public E elementAt(int anOrder) { // anOrder �� ° E ��ȯ
		LinkedNode<E> frontNode = this.rearNode().next();
		if (anOrder < 0 || anOrder > this.size() - 1) { // anOrder�� ���� ���̸�
			return null; // null ��ȯ
		} else {
			for (int order = 0; order < anOrder; order++) {
				frontNode = frontNode.next();
			}
			return frontNode.element(); // frontNode ��ȯ
		}
	}

	@Override
	public Iterator<E> iterator() {
		return (new CircularlyLinkedQueueIterator());
	}

	private class CircularlyLinkedQueueIterator implements Iterator<E> { // �ݺ��� ���� Ŭ����
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
