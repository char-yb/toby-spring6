package tobyspring.hellospring;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {

    private final SimpleExRateProvider exRateProvider;

    public PaymentService() {
        this.exRateProvider = new SimpleExRateProvider();
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) {
        BigDecimal exRate = exRateProvider.getExRate(currency); // 원화 환율

        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(
                orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }
}
