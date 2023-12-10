package chap02.exam02;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class ThreadStackExample {
    public static void main(String[] args) {

        IntStream.range(0, 3).mapToObj(i -> new Thread(new MyRunnable(i))).forEach(Thread::start);

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