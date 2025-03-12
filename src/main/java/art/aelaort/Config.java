package art.aelaort;

import io.kubernetes.client.openapi.ApiClient;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableCaching
public class Config {
	@Bean
	public ApiClient kubernetesApiClient() throws Exception {
		ApiClient client = io.kubernetes.client.util.Config.defaultClient();
		io.kubernetes.client.openapi.Configuration.setDefaultApiClient(client);
		return client;
	}
}
