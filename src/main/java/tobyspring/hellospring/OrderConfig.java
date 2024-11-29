package tobyspring.hellospring;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import tobyspring.hellospring.data.JdbcOrderRepository;
import tobyspring.hellospring.order.OrderRepository;
import tobyspring.hellospring.order.OrderService;
import tobyspring.hellospring.order.OrderServiceImpl;

@Configuration
@Import({DataConfig.class})
// @EnableTransactionManagement 얘 없어도 됨, 왜냐하면
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }

    @Bean
    public OrderService orderService(
            PlatformTransactionManager transactionManager, OrderRepository orderRepository) {
        return new OrderServiceImpl(orderRepository);
    }
}
