
public class LinkedBag<E> {

	// ����� �ν��Ͻ� ����
	private int _size; // ������ ������ �ִ� ������ ����(����� ����� ����)
	private LinkedNode<E> _head; // ���� ü���� �� �� ��带 �����Ѵ�

	// ������
	public LinkedBag() {
		setSize(0);
		setHead(null);
	}

	// getter / setter
	// Bag�� ����ִ� ������ Ȯ���Ѵ�
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

	// Bag�� ����ִ��� Ȯ���Ѵ�
	public boolean isEmpty() {
		return (this.size() == 0);
	}

	public boolean isFull() {
		return false;
		// ���� ���� ������ ���� ���� �����Ƿ�
		// �ý��� �޸� ���� ������ ���ٰ� �����Ѵ�
	}

	// Bag �ȿ� �־��� ���Ұ� �����ϴ��� Ȯ���Ѵ�
	public boolean doesContain(E anElement) {
		LinkedNode<E> currentNode = this.head(); // head�� currentNode�� ����
		while (currentNode != null) { // currentNode�� null�� �ƴѵ��� �ݺ�
			if (currentNode.element().equals(anElement)) {
				// ���� ã�� ���Ұ� �ִٸ� true ��ȯ
				return true;
			}
			currentNode = currentNode.next(); // ���� ��� �ݺ�
		}
		return false;
	}

	// Bag �ȿ� �־��� ���Ұ� �� �� �ִ��� Ȯ���Ѵ�
	public int frequencyOf(E anElement) {
		int frequencyCount = 0; // ���Ҹ� count�� ���� �ʱ�ȭ
		LinkedNode<E> currentNode = this._head; // head�� currentNode�� ����
		while (currentNode != null) { // currentNode�� null�� �ƴѵ��� �ݺ�
			if (currentNode.element().equals(anElement)) {
				// �־��� ���Ұ� �ִٸ� count����
				frequencyCount++;
			}
			currentNode = currentNode.next(); // ���� ��� �ݺ�
		}
		return frequencyCount;
	}

	// Bag �ȿ� �־��� ������ ���Ҹ� ��´�
	// �� ���� order 0, �� �ڰ� order size()-1
	// �־��� ������ ���� ������ ��� ������ null�� ��´�
	public E elementAt(int anOrder) {
		if ((anOrder < 0) || (anOrder >= this.size())) {
			return null; // anOrder �� ���� ������ ��� �ִ�
		} else { // anOrder�� ���� ���� �ȿ� �ִ�
			LinkedNode<E> currentNode = this.head();
			for (int i = 0; i < anOrder; i++) {
				currentNode = currentNode.next();
			}
			return currentNode.element();
		}
	}

	// Bag�� ���Ҹ� �߰��Ѵ�
	public boolean add(E anElement) {
		// LinkedList������ ���� ���� ������ �����Ƿ� ���ǹ��� Ȯ��.
		if (this.isFull()) {
			return false;
		} else {
			LinkedNode<E> newNode = new LinkedNode<E>(); // ���ο� ��� ����
			newNode.setElement(anElement); // ���ο� ��忡 �Է¹��� ���� �ִ´�
			newNode.setNext(this.head()); // ���ο� head�� �����Ѵ�
			this.setHead(newNode); // head ����
			this.setSize(this.size() + 1); // size ���������� ����
			return true;
		}
	}

	// Bag���� ���Ҹ� �����Ѵ�
	public boolean remove(E anElement) {
		if (this.isEmpty()) { // ����ִ��� Ȯ���Ѵ�
			return false; // ����ִٸ� false ��ȯ
		} else {
			LinkedNode<E> previousNode = null; // ������带 ��Ÿ�� ����
			LinkedNode<E> currentNode = this._head; // ���� ��带 head�� �����Ѵ�
			boolean found = false; // ã�Ҵ��� ���θ� ������ ����
			
			// ù ��° �ܰ� : ������ ��ġ ã��
			while (currentNode != null && !found) { // ��尡 ���������� �ݺ�
				if (currentNode.element().equals(anElement)) { // ã���� �ϴ� ���� ��带 �߰��ϸ�
					found = true; // �߰߿��θ� �����Ѵ�
				} else {
					// ã�� ���Ѱ�� ���� ���� �̵�
					previousNode = currentNode;
					currentNode = currentNode.next();
				}
			}

			// �� ��° �ܰ� : �����ϱ�
			if (!found) { // ã�� ���� ���� false�� ��ȯ
				return false;
			} else { // ������ ����� ã�� ���,
				if (currentNode == this.head()) { // ������ ����� head�� ���
					this.setHead(this.head().next()); // head�� next�� �ٲپ� head�� ���ش�
				}
				else {
					previousNode.setNext(currentNode.next()); // ��������� ������ �������� �������� �����Ͽ� �����带 �����Ѵ�.
				}
				this.setSize(this.size() - 1); // size�� �ϳ� �ٿ� �����Ѵ�
				return true;
			}
		}
	}

	// Bag���� ���� �ϳ��� �������� �����Ѵ�-> head ��� ����
	public E removeAny() {
		if (this.isEmpty()) { // ����ִ� ��� null ��ȯ
			return null;
		} else {
			E removedElement = this.head().element(); // head�� �ִ� element�� ����
			this.setHead(this.head().next()); // head ���� ��带 head�� ����
			this.setSize(this.size() - 1); // size�� �ϳ� �����Ѵ�
			return removedElement; // ������ element�� ��ȯ
		}
	}

	// Bag�� �ʱ�ȭ�Ѵ�. ��� ���� �����Ѵ�
	public void clear() {
		this.setSize(0);
		this.setHead(null);
	}
}
