package chap02.exam02;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadStartRunExample {
    public static void main(String[] args) {

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("{} : 스레드 실행 중..",Thread.currentThread().getName());
            }
        });

        thread.start();
//        thread.run();
//        myRunnable.run();

    }

    static class MyRunnable implements Runnable{

        @Override
        public void run() {
            log.info("{} : 스레드 실행 중..",Thread.currentThread().getName());
        }
    }
}