public class Consumer extends Thread {
	BoundedBuffer<Integer> b;
	private int max_sleep_time, cnt_remove;
	
	public Consumer() {
		max_sleep_time = 0;
		cnt_remove = 0;
	}
	
	public Consumer(int time, int count, BoundedBuffer<Integer> BB) {
		max_sleep_time = time;
		cnt_remove = count;
		b = BB;
	}
	
	public void run() {
		for (int i = 0; i < cnt_remove; i++) {
			int sleeptime = (int)(Math.random() * max_sleep_time);
			System.out.println(this.getName() + " starts sleeping " + sleeptime + "ms.");
			
			try {
				sleep(sleeptime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			int num = b.remove();
			System.out.println(this.getName() + " removes item " + num + ".");
		}
	}
}