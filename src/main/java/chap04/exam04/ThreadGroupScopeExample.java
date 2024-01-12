package chap04.exam04;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadGroupScopeExample {
    public static void main(String[] args) throws InterruptedException {

        ThreadGroup topGroup = new ThreadGroup("상위그룹");
        ThreadGroup subGroup = new ThreadGroup(topGroup, "하위그룹");

        // 상위 그룹 스레드에서 하위 그룹의 최대 우선 순위를 변경
        Thread topGroupThread = new Thread(topGroup, () -> {
            log.info("상위 그룹 스레드에서 하위 그룹의 최대 우선 순위 변경 전: {}", subGroup.getMaxPriority());
            subGroup.setMaxPriority(7);
            log.info("상위 그룹 스레드에서 하위 그룹의 최대 우선 순위 변경 후: {}", subGroup.getMaxPriority());
        }, "상위그룹스레드");

        // 하위 그룹 스레드에서 상위 그룹의 최대 우선 순위를 변경하려고 시도
        Thread subGroupThread = new Thread(subGroup, () -> {
            log.info("하위 그룹 스레드에서 상위 그룹의 최대 우선 순위 변경 전: {}", topGroup.getMaxPriority());
            topGroup.setMaxPriority(4);
            log.info("하위 그룹 스레드에서 상위 그룹의 최대 우선 순위 변경 후: {}", topGroup.getMaxPriority());
        }, "하위그룹스레드");

        topGroupThread.start();
        subGroupThread.start();

        topGroupThread.join();
        subGroupThread.join();

        // 이미 생성된 스레드는 변경사항이 적용되지 않는다
        log.info("{}: {}", topGroup.getName(), topGroup.getMaxPriority());
        log.info("{}: {}", subGroup.getName(), subGroup.getMaxPriority());

        Thread userThread1 = new Thread(topGroup, () -> {}, "유저스레드 1");
        Thread userThread2 = new Thread(subGroup, () -> {}, "유저스레드 2");

        userThread1.start();
        userThread2.start();

        userThread1.join();
        userThread2.join();

        // 최상위 그룹(topGroup) 설정을 따라간다
        log.info("{}: {}",userThread1.getName(), userThread1.getPriority());
        log.info("{}: {}",userThread2.getName(), userThread2.getPriority());
    }
}