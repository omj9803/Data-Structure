
public class LinkedNode<T> {

	// ����� �ν��Ͻ� ����
	private T _element; // ���� �����ϴ� ����Ʈ ����
	private LinkedNode<T> _next; // ���� ��带 ��Ÿ��

	// ������
	// �־��� ���� ���� ��� �����Ǵ� ������
	public LinkedNode() {
		this.setElement(null);
		this.setNext(null);
	}

	// �־��� ������ �����ϴ� ������
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
