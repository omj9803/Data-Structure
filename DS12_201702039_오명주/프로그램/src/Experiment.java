public class Experiment {
	private final ParameterSet _parameterSet;
	// Class�� �Լ����� �� ���� ������ �� ����
	// ������, ������ �ȿ����� ���� ������ �� �ִ�
	// ��, ��ü�� ������ �� ������ ���� �״�� �����Ѵ�

	// ������
	public Experiment(ParameterSet givenParameterSet) {
			this._parameterSet = givenParameterSet;
	}

	private ParameterSet parameterSet() {
		return this._parameterSet;
	}

	private Integer[] copyListOfGivenSize(Integer[] aList, int copiedSize) {
		Integer[] copiedList = null;
		if (copiedSize <= aList.length) {
			copiedList = new Integer[copiedSize];
			for (int i = 0; i < copiedSize; i++) {
				copiedList[i] = aList[i];
			}
		}
		return copiedList; // aList[]���� copiedSize��ŭ ������ ����Ʈ�� ��´�
	}

	private long durationOfSingleSort(Sort<Integer> aSort, Integer[] aList) { // ���� ���� �ð� ����
		Timer timer = new Timer(); // Timer ��ü ����
		timer.start(); // ����
		{ aSort.sort(aList, aList.length); } // �ð� ������ �ڵ�
		timer.stop(); // ��
		return timer.duration(); // ������ ���
	}

	public long[] durationsOfSort(Sort<Integer> aSort, Integer[] experimentList) {
		// ���� ����� �Ű������� �־��� ������ ������ �� : Class "Sort" �� �� ��
		int numberOfSteps = this.parameterSet().numberOfSizeIncreasingSteps(); // ũ�� ���� ������ ���� Ƚ��
		long[] durations = new long[numberOfSteps]; // ���� ����� ������ ��
		int sortingSize = this.parameterSet().startingSize(); // ���� �������� ���� ũ��
		int incrementSize = this.parameterSet().incrementSize(); // ���� ������ ���� ũ��
		for (int step = 0; step < numberOfSteps; step++) {
			Integer[] listForSorting = this.copyListOfGivenSize(experimentList, sortingSize); // ������ ����� ������ ����Ʈ ����
			durations[step] = this.durationOfSingleSort(aSort, listForSorting); // �����Ͽ� , �� ����� ����
			sortingSize += incrementSize; // ���� �ܰ��� ���� ������ ũ�⸦ ��´�
		}
		return durations;
	}
}
