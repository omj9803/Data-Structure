import java.util.Random;

public final class DataGenerator {
	// Static Class.
	// ���̻� ����� �ʿ䰡 �����Ƿ� "final" ����

	// �����ڴ� private. �ܺο��� ��ü�� ���� �� ����.
	private DataGenerator() {
	};

	// ��� �����Լ��� static
	public static Integer[] ascendingOrderList(int aSize) { // �������� ����Ʈ ����
		Integer[] list = null; // �ʱ�ȭ
		if (aSize > 0) { // aSize�� 0���� ũ�ٸ�
			list = new Integer[aSize]; // �迭 ����
			for (int i = 0; i < aSize; i++) { // i�� �°� ����Ʈ�� �� ����
				list[i] = i;
			}
		}
		return list;
	}

	public static Integer[] descendingOrderList(int aSize) { // �������� ����Ʈ ����
		Integer[] list = null; // �ʱ�ȭ
		int count = aSize - 1; // ���� ���� �� ����� count ����
		if (aSize > 0) { // aSize�� 0���� ũ�ٸ�
			list = new Integer[aSize]; // �迭����
			for (int i = 0; i < aSize; i++) { // i�� �°� ����Ʈ�� �� ����
				list[i] = count;
				count--;
			}
		}
		return list;
	}

	public static Integer[] randomOrderList(int aSize) { // ���� �� ����Ʈ ����
		// ��ġ�� ���Ұ� ���� ������ ����Ʈ�� �����Ͽ� , �����ش�
		Integer[] list = null;
		if (aSize > 0) {
			// �ϴ� Ascending order list �� �����
			list = new Integer[aSize];
			for (int i = 0; i < aSize; i++) {
				list[i] = i;
			}
			// �� ���� list[i] �� ���� ������ ��ġ r �� �����Ͽ� list[i] �� list[r] �� �¹ٲ۴�
			Random random = new Random();
			for (int i = 0; i < aSize; i++) {
				int r = random.nextInt(aSize);
				Integer temp = list[i];
				list[i] = list[r];
				list[r] = temp;
			}
		}

		return list;

	}

	public static Integer[] randomListWithoutDuplication(int aSize) {
		// ��ġ�� ���Ұ� ���� ������ ����Ʈ�� �����Ͽ� , �����ش�
		Integer[] list = null;
		if (aSize > 0) {
			// �ϴ� Ascending order list �� �����
			list = DataGenerator.ascendingOrderList(aSize);
			// �� ���� list[i] �� ���� ������ ��ġ r �� �����Ͽ� list[i] �� list[r] �� �¹ٲ۴�
			Random random = new Random();
			for (int i = 0; i < aSize; i++) {
				int randomIndex = random.nextInt(aSize);
				Integer temp = list[i];
				list[i] = list[randomIndex];
				list[randomIndex] = temp;
			}
		}
		return list;
	}
}
