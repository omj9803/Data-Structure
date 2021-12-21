
public class UnsortedArrayList<E extends Comparable<E>> {
	// ����� ���, ������
	private static final int DEFAULT_CAPACITY = 100;

	private int _capacity;
	private int _size;
	private E[] _elements;

	// Getter/Setter
	private int capacity() { // getter of capacity
		return this._capacity;
	}

	private void setCapacity(int newCapacity) { // setter of capacity
		this._capacity = newCapacity;
	}

	public int size() { // getter of size
		return this._size;
	}

	private void setSize(int newSize) { // setter of size
		this._size = newSize;
	}

	private E[] elements() { // getter of elements
		return this._elements;
	}

	private void setElements(E[] newElements) { // setter of elements
		this._elements = newElements;
	}

	// ������
	public UnsortedArrayList() {
		this.setCapacity(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public UnsortedArrayList(int givenCapacity) {
		this.setCapacity(givenCapacity);
		this.setElements((E[]) new Comparable[this.capacity()]);
	}

	public boolean isEmpty() { // ��������� true
		return (this.size() == 0);
	}

	public boolean isFull() { // ��á���� true
		return (this.size() == this._capacity);
	}

	public E elementAt(int anOrder) { // anOrder��° �迭 ��ȯ
		if (anOrder < 0 || anOrder >= this.size()) { // anOrder�� ���� ���̸�
			return null; // null ��ȯ
		} else {
			return this.elements()[anOrder]; // anOrder��° �迭 ��ȯ
		}
	}

	protected void setElementsAt(int anOrder, E anElement) {
		if (anOrder < 0 || anOrder >= this.size()) { // anOrder ���� ���̶��
			return; // ����
		} else { // ���� ���̶��
			this.elements()[anOrder] = anElement; // anOrder��° �迭 anElement�� ����
		}
	}

	public boolean doesContain(E anElement) { // ���� ���� Ȯ��
		return (this.orderOf(anElement) >= 0); // anElement�� orderOf�� ���� �ִٴ°� ���εǸ�
												// true�� ��ȯ
	}

	public int orderOf(E anElement) {
		int order = -1; // ���� ���� -1 �� ����
		for (int index = 0; index < this.size() && order < 0; index++) { // 0~������ &
			// order�� -1�϶� �ݺ�
			if (this.elements()[index].equals(anElement)) { // index��° �迭�� anElement�� ������
				order = index; // order �� index�� ����
			}
		}
		return order; // ���� �迭�� ã�� ������ order�� ��ȯ
	}

	private void makeRoomAt(int aPosition) {
		for (int i = this.size(); i > aPosition; i--) { // this.size()���� aPosition���� �ݺ�
			this.elements()[i] = this.elements()[i - 1]; // i��° �迭�� i - 1 ��° �迭�� ����
															// �� ĭ�� �̴� ��
		}
	}

	public boolean addToFirst(E anElement) {
		if (this.isFull()) { // ������ �߰��� ���ϴ�
			return false; // false ��ȯ
		} else {
			this.makeRoomAt(0); // 0��°���� ��ĭ�� �δ�
			this.elements()[0] = anElement; // 0��° �迭�� anElement ����
			this.setSize(this.size() + 1); // ������ + 1
			return true; // true��ȯ
		}
	}

	public boolean addToLast(E anElement) {
		if (this.isFull()) { // ������ �߰��� ���ϴ�
			return false; // false ��ȯ
		} else {
			this.elements()[this.size()] = anElement; // this.size()��° �迭�� anElement ����
			this.setSize(this.size() + 1); // ������ + 1
			return true; // true ��ȯ
		}
	}

	public boolean add(E anElement) { // ������ ��ġ�� add
		return this.addToLast(anElement); // ������ ��ġ�� �߰��ϴ� ���� ������
	}

	public Iterator<E> iterator() {
		return (new ListIterator());
	}

	private class ListIterator implements Iterator<E> {
		private int _nextPosition;

		private int nextPosition() {
			return this._nextPosition;
		}

		private void setNextPosition(int newNextPosition) {
			this._nextPosition = newNextPosition;
		}

		private ListIterator() {
			this.setNextPosition(0);
		}

		@Override
		public boolean hasNext() {
			return (this.nextPosition() < UnsortedArrayList.this.size());
		}

		@Override
		public E next() {
			E nextElement = null;
			if (this.hasNext()) {
				nextElement = UnsortedArrayList.this.elements()[this.nextPosition()];
				this.setNextPosition(this.nextPosition() + 1);
			}
			return nextElement;
		}
	}

}
