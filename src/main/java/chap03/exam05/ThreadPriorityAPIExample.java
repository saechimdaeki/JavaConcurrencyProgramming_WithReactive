package chap03.exam05;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadPriorityAPIExample {
    public static void main(String[] args) {

        Thread thread = new Thread();
        log.info("기본우선순위: {}", thread.getPriority());
        thread.start();


        Thread minThread = new Thread(() -> log.info("최소 우선순위: {}", Thread.currentThread().getPriority()));
        minThread.setPriority(Thread.MIN_PRIORITY);
        minThread.start();

        Thread normThread = new Thread(() -> log.info("기본 우선순위: {}", Thread.currentThread().getPriority()));
        normThread.setPriority(Thread.NORM_PRIORITY);
        normThread.start();

        Thread maxThread = new Thread(() -> log.info("최대 우선순위: {}", Thread.currentThread().getPriority()));
        maxThread.setPriority(Thread.MAX_PRIORITY);
        maxThread.start();

    }
}