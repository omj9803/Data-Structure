
public class InsertionSort<E extends Comparable<E>> extends Sort<E> {
	// Constructor
	public InsertionSort() {}

	// Public Method
	public boolean sort(E[] aList, int aSize) {
		if ((aSize < 1) || (aSize > aList.length)) { // aSize 값이 유효한지 판단
			return false;
		}
		int minLoc = 0; // 가장 작은 값이 존재하는 위치를 저장할 변수
		for (int i = 1; i < aSize; i++) { // 제일 작은 값을 찾아 minLoc에 저장
			if (aList[i].compareTo(aList[minLoc]) < 0) {
				minLoc = i;
			}
		}
		this.swap(aList, 0, minLoc); // 0을 삽입하여 실질적인 -무한대 값 역할을 한다.
		// Abstract class "Sort" 에 구현된 것을 그대로 사용하고 있다
		for (int i = 2; i < aSize; i++) {
			E insertedElement = aList[i]; // 삽입할 원소
			int insertionLoc = i - 1; // 삽입할 위치
			while (aList[insertionLoc].compareTo(insertedElement) > 0) { // 삽입할 위치의 원소 값이 삽입할 원소보다 크다면
				aList[insertionLoc + 1] = aList[insertionLoc]; // 그 뒤 값 뒤로 이동
				insertionLoc--;
			}
			// While loop조건이 false 라서 loop 종료 . 따라서 , (insertionLoc+1) 이 원소 삽입 위치
			aList[insertionLoc + 1] = insertedElement;
		}
		return true;
	}
} // End of "Sort"