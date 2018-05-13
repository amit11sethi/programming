package concurrency;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import testrule.Repeat;
import testrule.RepeatRule;

public class ExecutorsTest {
	
	@Rule
    public RepeatRule repeatRule = new RepeatRule();

	/**
	 * The code prints the thread name forever in a single thread. However, on
	 * running this code, you should see it terminate rather quickly, after
	 * maybe 7–10 lines are printed to the console. In JUnit, when the code in
	 * the main thread has completed, the test finishes.
	 */

	@Test
	public void executorExample() {
		final ExecutorService executor = Executors.newCachedThreadPool();
		final Runnable threadNamePrinter = new InfiniteThreadNamePrinter();
		System.out.println("Main thread: " + Thread.currentThread().getName());
		executor.execute(threadNamePrinter);
	}
	
	@Test
	public void streamExample() {
		List<Integer> list = (List) Arrays.asList(1,2,3,4,5,6,7,8);
		Stream<Integer> st = list.stream().filter(i ->  i % 2 == 0);
		System.out.println(st.count());
	}

	private static class InfiniteThreadNamePrinter implements Runnable {
		@Override
		public void run() {
			while (true) {
				System.out.println("Run from thread: " + Thread.currentThread().getName());
			}
		}
	}

	// Wait for threads to complete before completing the test

	@Test
	public void waitToComplete() throws InterruptedException {
		final ExecutorService executor = Executors.newCachedThreadPool();
		final CountDownLatch latch = new CountDownLatch(2);
		executor.execute(new FiniteThreadNamePrinterLatch(latch));
		latch.await(5, TimeUnit.SECONDS);
	}

	private static class FiniteThreadNamePrinterLatch implements Runnable {
		final CountDownLatch latch;

		private FiniteThreadNamePrinterLatch(final CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {
			for (int i = 0; i < 25; i++) {
				System.out.println("i = " + i + "Run from thread: " + Thread.currentThread().getName());
			}
			latch.countDown();
			for (int i = 0; i < 25; i++) {
				System.out.println("i = " + i + "Run from thread: " + Thread.currentThread().getName());
			}
			latch.countDown();
		}
	}

	// Shared State

	@Test
	@Repeat(50)
	public void sharedState() {
		final ExecutorService executorService = Executors.newCachedThreadPool();
		final SimpleCounter c = new SimpleCounter();
		executorService.execute(new CounterSetter(c));
		c.setNumber(200);
		assertEquals(200, c.getNumber());
	}

	private static class CounterSetter implements Runnable {
		private final SimpleCounter counter;

		private CounterSetter(SimpleCounter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			while (true) {
				counter.setNumber(100);
			}
		}
	}

	class SimpleCounter {
		private int number = 0;

		public void setNumber(int number) {
			this.number = number;
		}

		public int getNumber() {
			return number;
		}
	}

	@Test
	@Repeat(500)
	public void atomicSharedState() {
		final ExecutorService executorService = Executors.newCachedThreadPool();
		final AtomicCounter c = new AtomicCounter();
		executorService.execute(new AtomicCounterSetter(c));
		final int value = c.getNumber().incrementAndGet();
		assertEquals(1, value);
	}

	private static class AtomicCounterSetter implements Runnable {
		private final AtomicCounter counter;

		private AtomicCounterSetter(AtomicCounter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			while (true) {
				counter.getNumber().set(0);
			}
		}
	}

	class AtomicCounter {
		private final AtomicInteger number = new AtomicInteger(0);

		public AtomicInteger getNumber() {
			return number;
		}
	}

}
