package tobyspring.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.math.BigDecimal;

public interface ExRateExtractor {
    BigDecimal exRateExtract(String response) throws JsonProcessingException;
}
