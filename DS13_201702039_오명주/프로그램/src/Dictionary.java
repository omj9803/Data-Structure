
public abstract class Dictionary<Key extends Comparable<Key>, Obj extends Comparable<Obj>> {
	// Instance variable
	private int _size; // "private" �ӿ� ���� . ��� �޴� class ������ getter/setter �� ���ؼ��� �����Ѵ�
	// Getter/Setter

	public int size() {
		return this._size;
	}

	protected void setSize(int newSize) {
		this._size = newSize;
	}
	// setSize()�� ������ ũ�⸦ �����Ų�� . ���԰� ������ ������ ����� �� ����ȴ�
	// ���� "public" �Լ��� �ƴϾ�� �Ѵ� . �ܺο� �����Ǹ� �� �ȴ�
	// "private"�� �ƴϰ� "protected"�� ���� , ��� �޴� class �������� ����� �� �ְ� �ϱ� �����̴�
	// ��� �޴� class ������ , �ν��Ͻ� ���� "_size" �� ���� ����� �� ���� �����ϴ� ���� ������ ����̴�

	// Constructors
	public Dictionary() {
		this.setSize(0);
		// ��ӹ޴� class �� �����ڴ� �Ϲ������� ���� class �� �����ڸ� call �Ѵٴ� ���� ���� �� ��
	}

	// Public nonabstract method: �� class ���� �����Ǿ�� �Ѵ�
	public boolean isEmpty() {
		return (this.size() == 0);
	}

	// Public abstract methods
	public abstract boolean isFull();

	public abstract boolean keyDoesExist(Key aKey);

	public abstract Obj objectForKey(Key aKey);

	public abstract boolean addKeyAndObject(Key aKey, Obj anObject);

	public abstract Obj removeObjectForKey(Key aKey);

	public abstract void clear();

	public abstract Iterator<DictionaryElement<Key, Obj>> iterator();

}
