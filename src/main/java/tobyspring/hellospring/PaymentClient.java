package tobyspring.hellospring;

import java.math.BigDecimal;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.payment.Payment;
import tobyspring.hellospring.payment.PaymentService;

public class PaymentClient {

    public static void main(String[] args) {
        BeanFactory beanFactory =
                new AnnotationConfigApplicationContext(
                        PaymentConfig.class); // 구성 정보를 BeanFactory가 사용할 수 있도록 전달
        // PaymentService의 제어권을 BeanFactory에게 넘겨주었다.
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("Payment1: " + payment);
    }
}
