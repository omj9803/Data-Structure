
public class AppController {

	// ����� �ν��Ͻ� ����
	private Experiment _experiment;

	// Getters/Setters
	private Experiment experiment() {
		return this._experiment;
	}

	private void setExperiment(Experiment newExperiment) {
		this._experiment = newExperiment;
	}

	// ������
	public AppController() {
		this.setExperiment(new Experiment()); // Experiment ��ü�� ����
		this.experiment().generateData(); // experiment�� generateData ����, ���谴ü���� ���� ������ ����� �����͸� �����ϰ� ��
	}

	// ����� �Լ�
	// ����� ����ϴ� �Լ�
	private void showExperimentResults() {
		MeasuredResult[] results = this.experiment().measuredResults();
		// results �迭�� experiment().measuredResults()�� ����
		for (int i = 0; i < this.experiment().numberOfIteration(); i++) {
			AppView.outputResults(results[i].size(), results[i].durationForAdd() / 1000, // Nano �� Micro�� ��ȯ
					results[i].durationForMax() / 1000); // Nano �� Micro�� ��ȯ
		}
	}

	// ���� �Լ�
	public void run() {
		AppView.outputLine("<<<����Ʈ�� ���� ���� ���α׷��� �����մϴ� .>>>");
		AppView.outputLine("! ����Ʈ�� ������ ���� ������ ���̸� �˾ƺ��ϴ� : (���� : Micro Second)");
		AppView.outputLine("");
		AppView.outputLine("<Sorted Array List>");
		this.experiment().measureForSortedArrayList(); // ���� ��ü���� SortedArrayList�� ���� ���� ������ �����ϰ� �Ѵ�
		this.showExperimentResults(); // ���� ��� ���
		AppView.outputLine("");
		AppView.outputLine("<Unsorted Array List>");
		this.experiment().measureForUnsortedArrayList(); // ���� ��ü���� UnSortedArrayList�� ���� ���� ������ �����ϰ� �Ѵ�
		this.showExperimentResults(); // ���� ��� ���
		AppView.outputLine("");
		AppView.outputLine("<Sorted Linked List>");
		this.experiment().measureForSortedLinkedList(); // ���� ��ü���� SortedLinkedList�� ���� ���� ������ �����ϰ� �Ѵ�
		this.showExperimentResults(); // ���� ��� ���
		AppView.outputLine("");
		AppView.outputLine("<Unsorted Linked List>");
		this.experiment().measureForUnsortedLinkedList(); // ���� ��ü���� UnsortedLinkedList�� ���� ���� ������ �����ϰ� �Ѵ�
		this.showExperimentResults(); // ���� ��� ���
		AppView.outputLine("");
		AppView.outputLine("<<< ����Ʈ�� ���� ���� ���α׷��� �����մϴ� >>>"); // ����
	}
}
