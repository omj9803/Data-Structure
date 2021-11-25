public class DictionaryByBinarySearchTree<Key extends Comparable<Key>, Obj extends Comparable<Obj>>
		extends Dictionary<Key, Obj> {
	// Private instance variables
	private BinaryNode<DictionaryElement<Key, Obj>> _root;

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
	private DictionaryElement<Key, Obj> elementForKey(Key aKey) { // aKey�� �ش��ϴ� element ��ȯ
		if (aKey != null) { // aKey�� null�� �ƴ϶��
			BinaryNode<DictionaryElement<Key, Obj>> current = this.root(); // root�� current�� ����
			while (current != null) // current(root)�� null�� �ƴѵ��� �ݺ�
				if (current.element().key().compareTo(aKey) == 0) { // element�� key�� aKey�� ������
					return current.element(); // element ��ȯ
				} else if (current.element().key().compareTo(aKey) > 0) { // aKey�� �� ������
					current = current.left(); // left-subtree�� �̵�
				} else { // aKey�� �� ũ��
					current = current.right(); // right-subtree�� �̵�
				}
		}
		return null; // aKey�� null�̰ų� element�� ������ null ��ȯ
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
	public Obj objectForKey(Key aKey) { // aKey�� �ش��ϴ� object�� ��ȯ
		DictionaryElement<Key, Obj> element = this.elementForKey(aKey); // aKey�� �ش��ϴ� element
		if (element != null) { // element�� null�� �ƴϸ�
			return element.object(); // element�� object�� ��ȯ
		} else { // element�� null�̸�
			return null; // null ��ȯ
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
	public Obj removeObjectForKey(Key aKey) { // aKey�� �ش��ϴ� object�� ����
		if (aKey == null) { // aKey�� null�̸� null��ȯ
			return null;
		}
		if (this.root() == null) { // root�� null�̸� null ��ȯ
			return null;
		}
		if (aKey.compareTo(this.root().element().key()) == 0) { // this.root() is the node to be removed.
			Obj objectForRemove = this.root().element().object(); // root()�� object()�� ����
			if ((this.root().left() == null) && (this.root().right() == null)) { // root()�� �ڽ��� ���ٸ�
				this.setRoot(null); // root����
			} else if (this.root().left() == null) { // root()�� right()�� �����ϸ�
				this.setRoot(this.root().right()); // right()�� root�� ����
			} else if (this.root().right() == null) { // root()�� left()�� �����ϸ�
				this.setRoot(this.root().left()); // left()�� root�� ����
			} else { // root()�� left(), right() ��� ����
				this.root().setElement(this.removeRightMostElementOfLeftSubTree(this.root())); // left()���� ���� ū element�� ����
			}
			this.setSize(this.size() - 1); // size--
			return objectForRemove; // ������ object ��ȯ
		} // root()�� ������ ���Ұ� �ƴ϶��
		BinaryNode<DictionaryElement<Key, Obj>> current = this.root(); // root()�� current�� ����
		BinaryNode<DictionaryElement<Key, Obj>> child = null;
		do {
			if (aKey.compareTo(current.element().key()) < 0) { // aKey < current.element().key()
				child = current.left(); // child�� left()�� ����
				if (child == null) { // child�� null�̸� aKey�� ������������
					return null; // "aKey" does not
				}
				if (aKey.compareTo(child.element().key()) == 0) { // Found. "child" is to be removed
					Obj objectForRemove = child.element().object(); // child�� object ����
					if (child.left() == null && child.right() == null) { // child�� �ڽ��� ���ٸ�
						current.setLeft(null); // "child" is a leaf.
					} else if (child.left() == null) { // "child" has no left.
						current.setLeft(child.right());
					} else if (child.right() == null) { // "child" has no right
						current.setLeft(child.left());
					} else { // child�� left(), right() ��� ����
						child.setElement(this.removeRightMostElementOfLeftSubTree(child)); // left()���� ���� ū element�� ����
					}
					this.setSize(this.size() - 1); // size--
					return objectForRemove; // ������ object ��ȯ
				}
			} else { // aKey > current.element().key()
				child = current.right(); // child�� right()�� ����
				if (child == null) { // child�� null�̸� aKey�� ������������
					return null; // "aKey" does not
				}
				if (aKey.compareTo(child.element().key()) == 0) { // Found. "child" is to be removed.
					Obj objectForRemove = child.element().object(); // child�� object ����
					if (child.left() == null && child.right() == null) { // child�� �ڽ��� ���ٸ�
						current.setRight(null); // "child" is a leaf
					} else if (child.left() == null) { // "child" has no left
						current.setRight(child.right());
					} else if (child.right() == null) { // "child" has no right
						current.setRight(child.left());
					} else {
						child.setElement(this.removeRightMostElementOfLeftSubTree(child)); // left()���� ���� ū element�� ����
					}
					this.setSize(this.size() - 1);
					return objectForRemove;
				}
			}
			current = child; // ã�������� ������

		} while (true); // return �ɶ����� �ݺ�
	}

	private DictionaryElement<Key, Obj> removeRightMostElementOfLeftSubTree(
			BinaryNode<DictionaryElement<Key, Obj>> root) { // ������ element���� �ڽ��� ��� ������ �� left()���� ���� ū element�� ��ü�ϴ� �Լ�
		// At this point, "root" has non-empty left subtree, "root" is to be removed
		BinaryNode<DictionaryElement<Key, Obj>> leftOfRoot = root.left(); // root�� left�� leftOfRoot�� ����
		if (leftOfRoot.right() == null) { // leftOfRoot���� right-subtree�� �����Ƿ� leftOfRoot�� rightmost�� ��
			root.setLeft(leftOfRoot.left()); // leftOfRoot�� �����Ͽ� ��ȯ
			return leftOfRoot.element();
		} else { // leftOfRoot���� right-subtree�� �ϳ��̻� ����
			BinaryNode<DictionaryElement<Key, Obj>> parentOfRightMost = leftOfRoot; // leftOfRoot�� parentOfRightMost�� ��
			BinaryNode<DictionaryElement<Key, Obj>> rightMost = parentOfRightMost.right(); // parentOfRightMost�� right()�� rightMost�� ����
			while (rightMost.right() != null) { // rightMost�� null�̶�� ����
				parentOfRightMost = rightMost; // ������
				rightMost = rightMost.right(); // ������
			}
			parentOfRightMost.setRight(rightMost.left()); // rightMost�� �����Ͽ� ��ȯ
			return rightMost.element();
		}
	}

	@Override
	public void clear() {
		this.setSize(0);
		this.setRoot(null);

	}

	@Override
	public Iterator<DictionaryElement<Key, Obj>> iterator() { // DictionaryElement�� ������ Iterator
		return (new DictionaryIterator());
	}

	// Iterator�� ������ ���� Ŭ����
	private class DictionaryIterator implements Iterator<DictionaryElement<Key, Obj>> {
		private BinaryNode<DictionaryElement<Key, Obj>> _nextNode;
		private Stack<BinaryNode<DictionaryElement<Key, Obj>>> _stack; 

		// Getter/Setter
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
			this.setStack(new LinkedStack<BinaryNode<DictionaryElement<Key, Obj>>>());
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

	private void inorderRecursively(BinaryNode<DictionaryElement<Key, Obj>> aRootOfSubtree, int aLevel) { // ������ȸ�� recursive�ϰ� ����
		if (aRootOfSubtree != null) {
			this.inorderRecursively(aRootOfSubtree.left(), aLevel + 1); // left-subtree 
			DictionaryElement<Key, Obj> visitedElement = aRootOfSubtree.element(); // visit ������ element visitElement�� ����
			this.visitDelegate().visitForSortedOrder(visitedElement.key(), visitedElement.object(), aLevel); // visit ����
			this.inorderRecursively(aRootOfSubtree.right(), aLevel + 1); // right-subtree
		}
	}

	@Override
	public void scanInSortedOrder() {
		this.inorderRecursively(this.root(), 1);

	}

	private void reverseOfInorderRecursively(BinaryNode<DictionaryElement<Key, Obj>> aRootOfSubtree, int aLevel) { // ���� ��ȸ�� reverse�Ͽ� ����
		if (aRootOfSubtree != null) {
			this.reverseOfInorderRecursively(aRootOfSubtree.right(), aLevel + 1); // right-subtree 
			DictionaryElement<Key, Obj> visitedElement = aRootOfSubtree.element(); // visit ������ element visitElement�� ����
			this.visitDelegate().visitForReverseOfSortedOrder(visitedElement.key(), visitedElement.object(), aLevel); // visit ����
			this.reverseOfInorderRecursively(aRootOfSubtree.left(), aLevel + 1); // left-subtree
		}
	}

	@Override
	public void scanInReverseOfSortedOrder() {
		this.reverseOfInorderRecursively(this.root(), 1);

	}
}
