
public class UnsortedLinkedList<E extends Comparable<E>> {
	// ����� �ν��Ͻ� ����
	private int _size; // ����Ʈ�� ũ��(������ ����)
	private LinkedNode<E> _head; // ����ü���� �� �� ��带 ����
	private int _capacity; // �ִ�ġ

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

	// ����Ʈ�� ������� Ȯ���ϴ� �Լ�
	public boolean isEmpty() {
		return (this.size() == 0); // _size�� 0�̸� true�� ��ȯ
	}

	// ����Ʈ�� �� �� �ִ��� Ȯ���ϴ� �Լ�
	public boolean isFull() {
		return (this.size() == this.capacity()); // _size�� _capacity�� ������ true�� ��ȯ
	}

	// ������
	public UnsortedLinkedList(int givensize) {
		this.setCapacity(givensize);
		this.setHead(null);
	}

	// ���� �˾ƺ���
	// �ִ밪 ��ȯ�ϴ� �Լ�
	public E max() {
		if (this.isEmpty()) { // ����ִٸ� null ��ȯ
			return null;
		} else {
			LinkedNode<E> currentNode = this.head(); // ������ ���� �� ���� ����
			LinkedNode<E> maxNode = new LinkedNode<E>(this.head().element(), null);
			while (currentNode != null) { // ������ ������ �ݺ�
				if (maxNode.element().compareTo(currentNode.element()) < 0) {
					maxNode.setElement(currentNode.element());
				}
				currentNode = currentNode.next();
			}
			return maxNode.element();
		}
	}

	// ��� �߰��ϴ� �Լ�
	public void add(E anElement) {
		// head�� ��� �߰��ϴ� �ڵ�
		LinkedNode<E> nodeForAdd = new LinkedNode<E>(anElement, null);
		nodeForAdd.setNext(this.head());
		this.setHead(nodeForAdd);
		this.setSize(this.size() + 1);
	}

}
