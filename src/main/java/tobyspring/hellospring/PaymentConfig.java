package tobyspring.hellospring;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import tobyspring.hellospring.exRate.RestTemplateExRateProvider;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.payment.PaymentService;

// Bean Factory로 Configuration을 대신하였고, 구성정보를 등록하기 위해 Configuration을 사용
// Configuration은 Bean을 생성하고 관리하는 역할을 한다.
// @ComponentScan은 @Component 어노테이션이 붙은 클래스를 스캔해서 Bean으로 등록한다.
@Configuration
public class PaymentConfig {
    // Component Object 모델로 생성과 전달을 담당
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    // 생성 담당
    // private 접근 제어자로 생성된 객체는 Bean으로 등록할 수가 없다.
    // @Bean
    // public ExRateProvider cachedExRateProvider() {
    //     return new CachedExRateProvider(exRateProvider());
    // }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
