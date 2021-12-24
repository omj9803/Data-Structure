
public class LinkedNode<T> {

	// 비공개 인스턴스 변수
	private T _element; // 값을 저장하는 리스트 원소
	private LinkedNode<T> _next; // 다음 노드를 나타냄

	// 생성자
	// 주어진 값이 없을 경우 생성되는 생성자
	public LinkedNode() {
		this.setElement(null);
		this.setNext(null);
	}

	// 주어진 값으로 생성하는 생성자
	public LinkedNode(T givenElement, LinkedNode<T> givenNext) {
		this.setElement(givenElement);
		this.setNext(givenNext);
	}

	// Getters
	public T element() {
		return this._element;
	}

	public LinkedNode<T> next() {
		return this._next;
	}

	// Setters
	public void setElement(T newElement) {
		this._element = newElement;
	}

	public void setNext(LinkedNode<T> newNext) {
		this._next = newNext;
	}

}
