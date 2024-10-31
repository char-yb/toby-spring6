package tobyspring.hellospring.exRate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Map;

// json 데이터에서 다른 필드가 추가되더라도 무시하고 필요한 생성자에 선언된 필드만 사용한다.
@JsonIgnoreProperties(ignoreUnknown = true)
public record ExRateData(String result, Map<String, BigDecimal> rates) {}
