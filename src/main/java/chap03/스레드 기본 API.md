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

---

## interrupt()

- Interrupt 의 사전적 의미는 ‘방해하다’ 라는 뜻으로 어떤 주체의 행동이나 실행흐름을 방해한다는 의미로 해석 할 수 있다
- 자바 스레드에서 interrupt() 는 특정한 스레드에게 인터럽트 신호를 알려 줌으로써 스레드의 실행을 중단하거나, 작업 취소, 강제 종료 등으로 사용할 수 있다

- interrupt() 는 스레드에게 인터럽트가 발생했다는 신호를 보내는 메카니즘이다
- interrupt() 는 스레드가 현재 실행 흐름을 멈추고 인터럽트 이벤트를 먼저 처리하도록 시그널을 보내는 장치라 할 수 있다
- interrupted 속성
  - 스레드는 인터럽트 상태(Interrupt State )로 알려진 interrupted 를 가지고 있으며 인터럽트 발생 여부를 확인할 수 있는 상태 값이다. 기본값은 fasle 이다
  - 인터럽트된 스레드가 처리해야 하는 특별한 규칙이나 정해진 기준은 없으나 일반적으로 인터럽트 상태를 사용해서 스레드를 중지하거나, 작업을 취소하거나, 스레드를 종료 하는 등의 기능을 구현할 수 있다
  - 한 스레드가 다른 스레드를 인터럽트 할 수 있고 자기 자신을 인터럽트 할 수도 있다
  - interrupt() 하는 횟수는 제한이 없으며 인터럽트 할 때 마다 스레드의 인터럽트 상태를 true 로 변경한다

### 인터럽트 상태 확인 방법

- static boolean interrupted()
  - 스레드의 인터럽트 상태를 반환하는 정적 메소드이다
  - 만약 현재 인터럽트 상태가 true 인 경우 true 를 반환하고 인터럽트 상태를 false 로 초기화 하므로 인터럽트를 해제하는 역할을 한다
  - 인터럽트를 해제하는 경우 다른 곳에서 스레드에 대한 인터럽트 상태를 체크하는 곳이 있다면 별도의 처리가 필요할 수 있다
  - 인터럽트를 강제로 해제했기 때문에 다시 인터럽트를 걸어서 인터럽트 상태를 유지할 수 있다

<img width="868" alt="image" src="https://github.com/saechimdaeki/Dev-Diary/assets/40031858/675aa80b-7661-4a92-9a13-0d89451e719b">

- boolean isInterrupted()
  - 스레드의 인터럽트 상태를 반환하는 인스턴스 메서드이다
  - 이 메서드는 스레드의 인터럽트 상태를 변경하지 않고 계속 유지한다. 
  - 인터럽트 상태를 확인하는 용도로만 사용할 경우 interrupted() 보다 이 메서드를 사용하는 것이 좋다

- InterruptedException
  - InterruptedException 은 interrupt() 메카니즘의 일부이며 대기나 차단 등 블록킹 상태에 있거나 블록킹 상태를 만나는 시점의 스레드에 인터럽트 할 때 발생하는 예외이다
  - InterruptedException 이 발생하면 인터럽트 상태는 자동으로 초기화 된다. 즉 Thread.interrupted() 한 것과 같은 상태로 된다( interrupted = false)
  - 다른 곳에서 인터럽트 상태를 참조하고 있다면 예외 구문에서 대상 스레드에 다시 interrupt() 해야 할 수도 있다
  - InterruptedException 이 발생하는 케이스는 다음과 같다
    - Thread.sleep(), Thread.join(), Object.wait()
    - Future.get(), BlockingQueue.take()



<img width="1050" alt="image" src="https://github.com/saechimdaeki/Dev-Diary/assets/40031858/d17930a1-000b-400a-a767-71869af4ff89">

---

## name() / currentThread() / isAlive()

### Thread Name
- 멀티 스레드 환경에서 어떤 스레드가 실행 중인지 알아야 할 경우 스레드에 사용자 이름을 지정하면 실행 중인 스레드를 쉽게 찾을 수 있다
- 디버깅할 때 어떤 스레드가 무슨 작업을 하고 있는지 정확하게 파악하기 위해서 스레드 이름을 정하는 것이 큰 도움이 된다
- 자바에서 스레드가 생성되면 스레드 이름이 자동으로 주어진다. 이건 사용자가 정하는 것이 아니다
  - 가장 먼저 생성되는 메인 스레드의 이름은 main 이다. 
  - 스레드 이름은 Thread-0, Thread-1, Thread-2, .. Thread-n 과 같이  0 부터 순차적으로 숫자를 증가하면서 이름이 만들어진다
- 자바에서 사용자가 스레드 이름을 정할 수 있으며 두 가지 방법으로 가능하다
  - 스레드 객체 생성 시 인자로 전달
    - Thread myThread = new Thread([ThreadGroup],[Runnable],“myThread”);
  - setName(String name) 으로 설정
    - myThread.setName(“myThread”);
  - getName() 으로 스레드 이름 참조
    - myThread.getName();

### currentThread()
- Thread 클래스의 정적 메서드로서 현재 실행 중인 스레드 개체에 대한 참조를 반환한다
- ex) Thread.currentThread().getName(), if(Thread.currentThread() == thread)

### isAlive()
- 스레드가 살아 있는지 여부를 알 수 있다
- 스레드의 start() 메서드가 호출되고 스레드가 아직 종료되지 않은 경우 스레드가 활성 상태인 것으로 간주되어 true 를 반환한다 