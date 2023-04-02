package be.willemsdieter.hexagon.infrastructure.http;

import be.willemsdieter.hexagon.application.user.GetUserById;
import be.willemsdieter.hexagon.application.user.GetUserByIdUseCase;
import be.willemsdieter.hexagon.application.user.RegisterUser;
import be.willemsdieter.hexagon.application.user.RegisterUserUseCase;
import be.willemsdieter.hexagon.domain.Email;
import be.willemsdieter.hexagon.domain.Name;
import be.willemsdieter.hexagon.domain.UserId;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UsersRestController {

	private final RegisterUserUseCase registerUserUseCase;
	private final GetUserByIdUseCase getUserByIdUseCase;

	public UsersRestController(final RegisterUserUseCase registerUserUseCase,
							   final GetUserByIdUseCase getUserByIdUseCase) {
		this.registerUserUseCase = registerUserUseCase;
		this.getUserByIdUseCase = getUserByIdUseCase;
	}

	@PostMapping
	ResponseEntity<IdModel> registerUser(@Valid @RequestBody final RegisterUserRequest request) {
		final var data = new RegisterUser(new Name(request.firstName(), request.lastName(), request.nickName()), new Email(request.email()));
		final var result = this.registerUserUseCase.handle(data);

		if (result.isSuccess()) {
			return ResponseEntity.ok(new IdModel(result.getValue().value()));
		}
		return null;
	}

	@GetMapping("/{id}")
	ResponseEntity<UserModel> getUserById(@PathVariable long id) {
		return this.getUserByIdUseCase.handle(new GetUserById(id))
				.map(user -> ResponseEntity.ok(new UserModel(user.firstName(), user.lastName())))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
