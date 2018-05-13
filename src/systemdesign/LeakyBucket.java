package systemdesign;

import java.util.Date;


//The sliding window (SW) and the leaky bucket (LB). The SW admits no more than a specified number W
//of arrivals in any interval of specified length L. (We consider the intervals to be half
//open, i.e., [t, t + L).) The LB is a counter that increases by one up to a maximum
//capacity C for each arrival and decreases continuously at a given drain rate D to
//as low as zero; an arrival is admitted if the counter is less than or equal to C- 1
//(so that after the arrival it will be less than or equal to C). 



public class LeakyBucket {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		LeakyBucket bucketLimiter = new LeakyBucket();
		System.out.println(bucketLimiter.canConsume());
		Thread.sleep(200);
		System.out.println(bucketLimiter.canConsume());
		Thread.sleep(200);
		System.out.println(bucketLimiter.canConsume());
		Thread.sleep(200);
		System.out.println(bucketLimiter.canConsume());
		Thread.sleep(200);
		System.out.println(bucketLimiter.canConsume());
		Thread.sleep(200);
		System.out.println(bucketLimiter.canConsume());
		System.out.println(bucketLimiter.canConsume());
		Thread.sleep(10000);
		System.out.println(bucketLimiter.canConsume());
		System.out.println(bucketLimiter.canConsume());
		System.out.println(bucketLimiter.canConsume());
		System.out.println(bucketLimiter.canConsume());
		System.out.println(bucketLimiter.canConsume());
		System.out.println(bucketLimiter.canConsume());
		System.out.println(bucketLimiter.canConsume());
		System.out.println(bucketLimiter.canConsume());
		
		Thread.sleep(1000);
		if (bucketLimiter.canConsume()) {
		    // dispatch SMS
		}
	}

    private int numDropsInBucket = 5;
    private long timeOfLastDropLeak = 0;
    private long fillInterval = 1000;
    private long nextFillTime = 0;
    private final int _BUCKET_SIZE_IN_DROPS = 5;
    private final long _MS_BETWEEN_DROP_LEAKS = 1000 * 60 * 60; // 1 hour

//    public synchronized boolean addDropToBucket() {
//        Date now = new Date();
//        // first of all, let the bucket leak by the appropriate amount
//        if (timeOfLastDropLeak != null) {
//            long deltaT = now.getTime() - timeOfLastDropLeak.getTime();
//            // note round down as part of integer arithmetic
//            long numberToLeak = deltaT / _MS_BETWEEN_DROP_LEAKS;
//            System.out.println("numberToLeak = " + numberToLeak);
//            if (numberToLeak > 0) { //now go and do the leak
//                if (numDropsInBucket <= numberToLeak) {
//                    numDropsInBucket = 0;
//                } else {
//                    numDropsInBucket -= (int) numberToLeak;
//                }
//                timeOfLastDropLeak = now;
//            }
//        }
//
//        if (numDropsInBucket < _BUCKET_SIZE_IN_DROPS) {
//            numDropsInBucket++;
//            return true; // drop added
//        }
//
//        return false; // overflow
//    }
    
    public synchronized boolean canConsume() {
    	long curTime = System.currentTimeMillis();
    	int new_token = 0;
    	if(nextFillTime == 0) 
    		nextFillTime = curTime + fillInterval;
    	while(curTime > nextFillTime) {
    		new_token++;
    		nextFillTime = nextFillTime + fillInterval;	
    	}
    	numDropsInBucket = numDropsInBucket + new_token;
		numDropsInBucket = Math.min(numDropsInBucket, _BUCKET_SIZE_IN_DROPS);
    	
    	if(numDropsInBucket > 0) {
    		numDropsInBucket--;
    		return true;
    	} else return false;
    }
    
    
}
