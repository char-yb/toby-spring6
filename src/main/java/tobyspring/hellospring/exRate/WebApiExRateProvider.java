package tobyspring.hellospring.exRate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import tobyspring.hellospring.api.ApiExecutor;
import tobyspring.hellospring.api.SimpleApiExecutor;
import tobyspring.hellospring.payment.ExRateProvider;

// 실제로 애플리케이션이 시작될 때 실행되는 Object로 스프링 빈 클래스로 Object가 만들어져 사용
// @Component
public class WebApiExRateProvider implements ExRateProvider {
    // 의존관계
    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        // 콜백
        return runApiForExRate(url, new SimpleApiExecutor());
    }

    // api 서비스가 바뀌어도 로직 내부는 변함없이 사용할 수 있다. (고정 틀 같은 개념)
    // apiExecutor의 콜백 패턴에 따라 오프젝트 주입하여 템플릿을 사용
    private static BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor) {
        URI uri;
        try {
            uri = new URI(url); // 21부터는 URL가 아닌 URI를 사용
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return getParseKRWExRate(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static BigDecimal getParseKRWExRate(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        return data.rates().get("KRW");
    }
}

/** 데코레이터 디자인 패턴 오브젝트에 부가적인 기능/책임을 동적으로 부여한다. */
