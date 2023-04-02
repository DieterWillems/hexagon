package be.willemsdieter.hexagon.application.user;

import be.willemsdieter.hexagon.application.common.Result;
import be.willemsdieter.hexagon.domain.User;
import be.willemsdieter.hexagon.domain.UserId;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterUserUseCaseTest {

	@Mock
	private SaveUserPort saveUserPort;
	@InjectMocks
	private RegisterUserUseCase target;

	@Test
	void shouldReturnSuccessResultWhenNewUserRegistered() {
		final var data = Instancio.create(RegisterUser.class);

		final var expected = User.builder()
				.name(data.name())
				.email(data.email())
				.build();
		final var user = User.builder().id(new UserId(100L)).build();
		when(this.saveUserPort.save(expected)).thenReturn(user);

		final var result = target.handle(data);

		assertThat(result).isEqualTo(Result.success(new UserId(100L)));
	}

}