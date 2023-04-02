package be.willemsdieter.hexagon.application.user;

import be.willemsdieter.hexagon.domain.UserId;
import be.willemsdieter.hexagon.infrastructure.persistence.UserDto;

import java.util.Optional;

public interface GetUserByIdPort {

	Optional<UserDto> getById(UserId id);
}
