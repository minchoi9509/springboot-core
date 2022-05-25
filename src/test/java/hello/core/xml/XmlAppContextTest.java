package hello.core.xml;

import hello.core.member.MemberSerivce;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContextTest {
    @Test
    void xmlAppContext() {

        // appConfig.xml 파일을 생성해서 구현하면 똑같음.
//        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
//        MemberSerivce memberService = ac.getBean("memberService", MemberSerivce.class);
//        Assertions.assertThat(memberService).isInstanceOf(MemberSerivce.class);
    }
}
