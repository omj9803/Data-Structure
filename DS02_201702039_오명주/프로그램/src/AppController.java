
public class AppController {
	// 상수
	private static final int MENU_ADD = 1;
	private static final int MENU_REMOVE = 2;
	private static final int MENU_SEARCH = 3;
	private static final int MENU_FREQUENCY = 4;
	private static final int MENU_END_OF_RUN = 9;
	// static은 클래스 하나에 선언하지만 모든 클래스가 공유
	// final은 수정할 수 없는 상수를 의미

	// 비공개 인스턴스 변수들
	private ArrayBag<Coin> _coinBag;

	// getter/setter
	private ArrayBag<Coin> coinBag() {
		return this._coinBag;
	}

	private void setCoinBag(ArrayBag<Coin> newCoinBag) {
		this._coinBag = newCoinBag;
	}

	// 공개함수
	public void run() {
		// 프로그램을 시작하는 출력문
		AppView.outputLine("<<< 동전 가방 프로그램을 시작합니다 >>>");
		
		int coinBagSize = AppView.inputCapacityOfCoinBag(); // 가방의 최대 개수를 입력받는 입력문 호출
		this.setCoinBag(new ArrayBag<Coin>(coinBagSize)); // 입력받은 크기를 가진 객체 생성


		int menuNumber = AppView.inputMenuNumber(); // 수행할 메뉴 번호를 입력받는 입력문 호출
		while (menuNumber != MENU_END_OF_RUN) {
			switch (menuNumber) {
			case MENU_ADD: // 1을 입력받으면
				this.addCoin(); // addCoin함수 호출
				break;
			case MENU_REMOVE: // 2를 입력받으면
				this.removeCoin(); // removeCoin함수 호출
				break;
			case MENU_SEARCH: // 3을 입력받으면
				this.searchForCoin(); // searchForCoin함수 호출
				break;
			case MENU_FREQUENCY: // 4를 입력받으면
				this.frequencyOfCoin(); // freQuencyOfCoin함수 호출
				break;

			default:
				this.undefinedMenuNumber(menuNumber); // 잘못된 번호를 입력받은 경우
			}

			// 프로그램을 반복하여 수행
			menuNumber = AppView.inputMenuNumber();
		}
		this.showStatistics(); // 프로그램이 끝나면 통계 출력
		AppView.outputLine("<<< 동전 가방 프로그램을 종료합니다 >>>");
	}

	// 비공개함수
	// 정수를 입력받아 Bag에 넣는 함수
	private void addCoin() {
		if (this.coinBag().isFull()) { // 만약 Bag이 가득찼다면
			AppView.outputLine("- 동전 가방이 꽉 차서 동전을 넣을 수 없습니다 ."); // 동전을 넣지못한다는 출력문 출력
		} else { // Bag이 비었다면
			AppView.output("? 동전 값을 입력하시오: ");
			int coinValue = AppView.inputCoinValue(); // 동전 값을 입력받는다
			if (this.coinBag().add(new Coin(coinValue))) {
				AppView.outputLine("- 주어진 값을 갖는 동전을 가방에 성공적으로 넣었습니다 .");
			} else {
				AppView.outputLine("- 주어진 값을 갖는 동전을 가방에 넣는데 실패하였습니다 .");
			}
		}
	}

	// 정수를 입력받아 해당 동전이 있으면 Bag에서 삭제하는 함수
	private void removeCoin() {
		AppView.output("? 동전 값을 입력하시오: ");
		int coinValue = AppView.inputCoinValue(); // 동전 값을 입력받는다
		if (!this.coinBag().remove(new Coin(coinValue))) { // 정상적으로 삭제되는지 확인
			// 정상적으로 삭제되지 않는다면
			// 주어진 값의 동전이 Bag에 없으므로 존재하지 않는다는 출력문 출력
			AppView.outputLine("- 주어진 값을 갖는 동전은 가방 안에 존재하지 않습니다 .");
		} else {
			// 정상적으로 삭제된 경우
			AppView.outputLine("- 주어진 값을 갖는 동전 하나가 가방에서 정상적으로 삭제되었습니다 .");
		}
	}

	// 정수를 입력받아 해당 숫자의 동전이 Bag에 있는지 확인하는 함수
	private void searchForCoin() {
		AppView.output("? 동전 값을 입력하시오: ");
		int coinValue = AppView.inputCoinValue(); // 동전 값을 입력받는다
		if (this.coinBag().doesContain(new Coin(coinValue))) { // 해당 동전이 Bag에 있는지 확인
			AppView.outputLine("- 주어진 값을 갖는 동전이 가방 안에 존재합니다 .");
		} else {
			AppView.outputLine("- 주어진 값을 갖는 동전은 가방 안에 존재하지 않습니다 .");
		}
	}

	// 정수를 입력받아 해당 숫자의 동전이 Bag에 몇개 있는지 확인하는 함수
	private void frequencyOfCoin() {
		AppView.output("? 동전 값을 입력하시오: ");
		int coinValue = AppView.inputCoinValue(); // 동전 값을 입력받는다
		int frequency = this.coinBag().frequencyOf(new Coin(coinValue)); // 해당 숫자의 동전이 존재하는 개수의 정수를 입력받는다
		AppView.outputLine("- 주어진 값을 갖는 동전의 개수는 " + frequency + " 개 입니다 .");
	}

	// 메뉴에 없는 정수를 입력했을 경우 호출하는 함수
	private void undefinedMenuNumber(int menuNumber) {
		AppView.outputLine("- 선택된 메뉴 번호 " + menuNumber + " 는 잘못된 번호입니다 .");
	}

	// Bag에 있는 동전의 값의 합을 return하는 함수
	private int sumOfCoinValues() {
		int sum = 0;
		for (int i = 0; i < this.coinBag().size(); i++) { // Bag의 크기만큼 반복하여
			sum += this.coinBag().elementAt(i).value(); // 동전의 값을 순서대로 반환하여 sum에 합하여 저장
		}
		return sum;
	}

	// Bag에 있는 동전 중 가장 큰 값을 return하는 함수
	private int maxCoinValue() {
		int maxValue = 0;
		for (int i = 0; i < this.coinBag().size(); i++) { // Bag의 크기만큼 반복하여
			if (maxValue < this.coinBag().elementAt(i).value()) { // maxValue의 값을 동전의 값과 비교
				maxValue = this.coinBag().elementAt(i).value(); // maxValue보다 큰 값이면 다시 maxValue에 저장
			}
		}
		return maxValue;
	}

	// 동전의 개수, 가장큰 값, 동전들의 합을 출력하는 함수
	private void showStatistics() {
		AppView.outputLine(" 가방에 들어 있는 동전의 개수 : " + this.coinBag().size()); // Bag의 크기 출력
		AppView.outputLine("동전 중 가장 큰 값 : " + this.maxCoinValue()); // Bag 속의 가장 큰 동전 값 출력
		AppView.outputLine("모든 동전 값의 합 : " + this.sumOfCoinValues()); // Bag 속의 동전들의 합 출력
	}

}
