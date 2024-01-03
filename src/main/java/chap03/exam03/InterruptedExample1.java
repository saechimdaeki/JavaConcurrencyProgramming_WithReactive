package chap03.exam03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterruptedExample1 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                log.info("스레드가 작동 중입니다.");
            }
            log.info("스레드가 인터럽트 되었습니다.");
            log.info("인터럽트 상태: {}", Thread.currentThread().isInterrupted());
        });
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}