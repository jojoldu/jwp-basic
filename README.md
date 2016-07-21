#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* 

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* 

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* dao의 경우 데이터를 갖고있지 않아 문제가 없지만<br/> 
question과 answers의 경우 메소드 실행시마다 서로 다른 데이터가 호출되고 리턴되지않고 ShowController에서 갖고있어 사용자가 요구한 데이터가 호출되지 않는 경우가 발생할수있다. 
