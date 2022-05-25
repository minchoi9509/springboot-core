package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @Component 어노테이션이 붙은 것을 찾아서 모두 bean으로 등록 함
// @Configuration 어노테이션이 붙은 거는 제외한다는 뜻
// basePackages - 하위의 친구들만 bean으로 지정됨 / 탐색할 패키지의 시작 위치 지정 가능
// --> default: hello.core 하위에 있는(현재 파일의 위치 패키지) 모든 파일을 저장
// 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것!
// @SpringBootApplication 어노테이션에 기본적으로 @ComponentScan 어노테이션이 붙어 있기 때문에 알아서.. 컴포넌트 스캔이 제공 되고 있었던 것이었다.
@ComponentScan(
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // bean 이름 지정
//    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
