package be.willemsdieter.hexagon.application.user;

import be.willemsdieter.hexagon.application.common.Result;
import be.willemsdieter.hexagon.application.common.UseCase;
import be.willemsdieter.hexagon.domain.User;
import be.willemsdieter.hexagon.domain.UserId;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase implements UseCase<RegisterUser, Result<UserId>> {

	private final SaveUserPort saveUserPort;

	public RegisterUserUseCase(final SaveUserPort saveUserPort) {
		this.saveUserPort = saveUserPort;
	}

	@Override
	public Result<UserId> handle(final RegisterUser data) {
		final var user = User.builder()
				.name(data.name())
				.email(data.email())
				.build();
		final var saved = this.saveUserPort.save(user);
		return Result.success(saved.getId());
	}
}
