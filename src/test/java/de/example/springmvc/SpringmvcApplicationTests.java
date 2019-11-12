package de.example.springmvc;

import de.example.springmvc.domain.Customer;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.annotation.PostConstruct;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class SpringmvcApplicationTests {

	@LocalServerPort
	private int port;

private RestTemplate restTemplate;

	@PostConstruct
	public void init() {
		restTemplate = new RestTemplateBuilder().uriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:"+port)).build();
	}

		@Test
	void contextLoads() {
			ResponseEntity<Customer> customerResponseEntity = restTemplate.getForEntity("/customers/5dc7dda28297ef37873e58cc", Customer.class);
			assertThat(customerResponseEntity.getStatusCode().value(), equalTo(200));
		}

}
