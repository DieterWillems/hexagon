package be.willemsdieter.hexagon.application.user;

import be.willemsdieter.hexagon.testdouble.fake.GetUserByIdPortFake;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class GetUserByIdUseCaseTest {

	@Test
	void shouldGetUserById() {
		final GetUserByIdPort getUserByIdPort = new GetUserByIdPortFake();
		final var target = new GetUserByIdUseCase(getUserByIdPort);

		final var result = target.handle(new GetUserById(10L));

		assertSoftly(assertion -> {
			assertion.assertThat(result).hasValueSatisfying(userDto -> assertThat(userDto.id()).isEqualTo(10L));
			assertion.assertThat(result).hasValueSatisfying(userDto -> assertThat(userDto.firstName()).isNotNull());
			assertion.assertThat(result).hasValueSatisfying(userDto -> assertThat(userDto.lastName()).isNotNull());
		});
	}
}