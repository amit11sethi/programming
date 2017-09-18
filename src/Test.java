public class Test {

    public synchronized void m1() {
    	System.out.println("In M1");
        try { Thread.sleep(20000); 
        System.out.println("Done  M1");
        }
        catch (InterruptedException ie) {}
    }

    public void m2() {
    	System.out.println("In M2");
        try { Thread.sleep(2000); 
        System.out.println("Done M2");
        }
        catch (InterruptedException ie) {}
    }

    public static void main(String[] args) throws InterruptedException {
        final Test t = new Test();
        Thread t1 = new Thread() { public void run() { t.m1(); } };
        Thread t2 = new Thread() { public void run() { t.m2(); } };

        t1.start();
        Thread.sleep(5000);

        t2.start();
        Thread.sleep(500);

        System.out.println(t2.getState());
    }
}