package chap03.exam01;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicSleepExample {
    public static void main(String[] args) {
        try {
            log.info("2초 후에 메시지가 출력됩니다");
            Thread.sleep(2000);
            log.info("Heelo World");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}