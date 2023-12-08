package chap02.exam01;

import lombok.extern.slf4j.Slf4j;

public class ExtendThreadExample {
    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        myThread.start();
    }
 }

 @Slf4j
 class MyThread extends Thread{
     @Override
     public void run() {
         log.info("{} : 스레드 실행 중..",Thread.currentThread().getName());
     }
 }