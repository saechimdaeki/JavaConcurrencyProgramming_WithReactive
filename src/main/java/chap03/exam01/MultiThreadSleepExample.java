package chap03.exam01;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultiThreadSleepExample {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            try {
                log.info("1초 후에 메시지가 출력됩니다");
                Thread.sleep(1000);
                log.info("스레드 1이 깨어났습니다.");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                log.info("2초 후에 메시지가 출력됩니다");
                Thread.sleep(2000);
                log.info("스레드 2가 깨어났습니다.");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

        log.info("여기는 메인입니다.");


    }
}