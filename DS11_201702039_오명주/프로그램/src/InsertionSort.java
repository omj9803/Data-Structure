
public class InsertionSort<E extends Comparable<E>> extends Sort<E> {
	// Constructor
	public InsertionSort() {}

	// Public Method
	public boolean sort(E[] aList, int aSize) {
		if ((aSize < 1) || (aSize > aList.length)) { // aSize ���� ��ȿ���� �Ǵ�
			return false;
		}
		int minLoc = 0; // ���� ���� ���� �����ϴ� ��ġ�� ������ ����
		for (int i = 1; i < aSize; i++) { // ���� ���� ���� ã�� minLoc�� ����
			if (aList[i].compareTo(aList[minLoc]) < 0) {
				minLoc = i;
			}
		}
		this.swap(aList, 0, minLoc); // 0�� �����Ͽ� �������� -���Ѵ� �� ������ �Ѵ�.
		// Abstract class "Sort" �� ������ ���� �״�� ����ϰ� �ִ�
		for (int i = 2; i < aSize; i++) {
			E insertedElement = aList[i]; // ������ ����
			int insertionLoc = i - 1; // ������ ��ġ
			while (aList[insertionLoc].compareTo(insertedElement) > 0) { // ������ ��ġ�� ���� ���� ������ ���Һ��� ũ�ٸ�
				aList[insertionLoc + 1] = aList[insertionLoc]; // �� �� �� �ڷ� �̵�
				insertionLoc--;
			}
			// While loop������ false �� loop ���� . ���� , (insertionLoc+1) �� ���� ���� ��ġ
			aList[insertionLoc + 1] = insertedElement;
		}
		return true;
	}
} // End of "Sort"