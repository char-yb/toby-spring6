package tobyspring.hellospring.exRate;

import java.math.BigDecimal;
import tobyspring.hellospring.api.ApiExRateExtractor;
import tobyspring.hellospring.api.ApiTemplate;
import tobyspring.hellospring.api.HttpClientApiExecutor;
import tobyspring.hellospring.payment.ExRateProvider;

// 실제로 애플리케이션이 시작될 때 실행되는 Object로 스프링 빈 클래스로 Object가 만들어져 사용
// @Component
public class WebApiExRateProvider implements ExRateProvider {
    ApiTemplate apiTemplate = new ApiTemplate();

    // 의존관계
    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        // 콜백 인자 전달
        return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ApiExRateExtractor());
    }
}

/* 데코레이터 디자인 패턴 오브젝트에 부가적인 기능/책임을 동적으로 부여한다. */
