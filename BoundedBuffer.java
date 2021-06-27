public class BoundedBuffer<E> {
	private static int BUFFER_SIZE;
	
	private int count, in, out;
	private E[] buffer;
	
	public BoundedBuffer() {
		count = 0;
		in = 0;
		out = 0;
		BUFFER_SIZE = 0;
		buffer = (E[]) new Object[BUFFER_SIZE];
	}
	
	public BoundedBuffer(int size) {
		count = 0;
		in = 0;
		out = 0;
		BUFFER_SIZE = size;
		buffer = (E[]) new Object[BUFFER_SIZE];
	}
	
	public synchronized void insert(E item) {
		while (count == BUFFER_SIZE) {
			try {
				System.out.println("Inserting item " + item + " is waiting for the full buffer.");
				wait();
			} catch (InterruptedException ie) {}
		}
		
		buffer[in] = item;
		System.out.println("Item " + item + " has been inserted at index " + in + ".");
		in = (in + 1) % BUFFER_SIZE;
		count++;
		
		System.out.println("Notify() has been called in the insert().");
		notify();
	}
	
	public synchronized E remove() {
		E item;
		
		while (count == 0) {
			try {
				System.out.println("Removing item is waiting for the empty buffer.");
				wait();
			} catch (InterruptedException ie) {}
		}
		
		item = buffer[out];
		System.out.println("Item " + item + " has been removed from index " + out + ".");
		out = (out + 1) % BUFFER_SIZE;
		count--;
		
		System.out.println("Notify() has been called in the remove().");
		notify();
		
		return item;
	}
}