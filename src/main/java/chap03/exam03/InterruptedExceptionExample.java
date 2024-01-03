package chap03.exam03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterruptedExceptionExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                log.info("인터럽트 상태 1: {}", Thread.currentThread().isInterrupted());
                Thread.sleep(5000);
                log.info("스레드 잠자기 완료");
            } catch (InterruptedException e) { // InterruptedException 예외가 발생하면 인터럽트 상태가 초기화 된다 : false
                log.info("스레드가 인터럽트 되었습니다.");
                log.info("인터럽트 상태2: {}", Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
            }
        });

        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
        thread.join();
        log.info("인터럽트 상태 3: {}",thread.isInterrupted());
    }
}