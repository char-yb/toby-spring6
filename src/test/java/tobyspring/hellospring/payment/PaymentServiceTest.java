package tobyspring.hellospring.payment;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {

    Clock clock;

    @BeforeEach
    void init() {
        // given
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    void convertedAmount() throws IOException {
        testAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000), this.clock);
        testAmount(BigDecimal.valueOf(1000), BigDecimal.valueOf(10_000), this.clock);
        testAmount(BigDecimal.valueOf(3000), BigDecimal.valueOf(30_000), this.clock);
    }

    @Test
    void validUntil() {
        PaymentService paymentService =
                new PaymentService(new ExRateProviderStub(BigDecimal.valueOf(1_000)), clock);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // valid until이 prepare() 메소드에서 30분 뒤로 설정되었기 때문에 30분 뒤의 시간을 비교한다.
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }

    // Stub을 사용하면 외부 API를 사용할 필요없고, 인터넷이 끊겨도 테스트에 대한 검증이 가능하다.
    private static void testAmount(BigDecimal exRate, BigDecimal amount, Clock clock) {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // BigDecimal에서는 isEqualTo를 사용해선 안된다. 내부 부동 소수점이나 반올림 문제로 인해 오류가 발생할 수 있다.
        // 그래서 사용되는 것이 isEqualByComparingTo이다.

        // 환율 정보 가져온다.
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        // 원화환산 금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(amount);
    }

    // 1. 오브젝트가 적절한 관계를 갖는 것이 중요.
    // 2. 관계 사이에 인터페이스를 두어 추상화된 구현체를 외부에서 결정하여, 생성자를 통해 주입하게 만드는 의존관계 주입을 사용한다.

}
