package chap01.exam02;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class ContextSwitchExample {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> IntStream.range(0, 5).forEachOrdered(i -> {
            log.info("Thread 1: {}", i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));

        Thread thread2 = new Thread(() -> IntStream.range(0, 5).forEachOrdered(i -> {
            log.info("Thread 2: {}", i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));

        Thread thread3 = new Thread(() -> IntStream.range(0, 5).forEachOrdered(i -> {
            log.info("Thread 3: {}", i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));

        thread1.start();
        thread2.start();
        thread3.start();
    }
}