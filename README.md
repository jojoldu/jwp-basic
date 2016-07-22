#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* Tomcat(서블릿컨테이너)는 서블릿의 생명주기(Life Cycle)을 관리한다 <br/>
그래서 불필요한 객체 생성이 있을 경우 이를 효율적으로 처리될수 있도록 ServletContextListener 에서 처리를 하도록 구성되어 있다. <br/> 
웹어플리케이션이 시작되면 ServletContextListener의 contextInitialized 메소드를 호출한다(종료는 contextDestroyed) <br/>
contextInitialized 에 있는 DB생성, DB테이블 및 row데이터 생성, DB커넥션 객체 생성등을 수행후, @WebServlet을 갖고 있으며 loadOnStartup이 양수를 갖고 있는 Servlet들을 초기화 시킨다.<br/>
여기선 DispatcherServlet이 유일하여 DispatcherServlet.init 메소드를 호출하고 init 메소드에서 ReqeustMapping 객체를 생성한다.


#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* CharacterEncodingFilter -> ResourceFilter -> DispatcherServlet -> Controller -> View (Jsp/json) 

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* dao의 경우 데이터를 갖고있지 않아 문제가 없지만<br/> 
question과 answers의 경우 메소드 실행시마다 서로 다른 데이터가 호출되는데 해당 데이터를 ShowController가 갖고 있어 서로 다른 데이터가 사용자에게 전달될수 있다. 
