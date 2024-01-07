package chap04.exam02;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterruptedThreadStopExample {
    public static void main(String[] args) {

        Thread worker = new Thread(() -> {
            while (!Thread.interrupted()) {
                // 스레드의 작업을 수행합니다.
                log.info("작업 스레드가 실행 중입니다.");
            }
            log.info("작업 스레드가 중단되었습니다.");
            log.info("인터럽트 상태 1 : {}", Thread.currentThread().isInterrupted());
            // Thread.interrupted() 는 interrupt 상태를 초기화 한다.
            // 그래서 필요시 자신에게 interrupt() 를 호출해 준다
            Thread.currentThread().interrupt(); // 필요시 자신에게 인터럽트 걸어줌
            log.info("인터럽트 상태 2 : {}", Thread.currentThread().isInterrupted());

        });

        Thread stopper = new Thread(() -> {
            try {
                // 1초 후에 스레드를 중지 합니다.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            worker.interrupt();
            log.info("중단 스레드가 작업 스레드를 중단시켰습니다.");
        });

        worker.start();
        stopper.start();
    }
}