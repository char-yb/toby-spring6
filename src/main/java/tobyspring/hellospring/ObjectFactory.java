package tobyspring.hellospring;

public class ObjectFactory {
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    private ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }
}
