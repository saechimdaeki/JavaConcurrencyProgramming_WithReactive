package chap04.exam03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAndDaemonInheritanceExample {
    public static void main(String[] args) {

        // 사용자 스레드 생성
        Thread userThread = new Thread(() -> {
            Thread childOfUserThread = new Thread(() -> log.info("사용자 스레드의 자식 스레드의 데몬 상태: {}", Thread.currentThread().isDaemon()));
            childOfUserThread.start();
            log.info("사용자 스레드의 데몬 상태: {}" , Thread.currentThread().isDaemon());
        });

        // 데몬 스레드 생성
        Thread daemonThread = new Thread(() -> {
            Thread childOfDaemonThread = new Thread(() -> log.info("데몬 스레드의 자식 스레드의 데몬 상태: {}", Thread.currentThread().isDaemon()));
            childOfDaemonThread.start();
            log.info("데몬 스레드의 데몬 상태: {}", Thread.currentThread().isDaemon());
        });
        daemonThread.setDaemon(true);

        userThread.start();
        daemonThread.start();
    }
}