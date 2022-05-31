package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        /*
            PrototypeBean.init
            find prototypeBean2
            PrototypeBean.init
            bean1 = hello.core.scope.PrototypeTest$PrototypeBean@61861a29
            bean2 = hello.core.scope.PrototypeTest$PrototypeBean@31024624

            bean이 서로 다른 걸 확인 할 수 있고 조회 시점(getBean)에 bean이 생성되는 것을 확인 할 수 있음.
            싱글톤 빈은 스프링 컨테이너가 관리하기 때문에 스프링 컨테이너가 종료될 때 @PreDestroy로 선언된 메소드가 호출됬지만 지금의 경우에는 호출되지 않는 것을 알 수 있다.
            -> 프로토타입 빈을 조회한 클라이언트가 관리릃 해야 한다.
            수동으로 해당 프로토 타입 bean의 destroy() 메소드를 호출해서 닫아줘야한다.
         */
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        Assertions.assertThat(bean1).isNotSameAs(bean2);
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

    }
}
