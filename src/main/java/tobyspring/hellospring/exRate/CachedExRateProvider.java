package tobyspring.hellospring.exRate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import tobyspring.hellospring.payment.ExRateProvider;

public class CachedExRateProvider implements ExRateProvider {
    // target의 기능을 사용하는 데에 매번이 아닌 넘겨준 정보를 캐싱하겠다.
    private final ExRateProvider target;

    // 캐시된 환율 정보
    private BigDecimal cachedExRate;
    private LocalDateTime cacheExpiryTime;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        if (cachedExRate == null || cacheExpiryTime.isBefore(LocalDateTime.now())) {
            cachedExRate = this.target.getExRate(currency);
            cacheExpiryTime = LocalDateTime.now().plusSeconds(3);

            // 캐시가 업데이트 되었다.
            System.out.println("Cache updated: " + cachedExRate);
        }
        return cachedExRate;
    }
}
