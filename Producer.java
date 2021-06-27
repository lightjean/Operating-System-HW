public class Producer extends Thread {
	BoundedBuffer<Integer> b;
	private int max_sleep_time, cnt_insert;
	
	public Producer() {
		max_sleep_time = 0;
		cnt_insert = 0;
	}
	
	public Producer(int time, int count, BoundedBuffer<Integer> BB) {
		max_sleep_time = time;
		cnt_insert = count;
		b = BB;
	}
	
	public void run() {
		for (int i = 0; i < cnt_insert; i++) {
			int sleeptime = (int)(Math.random() * max_sleep_time);
			System.out.println(this.getName() + " starts sleeping " + sleeptime + "ms.");
			
			try {
				sleep(sleeptime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			int num = (int)(Math.random() * 100);
			b.insert(num);
			System.out.println(this.getName() + " inserts item " + num + ".");
		}
	}	
}