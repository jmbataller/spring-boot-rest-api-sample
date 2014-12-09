package com.company.capi;

import com.company.capi.Application;
import com.company.myloyal.Customer;
import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=7000", "management.port=0"})
public class ApplicationTests {

	@Value("${local.server.port}")
	int port;

	RestTemplate template = new TestRestTemplate();

	@Before
	public void setUp() {
	}

	@Test
	public void test(){

	}

	//@Test
	public void getJsonResponse() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("API_KEY", "test");
		ResponseEntity<String> response = template.exchange("http://localhost:" + port + "/customer/1/profile", HttpMethod.GET, new HttpEntity<Void>(headers), String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		System.out.println(response);
	}

	//@Test
	public void getCustomerProfile() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("API_KEY", "test");
		ResponseEntity<Customer> response = template.exchange("http://localhost:" + port + "/customer/1/profile", HttpMethod.GET, new HttpEntity<Void>(headers), Customer.class);
		assertEquals("1", response.getBody().getId());
	}
}
