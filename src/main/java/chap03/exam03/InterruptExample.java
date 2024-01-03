package chap03.exam03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            log.info("스레드 1이 작업을 시작합니다...");
            log.info("스레드 1 인터럽트 상태: {}" , Thread.currentThread().isInterrupted());
        });

        Thread thread2 = new Thread(() -> {
            log.info("스레드 2가 스레드 1을 인터럽트 합니다.");
            thread1.interrupt(); // 스레드 2가 스레드 1을 인터럽트
            log.info("스레드 2 인터럽트 상태: {}" , Thread.currentThread().isInterrupted());
        });

        thread2.start();
        Thread.sleep(1000);
        thread1.start();

        thread1.join();
        thread2.join();
        log.info("모든 스레드 작업 완료");
    }
}