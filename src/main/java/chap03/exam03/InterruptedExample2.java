package chap03.exam03;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class InterruptedExample2 {
    public static void main(String[] args) {
        Thread thread2 = new Thread(() -> {
            while (!Thread.interrupted()) {
                log.info("스레드 2 작동 중 ");
            }
            log.info("인터럽트 상태: {}" , Thread.currentThread().isInterrupted());
        });

        Thread thread1 = new Thread(() -> {
            IntStream.range(0, 5).forEach(i -> {
                log.info("스레드 1 작동 중 ");
                if (i == 2) {
                    thread2.interrupt();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        thread1.start();
        thread2.start();
    }
}