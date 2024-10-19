package tobyspring.hellospring;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    // 주문 번호
    private Long orderId;
    // 외환 통화
    private String currency;
    // 결제 금액 (Double 타입 사용 금지)
    // 왜? Double 타입은 부동소수점 방식을 사용하며, 이는 정확한 금액을 표현할 수 없기 때문입니다.
    private BigDecimal amount;
    // 환율
    private BigDecimal exRate;
    // 결제 금액 (원화)
    private BigDecimal convertedAmount;
    // 유효기간
    private LocalDateTime validUntil;

    public Long getOrderId() {
        return orderId;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getExRate() {
        return exRate;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public Payment(
            Long orderId,
            String currency,
            BigDecimal amount,
            BigDecimal exRate,
            BigDecimal convertedAmount,
            LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.amount = amount;
        this.exRate = exRate;
        this.convertedAmount = convertedAmount;
        this.validUntil = validUntil;
    }

    @Override
    public String toString() {
        return "Payment{"
                + "orderId="
                + orderId
                + ", currency='"
                + currency
                + '\''
                + ", amount="
                + amount
                + ", exRate="
                + exRate
                + ", convertedAmount="
                + convertedAmount
                + ", validUntil="
                + validUntil
                + '}';
    }
}
