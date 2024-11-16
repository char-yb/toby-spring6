package tobyspring.hellospring.exRate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;
import tobyspring.hellospring.payment.ExRateProvider;

// 실제로 애플리케이션이 시작될 때 실행되는 Object로 스프링 빈 클래스로 Object가 만들어져 사용
// @Component
public class WebApiExRateProvider implements ExRateProvider {
    // 의존관계
    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        URI uri;
        try {
            uri = new URI(url); // 21부터는 URL가 아닌 URI를 사용
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            HttpURLConnection connection =
                    (HttpURLConnection) uri.toURL().openConnection(); // casting
            try (BufferedReader br =
                    new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = br.lines().collect(Collectors.joining());
            }
            // br.close(); // stream은 열어주면 닫아줘야 한다.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            ExRateData data = mapper.readValue(response, ExRateData.class);

            return data.rates().get("KRW");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

/** 데코레이터 디자인 패턴 오브젝트에 부가적인 기능/책임을 동적으로 부여한다. */
