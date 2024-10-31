package tobyspring.hellospring.exRate;

import java.io.IOException;
import java.math.BigDecimal;
import tobyspring.hellospring.payment.ExRateProvider;

// @Component
// 그러나 @ComponentScan 대상이 둘 이상이면  No qualifying bean of type '클래스' available: expected single
// matching bean but found 2
// 이런 에러가 발생한다.
public class SimpleExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        if (currency.equals("USD")) return BigDecimal.valueOf(1200);
        throw new IllegalArgumentException("지원되지 않는 통화: " + currency);
    }
}
