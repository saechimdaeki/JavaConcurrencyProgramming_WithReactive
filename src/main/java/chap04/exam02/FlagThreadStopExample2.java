package chap04.exam02;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class FlagThreadStopExample2 {
    private AtomicBoolean running = new AtomicBoolean(true);

    public void volatileTest() {
        new Thread(() -> {
            int count = 0;
            while (running.get()) {
                count++;
            }
            log.info("Thread 1 종료. Count: {}" , count);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            log.info("Thread 2 종료중..");
            running.set(false);
        }).start();
    }

    public static void main(String[] args) {
        new FlagThreadStopExample2().volatileTest();
    }
}