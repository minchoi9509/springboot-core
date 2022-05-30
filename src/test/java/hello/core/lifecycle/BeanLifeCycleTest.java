package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    // 결론!!!!! @PostConstruct @PreDestroy 사용하자!!!!!
    @Test
    public void lifeCycleTest() {
        /*
         스프링 빈의 이벤트 라이플 사이클
         스프링 컨테이너 생성 -> 스프링 빈 생성(생성자 주입의 경우에는 사실 이때 의존 관계 주입 됨 ㅎㅎ) -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
         아 그러면 그냥 생성자로 해버리면 되는거 아니야? 라고 생각했는데 객체의 생성과 초기화를 분리하자고 바로 말씀해주셨다;
         생성자는 진짜 필수! 정보를 받고 메모리를 할당해서 객체를 생성하는 책임을 가진다.
         초기화는 생성된 값들을 활용해서! 외부 커넥션을 연결하는 무거운 동장을 수행한다.
        */
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        // 닫는 메소드
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        /*
         @Bean애 initMethod, destroyMethod를 붙여서 사용하는 방법
         스프링 빈이 스프링 코드에 의존하지 않는다.
         코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있다.
         ㄴ 외부 라이브러에서 보통 close, shutdown이라는 이름의 종료 메서드를 사용해서 .. (inferred)라는 추론 메서드가 디폴트 값이여서 자동으로 호출해줌; 헐;
         */
//        @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            // 스프링 빈은 객체를 생성하고, 의존 관계 주입이 다 끝난 후에 데이터 사용 가능
            // 초기화 작업은 의존 관계 주입까지 완료!! 하고 호출이필요 함 -> 해당 시점은 스프링에서 초기화 시점을 알려줌.
            // 객체 생성 후 외부 수정자 주입을 통해 url 셋팅 하는 경우
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://test.com");
            return networkClient;
        }
    }
}
