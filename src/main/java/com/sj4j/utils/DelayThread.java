package com.sj4j.utils;

public class DelayThread extends Thread {
    protected final long milliSeconds;

    public DelayThread(Runnable runnable, final long milliSeconds) {
        super(runnable);
        this.milliSeconds = milliSeconds;
    }

    @Override
    public void run() {
        System.out.println(getTag() + "=> run()");
        {
            long targetTime = System.currentTimeMillis() + milliSeconds;
            long delta = -1;
            do {
                long currentTime = System.currentTimeMillis();
                delta = targetTime - currentTime;
                if (delta > 0) {
                    try {
                        Thread.sleep(delta);
                    } catch (InterruptedException e) {
                        System.out.println(getTag() + "Exception: " + e);
                        e.printStackTrace(System.out);
                    }
                }
            } while (delta >= 0);

            super.run();

        }
        System.out.println(getTag() + "<- run()");
    }

    static String getTag() {
        return String.format("[%s]: ", Thread.currentThread().getName());
    }
}
