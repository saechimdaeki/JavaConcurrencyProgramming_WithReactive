package chap02.exam02;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j

public class SingleThreadAppTerminatedExample {
    public static void main(String[] args) {

        int sum = IntStream.range(0, 1000).sum();

        log.info("sum : {}", sum);

        log.info("메인 스레드 종료");

    }
}