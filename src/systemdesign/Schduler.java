package systemdesign;

import java.util.PriorityQueue;
import java.util.TimerTask;

class Task {
	state;
	period/occuerence;
	nextExecutionTime;
	cancel();
	run() {
		// add task steps here
	}
}


public class Schduler {
PriorityQueue<E> <Task> q= new ProrityQueue<>((a, b) - > a.nextExecutionTime - b.nextExecutionTime);
TimerThread th = new TimerThread(q);
schedule(Task t, long period) {
	if(t.state != "NEW") // Already scheduled
	else {
		task.nextExecutionTime = currentTime + period;
		task.period = period;
		task.state = "Scheduled";
		q.add(t);
		if(q.peek() == t) q.notify();
	}
}
void cancel();

}


class TimerThread extends Thread {
	PriorityQueue queue;
	TimeThread(Priority q) this.queue = q;
	
	void run() {
		while (true) {
            try {
                Task task;
                boolean taskFired;
                synchronized(queue) {
                    // Wait for queue to become non-empty
                    while (queue.isEmpty() )
                        queue.wait();
                    if (queue.isEmpty())
                        break; // Queue is empty and will forever remain; die

                    // Queue nonempty; look at first evt and do the right thing
                    long currentTime, executionTime;
                    task = queue.peek();
                    synchronized(task.lock) {
                        if (task.state == TimerTask.CANCELLED) {
                            queue.removeMin();
                            continue;  // No action required, poll queue again
                        }
                        currentTime = System.currentTimeMillis();
                        executionTime = task.nextExecutionTime;
                        if (taskFired = (executionTime<=currentTime)) {
                            if (task.period == 0) { // Non-repeating, remove
                                queue.removeMin();
                                task.state = TimerTask.EXECUTED;
                            } else { // Repeating task, reschedule
                                queue.rescheduleMin(
                                  task.period<0 ? currentTime   - task.period
                                                : executionTime + task.period);
                            }
                        }
                    }
                    if (!taskFired) // Task hasn't yet fired; wait
                        queue.wait(executionTime - currentTime);
                }
                if (taskFired)  // Task fired; run it, holding no locks
                    task.run();
            } catch(InterruptedException e) {
            }
	}
}
