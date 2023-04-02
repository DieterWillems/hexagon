package be.willemsdieter.hexagon.it;

import be.willemsdieter.hexagon.infrastructure.persistence.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class RegisterUserIntegrationTest {

	@Container
	public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:12")
			.withPassword("inmemory")
			.withUsername("inmemory");

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MockMvc mockMvc;

	@DynamicPropertySource
	 static void postgresqlProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
		registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
	}

	@Test
	void testRestEndpointForAllPersons() throws Exception {
		final var resourceAsStream = this.getClass().getResourceAsStream("/data/json/registerUser.json");

		this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(resourceAsStream.readAllBytes()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.notNullValue()));

	}

	//TODO Create a GET /{id} API Integration test
}
