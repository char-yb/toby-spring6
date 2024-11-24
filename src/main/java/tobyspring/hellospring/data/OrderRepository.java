package tobyspring.hellospring.data;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;
import tobyspring.hellospring.DataTemplate;
import tobyspring.hellospring.order.Order;

@Repository
public class OrderRepository {

    private final DataTemplate dataTemplate;

    public OrderRepository(EntityManagerFactory emf) {
        this.dataTemplate = new DataTemplate(emf);
    }

    public void save(Order order) {
        dataTemplate.save(order);
    }
}
