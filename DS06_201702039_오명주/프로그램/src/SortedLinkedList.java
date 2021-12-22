
public class SortedLinkedList<E extends Comparable<E>> {
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
	public SortedLinkedList(int givenSize) {
		this.setCapacity(givenSize);
		this.setHead(null);
	}

	// ����Ʈ�� ���Ҹ� �����ϴ� �Լ�
	public boolean add(E anElement) {
		if (this.isFull()) { // size = capacity��� false ��ȯ
			return false;
		} else {
			LinkedNode<E> nodeForAdd = new LinkedNode<E>(anElement, null); // �־��� anElement�� ���� ������
			if (this.isEmpty()) { // ����Ʈ�� ����ִٸ�
				this.setHead(nodeForAdd); // head�� ����
			} else {
				LinkedNode<E> current = this.head(); // ���� ���ϴ� ���
				LinkedNode<E> previous = null; // current�� �� ��� ������ �Ϸ��� , �� ��带 �˾ƾ� �Ѵ�
				while (current != null) { // ����Ʈ�� ���� ������ �� ���� �� �˻��Ѵ�
					if (current.element().compareTo(anElement) > 0) {
						break; // ������ ��ġ�� ã�� ���̹Ƿ� �� �˻� ����
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

	// max�� ��ȯ�ϴ� �Լ�
	public E max() {
		if (this.isEmpty()) {
			return null;
		} else {
			LinkedNode<E> currentNode = this._head; // ������ ���� �� ���� ����
			while (currentNode.next() != null) { // ����Ʈ ���������� �ݺ�
				currentNode = currentNode.next();
			}
			return currentNode.element(); // ����Ʈ ������ ��� element ��ȯ
		}
	}
}
