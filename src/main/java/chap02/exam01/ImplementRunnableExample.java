package chap02.exam01;

import lombok.extern.slf4j.Slf4j;

public class ImplementRunnableExample {
    public static void main(String[] args) {

        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task);
        thread.start();
    }
}

@Slf4j
class MyRunnable implements Runnable{

    @Override
    public void run() {
        log.info("{} : 스레드 실행 중..",Thread.currentThread().getName());
    }
}