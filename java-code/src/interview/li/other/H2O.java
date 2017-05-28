package interview.li.other;

import java.util.concurrent.locks.*;
public class H2O {

	private int HCount = 0;
	private int OCount = 0;
	private Lock lock = new ReentrantLock();
	private Condition condH = lock.newCondition();
	private Condition condO = lock.newCondition();
	
	
	public void H() throws InterruptedException{
		lock.lock();
		try {
			HCount++;
			if (HCount >= 2 && OCount >= 1) {
				System.out.println("2 H and 1 O consumed in H()");
				HCount -= 2;
				OCount -= 1;
				condH.signal();
				condO.signal();
			} else {
				condH.await();
			}
		} finally {
			lock.unlock();
		}
	}
	
	public void O() throws InterruptedException{
		lock.lock();
		try {
			OCount++;
			if (HCount >= 2 && OCount >= 1) {
				System.out.println("2 H and 1 O consumed in O()");
				HCount -= 2;
				OCount -= 1;
				condH.signal();
				condH.signal();
			} else {
				condO.await();
			}
		} finally {
			lock.unlock();
		}
	}
	
	  public static void main(String[] args) {
	        int n = 3;
	        final H2O h2o = new H2O();
	        Thread[] threads = new Thread[n];
	        for (int i=0; i<n; i++) {
	            final int id = i;
	            threads[i] = new Thread(new Runnable() {
	                @Override
	                public void run() {
	                    for (int j=0; j<3; j++) {
	                        if (id %3 == 0) {
	                            try {
	                            	System.out.println(String.format("Producing an O in thread %d", id));
	                                h2o.O();
	                            } catch (InterruptedException e) {
	                                System.out.println(String.format("Thread %d is interrupted for O().", id));
	                            }
	                        } else {
	                            try {
	                            	System.out.println(String.format("Producing an H in thread %d", id));
	                                h2o.H();
	                                
	                            } catch (InterruptedException e) {
	                                System.out.println(String.format("Thread %d is interrupted for H().", id));
	                            }
	                        }
	                    }
	                }
	            });
	        }
	        for (Thread thread : threads) {
	            thread.start();
	        }
	    }
}
