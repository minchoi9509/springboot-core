package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        assertThat(bean1.getCount()).isEqualTo(1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        assertThat(bean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean bean1 = ac.getBean(ClientBean.class);
        int count1 = bean1.logic();
        assertThat(count1).isEqualTo(1);

        // prototype bean이기 때문에 왠지 2가 아니라 새로운 프로토타입 타입의 bean이 생성되어서 1이 나와야 할거 같지만 아니다.......!
        // 우리의 의도한 바가 아니다!
        ClientBean bean2 = ac.getBean(ClientBean.class);
        int count2 = bean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    // @RequiredArgsConstructor
    static class ClientBean {
        // 생성 시점에 주입되어 계속 동일한 bean을 반환함.
        private final PrototypeBean prototypeBean;

        // DI <-> DL: 직접 필요한 의존관계를 찾는 것 -- spring 컨테이너에 직접! 질의하는게 아니라 (ApplicationContext 주입 받아서 하는게 아니라 얘를 통해서 간접적으로 질의)
        // 항상 새로운 프로토타입 빈이 생성되는 것을 확인 할 수 있음
        @Autowired
        // private ObjectProvider<PrototypeBean> prototypeBeanProvider; // 상속되었기 때문에 좀 더 기능이 있지만 그래도 스프링에 의존적임.
        // private ObjectFactory<PrototypeBean> prototypeBeanProvider; // 기능이 단순, 별도의 라이브러리 없음. 스프링에 의존적임
        private Provider<PrototypeBean> prototypeBeanProvider; // 자바 표준. get() 메서드 하나로 기능이 매우 단순! 자바 표준이니까 스프링이 아닌 다른 컨테이너에 사용 가능!

        @Autowired
        ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            // PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }


    // 프로토타입 빈
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy" + this);
        }
    }
}
