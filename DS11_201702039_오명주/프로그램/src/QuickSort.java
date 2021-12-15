
public class QuickSort<E extends Comparable<E>> extends Sort<E> {

	// Constructor
	public QuickSort() {
	}

	// Private methods
	private int pivot(E[] aList, int left, int right) { // pivot�� ��ȯ
		return left;
	}

	private int partition(E[] aList, int left, int right) {
		int toRight = left; // ���������� �� toRight�� left ��ġ�� ����
		int toLeft = right + 1; // �������� �� toLeft�� right+1 ��ġ�� ����
		int pivot = this.pivot(aList, left, right); 
		do {
			do {toRight++;} while (aList[toRight].compareTo(aList[pivot]) < 0); // Left���� Right�� �� ��ġ ����
			do {toLeft--;} while (aList[toLeft].compareTo(aList[pivot]) > 0); // Right���� Left�� �� ��ġ ����
			if (toRight < toLeft) { // toRight < toLeft���
				this.swap(aList, toRight, toLeft); // �ΰ��� �迭�� �ٲ۴�.
			}
		} while (toRight < toLeft); // toRight > toLeft�� �Ǵ� ���� Ż��
		this.swap(aList, pivot, toLeft); // pivot�� toLeft�� �ٲ۴�.
		return toLeft; // pivot ��ġ�� toLeft �̴�.
	}

	private void quickSortRecursively(E[] aList, int left, int right) {
		if (left < right) { // left<right�̸�
			int mid = this.partition(aList, left, right); // ��Ƽ�� ���� pivot ��ġ
			this.quickSortRecursively(aList, left, mid - 1); // �������� ���� �ٽ� �������Ѵ�.
			this.quickSortRecursively(aList, mid + 1, right); // �������� ���� �ٽ� �������Ѵ�.
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
