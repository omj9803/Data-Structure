
public class SortedArrayList<E extends Comparable<E>> {
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
	public SortedArrayList() {
		this.setCapacity(SortedArrayList.DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public SortedArrayList(int givenCapacity) {
		this.setCapacity(givenCapacity); // Capacity ����
		this.setElements((E[]) new Comparable[this.capacity()]); // Setter Of Elements
	}

	// �� ĭ�� �迭�� ���Ҹ� �ڷ� �̷�� �Լ� -> ������ ��ġ Ȯ��
	private void makeRoomAt(int aPosition) {
		for (int i = this.size(); i > aPosition; i--) { // this.size()���� aPosition���� Ŭ ������ i --
			this.elements()[i] = this.elements()[i - 1]; // �迭�� �� ĭ�� ����.
		}
	}

	// ����Ʈ�� ����ִ��� Ȯ���ϴ� �Լ�
	public boolean isEmpty() {
		return (this._size == 0); // _size�� 0�̸� true�� ��ȯ
	}

	// ���Ҹ� �߰��ϴ� �Լ�
	public void add(E anElement) {
		if (this.size() == 0) { // size�� 0�� ��
			this.elements()[this.size()] = anElement; // ù��° �迭�� anElement�� ��
		} else {
			int order = 0; // ���� order = 0 ���� ����
			for (int i = 0; i < this.size(); i++) { // this.size() ��ŭ �ݺ�
				if (this.elements()[i].compareTo(anElement) > 0) { // ó������ ������ �迭�� anElement�� �� �� +1�� ������
					order++; // ������ + 1 ���ش�.
					break; // ����
				}
			}
			if (order == this.size()) { // ã�� ������ ������ �������
				this.elements()[order] = anElement; // order+1��°�� anElement ����
			} else {
				this.makeRoomAt(order); // order��°���� �� ĭ�� �̷��
				this.elements()[order] = anElement; // order�� ° �迭�� anElement ����
			}
		}
		this.setSize(this.size() + 1); // ������ + 1
	}

	// max ���� ���ϴ� �Լ�
	public E max() {
		// SortedArrayList���� ���� ū ���� �� �ڿ� �ִ�.
		if (this.isEmpty()) {
			return null;
		} else {
			return this.elements()[this.size() - 1]; // ������ ���Ҹ� ��ȯ
		}
	}
}
