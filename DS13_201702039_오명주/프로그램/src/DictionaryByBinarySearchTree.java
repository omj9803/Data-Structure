public class DictionaryByBinarySearchTree<Key extends Comparable<Key>, Obj extends Comparable<Obj>>
		extends Dictionary<Key, Obj> {
	// Private instance variables
	private BinaryNode<DictionaryElement<Key, Obj>> _root;
	// private int _size ;
	// �ʿ� ���� ������ ? Getter/Setter ��?

	// Getter/Setter
	protected BinaryNode<DictionaryElement<Key, Obj>> root() {
		return this._root;
	}

	private void setRoot(BinaryNode<DictionaryElement<Key, Obj>> newRoot) {
		this._root = newRoot;
	}

	// Constructor
	public DictionaryByBinarySearchTree() {
		this.clear();
	}

	// Private methods
	private DictionaryElement<Key, Obj> elementForKey(Key aKey) {
		if (aKey != null) {
			BinaryNode<DictionaryElement<Key, Obj>> current = this.root();
			while (current != null)
				if (current.element().key().compareTo(aKey) == 0) {
					return current.element();
				} else if (current.element().key().compareTo(aKey) > 0) {
					current = current.left();
				} else {
					current = current.right();
				}
		}
		return null;
	}

	@Override
	public boolean isFull() {
		return false; // Always false
	}

	@Override
	public boolean keyDoesExist(Key aKey) {
		return (this.elementForKey(aKey) != null);
	}

	@Override
	public Obj objectForKey(Key aKey) {
		DictionaryElement<Key, Obj> element = this.elementForKey(aKey);
		if (element != null) {
			return element.object();
		} else {
			return null;
		}
	}

	@Override
	public boolean addKeyAndObject(Key aKey, Obj anObject) { // Key�� Object�� ������ ����
		if (aKey == null) {
			return false; // In any case, "aKey" cannot be null for add
		}
		DictionaryElement<Key, Obj> elementForAdd = new DictionaryElement<Key, Obj>(aKey, anObject); // ������ ���� ��ü ����
		BinaryNode<DictionaryElement<Key, Obj>> nodeForAdd = new BinaryNode<DictionaryElement<Key, Obj>>(elementForAdd,
				null, null);
		if (this.root() == null) { // ���� root�� null�̸�
			this.setRoot(nodeForAdd); // �ش� ���Ҹ� root�� ����
			this.setSize(1); // size 1�� ����
			return true;
		}
		BinaryNode<DictionaryElement<Key, Obj>> current = this.root(); // root�� null�� �ƴϸ� current ���� ����
		while (aKey.compareTo(current.element().key()) != 0) { // current ������ key�� aKey�� ���������� while�� ����
			if (aKey.compareTo(current.element().key()) < 0) { // aKey�� root���� �۴ٸ� (left��)
				if (current.left() == null) { // left�� null�̸�
					current.setLeft(nodeForAdd); // left�� ����
					this.setSize(this.size() + 1); // size ����
					return true;
				} else { // left�� null�� �ƴϸ� 
					current = current.left(); // left�� �̵�
				}
			} else { // aKey�� root���� ũ�ٸ� (right��)
				if (current.right() == null) { // right�� null�̸�
					current.setRight(nodeForAdd); // right�� ����
					this.setSize(this.size() + 1); // size ����
					return true;
				} else { // right�� null�� �ƴϸ�
					current = current.right(); // right�� �̵�
				}
			}
		} // End of while
		return false;
	}

	@Override
	public Obj removeObjectForKey(Key aKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		this.setSize(0);
		this.setRoot(null);

	}

	@Override
	public Iterator<DictionaryElement<Key, Obj>> iterator() {
		return (new DictionaryIterator());
	}

	private class DictionaryIterator implements Iterator<DictionaryElement<Key, Obj>> {
		private BinaryNode<DictionaryElement<Key, Obj>> _nextNode;
		private Stack<BinaryNode<DictionaryElement<Key, Obj>>> _stack;

		private BinaryNode<DictionaryElement<Key, Obj>> nextNode() {
			return this._nextNode;
		}

		private void setNextNode(BinaryNode<DictionaryElement<Key, Obj>> newNextNode) {
			this._nextNode = newNextNode;
		}

		private Stack<BinaryNode<DictionaryElement<Key, Obj>>> stack() {
			return this._stack;
		}

		private void setStack(Stack<BinaryNode<DictionaryElement<Key, Obj>>> newStack) {
			this._stack = newStack;
		}

		// Constructor
		private DictionaryIterator() {
			this.setStack(new ArrayList<BinaryNode<DictionaryElement<Key, Obj>>>());
			this.setNextNode(DictionaryByBinarySearchTree.this.root());
		}

		@Override
		public boolean hasNext() { // ������尡 �ִ��� Ȯ���ϴ� �Լ�
			return ((this.nextNode() != null) || (!this.stack().isEmpty())); // nextNode�� null�� �ƴϸ� true
		}

		@Override
		public DictionaryElement<Key, Obj> next() { // ����Ž��
			if (!this.hasNext()) { // hasNext�� false���
				return null;
			} else {
				while (this.nextNode() != null) { // nextNode�� null�� �ƴѵ��� �ݺ�
					this.stack().push(this.nextNode()); // stack�� nextNode�� push
					this.setNextNode(this.nextNode().left()); // nextNode�� left�� nextNode�� ����
				}
				BinaryNode<DictionaryElement<Key, Obj>> poppedNode = this.stack().pop(); // stack�� pop�Ͽ� ����
				DictionaryElement<Key, Obj> nextElement = poppedNode.element(); // pop�� ���Ҹ� element�� ����
				this.setNextNode(poppedNode.right()); // poppedNode�� right�� nextNode�� ����
				return nextElement;
			}
		}
	}
}
