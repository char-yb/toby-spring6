package tobyspring.hellospring;

import java.math.BigDecimal;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.order.Order;
import tobyspring.hellospring.order.OrderService;

public class OrderClient {

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        // DataTemplate dataTemplate = beanFactory.getBean(DataTemplate.class);
        OrderService orderService = beanFactory.getBean(OrderService.class);

        Order order = orderService.createOrder("1234", BigDecimal.valueOf(100.0));
        System.out.println(order);
    }
}
