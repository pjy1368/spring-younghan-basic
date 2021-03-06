package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        final AppConfig appConfig = new AppConfig();
        assertThat(appConfig.memberService()).isNotSameAs(appConfig.memberService());
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        assertThat(SingletonService.getInstance()).isSameAs(SingletonService.getInstance());
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        assertThat(context.getBean("memberService", MemberService.class))
            .isSameAs(context.getBean("memberService", MemberService.class));
    }
}
