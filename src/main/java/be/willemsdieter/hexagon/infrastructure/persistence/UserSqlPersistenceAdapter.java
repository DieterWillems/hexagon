package be.willemsdieter.hexagon.infrastructure.persistence;

import be.willemsdieter.hexagon.application.user.GetUserByIdPort;
import be.willemsdieter.hexagon.application.user.SaveUserPort;
import be.willemsdieter.hexagon.domain.Email;
import be.willemsdieter.hexagon.domain.Name;
import be.willemsdieter.hexagon.domain.User;
import be.willemsdieter.hexagon.domain.UserId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class UserSqlPersistenceAdapter implements SaveUserPort, GetUserByIdPort {

	private final UserRepository userRepository;

	UserSqlPersistenceAdapter(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User save(User user) {
		final var entity =  UserEntity.builder()
				.firstName(user.getName().firstName())
				.lastName(user.getName().lastName())
				.nickName(user.getName().nickName())
				.email(user.getEmail().email())
				.build();
		final var persisted = this.userRepository.save(entity);
		return User.builder().id(new UserId(persisted.getId()))
				.name(new Name(persisted.getFirstName(), persisted.getLastName(), persisted.getNickName()))
				.email(new Email(persisted.getEmail()))
				.build();
	}

	public Optional<UserDto> getById(final UserId id) {
		return this.userRepository.findById(id.value())
				.map(e -> UserDto.builder().firstName(e.getFirstName())
						.lastName(e.getLastName())
						.build());
	}
}
