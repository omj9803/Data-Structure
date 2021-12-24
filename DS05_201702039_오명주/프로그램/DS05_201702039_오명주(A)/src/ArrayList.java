
public class ArrayList<T> {
	// Constants
	// ����Ʈ �ִ�ũ�⸦ �������� �ʾ��� ��� Default��
	private static final int DEFAULT_CAPACITY = 100;

	// Private Instance Variables
	private int _capacity; // ����Ʈ �ִ�ũ��
	private int _size; // ���� ����Ʈ ũ��
	private T[] _elements; // ����Ʈ ���

	// ListIterator �����Ͽ� ���
	public Iterator<T> iterator() {
		return (new ListIterator());
	}

	// Getters/Setters
	public int capacity() {
		return this._capacity;
	}

	public void setCapacity(int newCapacity) {
		this._capacity = newCapacity;
	}

	public int size() {
		return this._size;
	}

// �̹� ���������� ���ʿ��� �Լ�
//	private void setSize(int newSize) {
//		this._size = newSize;
//	}

	private T[] elements() {
		return this._elements;
	}

	private void setElements(T[] newElements) {
		this._elements = newElements;
	}

	// Constructor
	@SuppressWarnings("unchecked")
	public ArrayList(int givenCapacity) {
		this.setCapacity(givenCapacity);
		this.setElements((T[]) new Object[this.capacity()]);
	}

	public ArrayList() {
		this(ArrayList.DEFAULT_CAPACITY);
	}

	// ����Ʈ�� ������� Ȯ��
	public boolean isEmpty() {
		return (this.size() == 0);
	}

	// ����Ʈ�� ���� ���ִ��� Ȯ��
	public boolean isFull() {
		return (this.size() == this.capacity());
	}

	// ����Ʈ�� ���Ұ� �ִ��� Ȯ��
	public boolean doesContain(T anElement) {
		return (this.orderOf(anElement) != -1); // orderOf : �������� ������ -1 ��ȯ
	}

	// �־��� ������ �ִ� ���Ҹ� ��ȯ
	public T elementAt(int order) {
		if (this.anElementDoesExistAt(order)) { // ��ȿ�� �������� Ȯ��
			int position = order;
			return this._elements[position];
		} else {
			return null;
		}
	}

	// ���Ұ� ����Ʈ�� ��ȿ���� Ȯ��
	private boolean anElementDoesExistAt(int order) {
		return ((order >= 0) && (order < this.size()));
	}

	// ����Ʈ�� ù��° ���� ��ȯ
	public T first() {
		if (this.isEmpty()) { // ����ִٸ� null��ȯ
			return null;
		} else {
			return this.elementAt(0); // 0��° ���� ��ȯ
		}
	}

	// ����Ʈ�� ������ ���� ��ȯ
	public T last() {
		if (this.isEmpty()) { // ����ִٸ� null��ȯ
			return null;
		} else {
			return this._elements[this.size() - 1]; // size-1 ��° ���� ��ȯ
		}
	}

	// ���� anElement �� ����Ʈ �ȿ� �����ϸ� �ش� ��ġ�� �����ش�
	// �������� ������ -1 �� �����ش�
	public int orderOf(T anElement) {
		for (int order = 0; order < this.size(); order++) {
			if (this._elements[order].equals(anElement)) {
				return order;
			}
		}
		return -1; // �־��� ���� anElement �� ����Ʈ �ȿ� ����
	}

	// ���� ����
	public boolean addTo(T anElement, int order) {
		if (this.isFull()) { // ����Ʈ�� �� á�ٸ� false ��ȯ
			return false;
		} else {
			if ((order >= 0) && (order <= this.size())) { // sizeũ��: �������� ���Ҹ� �ְڴٴ� �ǹ�
				this.makeRoomAt(order); // ������ ������ Ȯ��
				this._elements[order] = anElement; // ����
				this._size++; // ������ ����
				return true;
			} else {
				return false; // �߸��� ���� ��ġ
			}
		}
	}

	// ������ ������ �����
	private void makeRoomAt(int position) {
		for (int i = this.size(); i > position; i--) { // size ũ����� ����
			this._elements[i] = this._elements[i - 1]; // ��ĭ�� �ڷ� ����
		}
	}

	// ����Ʈ ù��°�� ���� ����
	public boolean addToFirst(T anElement) {
		return this.addTo(anElement, 0);
	}

	// ����Ʈ �������� ���� ����
	public boolean addToLast(T anElement) {
		return this.addTo(anElement, this.size());
	}

	// ������ ��ġ�� ���� ����
	public boolean add(T anElement) {
		return this.addToLast(anElement); // ���� ȿ������ ���� ����
	}

	// �־��� ������ ���Ҹ� ����
	public T removeFrom(int order) {
		// �־��� ���� order �� ���Ұ� ������ null �� return �Ѵ�
		// ���Ұ� ������ ����Ʈ���� �����Ͽ� return �Ѵ�
		T removedElement = null;
		if (this.anElementDoesExistAt(order)) {
			// ����Ʈ�� empty �̸� �� ������ false �� ��´�
			// ���� , ������ empty �˻縦 ���� �ʾƵ� �����ϴ�
			removedElement = this._elements[order];
			this.removeGapAt(order); // �� ���� ����
			this._size--; // ������ ����
		}
		return removedElement;
	}

	// ������ �� ������ ����
	private void removeGapAt(int position) {
		// ����Ʈ�� empty �� �ƴ� : ������ (this.size() > 0)
		// position �� valid: ������ (0 <= position < this.size())
		for (int i = position + 1; i < this.size(); i++) {
			this._elements[i - 1] = this._elements[i];
		}
		this._elements[this.size() - 1] = null;
	}

	// ù��° ���� ����
	public T removeFirst() {
		return removeFrom(0);
	}

	// ������ ���� ����
	public T removeLast() {
		return removeFrom(this._size - 1);
	}

	// ������ ���� ����
	public T removeAny() {
		return removeLast();
	}

	// ����Ʈ Ư�� ������ ���� ��ü
	public boolean replaceAt(T anElement, int order) {
		if (this.anElementDoesExistAt(order)) { // ������ ����Ʈ���� ��ȿ���� Ȯ��
			this._elements[order] = anElement; // ��ü ����
			return true;
		} else {
			return false;
		}
	}

	// Inner Class "ListIterator"�� ����
	private class ListIterator implements Iterator<T> {

		private int _nextPosition; // �迭������ ���� ���� ��ġ

		// ���� ��ġ ��ȯ�ϴ� getter
		private int nextPosition() {
			return this._nextPosition;
		}

		// ���� ��ġ �����ϴ� setter
		private void setNextPosition(int newNextPosition) {
			this._nextPosition = newNextPosition;
		}

		// ������
		private ListIterator() {
			this.setNextPosition(0);
		}

		// ���� ���Ұ� �����ϴ����� �˾Ƴ���
		@Override
		public boolean hasNext() {
			return (this.nextPosition() < ArrayList.this.size());
		}

		// ���� ���Ҹ� ����. ������ null�� ��´�
		@Override
		public T next() {
			T nextElement = null;
			if (this.hasNext()) { // ���� ���Ұ� �����ϴ� ���� �ݺ�
				nextElement = ArrayList.this.elements()[this.nextPosition()];
				this.setNextPosition(this.nextPosition() + 1); // �ɾ����
			}
			return nextElement;
		}

	}

}
