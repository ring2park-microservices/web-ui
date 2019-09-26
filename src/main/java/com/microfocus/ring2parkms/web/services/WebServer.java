package com.microfocus.ring2parkms.web.services;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Accounts web-server. Works as a microservice client, fetching data from the
 * User-Service. Uses the Discovery Server (Eureka) to find the microservice.
 *
 * @author Kevin A. Lee
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false) // Disable component scanner
public class WebServer {

	/**
	 * URL uses the logical name of account-service - upper or lower case,
	 * doesn't matter.
	 */
	public static final String USERS_SERVICE_URL = "http://USERS-SERVICE";

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 *
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for web-server.properties or web-server.yml
		System.setProperty("spring.config.name", "web-server");
		SpringApplication.run(WebServer.class, args);
	}

	/**
	 * A customized RestTemplate that has the ribbon load balancer build in.
	 * Note that prior to the "Brixton" 
	 *
	 * @return
	 */
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * The AccountService encapsulates the interaction with the micro-service.
	 *
	 * @return A new service instance.
	 */
	@Bean
	public WebUsersService usersService() {
		return new WebUsersService(USERS_SERVICE_URL);
	}

	/**
	 * Create the controller, passing it the {@link WebUsersService} to use.
	 *
	 * @return
	 */
	@Bean
	public WebUsersController usersController() {
		return new WebUsersController(usersService());
	}

	@Bean
	public HomeController homeController() {
		return new HomeController();
	}
}
