
public class AppController {
	// ���
	private static final int MENU_ADD = 1;
	private static final int MENU_REMOVE = 2;
	private static final int MENU_SEARCH = 3;
	private static final int MENU_FREQUENCY = 4;
	private static final int MENU_END_OF_RUN = 9;
	// static�� Ŭ���� �ϳ��� ���������� ��� Ŭ������ ����
	// final�� ������ �� ���� ����� �ǹ�

	// ����� �ν��Ͻ� ������
	private ArrayBag<Coin> _coinBag;

	// getter/setter
	private ArrayBag<Coin> coinBag() {
		return this._coinBag;
	}

	private void setCoinBag(ArrayBag<Coin> newCoinBag) {
		this._coinBag = newCoinBag;
	}

	// �����Լ�
	public void run() {
		// ���α׷��� �����ϴ� ��¹�
		AppView.outputLine("<<< ���� ���� ���α׷��� �����մϴ� >>>");
		
		int coinBagSize = AppView.inputCapacityOfCoinBag(); // ������ �ִ� ������ �Է¹޴� �Է¹� ȣ��
		this.setCoinBag(new ArrayBag<Coin>(coinBagSize)); // �Է¹��� ũ�⸦ ���� ��ü ����


		int menuNumber = AppView.inputMenuNumber(); // ������ �޴� ��ȣ�� �Է¹޴� �Է¹� ȣ��
		while (menuNumber != MENU_END_OF_RUN) {
			switch (menuNumber) {
			case MENU_ADD: // 1�� �Է¹�����
				this.addCoin(); // addCoin�Լ� ȣ��
				break;
			case MENU_REMOVE: // 2�� �Է¹�����
				this.removeCoin(); // removeCoin�Լ� ȣ��
				break;
			case MENU_SEARCH: // 3�� �Է¹�����
				this.searchForCoin(); // searchForCoin�Լ� ȣ��
				break;
			case MENU_FREQUENCY: // 4�� �Է¹�����
				this.frequencyOfCoin(); // freQuencyOfCoin�Լ� ȣ��
				break;

			default:
				this.undefinedMenuNumber(menuNumber); // �߸��� ��ȣ�� �Է¹��� ���
			}

			// ���α׷��� �ݺ��Ͽ� ����
			menuNumber = AppView.inputMenuNumber();
		}
		this.showStatistics(); // ���α׷��� ������ ��� ���
		AppView.outputLine("<<< ���� ���� ���α׷��� �����մϴ� >>>");
	}

	// ������Լ�
	// ������ �Է¹޾� Bag�� �ִ� �Լ�
	private void addCoin() {
		if (this.coinBag().isFull()) { // ���� Bag�� ����á�ٸ�
			AppView.outputLine("- ���� ������ �� ���� ������ ���� �� �����ϴ� ."); // ������ �������Ѵٴ� ��¹� ���
		} else { // Bag�� ����ٸ�
			AppView.output("? ���� ���� �Է��Ͻÿ�: ");
			int coinValue = AppView.inputCoinValue(); // ���� ���� �Է¹޴´�
			if (this.coinBag().add(new Coin(coinValue))) {
				AppView.outputLine("- �־��� ���� ���� ������ ���濡 ���������� �־����ϴ� .");
			} else {
				AppView.outputLine("- �־��� ���� ���� ������ ���濡 �ִµ� �����Ͽ����ϴ� .");
			}
		}
	}

	// ������ �Է¹޾� �ش� ������ ������ Bag���� �����ϴ� �Լ�
	private void removeCoin() {
		AppView.output("? ���� ���� �Է��Ͻÿ�: ");
		int coinValue = AppView.inputCoinValue(); // ���� ���� �Է¹޴´�
		if (!this.coinBag().remove(new Coin(coinValue))) { // ���������� �����Ǵ��� Ȯ��
			// ���������� �������� �ʴ´ٸ�
			// �־��� ���� ������ Bag�� �����Ƿ� �������� �ʴ´ٴ� ��¹� ���
			AppView.outputLine("- �־��� ���� ���� ������ ���� �ȿ� �������� �ʽ��ϴ� .");
		} else {
			// ���������� ������ ���
			AppView.outputLine("- �־��� ���� ���� ���� �ϳ��� ���濡�� ���������� �����Ǿ����ϴ� .");
		}
	}

	// ������ �Է¹޾� �ش� ������ ������ Bag�� �ִ��� Ȯ���ϴ� �Լ�
	private void searchForCoin() {
		AppView.output("? ���� ���� �Է��Ͻÿ�: ");
		int coinValue = AppView.inputCoinValue(); // ���� ���� �Է¹޴´�
		if (this.coinBag().doesContain(new Coin(coinValue))) { // �ش� ������ Bag�� �ִ��� Ȯ��
			AppView.outputLine("- �־��� ���� ���� ������ ���� �ȿ� �����մϴ� .");
		} else {
			AppView.outputLine("- �־��� ���� ���� ������ ���� �ȿ� �������� �ʽ��ϴ� .");
		}
	}

	// ������ �Է¹޾� �ش� ������ ������ Bag�� � �ִ��� Ȯ���ϴ� �Լ�
	private void frequencyOfCoin() {
		AppView.output("? ���� ���� �Է��Ͻÿ�: ");
		int coinValue = AppView.inputCoinValue(); // ���� ���� �Է¹޴´�
		int frequency = this.coinBag().frequencyOf(new Coin(coinValue)); // �ش� ������ ������ �����ϴ� ������ ������ �Է¹޴´�
		AppView.outputLine("- �־��� ���� ���� ������ ������ " + frequency + " �� �Դϴ� .");
	}

	// �޴��� ���� ������ �Է����� ��� ȣ���ϴ� �Լ�
	private void undefinedMenuNumber(int menuNumber) {
		AppView.outputLine("- ���õ� �޴� ��ȣ " + menuNumber + " �� �߸��� ��ȣ�Դϴ� .");
	}

	// Bag�� �ִ� ������ ���� ���� return�ϴ� �Լ�
	private int sumOfCoinValues() {
		int sum = 0;
		for (int i = 0; i < this.coinBag().size(); i++) { // Bag�� ũ�⸸ŭ �ݺ��Ͽ�
			sum += this.coinBag().elementAt(i).value(); // ������ ���� ������� ��ȯ�Ͽ� sum�� ���Ͽ� ����
		}
		return sum;
	}

	// Bag�� �ִ� ���� �� ���� ū ���� return�ϴ� �Լ�
	private int maxCoinValue() {
		int maxValue = 0;
		for (int i = 0; i < this.coinBag().size(); i++) { // Bag�� ũ�⸸ŭ �ݺ��Ͽ�
			if (maxValue < this.coinBag().elementAt(i).value()) { // maxValue�� ���� ������ ���� ��
				maxValue = this.coinBag().elementAt(i).value(); // maxValue���� ū ���̸� �ٽ� maxValue�� ����
			}
		}
		return maxValue;
	}

	// ������ ����, ����ū ��, �������� ���� ����ϴ� �Լ�
	private void showStatistics() {
		AppView.outputLine(" ���濡 ��� �ִ� ������ ���� : " + this.coinBag().size()); // Bag�� ũ�� ���
		AppView.outputLine("���� �� ���� ū �� : " + this.maxCoinValue()); // Bag ���� ���� ū ���� �� ���
		AppView.outputLine("��� ���� ���� �� : " + this.sumOfCoinValues()); // Bag ���� �������� �� ���
	}

}
