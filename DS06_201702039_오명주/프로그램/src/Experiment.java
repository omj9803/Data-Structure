import java.util.Random;

public class Experiment {
	private static final int DEFAULT_NUMBER_OF_ITRATION = 5;
	private static final int DEFAULT_FIRST_SIZE = 10000; // ù ��° ���� ������ ũ��
	private static final int DEFAULT_SIZE_INCREMENT = 10000; // ���� ������ ũ�� ������

	private int _numberOfIteration; // �ݺ�Ƚ��
	private int _firstSize; // ó��ũ��
	private int _sizeIncrement; // ������
	private Coin[] _data; // ������ �迭����
	private MeasuredResult[] _measuredResults; // ������ �迭���� -> �����������

	// getter / setter ����
	public int numberOfIteration() {
		return this._numberOfIteration;
	}

	public void setNumberOfIteration(int newNumberOfIteration) {
		this._numberOfIteration = newNumberOfIteration;
	}

	public int firstSize() {
		return _firstSize;
	}

	public void setFirstSize(int newFirstSize) {
		this._firstSize = newFirstSize;
	}

	public int sizeIncrement() {
		return _sizeIncrement;
	}

	public void setSizeIncrement(int newSizeIncrement) {
		this._sizeIncrement = newSizeIncrement;
	}

	public Coin[] data() {
		return this._data;
	}

	public void setData(Coin[] newData) {
		this._data = newData;
	}

	public MeasuredResult[] measuredResults() {
		return this._measuredResults;
	}

	public void setMeasuredResults(MeasuredResult[] newMeasuredResults) {
		this._measuredResults = newMeasuredResults;
	}

	// ���� �������� �ִ� ũ�⸦ ����Ͽ� �����ش�
	public int maxSize() { // maxSize�� ����
		return this.firstSize() + this.sizeIncrement() * (this.numberOfIteration() - 1);
	}

	// ������
	public Experiment() {
		this.setNumberOfIteration(DEFAULT_NUMBER_OF_ITRATION); // �ݺ� 5
		this.setFirstSize(DEFAULT_FIRST_SIZE); // ó��ũ�� 10000
		this.setSizeIncrement(DEFAULT_SIZE_INCREMENT); // ������ 10000
		this.setData(new Coin[this.maxSize()]); // Setter of Data
		this.setMeasuredResults(new MeasuredResult[this.numberOfIteration()]); // Setter of MeasuredResults

	}

	public Experiment(int givenNumberOfIteration, int givenFirstSize, int givenSizeIncrement) {
		this.setNumberOfIteration(givenNumberOfIteration); // �ݺ��� givenNumberOfIteration
		this.setFirstSize(givenFirstSize); // ó��ũ�� givenFirstSize
		this.setSizeIncrement(givenSizeIncrement); // ������ givenSizeIncrement
		this.setData(new Coin[this.maxSize()]); // Setter of Data
		this.setMeasuredResults(new MeasuredResult[this.numberOfIteration()]); // Setter of MeasuredResults

	}

	// ���� ������ �ʿ��� �����͸� �����Ѵ�, �������
	public void generateData() {
		Random random = new Random(); // random ����
		for (int i = 0; i < this.maxSize(); i++) { // this.maxSize()��ŭ �ݺ�
			int randomCoinValue = random.nextInt(this.maxSize()); // 0~maxSize()�� ������ ���� �� randomCoinValue�� ����
			this.data()[i] = new Coin(randomCoinValue); // data�� i��° �迭�� randomCoinValue�� ���� ����
		}
	}

	public void measureForUnsortedArrayList() {
		@SuppressWarnings("unused")
		Coin maxCoin;

		long durationForAdd, durationForMax;
		long start, stop;

		int dataSize = this.firstSize();
		for (int iteration = 0; iteration < this.numberOfIteration(); iteration++) {
			UnsortedArrayList<Coin> list = new UnsortedArrayList<Coin>(dataSize);
			durationForAdd = 0;
			durationForMax = 0;
			for (int i = 0; i < dataSize; i++) {
				start = System.nanoTime();
				list.add(this.data()[i]);
				stop = System.nanoTime();
				durationForAdd += (stop - start);

				start = System.nanoTime();
				maxCoin = list.max();
				stop = System.nanoTime();
				durationForMax += (stop - start);
			}
			this.measuredResults()[iteration] = new MeasuredResult(dataSize, durationForAdd, durationForMax);
			dataSize += this.sizeIncrement();
		}
	}

