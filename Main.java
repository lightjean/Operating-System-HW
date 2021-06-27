public class Main {
	public static void main(String[] args) {
		int max_buffer_size = Integer.parseInt(args[0]);
		int cnt_p = Integer.parseInt(args[1]);
		int cnt_insert = Integer.parseInt(args[2]);
		int p_sleeptime = Integer.parseInt(args[3]);
		int cnt_c = Integer.parseInt(args[4]);
		int cnt_remove = Integer.parseInt(args[5]);
		int c_sleeptime = Integer.parseInt(args[6]);
		
		BoundedBuffer<Integer> BB = new BoundedBuffer<>(max_buffer_size);
		
		for (int i = 1; i <= cnt_p; i++) {
			Thread p = new Producer(p_sleeptime, cnt_insert, BB);
			p.setName("P" + i);
			p.start();
		}
		
		for (int i = 1; i <= cnt_c; i++) {
			Thread c = new Consumer(c_sleeptime, cnt_remove, BB);
			c.setName("C" + i);
			c.start();
		}
	}
}