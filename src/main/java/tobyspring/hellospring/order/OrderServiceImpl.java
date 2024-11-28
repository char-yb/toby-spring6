package tobyspring.hellospring.order;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(String no, BigDecimal amount) {
        Order order = new Order(no, amount);
        // jdbd에서는 auto-commit이 기본값이므로 트랜잭션을 시작하지 않는대로 sql이 시작되 자동으로 트랜잭션을 생성하여 커밋을 해준다.
        this.orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> createOrders(List<OrderReq> reqs) {
        return reqs.stream().map(req -> createOrder(req.orderNo(), req.totalAmount())).toList();
    }
}
