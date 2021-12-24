
public class LinkedList<T> {

	// ����� �ν��Ͻ� ����
	private int _size; // ����Ʈ�� ������ �ִ� ������ ����
	private LinkedNode<T> _head; // LinkedChain �� �� �� ���

	// ������ ��ȿ���� Ȯ���ϴ� �Լ�
	private boolean anElementDoesExistAt(int order) {
		return ((order >= 0) && (order < this.size()));
	}

	// ListIterator �����Ͽ� ���
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	// ������
	public LinkedList() {
		this._head = null;
		this._size = 0;
	}

	// ���� �˾ƺ���
	// ����Ʈ�� ����ִ��� Ȯ��
	public boolean isEmpty() {
		return (this._head == null);
		// �Ǵ�  return (this._size == 0)
	}

	// ����Ʈ�� ���� ���ִ��� Ȯ��
	public boolean isFull() {
		// �ý��� �޸𸮰� ���ڶ�� ���� ���ٰ� ����
		return false; // ������ full �� �ƴϴ�
	}

	// ����Ʈ ũ�⸦ ��ȯ
	public int size() {
		return this._size;
	}

	// �־��� ���Ұ� ���ԵǾ��ִ��� Ȯ��
	public boolean doesContain(T anElement) {
		return (orderOf(anElement) != -1); // orderOf : �������������� -1�� ��ȯ
	}

	// �־��� ������ ���Ҹ� ��ȯ
	public T elementAt(int order) {
		if (this.anElementDoesExistAt(order)) { // ������ ��ȿ���� Ȯ��
			LinkedNode<T> currentNode = this._head; // head�� current�� ����
			int nodeCount = 0;
			while (nodeCount < order) { // ������ŭ �ݺ�
				currentNode = currentNode.next(); // �ɾ��
				nodeCount++;
			}
			return currentNode.element(); // �ش� ������ ���� ��ȯ
		} else {
			return null; // ������ ��ȿ���� �ʴٸ� null�� ��ȯ
		}
	}

	// ����Ʈ ù��° ���Ҹ� ��ȯ
	public T first() {
		if (this.isEmpty()) {
			return null; // ������ ���Ұ� ������ �� �����Ƿ�
		} else {
			return elementAt(0);
			// �Ǵ� �̷��� : return this._head.element()
		}
	}

	// ����Ʈ ������ ���Ҹ� ��ȯ
	public T last() {
		if (this.isEmpty()) {
			return null; // ������ ���Ұ� ������ �� �����Ƿ�
		} else {
			return elementAt(this.size() - 1);
		}
	}

	// �־��� ������ ������ ��ȯ
	public int orderOf(T anElement) { // ���� �˻�
		int order = 0;
		LinkedNode<T> currentNode = this._head; // head�� current�� ����
		// ����Ʈ ũ�⸸ŭ ���ų� ���Ҹ� ã�� ������ �ݺ�
		while (currentNode != null && (!currentNode.element().equals(anElement))) {
			order++;
			currentNode = currentNode.next();
		}
		if (currentNode == null) { // Not Found
			return -1; // �������� ������ -1 �� �����ֱ�� �Ѵ�
		} else {
			return order;
		}
	}

	// �־��� ���Ҹ� �־��� ������ ����
	public boolean addTo(T anElement, int order) {
		if ((order < 0) || (order > this.size())) { // order �� ��ȿ���� �˻�
			return false;
		} else if (this.isFull()) { // ���� �� �ִ��� Ȯ�� -> LinkedList���� ���ǹ�
			return false;
		} else {
			LinkedNode<T> nodeForAdd = new LinkedNode<T>(anElement, null);
			if (order == 0) { // �� �� ������ ���� . �� (previous) ��尡 �������� �ʴ´�
				nodeForAdd.setNext(this._head);
				this._head = nodeForAdd;
			} else { // ������ �� ���� �ƴϹǷ� , �ݵ�� �� (previous) ��尡 �����Ѵ�
				LinkedNode<T> previousNode = this._head;
				for (int i = 1; i < order; i++) {
					previousNode = previousNode.next(); // ������ ��ġ�� �� ��带 ã�´�
				}
				nodeForAdd.setNext(previousNode.next());
				previousNode.setNext(nodeForAdd);
			}
			this._size++;
			return true;
		}
	}

	// ����Ʈ ù��°�� ����
	public boolean addToFirst(T anElement) {
		return this.addTo(anElement, 0);
	}

	// ����Ʈ �������� ����
	public boolean addToLast(T anElement) {
		return this.addTo(anElement, this.size());
	}

	// ������ ������ ����
	public boolean add(T anElement) {
		return this.addToFirst(anElement);
	}

	// �־��� ������ ���Ҹ� ����
	public T removeFrom(int order) {
		if (!this.anElementDoesExistAt(order)) { // ������ ���Ұ� ���ų� , �߸��� ��ġ
			return null;
		} else {
			// ����Ʈ�� ��� ���� ������ , ������ ���Ұ� ����
			LinkedNode<T> removedNode = null;
			if (order == 0) { // ������ ���Ұ� �� �� ����
				removedNode = this._head;
				this._head = this._head.next();
			} else { // ������ ������ ��ġ�� �� ���� �ƴϸ� , ���� ���Ұ� �� �� �̻�
				LinkedNode<T> previousNode = this._head;
				for (int i = 1; i < order; i++) {
					previousNode = previousNode.next(); // ������ ��ġ�� �� ��带 ã�´�
				}
				removedNode = previousNode.next();
				previousNode.setNext(removedNode.next());
			}
			this._size--; // ������ ����
			return removedNode.element();
		}
	}

	// ù��° ���Ҹ� ����
	public T removeFirst() {
		return (this.removeFrom(0));
	}

	// ������ ���Ҹ� ����
	public T removeLast() {
		return (removeFrom(this.size() - 1));
	}

	// ������ ���Ҹ� ����
	public T removeAny() {
		return this.removeFirst();
	}

	// �־��� ������ ���Ҹ� �־��� ���ҷ� ��ü
	public boolean replaceAt(T anElement, int order) {
		if (!this.anElementDoesExistAt(order)) {
			// ��ü�� ��尡 ���ų� , �߸��� ��ġ
			return false;
		} else {
			LinkedNode<T> currentNode = this._head; // head�� ����
			for (int i = 0; i < order; i++) { // ������ŭ �ݺ��Ͽ� ��ġ ã�´�
				currentNode = currentNode.next();
				// ���Ҹ� ��ü�� ��带 ã�´�
			}
			currentNode.setElement(anElement);
			return true;
		}
	}

	// Inner Class "ListIterator"�� ����
	private class ListIterator implements Iterator<T> {

		private LinkedNode<T> _nextNode; // ���� ü�ο��� ���� ���Ҹ� �����ϴ� ���

		// ������
		private ListIterator() {
			this._nextNode = LinkedList.this._head;
		}

		// ���� ���Ұ� �����ϴ����� �˾Ƴ���
		@Override
		public boolean hasNext() {
			return (this._nextNode != null);
		}

		// ���� ���Ҹ� ����. ������ null�� ��´�
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
