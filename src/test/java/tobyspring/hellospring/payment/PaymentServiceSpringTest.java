package tobyspring.hellospring.payment;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.TestObjectFactory;

// SpringExtension을 사용하면 Spring의 기능을 구성 정보로 넣을 수 있다.
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceSpringTest {

    @Autowired PaymentService paymentService;
    // 테스트를 위한 용도의 클래스를 선언하였고, 그에 따른 setter로 조작이 가능하다.
    @Autowired ExRateProviderStub exRateProviderStub;

    @Test
    void convertedAmount() throws IOException {
        // Stub에 기본적으로 세팅한 값으로 사용
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // BigDecimal에서는 isEqualTo를 사용해선 안된다. 내부 부동 소수점이나 반올림 문제로 인해 오류가 발생할 수 있다.
        // 그래서 사용되는 것이 isEqualByComparingTo이다.

        // 환율 정보 가져온다.
        assertThat(payment.getExRate()).isEqualByComparingTo(BigDecimal.valueOf(1_000));
        // 원화환산 금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));

        // exRate: 500
        // Stub에 기본적으로 세팅한 값으로 사용
        exRateProviderStub.setExRate(BigDecimal.valueOf(500));
        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // BigDecimal에서는 isEqualTo를 사용해선 안된다. 내부 부동 소수점이나 반올림 문제로 인해 오류가 발생할 수 있다.
        // 그래서 사용되는 것이 isEqualByComparingTo이다.

        // 환율 정보 가져온다.
        assertThat(payment2.getExRate()).isEqualByComparingTo(BigDecimal.valueOf(500));
        // 원화환산 금액 계산
        assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(5_000));
    }
}
