package chap03.exam03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterruptedExample3 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                log.info("스레드 작동 중");
                if (Thread.interrupted()) {
                    log.info("인터럽트 상태 초기화 되었습니다.");
                    break;
                }
            }
            log.info("인터럽트 상태: {}", Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt();
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