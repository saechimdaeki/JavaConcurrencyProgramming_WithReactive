package chap03.exam04;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadNamingExample {
    public static void main(String[] args) throws InterruptedException {

        // 스레드 이름을 생성자에 전달하여 설정
        Thread myThread = new Thread(() -> log.info("현재 스레드 이름: {}", Thread.currentThread().getName()), "myThread");
        myThread.start();

        // setName() 메서드를 사용하여 스레드 이름 설정
        Thread yourThread = new Thread(() -> log.info("현재 스레드 이름: {}", Thread.currentThread().getName()),"yourThread");
        yourThread.start();

        // 여러 개의 스레드를 생성하여 기본 스레드 이름을 출력
        for (int i = 0; i < 5; i++) {
            Thread defaultThread = new Thread(() -> log.info("현재 스레드 이름: {}", Thread.currentThread().getName()));
            defaultThread.start();
        }

        Thread.sleep(2000);
    }
}