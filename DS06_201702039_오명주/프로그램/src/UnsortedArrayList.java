
public class UnsortedArrayList<E extends Comparable<E>> {
	// 비공개 상수, 변수들
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

	// 생성자
	public UnsortedArrayList() {
		this.setCapacity(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public UnsortedArrayList(int givenCapacity) {
		this.setCapacity(givenCapacity);
		this.setElements((E[]) new Comparable[this.capacity()]);
	}

	// 리스트가 비어있는지 확인하는 함수
	public boolean isEmpty() {
		return (this.size() == 0);
	}

	// 원소를 더하는 함수
	public void add(E anElement) {
		this.elements()[this.size()] = anElement;
		this.setSize(this.size() + 1);
	}

	// max값을 반환하는 함수
	public E max() {
		if (this.isEmpty()) {
			return null;
		} else {
			E maxElement = this.elements()[0];
			for (int i = 1; i < this.size(); i++) {
				if (maxElement.compareTo(this.elements()[i]) < 0)
					maxElement = this.elements()[i];
			}
			return maxElement;
		}
	}

}
