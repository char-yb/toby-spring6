package tobyspring.hellospring.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String orderNo;

    private BigDecimal totalAmount;

    public Order() {}

    @Override
    public String toString() {
        return "Order{"
                + "id="
                + id
                + ", orderNo='"
                + orderNo
                + '\''
                + ", totalAmount="
                + totalAmount
                + '}';
    }

    public Long getId() {
        return id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Order(String orderNo, BigDecimal totalAmount) {
        this.orderNo = orderNo;
        this.totalAmount = totalAmount;
    }
}
