package tobyspring.hellospring.exRate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import tobyspring.hellospring.payment.ExRateProvider;

public class CachedExRateProvider implements ExRateProvider {
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
