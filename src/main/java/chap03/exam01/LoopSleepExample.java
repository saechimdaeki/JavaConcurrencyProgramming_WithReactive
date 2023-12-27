package chap03.exam01;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoopSleepExample {
    public static void main(String[] args) {

        for(int i =0; i<7; i++) {
            try {
                log.info("반복 : {}", i + 1);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}