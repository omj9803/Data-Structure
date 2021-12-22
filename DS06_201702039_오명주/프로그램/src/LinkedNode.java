
public class LinkedNode<E> {
	// ����� �ν��Ͻ� ����
	private E _element; // ���� ��忡 �ִ� ����
	private LinkedNode<E> _next; // �������

	// Getter/Setter
	public E element() {
		return this._element;
	}

	public void setElement(E newElement) {
		this._element = newElement;
	}

	public LinkedNode<E> next() {
		return this._next;
	}

	public void setNext(LinkedNode<E> newNext) {
		this._next = newNext;
	}

	// ������
	public LinkedNode() { // ��� ���� ��
		this.setElement(null);
		this.setNext(null);
	}

	public LinkedNode(E givenElement) { // ���� �ϳ��϶� (head ����)
		this.setElement(givenElement);
		this.setNext(null);
	}

	public LinkedNode(E givenElement, LinkedNode<E> givenNext) {
		this.setElement(givenElement);
		this.setNext(givenNext);
	}

}
