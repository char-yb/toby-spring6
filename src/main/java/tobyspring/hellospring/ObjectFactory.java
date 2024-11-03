package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.exRate.WebApiExRateProvider;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.payment.PaymentService;

// Bean Factory로 Configuration을 대신하였고,
// 구성정보를 등록하기 위해 Configuration을 사용
// Configuration은 Bean을 생성하고 관리하는 역할을 한다.
@Configuration
/*
@ComponentScan은 @Component 어노테이션이 붙은 클래스를 스캔해서 Bean으로 등록한다.
 */
// @ComponentScan
public class ObjectFactory {
    // Component Object 모델
    // 생성과 전달을 담당
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    // 생성 담당
    // private 접근 제어자로 생성된 객체는 Bean으로 등록할 수가 없다.
    /*@Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }*/

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }
}
