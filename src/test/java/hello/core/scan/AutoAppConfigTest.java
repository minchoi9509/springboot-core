package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberSerivce;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        // 로그 통해서 component scan 과정을 확인 할 수 있음 - ClassPathBeanDefinitionScanner
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberSerivce memberService = ac.getBean(MemberSerivce.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberSerivce.class);
    }
}
