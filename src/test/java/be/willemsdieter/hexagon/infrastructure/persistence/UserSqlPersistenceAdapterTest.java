package be.willemsdieter.hexagon.infrastructure.persistence;

import be.willemsdieter.hexagon.domain.Email;
import be.willemsdieter.hexagon.domain.Name;
import be.willemsdieter.hexagon.domain.User;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserSqlPersistenceAdapterTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserSqlPersistenceAdapterTest.class);

	@Autowired
	private UserRepository userRepository;
	@Container
	private static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:15")
			.withDatabaseName("hyreus")
			.withUsername("postgres")
			.withPassword("pass");

	@DynamicPropertySource
	static void registerPgProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", () -> container.getJdbcUrl());
		registry.add("spring.datasource.username", () -> "postgres");
		registry.add("spring.datasource.password", () -> "pass");
	}

	@Test
	void shouldPersistUserInDatabase() {
		final var user = User.builder()
				.name(new Name("Lars", "Willems", "LW"))
				.email(new Email("Lars.W@mail.com"))
				.build();
		final var target = new UserSqlPersistenceAdapter(this.userRepository);
		target.save(user);

		final var result = this.userRepository.findAll();

		assertThat(result)
				.usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
				.containsExactly(UserEntity.builder().firstName("Lars")
						.lastName("Willems")
						.nickName("LW")
						.email("Lars.W@mail.com")
						.build());
	}

	@Test
	void shouldReturnUserIdWhenPersistedInDatabase() {
		final var user = User.builder()
				.name(new Name("Lars", "Willems", "LW"))
				.email(new Email("Lars.W@mail.com"))
				.build();
		final var target = new UserSqlPersistenceAdapter(this.userRepository);
		final var result = target.save(user);

		assertThat(result.getId()).isNotNull();
		assertThat(result)
				.usingRecursiveComparison()
				.ignoringFields("id")
				.isEqualTo(User.builder()
						.name(new Name("Lars","Willems","LW"))
						.email(new Email("Lars.W@mail.com"))
						.build());
	}

	//TODO Add a SQL findById test (mainly to check if the DTO is properly populated)
}
