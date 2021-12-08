
public interface Queue<E> {
		public int size();
		public boolean isFull();
		public boolean isEmpty();
		
		public E front();
		public E rear();
		
		public boolean enQueue(E anElement);
		public E deQueue();
		
		public void clear();
		
		public E elementAt(int anOrder);
		public Iterator<E> iterator();
	}
