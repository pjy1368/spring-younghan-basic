package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContestInfoTest {

    final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        final String[] names = ac.getBeanDefinitionNames();
        for (final String name : names) {
            System.out.println("name = " + name + " object = " + ac.getBean(name));
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        final String[] names = ac.getBeanDefinitionNames();
        for (final String name : names) {
            final BeanDefinition beanDefinition = ac.getBeanDefinition(name);

            // Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE: 스프링 내부에서 사용하는 빈
           if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("name = " + name + " object = " + ac.getBean(name));
            }
        }
    }
}
