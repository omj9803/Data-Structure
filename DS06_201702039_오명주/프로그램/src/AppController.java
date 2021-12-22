
public class AppController {

	// 비공개 인스턴스 변수
	private Experiment _experiment;

	// Getters/Setters
	private Experiment experiment() {
		return this._experiment;
	}

	private void setExperiment(Experiment newExperiment) {
		this._experiment = newExperiment;
	}

	// 생성자
	public AppController() {
		this.setExperiment(new Experiment()); // Experiment 객체를 생성
		this.experiment().generateData(); // experiment의 generateData 실행, 실험객체에게 성능 측정에 사용할 데이터를 생성하게 함
	}

	// 비공개 함수
	// 결과를 출력하는 함수
	private void showExperimentResults() {
		MeasuredResult[] results = this.experiment().measuredResults();
		// results 배열을 experiment().measuredResults()로 설정
		for (int i = 0; i < this.experiment().numberOfIteration(); i++) {
			AppView.outputResults(results[i].size(), results[i].durationForAdd() / 1000, // Nano 를 Micro로 변환
					results[i].durationForMax() / 1000); // Nano 를 Micro로 변환
		}
	}

	// 공개 함수
	public void run() {
		AppView.outputLine("<<<리스트의 성능 측정 프로그램을 시작합니다 .>>>");
		AppView.outputLine("! 리스트의 구현에 따른 성능의 차이를 알아봅니다 : (단위 : Micro Second)");
		AppView.outputLine("");
		AppView.outputLine("<Sorted Array List>");
		this.experiment().measureForSortedArrayList(); // 실험 객체에게 SortedArrayList에 대한 성능 측정을 실행하게 한다
		this.showExperimentResults(); // 실험 결과 출력
		AppView.outputLine("");
		AppView.outputLine("<Unsorted Array List>");
		this.experiment().measureForUnsortedArrayList(); // 실험 객체에게 UnSortedArrayList에 대한 성능 측정을 실행하게 한다
		this.showExperimentResults(); // 실험 결과 출력
		AppView.outputLine("");
		AppView.outputLine("<Sorted Linked List>");
		this.experiment().measureForSortedLinkedList(); // 실험 객체에게 SortedLinkedList에 대한 성능 측정을 실행하게 한다
		this.showExperimentResults(); // 실험 결과 출력
		AppView.outputLine("");
		AppView.outputLine("<Unsorted Linked List>");
		this.experiment().measureForUnsortedLinkedList(); // 실험 객체에게 UnsortedLinkedList에 대한 성능 측정을 실행하게 한다
		this.showExperimentResults(); // 실험 결과 출력
		AppView.outputLine("");
		AppView.outputLine("<<< 리스트의 성능 측정 프로그램을 종료합니다 >>>"); // 종료
	}
}
