package chap02.exam01;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnonymousThreadClassExample {
    public static void main(String[] args) {

        Thread thread = new Thread() {
            @Override
            public void run() {
                log.info("{} : 스레드 실행 중..",Thread.currentThread().getName());
            }
        };

        thread.start();
    }
}