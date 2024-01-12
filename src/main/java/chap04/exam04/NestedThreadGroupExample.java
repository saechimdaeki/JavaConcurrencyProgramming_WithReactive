package chap04.exam04;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NestedThreadGroupExample {
    public static void main(String[] args) throws InterruptedException {

        // 최상위 스레드 그룹 생성
        ThreadGroup topGroup = new ThreadGroup("최상위 스레드 그룹");

        // 최상위 스레드 그룹 내부에 하위 스레드 그룹 생성
        ThreadGroup subGroup = new ThreadGroup(topGroup, "하위 스레드 그룹");

        // 최상위 스레드 그룹에 속한 스레드 생성
        Thread topGroupThread = new Thread(topGroup, new MyRunnable(), "TopGroupThread");

        // 하위 스레드 그룹에 속한 스레드 생성
        Thread subGroupThread = new Thread(subGroup, new MyRunnable(), "SubGroupThread");

        // 그룹 정보 출력
        log.info("{} 는 {} 에 속해 있습니다.", topGroupThread.getName(), topGroupThread.getThreadGroup().getName());
        log.info("{} 는 {} 에 속해 있습니다.", subGroupThread.getName(), subGroupThread.getThreadGroup().getName());

        topGroupThread.start();
        subGroupThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("\n-----------------------------------------------------------");
        log.info("최상위 스레드 그룹의 정보:");
        // 최상위 스레드 그룹의 정보 출력
        topGroup.list();
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            log.info("{} 가 속한 스레드 그룹의 정보: {}", Thread.currentThread().getName(), Thread.currentThread().getThreadGroup().getName());
        }
    }
}