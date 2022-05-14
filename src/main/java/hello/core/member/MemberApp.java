package hello.core.member;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberSerivce;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        // 순수 자바 기반
//        AppConfig appConfig = new AppConfig();
//        MemberSerivce memberSerivce = appConfig.memberSerivce();

        // Annotation Config - AppConfig에서 설정한 환경 설정 정보를 기반으로 Spring Container에 저장하겠다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberSerivce memberSerivce = applicationContext.getBean("memberService", MemberSerivce.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberSerivce.join(member);

        Member findMember = memberSerivce.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
