package chap03.exam04;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CurrentThreadExample {
    public static void main(String[] args) {

        // 현재 실행 중인 스레드를 가져와 이름 출력
        log.info("현재 스레드: {}", Thread.currentThread());
        log.info("현재 스레드 이름: {}", Thread.currentThread().getName());

        // Thread 객체를 사용하여 스레드 생성 및 시작
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                log.info("현재 스레드: {}", Thread.currentThread());
                // 현재 스레드의 이름을 출력
                log.info("현재 스레드 이름 (Thread 객체 사용): {}", getName());
            }
        };
        thread1.start();

        Thread thread2 = new Thread(new ThreadName());
        thread2.start();
    }

    static class ThreadName implements Runnable {

        @Override
        public void run() {
            log.info("현재 스레드: {}", Thread.currentThread());
            // 현재 스레드의 이름을 출력
            log.info("현재 스레드 이름 (Runnable 사용): {}", Thread.currentThread().getName());
        }
    }
}