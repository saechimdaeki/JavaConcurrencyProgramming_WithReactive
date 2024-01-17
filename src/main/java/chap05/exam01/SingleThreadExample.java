package chap05.exam01;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingleThreadExample {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int sum = 0;
        for (int i = 1; i <= 1000; i++) {
            sum += i;
            try {
                Thread.sleep(1);
                throw new RuntimeException("error");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        log.info("합계: {}", sum);
        log.info("싱글 스레드 처리 시간: {}ms", System.currentTimeMillis() - start);

    }
}