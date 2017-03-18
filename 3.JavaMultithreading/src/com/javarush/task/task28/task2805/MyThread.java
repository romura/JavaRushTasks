package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by GETMAN on 20.02.2017.
 */
public class MyThread extends Thread {
    static AtomicInteger count = new AtomicInteger(1);

    public MyThread() {
        if (count.get() > 10) count.set(1);
        this.setPriority(count.getAndIncrement());

    }

    public MyThread(Runnable target) {
        super(target);
        if (count.get() > 10) count.set(1);
        this.setPriority(count.getAndIncrement());
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        if (count.get() > 10) count.set(1);
        this.setPriority(count.getAndIncrement());

        int maxPriority = this.getThreadGroup().getMaxPriority();
        if (count.get() > maxPriority){
            this.setPriority(maxPriority);
        }
    }

    public MyThread(String name) {
        super(name);
        if (count.get() > 10) count.set(1);
        this.setPriority(count.getAndIncrement());
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);

        if (count.get() > 10) count.set(1);
        this.setPriority(count.getAndIncrement());

        int maxPriority = this.getThreadGroup().getMaxPriority();
        if (count.get() > maxPriority){
            this.setPriority(maxPriority);
        }
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        if (count.get() > 10) count.set(1);
        this.setPriority(count.getAndIncrement());
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        if (count.get() > 10) count.set(1);
        this.setPriority(count.getAndIncrement());

        int maxPriority = this.getThreadGroup().getMaxPriority();
        if (count.get() > maxPriority){
            this.setPriority(maxPriority);
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        if (count.get() > 10) count.set(1);
        this.setPriority(count.getAndIncrement());

        int maxPriority = this.getThreadGroup().getMaxPriority();
        if (count.get() > maxPriority){
            this.setPriority(maxPriority);
        }
    }
}