	// Sorted Array�� ������ List�� ������ �����Ѵ�
	public void measureForSortedArrayList() {
		@SuppressWarnings("unused")
		Coin maxCoin;

		long durationForAdd, durationForMax;
		long start, stop;

		int dataSize = this.firstSize();
		for (int iteration = 0; iteration < this.numberOfIteration(); iteration++) {
			SortedArrayList<Coin> list = new SortedArrayList<Coin>(dataSize);
			durationForAdd = 0;
			durationForMax = 0;
			// �����Ǿ� ����� �������� �ϳ��� ������ �迭�� �߰��ϴ� �ݺ���
			for (int i = 0; i < dataSize; i++) {
				start = System.nanoTime();
				list.add(this.data()[i]);
				stop = System.nanoTime();
				durationForAdd += (stop - start);

				start = System.nanoTime();
				maxCoin = list.max();
				stop = System.nanoTime();
				durationForMax += (stop - start);
			}
			// ������ ��� ���� ����
			this.measuredResults()[iteration] = new MeasuredResult(dataSize, durationForAdd, durationForMax);
			dataSize += this.sizeIncrement();
		}
	}

	public void measureForSortedLinkedList() {
		@SuppressWarnings("unused")
		Coin maxCoin; // �ִ밪 Coin ����

		long durationForAdd, durationForMax; // �հ� �ִ밪 - ��� ��
		long start, stop; // ���۰� ���� - ���� ����

		int dataSize = this.firstSize(); // dataSize = firstSize = 10000
		for (int iteration = 0; iteration < this.numberOfIteration(); iteration++) { // 0~4���� �ݺ�
			SortedLinkedList<Coin> list = new SortedLinkedList<Coin>(dataSize); // dataSize�� ���� list����
			durationForAdd = 0; // �ʱⰪ 0
			durationForMax = 0; // �ʱⰪ 0
			for (int i = 0; i < dataSize; i++) { // dataSize��ŭ �ݺ�
				start = System.nanoTime(); // ����
				list.add(this.data()[i]); // i��° �迭�� list�� add
				stop = System.nanoTime(); // ����
				durationForAdd += (stop - start); // ������� - ���۽��� �� ��� �����ش�.

				start = System.nanoTime(); // ����
				maxCoin = list.max(); // list���� maxCoin�� ã��
				stop = System.nanoTime(); // ����
				durationForMax += (stop - start); // ������� - ���۽����� ��� �����ش�.
			}
			this.measuredResults()[iteration] = new MeasuredResult(dataSize, durationForAdd, durationForMax);
			dataSize += this.sizeIncrement();
		}
	}

	public void measureForUnsortedLinkedList() {
		@SuppressWarnings("unused")
		Coin maxCoin;

		long durationForAdd, durationForMax;
		long start, stop;

		int dataSize = this.firstSize();
		for (int iteration = 0; iteration < this.numberOfIteration(); iteration++) {
			UnsortedLinkedList<Coin> list = new UnsortedLinkedList<Coin>(dataSize);
			durationForAdd = 0;
			durationForMax = 0;
			for (int i = 0; i < dataSize; i++) {
				start = System.nanoTime();
				list.add(this.data()[i]);
				stop = System.nanoTime();
				durationForAdd += (stop - start);

				start = System.nanoTime();
				maxCoin = list.max();
				stop = System.nanoTime();
				durationForMax += (stop - start);
			}
			this.measuredResults()[iteration] = new MeasuredResult(dataSize, durationForAdd, durationForMax);
			dataSize += this.sizeIncrement();
		}
	}
}
