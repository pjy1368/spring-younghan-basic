package hello.core.scan;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        final ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        assertThat(ac.getBean(MemberService.class)).isInstanceOf(MemberService.class);
    }
}
