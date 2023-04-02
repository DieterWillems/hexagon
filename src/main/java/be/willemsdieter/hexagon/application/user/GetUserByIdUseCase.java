package be.willemsdieter.hexagon.application.user;

import be.willemsdieter.hexagon.application.common.UseCase;
import be.willemsdieter.hexagon.domain.UserId;
import be.willemsdieter.hexagon.infrastructure.persistence.UserDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserByIdUseCase implements UseCase<GetUserById, Optional<UserDto>> {

	private final GetUserByIdPort getUserByIdPort;

	public GetUserByIdUseCase(final GetUserByIdPort getUserByIdPort) {
		this.getUserByIdPort = getUserByIdPort;
	}

	@Override
	public Optional<UserDto> handle(final GetUserById data) {
		return getUserByIdPort.getById(new UserId(data.id()));
	}
}
