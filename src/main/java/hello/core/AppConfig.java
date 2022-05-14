package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberSerivce;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    /*
     * 역할과 구현 클래스가 한눈에 들어온다. 애플리케이션의 전체 구성이 어떻게 되어 있는지 파악이 가능하다.
     */

    public MemberSerivce memberSerivce() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private FixDiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
