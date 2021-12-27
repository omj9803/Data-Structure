
public class LinkedNode<E> {

	// 비공개 인스턴스 변수
	private E _element; // 현재 노드에 있는 코인
	private LinkedNode<E> _next; // 다음노드

	// 생성자
	// element와 next를 null로 초기화한다
	public LinkedNode() {
		this.setElement(null);
		this.setNext(null);
	}

	// element를 givenElement로, next를 null로 초기화한다
	public LinkedNode(E givenElement) {
		this.setElement(givenElement);
		this.setNext(null);
	}

	// element를 givenElement로, next를 givenNext로 초기화한다
	public LinkedNode(E givenElement, LinkedNode<E> givenNext) {
		this.setElement(givenElement);
		this.setNext(givenNext);
	}

	// LinkedNode 객체에 있는 element를 얻는다
	public E element() {
		return this._element;
	}

	// LinkedNode 객체의 다음 LinkedNode 객체를 얻는다
	public LinkedNode<E> next() {
		return this._next;
	}

	// LinkedNode에 있는 element를 newElement로 변경한다
	public void setElement(E newElement) {
		this._element = newElement;
	}

	// LinkedNode 객체의 next를 newNext로 변경한다
	public void setNext(LinkedNode<E> newNext) {
		this._next = newNext;
	}

}
