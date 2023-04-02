package be.willemsdieter.hexagon.testdouble.fake;

import be.willemsdieter.hexagon.application.user.GetUserByIdPort;
import be.willemsdieter.hexagon.domain.UserId;
import be.willemsdieter.hexagon.infrastructure.persistence.UserDto;

import java.util.Map;
import java.util.Optional;

public class GetUserByIdPortFake implements GetUserByIdPort {

	private final Map<UserId, UserDto> userData;

	public GetUserByIdPortFake() {
		userData = Map.of(new UserId(10L), wireUpUserDto());
	}

	private static UserDto wireUpUserDto() {
		return UserDto.builder()
				.firstName("firstName")
				.lastName("lastName")
				.build();
	}

	@Override
	public Optional<UserDto> getById(final UserId id) {
		return Optional.ofNullable(this.userData.get(id))
				.map(u -> UserDto.builder().id(id.value())
						.firstName(u.firstName())
						.lastName(u.lastName())
						.build());
	}
}


