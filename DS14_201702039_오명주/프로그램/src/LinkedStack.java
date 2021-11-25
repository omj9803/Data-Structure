
public class LinkedStack<E> implements Stack<E> {

	// Instance Variables
	private int _size;
	private LinkedNode<E> _top;

	// »ý¼ºÀÚ
	public LinkedStack() {
		this.setSize(0);
		this.setTop(null);
	}

	// Getters/Setters
	private void setSize(int newSize) {
		this._size = newSize;
	}

	private LinkedNode<E> top() {
		return this._top;
	}

	private void setTop(LinkedNode<E> newTop) {
		this._top = newTop;
	}

	@Override
	public int size() {
		return this._size;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return (this.size() == 0);
	}

	@Override
	public boolean push(E anElement) {
		if (this.isFull()) {
			return false;
		} else {
			LinkedNode<E> nodeForAdd = new LinkedNode<E>(anElement, null);
			nodeForAdd.setNext(this.top());
			this.setSize(this.size() + 1);
			this.setTop(nodeForAdd);
			return true;
		}
	}

	@Override
	public E pop() {
		if (this.isEmpty()) {
			return null;
		} else {
			LinkedNode<E> topNode = this.top();
			E poppedElement = topNode.element();
			this.setTop(topNode.next());
			this.setSize(this.size() - 1);
			return poppedElement;
		}
	}

	@Override
	public E peek() {
		if (this.isEmpty()) {
			return null;
		} else {
			LinkedNode<E> topNode = this.top();
			return topNode.element();
		}
	}

	@Override
	public void clear() {
		this.setSize(0);
		this.setTop(null);

	}

}
