package chap02.exam02;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultiThreadAppTerminatedExample {
    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new ThreadStackExample.MyRunnable(i));
            thread.start();
        }
        log.info("메인 스레드 종료");

    }
    static class MyRunnable implements Runnable{

        private final int threadId;

        public MyRunnable(int threadId) {

            this.threadId = threadId;
        }

        @Override
        public void run() {
            log.info("{} : 스레드 실행 중..",Thread.currentThread().getName());
            firstMethod(threadId);
        }

        private void firstMethod(int threadId) {

            int localValue = threadId + 100;
            secondMethod(localValue);

        }

        private void secondMethod(int localValue) {
            log.info("{} : 스레드 ID : {}, Value:{}",Thread.currentThread().getName(), threadId, localValue);
        }
    }
}