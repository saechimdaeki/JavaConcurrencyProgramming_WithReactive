package chap01.exam01;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class ConcurrencyExample {
    public static void main(String[] args) {

        int cpuCores = Runtime.getRuntime().availableProcessors() * 2;

        // CPU 개수를 초과하는 데이터를 생성
        List<Integer> data = IntStream.range(0, cpuCores).boxed().toList();

        // CPU 개수를 초과하는 데이터를 병렬로 처리
        long startTime2 = System.currentTimeMillis();
        long sum2 = data.parallelStream()
                .mapToLong(i -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return (long) i * i;
                })
                .sum();

        long endTime2 = System.currentTimeMillis();

        log.info("CPU 개수를 초과하는 데이터를 병렬로 처리하는 데 걸린 시간: {} ms", (endTime2 - startTime2));
        log.info("결과2: {}", sum2);
    }
}
