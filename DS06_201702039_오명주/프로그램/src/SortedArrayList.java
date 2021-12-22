
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

	// 생성자
	public SortedArrayList() {
		this.setCapacity(SortedArrayList.DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public SortedArrayList(int givenCapacity) {
		this.setCapacity(givenCapacity); // Capacity 설정
		this.setElements((E[]) new Comparable[this.capacity()]); // Setter Of Elements
	}

	// 한 칸씩 배열의 원소를 뒤로 미루는 함수 -> 삽입할 위치 확보
	private void makeRoomAt(int aPosition) {
		for (int i = this.size(); i > aPosition; i--) { // this.size()부터 aPosition보다 클 때까지 i --
			this.elements()[i] = this.elements()[i - 1]; // 배열을 한 칸씩 당긴다.
		}
	}

	// 리스트가 비어있는지 확인하는 함수
	public boolean isEmpty() {
		return (this._size == 0); // _size가 0이면 true를 반환
	}

	// 원소를 추가하는 함수
	public void add(E anElement) {
		if (this.size() == 0) { // size가 0일 때
			this.elements()[this.size()] = anElement; // 첫번째 배열에 anElement가 들어감
		} else {
			int order = 0; // 순서 order = 0 으로 선언
			for (int i = 0; i < this.size(); i++) { // this.size() 만큼 반복
				if (this.elements()[i].compareTo(anElement) > 0) { // 처음부터 마지막 배열을 anElement와 비교 후 +1이 나오면
					order++; // 순서를 + 1 해준다.
					break; // 종료
				}
			}
			if (order == this.size()) { // 찾은 순서가 마지막 순서라면
				this.elements()[order] = anElement; // order+1번째에 anElement 삽입
			} else {
				this.makeRoomAt(order); // order번째부터 한 칸씩 미루기
				this.elements()[order] = anElement; // order번 째 배열에 anElement 삽입
			}
		}
		this.setSize(this.size() + 1); // 사이즈 + 1
	}

	// max 값을 구하는 함수
	public E max() {
		// SortedArrayList에서 가장 큰 값은 맨 뒤에 있다.
		if (this.isEmpty()) {
			return null;
		} else {
			return this.elements()[this.size() - 1]; // 마지막 원소를 반환
		}
	}
}
