package pl.piomin.services.account;

import org.springframework.cloud.client.discovery.event.InstancePreRegisteredEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:strongant.bai@baozun.com">strongant.bai@baozun.com</a>
 * @since 2022/11/7
 */
@Component
public class WarmUpComponent implements ApplicationListener<InstancePreRegisteredEvent> {


	@Override
	public void onApplicationEvent(InstancePreRegisteredEvent instancePreRegisteredEvent) {
		try {
			System.out.println("instancePreRegisteredEvent start = " + instancePreRegisteredEvent.toString());
			Thread.sleep(5000L);
			System.out.println("instancePreRegisteredEvent end = " + instancePreRegisteredEvent.toString());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
