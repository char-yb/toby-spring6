package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// 실제로 애플리케이션이 시작될 때 실행되는 Object로 스프링 빈 클래스로 Object가 만들어져 사용
public class PaymentService {

    // IOC 컨테이너가 주입
    private final ExRateProvider exRateProvider;

    public PaymentService(ExRateProvider exRateProvider) {
        this.exRateProvider = exRateProvider;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount)
            throws IOException {
        BigDecimal exRate = exRateProvider.getExRate(currency); // 원화 환율

        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(
                orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }
}
