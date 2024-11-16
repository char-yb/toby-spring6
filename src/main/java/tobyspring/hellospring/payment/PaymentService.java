package tobyspring.hellospring.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

// 실제로 애플리케이션이 시작될 때 실행되는 Object로 스프링 빈 클래스로 Object가 만들어져 사용
// 스프링 빈 클래스로 사용하기 위해 @Component 어노테이션을 사용
// @Service // 사실 Service 어노테이션은 @Component 어노테이션을 포함하고 있음
public class PaymentService {

    // IOC 컨테이너가 주입
    private final ExRateProvider exRateProvider;
    private final Clock clock;

    public PaymentService(ExRateProvider exRateProvider, Clock clock) {
        this.exRateProvider = exRateProvider;
        this.clock = clock;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount)
            throws IOException {
        BigDecimal exRate = exRateProvider.getExRate(currency); // 원화 환율

        return Payment.createPrepared(
                orderId, currency, foreignCurrencyAmount, exRate, LocalDateTime.now(clock));
    }
}
