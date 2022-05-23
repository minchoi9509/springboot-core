package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberSerivce;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Spring을 이용해서 DI 컨테이너 적용하기
@Configuration
public class AppConfig {

    /*
     * 역할과 구현 클래스가 한눈에 들어온다. 애플리케이션의 전체 구성이 어떻게 되어 있는지 파악이 가능하다.
     * 싱글톤 컨테이너 - 싱글톤으로 관리되고 있는 빈.
     */

    @Bean
    public MemberSerivce memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberService");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public FixDiscountPolicy discountPolicy() {
        System.out.println("call AppConfig.discountPolicy");
        return new FixDiscountPolicy();
    }
}
