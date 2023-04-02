package be.willemsdieter.hexagon.infrastructure.http;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

record RegisterUserRequest(@NotNull String firstName,
								  @NotNull String lastName,
								  @NotNull String nickName,
								  @NotNull @Email String email) {
}
