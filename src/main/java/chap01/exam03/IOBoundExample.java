package chap01.exam03;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class IOBoundExample {
    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors() / 2;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                try {

                    // IO 가 집중 되는 작업
                    for (int j = 0; j < 5; j++) {
                        Files.readAllLines(Path.of("src/main/java/chap01/exam03/sample.txt"));
                        log.info("스레드: {}, {}", Thread.currentThread().getName(), j);
                    }

                    // 아주 빠른 Cpu 연산
                    int result = 0;
                    for (long j = 0; j < 10; j++) {
                        result += j;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }
}