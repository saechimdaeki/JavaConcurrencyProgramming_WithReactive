# 스레드 기본 API

## sleep()

### API 및 예외

- static sleep(long millis) throws InterruptedException
  - 지정한 밀리초 시간 동안 스레드를 수면 상태로 만든다
  - 밀리초에 대한 인수 값은 음수가 될 수 없으며 음수 일 경우 IllegalArgumentException 이 발생한다

- static sleep( long   millis,  int  nanos) InterruptedException
  - 지정한 밀리초에 나노초를 더한 시간 동안 스레드를 수면 상태로 만든다, 
  - 나노초의 범위는 0 에서 999999 이다

- InterruptedException
  - 스레드가 수면 중에 인터럽트 될 경우 InterruptedException 예외를 발생시킨다
  - 다른 스레드는 잠자고 있는 스레드에게 인터럽트, 즉 중단(멈춤) 신호를 보낼 수 있다
  - InterruptedException 예외가 발생하면 스레드는 수면상태에서 깨어나고 실행대기 상태로 전환되어 실행상태를 기다린다

기본 코드 방식

```java
try {
    Thread.sleep(3000);
    // business logic
} catch (InterruptedException e) {

}
```

<img width="1099" alt="image" src="https://github.com/saechimdaeki/Dev-Diary/assets/40031858/528103fb-575c-4632-bdd3-3c546b4cf2e4">



<img width="1089" alt="image" src="https://github.com/saechimdaeki/Dev-Diary/assets/40031858/56ae34e9-b39d-4f10-9093-0a89ad02e35d">

### sleep() 작동 방식 정리

- sleep() 이 되면 OS 스케줄러는 현재 스레드를 지정된 시간 동안 대기 상태로 전환하고 다른 스레드 혹은 프로세스에게 CPU 를 사용하도록 한다 
- 대기 시간이 끝나면 스레드 상태는 바로 실행상태가 아닌 실행 대기 상태로 전환 되고 CPU 가 실행을 재개할 때 까지 기다린다. 
- 실행 상태가 되면 스레드는 남은 지점부터 실행을 다시 시작한다
- 동기화 메서드 영역에서 수면 중인 스레드는 획득한 모니터나 락을 잃지 않고 계속 유지한다
- sleep() 중인 스레드에게 인터럽트가 발생할 경우 현재 스레드는 대기에서 해제되고 실행상태로 전환되어 예외를 처리하게 된다
- 스레드의 수면 시간은 OS 스케줄러 및 시스템 기능에 따라 제한되기 때문에 정확성이 보장되지 않으며 시스템의 부하가 많고 적음에 따라 지정한 수면 시간과 차이가 날 수 있다

---

## join()

- join() 메서드는 한 스레드가 다른 스레드가 종료될 때까지 실행을 중지하고 대기상태에 들어갔다가 스레드가 종료되면 실행대기 상태로 전환된다
- 스레드의 순서를 제어하거나 다른 스레드의 작업을 기다려야 하거나 순차적인 흐름을 구성하고자 할 때 사용할 수 있다
- Object 클래스의 wait() 네이티브 메서드로 연결되며 시스템 콜을 통해 커널모드로 수행한다. 내부적으로 wait() & notify() 흐름을 가지고 제어한다

### API 및 예외 처리

- void join() throws InterruptedException
  - 스레드의 작업이 종료 될 때까지 대기 상태를 유지한다

- void join(long millis) throws InterruptedException
  - 지정한 밀리초 시간 동안 스레드의 대기 상태를 유지한다
  - 밀리초에 대한 인수 값은 음수가 될 수 없으며 음수 일 경우 IllegalArgumentException 이 발생한다

- void join( long   millis,  int  nanos) InterruptedException
  - 지정한 밀리초에 나노초를 더한 시간 동안 스레드의 대기 상태를 유지한다 
나노초의 범위는 0 에서 999999 이다

- InterruptedException
  - 스레드가 인터럽트 될 경우 InterruptedException 예외가 발생시킨다
  - 다른 스레드는 join() 을 수행 중인 스레드에게 인터럽트, 즉 중단(멈춤) 신호를 보낼 수 있다
  - InterruptedException 예외가 발생하면 스레드는 대기상태에서 실행대기 상태로 전환되어 실행상태를 기다린다

### join 작동 방식

<img width="1084" alt="image" src="https://github.com/saechimdaeki/Dev-Diary/assets/40031858/f86047a2-4733-4bb7-8145-c79b06353c9b">

### join() 작동 방식 정리

- join() 을 실행하면 OS 스케줄러는 현재 스레드를 대기 상태로 전환하고 join() 을 수행중인 스레드에게 CPU 를 사용하도록 한다 
- join() 을 수행중인 스레드의 작업이 종료되면 현재 스레드는 실행 대기 상태로 전환 되고 CPU 가 실행을 재개할 때 까지 기다린다. 
- 실행 상태가 되면 스레드는 남은 지점부터 실행을 다시 시작한다
- join() 을 수행중인 스레드가 여러 개일 경우 각 스레드의 작업이 종료될 때 까지 현재 스레드는 대기하고 종료 이후 실행을 재개하는 흐름을 반복한다
- join() 을 수행중인 스레드에게 인터럽트가 발생할 경우 현재 스레드는 대기에서 해제되고 실행상태로 전환되어 예외를 처리하게 된다 
