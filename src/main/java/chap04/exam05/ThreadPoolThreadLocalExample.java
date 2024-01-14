package chap04.exam05;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
public class ThreadPoolThreadLocalExample {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2); // 2개의 스레드를 가진 스레드 풀 생성

        // 첫 번째 작업: ThreadLocal 값을 설정
        executor.submit(() -> {
            threadLocal.set("작업 1의 값");
            log.info("{}: {}", Thread.currentThread().getName() ,threadLocal.get());
            // 작업 종료 후 값을 지워야 함
//            threadLocal.remove();
        });

        // 잠시 대기
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 여러 번의 두 번째 작업: ThreadLocal 값을 설정하지 않고 바로 값을 가져와 출력
        IntStream.range(0, 5).<Runnable>mapToObj(i -> () -> {
            log.info("{}: {}", Thread.currentThread().getName(), threadLocal.get());
        }).forEach(executor::submit);

        executor.shutdown();
    }
}