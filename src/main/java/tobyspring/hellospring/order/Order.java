package tobyspring.hellospring.order;

import java.math.BigDecimal;

public class Order {

    private Long id;

    private String orderNo;

    private BigDecimal totalAmount;

    public Order() {}

    public void setId(Long id) {
        this.id = id;
    }

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
