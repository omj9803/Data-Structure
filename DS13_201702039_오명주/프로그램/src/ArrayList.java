
public class ArrayList<E> implements Stack<E> {

	// Constant
	private static final int DEFAULT_CAPACITY = 1000;

	// private instance variables
	private int _capacity;
	private int _size;
	private E[] _elements;

	// Getters/Setters
	public int capacity() {
		return this._capacity;
	}

	private void setCapacity(int newCapacity) {
		this._capacity = newCapacity;
	}

	@Override
	public int size() {
		return this._size;
	}

	public void setSize(int newSize) {
		this._size = newSize;
	}

	private E[] elements() {
		return this._elements;
	}

	private void setElements(E[] newElements) {
		this._elements = newElements;
	}

	// Constructor
	public ArrayList() {
		this(ArrayList.DEFAULT_CAPACITY); // �ٸ� ������ ���
	}

	@SuppressWarnings("unchecked")
	public ArrayList(int givenCapacity) {
		this.setCapacity(givenCapacity);
		this.setElements((E[]) new Object[this.capacity()]);
	}

	// Private Methods
	private void makeRoomAt(int aPosition) {
		for (int i = this.size(); i > aPosition; i--) { // size~aPosition�ݺ�
			this.elements()[i] = this.elements()[i - 1]; // i-1��° ���Ҹ� i�� ����
		}
	}

	private void removeGapAt(int aPosition) {
		for (int i = aPosition + 1; i < this.size(); i++) { // aPosition+1~size�ݺ�
			this.elements()[i - 1] = this.elements()[i]; // i��° ���Ҹ� i-1�� ����
		}
		this.elements()[this.size() - 1] = null; // ������ ���� ����
	}

	// public Methods
	@Override
	public boolean isFull() { // �迭�� ���� á���� Ȯ��
		return (this.capacity() == this.size());
	}

	@Override
	public boolean isEmpty() { // �迭�� ����ִ°� Ȯ��
		return (this.size() == 0);
	}

	public boolean doesContain(E anElement) { // ���� ���� Ȯ��
		return (this.orderOf(anElement) >= 0); // anElement�� orderOf�� ���� �ִٴ°� ���εǸ� true�� ��ȯ
	}

	public int orderOf(E anElement) {
		int order = -1; // ���� ���� -1 �� ����
		for (int index = 0; index < this.size() && order < 0; index++) { // 0~������ & order�� -1�϶� �ݺ�
			if (this.elements()[index].equals(anElement)) { // index��° �迭�� anElement�� ������
				order = index; // order �� index�� ����
			}
		}
		return order; // ���� �迭�� ã�� ������ order�� ��ȯ
	}

	public E elementAt(int anOrder) { // anOrder��° ���� ��ȯ
		if (anOrder < 0 || anOrder >= this.size()) { // anOrder�� ���� ���̸�
			return null; // null ��ȯ
		} else {
			return this.elements()[anOrder]; // anOrder��° ���� ��ȯ
		}
	}

	public void setElementsAt(int anOrder, E anElement) { // anOrder��°�� ���һ���
		if (anOrder < 0 || anOrder >= this.size()) { // �Է��� anOrder�� ��ȿ�� �Ǵ�
			return;
		} else {
			this.elements()[anOrder] = anElement; // anOrder��° �迭 anElement�� ����
		}
	}

	public boolean addTo(E anElement, int anOrder) { // anOrder��°�� ���һ���
		if (this.isFull()) { // �迭�� ���� á�ٸ�
			return false; // false�� ��ȯ
		} else if (anOrder < 0 || anOrder > this.size()) { // �Է��� anOrder�� ��ȿ�� �Ǵ�
			return false; // ��ȿ���� �ʴٸ� false�� ��ȯ
		} else {
			this.makeRoomAt(anOrder); // anOrder ������ ������ �ڸ� ����
			this.elements()[anOrder] = anElement; // �ش� ������ ���� ����
			this.setSize(this.size() + 1); // size set
			return true;
		}
	}

	public boolean addToFirst(E anElement) { // �迭 ù��°�� ���һ���
		return addTo(anElement, 0);
	}

	public boolean addToLast(E anElement) { // �迭 �������� ���һ���
		return addTo(anElement, this.size());
	}

	public E removeFrom(int anOrder) {
		if (anOrder < 0 || anOrder >= this.size()) { // �Էµ� anOrder ��ȿ�� �Ǵ�
			return null; // ��ȿ���� �ʴٸ� null ��ȯ
		} else {
			E removedElement = this.elements()[anOrder]; // ������ ���Ҹ� removedElement�� ����
			this.removeGapAt(anOrder); // anOrder ���� ���� ��ĭ�� ������ ����
			this.setSize(this.size() - 1); // size set
			return removedElement;
		}
	}

	public E removeFirst() { // ù��° ���� ����
		return removeFrom(0);
	}

	public E removeLast() { // ������ ���� ����
		return removeFrom(this.size() - 1);
	}

	@Override
	public boolean push(E anElement) {
		return this.addToLast(anElement); // �迭 �������� add
	}

	@Override
	public E pop() {
		return this.removeLast(); // �迭 ������ ���� remove
	}

	@Override
	public E peek() {
		if (this.isEmpty()) { // �迭�� empty ���
			return null; // null�� ��ȯ
		} else {
			return this.elementAt(this.size() - 1); // Last element
		}
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.size(); i++) { // �迭�� size��ŭ �ݺ�
			this.elements()[i] = null; // ��� �迭�� null ó��
		}
		this.setSize(0); // size�� 0���� set
	}

}
