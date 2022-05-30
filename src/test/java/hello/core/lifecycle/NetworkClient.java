package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 url = " + url);
//        connect();
//        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + "message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url );
    }

    /*
     인터페이스 InitializingBean, DisposableBean 이용
     단점: 스프링 전용 인터페이스이기 때문에 너무 스프링에 의존적임. 초기화, 소멸 메서드의 이름은 변경 불가능하고, 코드를 고칠 수 없는 외부 라이브러리에는 적용 할 수 없음;
     거의 사용하지 않음 !
     implements InitializingBean, DisposableBean
     */
    // InitializingBean - 의존 관계 주입이 끝나면 호출
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    // DisposableBean - Bean이 종료 되면 호출
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }

    // import javax.annotation 자바 표준이기 때문에 스프링에 종속적인 기술이 아님
    // 유일한 단점은 외부 라이브러리에는 적용하지 못함!
    @PostConstruct
    public void init()
    {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close()
    {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
