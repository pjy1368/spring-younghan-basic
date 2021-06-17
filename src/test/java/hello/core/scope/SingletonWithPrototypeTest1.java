package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        final PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        final PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        final AnnotationConfigApplicationContext ac =
            new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        final ClientBean clientBean1 = ac.getBean(ClientBean.class);
        assertThat(clientBean1.logic()).isEqualTo(1);

        final ClientBean clientBean2 = ac.getBean(ClientBean.class);
        assertThat(clientBean2.logic()).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {

        private final Provider<PrototypeBean> provider;

        public ClientBean(Provider<PrototypeBean> provider) {
            this.provider = provider;
        }

        public int logic() {
            final PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            return prototypeBean.count;
        }

        @PostConstruct
        public void init() {
            System.out.println("ClientBean.init" + this);
        }
    }

    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy " + this);
        }
    }
}
