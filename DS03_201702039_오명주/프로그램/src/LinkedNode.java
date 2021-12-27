
public class LinkedNode<E> {

	// ����� �ν��Ͻ� ����
	private E _element; // ���� ��忡 �ִ� ����
	private LinkedNode<E> _next; // �������

	// ������
	// element�� next�� null�� �ʱ�ȭ�Ѵ�
	public LinkedNode() {
		this.setElement(null);
		this.setNext(null);
	}

	// element�� givenElement��, next�� null�� �ʱ�ȭ�Ѵ�
	public LinkedNode(E givenElement) {
		this.setElement(givenElement);
		this.setNext(null);
	}

	// element�� givenElement��, next�� givenNext�� �ʱ�ȭ�Ѵ�
	public LinkedNode(E givenElement, LinkedNode<E> givenNext) {
		this.setElement(givenElement);
		this.setNext(givenNext);
	}

	// LinkedNode ��ü�� �ִ� element�� ��´�
	public E element() {
		return this._element;
	}

	// LinkedNode ��ü�� ���� LinkedNode ��ü�� ��´�
	public LinkedNode<E> next() {
		return this._next;
	}

	// LinkedNode�� �ִ� element�� newElement�� �����Ѵ�
	public void setElement(E newElement) {
		this._element = newElement;
	}

	// LinkedNode ��ü�� next�� newNext�� �����Ѵ�
	public void setNext(LinkedNode<E> newNext) {
		this._next = newNext;
	}

}
