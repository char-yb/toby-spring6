package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PaymentClient {

    public static void main(String[] args) throws IOException {
        BeanFactory beanFactory =
                new AnnotationConfigApplicationContext(
                        ObjectFactory.class); // 구성 정보를 BeanFactory가 사용할 수 있도록 전달
        // PaymentService의 제어권을 BeanFactory에게 넘겨주었다.
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);
        // PaymentService paymentService2 = beanFactory.getBean(PaymentService.class);
        //
        // System.out.println(paymentService);
        // System.out.println(paymentService2);
        // System.out.println(paymentService == paymentService2);
        // System.out.println(paymentService.equals(paymentService2));

        // 생성자로 호출하여 생성된 객체인데 둘이 같다고?
        // 왜지? -> ObjectFactory에 @Bean을 사용하여 객체를 생성하고 관리하기 때문에 같다.
        // 핵심. Spring Bean은 기본적으로 싱글톤이다.
        // Spring은 기본적으로 싱글톤 레지스트리로 동작하게 되어있다.
        ObjectFactory objectFactory = beanFactory.getBean(ObjectFactory.class);
        PaymentService paymentService1 = objectFactory.paymentService();
        PaymentService paymentService2 = objectFactory.paymentService();
        System.out.println(paymentService1);
        System.out.println(paymentService2);
        System.out.println(paymentService1 == paymentService2);
        System.out.println(paymentService1.equals(paymentService2));

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
