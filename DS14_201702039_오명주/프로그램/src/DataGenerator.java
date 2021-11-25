import java.util.Random;

public final class DataGenerator {
	// Static Class.
	// 더이상 상속할 필요가 없으므로 "final" 선언

	// 생성자는 private. 외부에서 객체를 만들 수 없다.
	private DataGenerator() {
	};

	// 모든 공개함수는 static
	public static Integer[] ascendingOrderList(int aSize) { // 오름차순 리스트 생성
		Integer[] list = null; // 초기화
		if (aSize > 0) { // aSize가 0보다 크다면
			list = new Integer[aSize]; // 배열 생성
			for (int i = 0; i < aSize; i++) { // i에 맞게 리스트에 값 생성
				list[i] = i;
			}
		}
		return list;
	}

	public static Integer[] descendingOrderList(int aSize) { // 내림차순 리스트 생성
		Integer[] list = null; // 초기화
		int count = aSize - 1; // 값을 넣을 때 사용할 count 생성
		if (aSize > 0) { // aSize가 0보다 크다면
			list = new Integer[aSize]; // 배열생성
			for (int i = 0; i < aSize; i++) { // i에 맞게 리스트에 값 생성
				list[i] = count;
				count--;
			}
		}
		return list;
	}

	public static Integer[] randomOrderList(int aSize) { // 랜덤 값 리스트 생성
		// 겹치는 원소가 없는 무작위 리스트를 생성하여 , 돌려준다
		Integer[] list = null;
		if (aSize > 0) {
			// 일단 Ascending order list 를 만든다
			list = new Integer[aSize];
			for (int i = 0; i < aSize; i++) {
				list[i] = i;
			}
			// 각 원소 list[i] 에 대해 무작위 위치 r 을 생성하여 list[i] 와 list[r] 를 맞바꾼다
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
		// 겹치는 원소가 없는 무작위 리스트를 생성하여 , 돌려준다
		Integer[] list = null;
		if (aSize > 0) {
			// 일단 Ascending order list 를 만든다
			list = DataGenerator.ascendingOrderList(aSize);
			// 각 원소 list[i] 에 대해 무작위 위치 r 을 생성하여 list[i] 와 list[r] 를 맞바꾼다
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
