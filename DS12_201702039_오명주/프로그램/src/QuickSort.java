
public class QuickSort<E extends Comparable<E>> extends Sort<E> {

	// Constructor
	public QuickSort() {
	}

	// Private methods
	private int pivot(E[] aList, int left, int right) { // pivot값 반환
		return left;
	}

	private int partition(E[] aList, int left, int right) {
		int toRight = left; // 오른쪽으로 갈 toRight는 left 위치에 지정
		int toLeft = right + 1; // 왼쪽으로 갈 toLeft는 right+1 위치에 지정
		int pivot = this.pivot(aList, left, right); 
		do {
			do {toRight++;} while (aList[toRight].compareTo(aList[pivot]) < 0); // Left에서 Right로 갈 위치 선정
			do {toLeft--;} while (aList[toLeft].compareTo(aList[pivot]) > 0); // Right에서 Left로 갈 위치 선정
			if (toRight < toLeft) { // toRight < toLeft라면
				this.swap(aList, toRight, toLeft); // 두개의 배열을 바꾼다.
			}
		} while (toRight < toLeft); // toRight > toLeft가 되는 순간 탈출
		this.swap(aList, pivot, toLeft); // pivot과 toLeft를 바꾼다.
		return toLeft; // pivot 위치가 toLeft 이다.
	}

	private void quickSortRecursively(E[] aList, int left, int right) {
		if (left < right) { // left<right이면
			int mid = this.partition(aList, left, right); // 파티션 후의 pivot 위치
			this.quickSortRecursively(aList, left, mid - 1); // 나누어진 반을 다시 퀵정렬한다.
			this.quickSortRecursively(aList, mid + 1, right); // 나누어진 반을 다시 퀵정렬한다.
		}
	}

	@Override
	public boolean sort(E[] aList, int aSize) {
		if ((aSize < 1) || (aSize > aList.length)) {
			return false;
		}
		int maxLoc = 0;
		for (int i = 1; i < aSize; i++) {
			if (aList[i].compareTo(aList[maxLoc]) > 0) {
				maxLoc = i;
			}
		}
		this.swap(aList, maxLoc, aSize - 1);
		this.quickSortRecursively(aList, 0, aSize - 2);
		return true;
	}
} // End of "Sort"
