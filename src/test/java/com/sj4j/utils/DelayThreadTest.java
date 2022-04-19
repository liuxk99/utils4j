package com.sj4j.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DelayThreadTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testStart() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": =>" +  "testStart()");
        final long currentTime = System.currentTimeMillis();
        System.out.println("current: " + currentTime);
        new DelayThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": " +  "DelayThread#1: "
                    + (System.currentTimeMillis() - currentTime));
            }
        }, 1000).start();

        Thread t = new DelayThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": " +  "DelayThread#2: "
                    + (System.currentTimeMillis() - currentTime));
            }
        }, 3000);
        t.start();

        new DelayThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": " +  "DelayThread#3: "
                    + (System.currentTimeMillis() - currentTime));
            }
        }, 2000).start();

        Thread.sleep(1000);
        t.interrupt();

        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + ": <-" +  "testStart()");
    }
}