import java.util.Random;

public class Experiment {
	private static final int DEFAULT_NUMBER_OF_ITRATION = 5;
	private static final int DEFAULT_FIRST_SIZE = 10000; // 첫 번째 실험 데이터 크기
	private static final int DEFAULT_SIZE_INCREMENT = 10000; // 실험 데이터 크기 증가량

	private int _numberOfIteration; // 반복횟수
	private int _firstSize; // 처음크기
	private int _sizeIncrement; // 증가량
	private Coin[] _data; // 데이터 배열원소
	private MeasuredResult[] _measuredResults; // 실험결과 배열원소 -> 측정결과저장

	// getter / setter 선언
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

	// 실험 데이터의 최대 크기를 계산하여 돌려준다
	public int maxSize() { // maxSize의 정의
		return this.firstSize() + this.sizeIncrement() * (this.numberOfIteration() - 1);
	}

	// 생성자
	public Experiment() {
		this.setNumberOfIteration(DEFAULT_NUMBER_OF_ITRATION); // 반복 5
		this.setFirstSize(DEFAULT_FIRST_SIZE); // 처음크기 10000
		this.setSizeIncrement(DEFAULT_SIZE_INCREMENT); // 증가량 10000
		this.setData(new Coin[this.maxSize()]); // Setter of Data
		this.setMeasuredResults(new MeasuredResult[this.numberOfIteration()]); // Setter of MeasuredResults

	}

	public Experiment(int givenNumberOfIteration, int givenFirstSize, int givenSizeIncrement) {
		this.setNumberOfIteration(givenNumberOfIteration); // 반복자 givenNumberOfIteration
		this.setFirstSize(givenFirstSize); // 처음크기 givenFirstSize
		this.setSizeIncrement(givenSizeIncrement); // 증가량 givenSizeIncrement
		this.setData(new Coin[this.maxSize()]); // Setter of Data
		this.setMeasuredResults(new MeasuredResult[this.numberOfIteration()]); // Setter of MeasuredResults

	}

	// 성능 측정에 필요한 데이터를 생성한다, 난수사용
	public void generateData() {
		Random random = new Random(); // random 선언
		for (int i = 0; i < this.maxSize(); i++) { // this.maxSize()만큼 반복
			int randomCoinValue = random.nextInt(this.maxSize()); // 0~maxSize()의 난수를 생성 후 randomCoinValue에 대입
			this.data()[i] = new Coin(randomCoinValue); // data의 i번째 배열은 randomCoinValue를 갖는 코인
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

	// Sorted Array로 구현한 List의 성능을 측정한다
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
			// 생성되어 저장된 난수들을 하나씩 꺼내와 배열에 추가하는 반복문
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
			// 측정한 결과 각각 저장
			this.measuredResults()[iteration] = new MeasuredResult(dataSize, durationForAdd, durationForMax);
			dataSize += this.sizeIncrement();
		}
	}

	public void measureForSortedLinkedList() {
		@SuppressWarnings("unused")
		Coin maxCoin; // 최대값 Coin 선언

		long durationForAdd, durationForMax; // 합과 최대값 - 결과 값
		long start, stop; // 시작과 종료 - 변수 측정

		int dataSize = this.firstSize(); // dataSize = firstSize = 10000
		for (int iteration = 0; iteration < this.numberOfIteration(); iteration++) { // 0~4까지 반복
			SortedLinkedList<Coin> list = new SortedLinkedList<Coin>(dataSize); // dataSize를 갖는 list생성
			durationForAdd = 0; // 초기값 0
			durationForMax = 0; // 초기값 0
			for (int i = 0; i < dataSize; i++) { // dataSize만큼 반복
				start = System.nanoTime(); // 시작
				list.add(this.data()[i]); // i번째 배열을 list에 add
				stop = System.nanoTime(); // 종료
				durationForAdd += (stop - start); // 종료시점 - 시작시점 을 계속 더해준다.

				start = System.nanoTime(); // 시작
				maxCoin = list.max(); // list에서 maxCoin을 찾기
				stop = System.nanoTime(); // 종료
				durationForMax += (stop - start); // 종료시점 - 시작시점을 계속 더해준다.
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
