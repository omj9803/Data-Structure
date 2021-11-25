
public interface VisitDelegate<Key, Obj> {
	public void visitForSortedOrder(Key aKey, Obj aObj, int aLevel);
	public void visitForReverseOfSortedOrder(Key aKey, Obj aObj, int aLevel);
}