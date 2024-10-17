package tobyspring.hellospring;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {

	public Payment prepare(
		Long orderId,
		String currency,
		BigDecimal foreignCurrencyAmount
	) {
		// 1. 환율 가져오기
		// 2. 금액 계산
		// 3. 유효 시간 계산
		return new Payment(
			orderId,
			currency,
			foreignCurrencyAmount,
			BigDecimal.valueOf(1200),
			foreignCurrencyAmount.multiply(BigDecimal.valueOf(1200)),
			LocalDateTime.now()
		);
	}

	public static void main(String[] args) {
		PaymentService paymentService = new PaymentService();
		Payment payment = paymentService.prepare(
			100L,
			"USD",
			BigDecimal.valueOf(50.7)
		);
		System.out.println(payment);
	}
}
