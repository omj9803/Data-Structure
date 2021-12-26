
public class ArrayBag<E> {

	// ����� �ν��Ͻ� ����
	private static final int DEFAULT_CAPACITY = 100;
	private int _capacity; // Bag�� �ִ�ũ��
	private int _size; // Bag�� ������ ���� ����ִ� ũ��
	private E _elements[]; // ArrayBag�� ���ҵ��� ���� java �迭

	// ������
	// ��ü ������ Bag�� ���� �������� �ʴ°��
	@SuppressWarnings("unchecked") // ����ȯ�� ���� ��� ����
	public ArrayBag() {
		this.setCapacity(ArrayBag.DEFAULT_CAPACITY); // Bag�� ũ��� default 100���� ����
		this.setElements((E[]) new Object[this.capacity()]);
		this.setSize(0);
	}

	// ��ü ������ Bag�� ���� �����ϴ� ���
	@SuppressWarnings("unchecked") // ����ȯ�� ���� ��� ����
	public ArrayBag(int givenCapacity) {
		this.setCapacity(givenCapacity); // Bag�� ũ��� �Է¹��� ������ ������ ����
		this.setElements((E[]) new Object[this.capacity()]);
		this.setSize(0);
	}

	// ����� �Լ� -> class ���ο����� ���
	// getter / setter
	private int capacity() {
		return this._capacity;
	}

	private void setCapacity(int newCapacity) {
		this._capacity = newCapacity;
	}

	private void setSize(int newSize) {
		this._size = newSize;
	}

	private E[] elements() {
		return this._elements;
	}

	private void setElements(E[] newElements) {
		this._elements = newElements;
	}

	// �ش� ������ index�� ��ȯ�Ѵ�
	// �ش� ������ Bag�� ���ٸ� ������ ��ȯ
	private int indexOf(E anElement) {
		int foundIndex = -1; // ������ ����
		for (int i = 0; i < this.size() && foundIndex < 0; i++) {
			if (this.elements()[i].equals(anElement)) { // ���� ã�� ���Ұ� Bag �ȿ� �����Ѵٸ�
				foundIndex = i; // �ش� ������ index�� foundIndex�� ����
			}
		}
		return foundIndex;
	}

	// �����Լ�
	// Bag�� ����ִ� ������ ������ �˷��ش�
	public int size() {
		return this._size;
	}

	// Bag�� ����ִ��� �˷��ش�
	public boolean isEmpty() {
		return (this.size() == 0);
		// ���� ����ִ� ������ ����, size�� 0�̸� true�� ��ȯ
		// 0�� �ƴϸ� false�� ��ȯ
	}

	// Bag�� ���� �� �ִ��� �˷��ش�
	public boolean isFull() {
		return (this.size() == this.capacity());
		// ���� ����ִ� ������ ����(size)�� Bag�� ũ��(capacity)�� �����ϸ� true�� ��ȯ
		// ���������� false�� ��ȯ
	}

	// �־��� ���Ұ� Bag�� �ִ��� �˷��ش�
	public boolean doesContain(E anElement) {
		return (this.indexOf(anElement) >= 0); // ���Ұ� ���ٸ� ������ ��ȯ�Ǿ� false�� ��ȯ�ϰ� �ȴ�
	}

	// �־��� ���Ұ� Bag�� � �ִ��� �˷��ش�
	public int frequencyOf(E anElement) {
		int frequencyCount = 0;
		for (int i = 0; i < this._size; i++) { // Bag�� ���� ����ϴ� ũ�⸸ŭ �ݺ�
			if (this.elements()[i].equals(anElement)) { // ã�� ���Ұ� Bag�� ���Ҷ��
				frequencyCount++; // ���� ����
			}
		}
		return frequencyCount;
	}

	// Bag�� �־��� ���Ҹ� �ִ´�
	public boolean add(E anElement) {
		if (this.isFull()) { // ������ �� á���Ƿ� ���� �� ����
			return false;
		} else {
			// �� ���� ������ �����Ƿ� �ִ´�
			// ������ ������ �߿����� �����Ƿ� �ƹ����� �־ �ȴ�
			// ��, �� �պ��� ���ִ� ���´� �����ؾ��Ѵ�
			// ���� ���Ѱ��� �迭 �� ������ ���� ����ĭ
			this.elements()[this.size()] = anElement;
			this.setSize(this.size() + 1); // ���� ����ϴ� ũ�� +1
			return true;
		}
	}

	// Bag���� ������ ���Ҹ� ã�Ƽ� ������ �����Ѵ�
	public boolean remove(E anElement) {
		int foundIndex = -1; // ������ ����
		boolean found = false;
		// �ܰ�1 : �־��� ������ ��ġ�� ã�´�
		for (int i = 0; i < this.size() && !found; i++) { // Bag�� ���� ����ϴ� ũ�⸸ŭ �ݺ�
			if (this.elements()[i].equals(anElement)) { // ���� ���� ���� ã�� ���,
				foundIndex = i; // �ε��� ����
				found = true; // ã������ ����
			}
		}
		// �ܰ�2 : ������ ���� ������ ��� ���Ҹ� �������� �� ĭ�� �̵���Ų��
		if (!found) { // ã�� ���ߴٸ� found�� false�̹Ƿ� not�� �ϸ� true�� �ȴ�
			return false;
		} else { // ã�����
			for (int i = foundIndex; i < this.size() - 1; i++) { // ã�� ���� ������ ��� ���� �ݺ�
				this.elements()[i] = this.elements()[i + 1]; // ��ĭ ������ ����
			}
			this.elements()[this.size() - 1] = null; // ���̻� �ǹ� ���� �������� null��!
			this.setSize(this.size() - 1); // ���� ����ϴ� ũ�� -1
			return true;
		}
	}

	// Bag�� ����
	public void clear() {
		// ��� ���ҿ� ���� null�� ����
		for (int i = 0; i < this.size(); i++) {
			this.elements()[i] = null;
		}
		this.setSize(0); // ���� ����ϴ� ũ�⵵ 0���� ����
	}

	// �־��� ���� anOrder�� �ִ� ���Ҹ� �����ش�
	public E elementAt(int anOrder) {
		if ((0 <= anOrder) && (anOrder < this.size())) { // �־��� ������ ���� ��ȿ�� Ȯ��
			return this.elements()[anOrder];
		} else {
			return null;
		}

	}

}
