package chap04.exam03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserThreadLifecycleExample {
    public static void main(String[] args) throws InterruptedException {
        // 사용자 스레드 1 생성
        Thread userThread1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {

                log.info("사용자 스레드 1 실행 중...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("사용자 스레드 1 종료.");
        });

        // 사용자 스레드 2 생성
        Thread userThread2 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                log.info("사용자 스레드 2 실행 중...");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("사용자 스레드 2 종료.");
        });

        userThread1.start();
        userThread2.start();

        log.info("모든 사용자 스레드가 종료되었습니다. 메인 스레드 종료.");
    }
}