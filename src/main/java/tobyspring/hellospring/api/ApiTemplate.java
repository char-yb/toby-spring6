package tobyspring.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {
    private final ApiExecutor apiExecutor;
    private final ExRateExtractor exRateExtractor;

    public ApiTemplate() {
        this.apiExecutor = new HttpClientApiExecutor();
        this.exRateExtractor = new ApiExRateExtractor();
    }

    public ApiTemplate(ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        this.apiExecutor = apiExecutor;
        this.exRateExtractor = exRateExtractor;
    }

    public BigDecimal getForExRate(String url) {
        return this.getForExRate(url, apiExecutor, exRateExtractor);
    }

    public BigDecimal getForExRate(String url, ApiExecutor apiExecutor) {
        return this.getForExRate(url, apiExecutor, this.exRateExtractor);
    }

    public BigDecimal getForExRate(String url, ExRateExtractor exRateExtractor) {
        return this.getForExRate(url, this.apiExecutor, exRateExtractor);
    }

    // api 서비스가 바뀌어도 로직 내부는 변함없이 사용할 수 있다. (고정 틀 같은 개념)
    // apiExecutor의 콜백 패턴에 따라 오프젝트 주입하여 템플릿을 사용
    public BigDecimal getForExRate(
            String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
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
            return exRateExtractor.exRateExtract(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
