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
	private DictionaryElement<Key, Obj> elementForKey(Key aKey) { // aKey에 해당하는 element 반환
		if (aKey != null) { // aKey가 null이 아니라면
			BinaryNode<DictionaryElement<Key, Obj>> current = this.root(); // root를 current로 지정
			while (current != null) // current(root)가 null이 아닌동안 반복
				if (current.element().key().compareTo(aKey) == 0) { // element의 key와 aKey가 같으면
					return current.element(); // element 반환
				} else if (current.element().key().compareTo(aKey) > 0) { // aKey가 더 작으면
					current = current.left(); // left-subtree로 이동
				} else { // aKey가 더 크면
					current = current.right(); // right-subtree로 이동
				}
		}
		return null; // aKey가 null이거나 element가 없으면 null 반환
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
	public Obj objectForKey(Key aKey) { // aKey에 해당하는 object를 반환
		DictionaryElement<Key, Obj> element = this.elementForKey(aKey); // aKey에 해당하는 element
		if (element != null) { // element가 null이 아니면
			return element.object(); // element의 object를 반환
		} else { // element가 null이면
			return null; // null 반환
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
	public Obj removeObjectForKey(Key aKey) { // aKey에 해당하는 object를 삭제
		if (aKey == null) { // aKey가 null이면 null반환
			return null;
		}
		if (this.root() == null) { // root가 null이면 null 반환
			return null;
		}
		if (aKey.compareTo(this.root().element().key()) == 0) { // this.root() is the node to be removed.
			Obj objectForRemove = this.root().element().object(); // root()의 object()를 저장
			if ((this.root().left() == null) && (this.root().right() == null)) { // root()에 자식이 없다면
				this.setRoot(null); // root삭제
			} else if (this.root().left() == null) { // root()의 right()가 존재하면
				this.setRoot(this.root().right()); // right()를 root로 설정
			} else if (this.root().right() == null) { // root()의 left()가 존재하면
				this.setRoot(this.root().left()); // left()를 root로 설정
			} else { // root()의 left(), right() 모두 존재
				this.root().setElement(this.removeRightMostElementOfLeftSubTree(this.root())); // left()에서 가장 큰 element로 설정
			}
			this.setSize(this.size() - 1); // size--
			return objectForRemove; // 삭제한 object 반환
		} // root()가 삭제할 원소가 아니라면
		BinaryNode<DictionaryElement<Key, Obj>> current = this.root(); // root()를 current로 설정
		BinaryNode<DictionaryElement<Key, Obj>> child = null;
		do {
			if (aKey.compareTo(current.element().key()) < 0) { // aKey < current.element().key()
				child = current.left(); // child를 left()로 지정
				if (child == null) { // child가 null이면 aKey가 존재하지않음
					return null; // "aKey" does not
				}
				if (aKey.compareTo(child.element().key()) == 0) { // Found. "child" is to be removed
					Obj objectForRemove = child.element().object(); // child의 object 저장
					if (child.left() == null && child.right() == null) { // child의 자식이 없다면
						current.setLeft(null); // "child" is a leaf.
					} else if (child.left() == null) { // "child" has no left.
						current.setLeft(child.right());
					} else if (child.right() == null) { // "child" has no right
						current.setLeft(child.left());
					} else { // child의 left(), right() 모두 존재
						child.setElement(this.removeRightMostElementOfLeftSubTree(child)); // left()에서 가장 큰 element로 설정
					}
					this.setSize(this.size() - 1); // size--
					return objectForRemove; // 삭제한 object 반환
				}
			} else { // aKey > current.element().key()
				child = current.right(); // child를 right()로 지정
				if (child == null) { // child가 null이면 aKey가 존재하지않음
					return null; // "aKey" does not
				}
				if (aKey.compareTo(child.element().key()) == 0) { // Found. "child" is to be removed.
					Obj objectForRemove = child.element().object(); // child의 object 저장
					if (child.left() == null && child.right() == null) { // child의 자식이 없다면
						current.setRight(null); // "child" is a leaf
					} else if (child.left() == null) { // "child" has no left
						current.setRight(child.right());
					} else if (child.right() == null) { // "child" has no right
						current.setRight(child.left());
					} else {
						child.setElement(this.removeRightMostElementOfLeftSubTree(child)); // left()에서 가장 큰 element로 설정
					}
					this.setSize(this.size() - 1);
					return objectForRemove;
				}
			}
			current = child; // 찾을때까지 내려감

		} while (true); // return 될때까지 반복
	}

	private DictionaryElement<Key, Obj> removeRightMostElementOfLeftSubTree(
			BinaryNode<DictionaryElement<Key, Obj>> root) { // 삭제할 element에게 자식이 모두 존재할 때 left()에서 가장 큰 element로 대체하는 함수
		// At this point, "root" has non-empty left subtree, "root" is to be removed
		BinaryNode<DictionaryElement<Key, Obj>> leftOfRoot = root.left(); // root의 left를 leftOfRoot로 저장
		if (leftOfRoot.right() == null) { // leftOfRoot에게 right-subtree가 없으므로 leftOfRoot가 rightmost가 됨
			root.setLeft(leftOfRoot.left()); // leftOfRoot를 삭제하여 반환
			return leftOfRoot.element();
		} else { // leftOfRoot에게 right-subtree가 하나이상 존재
			BinaryNode<DictionaryElement<Key, Obj>> parentOfRightMost = leftOfRoot; // leftOfRoot가 parentOfRightMost가 됨
			BinaryNode<DictionaryElement<Key, Obj>> rightMost = parentOfRightMost.right(); // parentOfRightMost의 right()를 rightMost로 저장
			while (rightMost.right() != null) { // rightMost가 null이라면 종료
				parentOfRightMost = rightMost; // 내려감
				rightMost = rightMost.right(); // 내려감
			}
			parentOfRightMost.setRight(rightMost.left()); // rightMost를 삭제하여 반환
			return rightMost.element();
		}
	}

	@Override
	public void clear() {
		this.setSize(0);
		this.setRoot(null);

	}

	@Override
	public Iterator<DictionaryElement<Key, Obj>> iterator() { // DictionaryElement를 가지는 Iterator
		return (new DictionaryIterator());
	}

	// Iterator을 구현한 내부 클래스
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

	private void inorderRecursively(BinaryNode<DictionaryElement<Key, Obj>> aRootOfSubtree, int aLevel) { // 중위순회를 recursive하게 구현
		if (aRootOfSubtree != null) {
			this.inorderRecursively(aRootOfSubtree.left(), aLevel + 1); // left-subtree 
			DictionaryElement<Key, Obj> visitedElement = aRootOfSubtree.element(); // visit 수행할 element visitElement에 저장
			this.visitDelegate().visitForSortedOrder(visitedElement.key(), visitedElement.object(), aLevel); // visit 수행
			this.inorderRecursively(aRootOfSubtree.right(), aLevel + 1); // right-subtree
		}
	}

	@Override
	public void scanInSortedOrder() {
		this.inorderRecursively(this.root(), 1);

	}

	private void reverseOfInorderRecursively(BinaryNode<DictionaryElement<Key, Obj>> aRootOfSubtree, int aLevel) { // 중위 순회를 reverse하여 구현
		if (aRootOfSubtree != null) {
			this.reverseOfInorderRecursively(aRootOfSubtree.right(), aLevel + 1); // right-subtree 
			DictionaryElement<Key, Obj> visitedElement = aRootOfSubtree.element(); // visit 수행할 element visitElement에 저장
			this.visitDelegate().visitForReverseOfSortedOrder(visitedElement.key(), visitedElement.object(), aLevel); // visit 수행
			this.reverseOfInorderRecursively(aRootOfSubtree.left(), aLevel + 1); // left-subtree
		}
	}

	@Override
	public void scanInReverseOfSortedOrder() {
		this.reverseOfInorderRecursively(this.root(), 1);

	}
}
