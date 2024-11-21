package tobyspring.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {
    // api 서비스가 바뀌어도 로직 내부는 변함없이 사용할 수 있다. (고정 틀 같은 개념)
    // apiExecutor의 콜백 패턴에 따라 오프젝트 주입하여 템플릿을 사용
    public BigDecimal getExRate(
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
