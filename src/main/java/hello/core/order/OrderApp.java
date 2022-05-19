package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberSerivce;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberSerivce memberSerivce = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        // ApplicationContext(인터페이스) - 스프링 컨테이너
        // 스프링 컨테이너는 XML을 기반으로 만들 수 있고, 에노테이션 기반의 자바 설정 클래스로 만들 수 있다.
        //
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberSerivce memberSerivce = applicationContext.getBean("memberService", MemberSerivce.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberSerivce.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 100000);

        System.out.println(order);
    }
}
