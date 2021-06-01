public class DictionaryByBinarySearchTree<Key extends Comparable<Key>, Obj extends Comparable<Obj>>
		extends Dictionary<Key, Obj> {
	// Private instance variables
	private BinaryNode<DictionaryElement<Key, Obj>> _root;
	// private int _size ;
	// 필요 없는 이유는 ? Getter/Setter 는?

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
	public boolean addKeyAndObject(Key aKey, Obj anObject) { // Key와 Object를 쌍으로 삽입
		if (aKey == null) {
			return false; // In any case, "aKey" cannot be null for add
		}
		DictionaryElement<Key, Obj> elementForAdd = new DictionaryElement<Key, Obj>(aKey, anObject); // 삽입을 위한 객체 생성
		BinaryNode<DictionaryElement<Key, Obj>> nodeForAdd = new BinaryNode<DictionaryElement<Key, Obj>>(elementForAdd,
				null, null);
		if (this.root() == null) { // 만약 root가 null이면
			this.setRoot(nodeForAdd); // 해당 원소를 root로 설정
			this.setSize(1); // size 1로 설정
			return true;
		}
		BinaryNode<DictionaryElement<Key, Obj>> current = this.root(); // root가 null이 아니면 current 원소 지정
		while (aKey.compareTo(current.element().key()) != 0) { // current 원소의 key가 aKey가 같지않으면 while문 실행
			if (aKey.compareTo(current.element().key()) < 0) { // aKey가 root보다 작다면 (left로)
				if (current.left() == null) { // left가 null이면
					current.setLeft(nodeForAdd); // left로 설정
					this.setSize(this.size() + 1); // size 증가
					return true;
				} else { // left가 null이 아니면 
					current = current.left(); // left로 이동
				}
			} else { // aKey가 root보다 크다면 (right로)
				if (current.right() == null) { // right가 null이면
					current.setRight(nodeForAdd); // right로 설정
					this.setSize(this.size() + 1); // size 증가
					return true;
				} else { // right가 null이 아니면
					current = current.right(); // right로 이동
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
		public boolean hasNext() { // 다음노드가 있는지 확인하는 함수
			return ((this.nextNode() != null) || (!this.stack().isEmpty())); // nextNode가 null이 아니면 true
		}

		@Override
		public DictionaryElement<Key, Obj> next() { // 중위탐색
			if (!this.hasNext()) { // hasNext가 false라면
				return null;
			} else {
				while (this.nextNode() != null) { // nextNode가 null이 아닌동안 반복
					this.stack().push(this.nextNode()); // stack에 nextNode를 push
					this.setNextNode(this.nextNode().left()); // nextNode의 left를 nextNode로 설정
				}
				BinaryNode<DictionaryElement<Key, Obj>> poppedNode = this.stack().pop(); // stack을 pop하여 저장
				DictionaryElement<Key, Obj> nextElement = poppedNode.element(); // pop한 원소를 element에 저장
				this.setNextNode(poppedNode.right()); // poppedNode의 right를 nextNode로 설정
				return nextElement;
			}
		}
	}
}
