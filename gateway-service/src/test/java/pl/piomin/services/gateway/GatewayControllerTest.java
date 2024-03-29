package pl.piomin.services.gateway;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import pl.piomin.services.gateway.model.Order;
import pl.piomin.services.gateway.model.OrderStatus;

public class GatewayControllerTest {

	TestRestTemplate template = new TestRestTemplate();

	@Test
	public void testOrder() throws InterruptedException {
		for (int i = 0; i < 5; i++) {
			sendAndAcceptOrder();
			Thread.sleep(1000);
		}
	}

	private void sendAndAcceptOrder() {
		try {
			Random r = new Random();
			Order order = new Order();
			order.setCustomerId((long) r.nextInt(3) + 1);
			order.setProductIds(Arrays.asList(new Long[] { (long) r.nextInt(10) + 1, (long) r.nextInt(10) + 1 }));
			order = template.postForObject("http://localhost:8080/order/", order, Order.class);
			if (order.getStatus() != OrderStatus.REJECTED) {
				template.put("http://localhost:8080/order/{id}", null, order.getId());
			}
		} catch (Exception e) {

		}
	}
	
}
