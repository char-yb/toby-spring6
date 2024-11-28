package tobyspring.hellospring.order;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import javax.sql.DataSource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.OrderConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {

    @Autowired OrderService orderService;

    @Autowired DataSource dataSource;

    @Test
    void createOrder() {
        var order = orderService.createOrder("1234", BigDecimal.ONE);
        Assertions.assertThat(order.getId()).isGreaterThan(0);
    }

    @Test
    void createOrders() {
        List<OrderReq> orderReqs =
                List.of(
                        new OrderReq("1", BigDecimal.ONE),
                        new OrderReq("2", BigDecimal.TEN),
                        new OrderReq("3", BigDecimal.ZERO));
        var orders = orderService.createOrders(orderReqs);

        Assertions.assertThat(orders).hasSize(3);
        orders.forEach(
                order -> {
                    Assertions.assertThat(order.getId()).isGreaterThan(0);
                });
    }

    @Test
    void createDuplicatedOrders() {
        List<OrderReq> orderReqs =
                List.of(
                        new OrderReq("0300", BigDecimal.ONE),
                        new OrderReq("0300", BigDecimal.TEN),
                        new OrderReq("0300", BigDecimal.ZERO));

        assertThatThrownBy(() -> orderService.createOrders(orderReqs))
                .isInstanceOf(DataIntegrityViolationException.class);

        JdbcClient client = JdbcClient.create(dataSource);
        Long count =
                client.sql("select count(*) from orders where orderNo = '0300'")
                        .query(Long.class)
                        .single();

        // Transaction을 사용하지 않는 경우
        assertThat(count).isEqualTo(0); // 실제로는 1이다.
        // -> SQL이 하나의 트랜잭션에 묶여서 동작되지 않기 때문

    }
}
