
public class ArrayBag<E> {

	// 비공개 인스턴스 변수
	private static final int DEFAULT_CAPACITY = 100;
	private int _capacity; // Bag의 최대크기
	private int _size; // Bag의 동전이 실제 들어있는 크기
	private E _elements[]; // ArrayBag의 원소들을 담을 java 배열

	// 생성자
	// 객체 생성시 Bag의 값을 지정하지 않는경우
	@SuppressWarnings("unchecked") // 형변환에 대한 경고를 제외
	public ArrayBag() {
		this.setCapacity(ArrayBag.DEFAULT_CAPACITY); // Bag의 크기는 default 100으로 설정
		this.setElements((E[]) new Object[this.capacity()]);
		this.setSize(0);
	}

	// 객체 생성시 Bag의 값을 지정하는 경우
	@SuppressWarnings("unchecked") // 형변환에 대한 경고를 제외
	public ArrayBag(int givenCapacity) {
		this.setCapacity(givenCapacity); // Bag의 크기는 입력받은 정수의 값으로 설정
		this.setElements((E[]) new Object[this.capacity()]);
		this.setSize(0);
	}

	// 비공개 함수 -> class 내부에서만 사용
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

	// 해당 동전의 index를 반환한다
	// 해당 동전이 Bag에 없다면 음수를 반환
	private int indexOf(E anElement) {
		int foundIndex = -1; // 음수를 설정
		for (int i = 0; i < this.size() && foundIndex < 0; i++) {
			if (this.elements()[i].equals(anElement)) { // 만약 찾는 원소가 Bag 안에 존재한다면
				foundIndex = i; // 해당 원소의 index를 foundIndex에 저장
			}
		}
		return foundIndex;
	}

	// 공개함수
	// Bag에 들어있는 원소의 개수를 알려준다
	public int size() {
		return this._size;
	}

	// Bag이 비어있는지 알려준다
	public boolean isEmpty() {
		return (this.size() == 0);
		// 실제 들어있는 동전의 개수, size가 0이면 true를 반환
		// 0이 아니면 false를 반환
	}

	// Bag이 가득 차 있는지 알려준다
	public boolean isFull() {
		return (this.size() == this.capacity());
		// 실제 들어있는 동전의 개수(size)와 Bag의 크기(capacity)가 동일하면 true를 반환
		// 같지않으면 false를 반환
	}

	// 주어진 원소가 Bag에 있는지 알려준다
	public boolean doesContain(E anElement) {
		return (this.indexOf(anElement) >= 0); // 원소가 없다면 음수가 반환되어 false를 반환하게 된다
	}

	// 주어진 원소가 Bag에 몇개 있는지 알려준다
	public int frequencyOf(E anElement) {
		int frequencyCount = 0;
		for (int i = 0; i < this._size; i++) { // Bag의 실제 사용하는 크기만큼 반복
			if (this.elements()[i].equals(anElement)) { // 찾는 원소가 Bag의 원소라면
				frequencyCount++; // 개수 증가
			}
		}
		return frequencyCount;
	}

	// Bag에 주어진 원소를 넣는다
	public boolean add(E anElement) {
		if (this.isFull()) { // 가방이 꽉 찼으므로 넣을 수 없다
			return false;
		} else {
			// 빈 여유 공간이 있으므로 넣는다
			// 원소의 순서가 중요하지 않으므로 아무곳에 넣어도 된다
			// 단, 맨 앞부터 차있는 상태는 유지해야한다
			// 가장 편한곳은 배열 맨 마지막 원소 다음칸
			this.elements()[this.size()] = anElement;
			this.setSize(this.size() + 1); // 실제 사용하는 크기 +1
			return true;
		}
	}

	// Bag에서 지정된 원소를 찾아서 있으면 제거한다
	public boolean remove(E anElement) {
		int foundIndex = -1; // 음수로 설정
		boolean found = false;
		// 단계1 : 주어진 원소의 위치를 찾는다
		for (int i = 0; i < this.size() && !found; i++) { // Bag의 실제 사용하는 크기만큼 반복
			if (this.elements()[i].equals(anElement)) { // 만약 같은 값을 찾은 경우,
				foundIndex = i; // 인덱스 저장
				found = true; // 찾았음을 저장
			}
		}
		// 단계2 : 삭제된 원소 이후의 모든 원소를 앞쪽으로 한 칸씩 이동시킨다
		if (!found) { // 찾지 못했다면 found는 false이므로 not을 하면 true가 된다
			return false;
		} else { // 찾은경우
			for (int i = foundIndex; i < this.size() - 1; i++) { // 찾은 원소 이후의 모든 원소 반복
				this.elements()[i] = this.elements()[i + 1]; // 한칸 앞으로 저장
			}
			this.elements()[this.size() - 1] = null; // 더이상 의미 없는 소유권은 null로!
			this.setSize(this.size() - 1); // 실제 사용하는 크기 -1
			return true;
		}
	}

	// Bag을 비운다
	public void clear() {
		// 모든 원소에 대해 null을 진행
		for (int i = 0; i < this.size(); i++) {
			this.elements()[i] = null;
		}
		this.setSize(0); // 실제 사용하는 크기도 0으로 설정
	}

	// 주어진 순서 anOrder에 있는 원소를 돌려준다
	public E elementAt(int anOrder) {
		if ((0 <= anOrder) && (anOrder < this.size())) { // 주어진 순서에 대한 유효성 확인
			return this.elements()[anOrder];
		} else {
			return null;
		}

	}

}
