package be.willemsdieter.hexagon.infrastructure.http;

import be.willemsdieter.hexagon.application.user.GetUserByIdUseCase;
import be.willemsdieter.hexagon.application.user.RegisterUser;
import be.willemsdieter.hexagon.application.user.RegisterUserUseCase;
import be.willemsdieter.hexagon.domain.Email;
import be.willemsdieter.hexagon.domain.Name;
import be.willemsdieter.hexagon.domain.UserId;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static be.willemsdieter.hexagon.application.common.Result.success;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UsersRestController.class)
class UsersRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private RegisterUserUseCase registerUserUseCase;
	@MockBean
	private GetUserByIdUseCase getUserByIdUseCase;

	@Test
	void shouldReturnStatusOkWhenUserRegistered() throws Exception {
		final var resourceAsStream = this.getClass().getResourceAsStream("/data/json/registerUser.json");

		final var data = new RegisterUser(new Name("John", "Williams", "J-W"), new Email("john.williams@mail.com"));
		when(this.registerUserUseCase.handle(data)).thenReturn(success(new UserId(100L)));

		this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(resourceAsStream.readAllBytes()))
				.andExpect(status().isOk());
	}

	@Test
	void shouldReturnStatusBadRequestWhenUserHasNoFirstName() throws Exception {
		final var resourceAsStream = this.getClass().getResourceAsStream("/data/json/registerUser.json");
		final var jsonNode = this.objectMapper.readTree(resourceAsStream);
		((ObjectNode) jsonNode).putNull("firstName");

		this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonNode.toString()))
						.andExpect(status().isBadRequest());
	}

	@Test
	void shouldReturnStatusBadRequestWhenUserHasNoLastName() throws Exception {
		final var resourceAsStream = this.getClass().getResourceAsStream("/data/json/registerUser.json");
		final var jsonNode = this.objectMapper.readTree(resourceAsStream);
		((ObjectNode) jsonNode).putNull("lastName");

		this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonNode.toString()))
						.andExpect(status().isBadRequest());
	}

	@Test
	void shouldReturnStatusBadRequestWhenUserHasNoNickName() throws Exception {
		final var resourceAsStream = this.getClass().getResourceAsStream("/data/json/registerUser.json");
		final var jsonNode = this.objectMapper.readTree(resourceAsStream);
		((ObjectNode) jsonNode).putNull("nickName");

		this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonNode.toString()))
						.andExpect(status().isBadRequest());
	}

	@Test
	void shouldReturnStatusBadRequestWhenUserHasNoEMail() throws Exception {
		final var resourceAsStream = this.getClass().getResourceAsStream("/data/json/registerUser.json");
		final var jsonNode = this.objectMapper.readTree(resourceAsStream);
		((ObjectNode) jsonNode).putNull("email");

		this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonNode.toString()))
						.andExpect(status().isBadRequest());
	}

	@Test
	void shouldReturnStatusBadRequestWhenUserEMailIsInvalid() throws Exception {
		final var resourceAsStream = this.getClass().getResourceAsStream("/data/json/registerUser.json");
		final var jsonNode = this.objectMapper.readTree(resourceAsStream);
		((ObjectNode) jsonNode).put("email", "invalid-e-mail");

		this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonNode.toString()))
						.andExpect(status().isBadRequest());
	}

	//TODO Controller test for the GET /{id} method.
}