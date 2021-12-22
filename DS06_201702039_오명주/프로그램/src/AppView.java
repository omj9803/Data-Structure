
public class AppView {

	// 출력 관련 함수
	// 한줄을 출력하는 함수 (한줄이 띄워진다)
	public static void outputLine(String aMessage) {
		System.out.println(aMessage);
	}

	// 한줄을 출력하는 함수 (한줄이 띄워지지않는다)
	public void output(String aMessage) {
		System.out.print(aMessage);
	}

	// 포맷에 맞게 출력하는 함수
	public static void outputResults(int size, long durationForAdd, long durationForMax) {
		System.out.println("[크기 : " + String.format("%5d", size) + "] " + "삽입 : " + String.format("%8d", durationForAdd)
				+ " , 최대값 : " + String.format("%8d ", durationForMax));
	}
}
