package tobyspring.hellospring.order;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PlatformTransactionManager transactionManager;

    public OrderService(
            OrderRepository orderRepository, PlatformTransactionManager transactionManager) {
        this.orderRepository = orderRepository;
        this.transactionManager = transactionManager;
    }

    public Order createOrder(String no, BigDecimal amount) {
        Order order = new Order(no, amount);
        // jdbd에서는 auto-commit이 기본값이므로 트랜잭션을 시작하지 않는대로 sql이 시작되 자동으로 트랜잭션을 생성하여 커밋을 해준다.
        this.orderRepository.save(order);
        return order;
    }

    public List<Order> createOrders(List<OrderReq> reqs) {
        return new TransactionTemplate(transactionManager)
                .execute(
                        status ->
                                reqs.stream()
                                        .map(req -> createOrder(req.orderNo(), req.totalAmount()))
                                        .toList());
    }
}
