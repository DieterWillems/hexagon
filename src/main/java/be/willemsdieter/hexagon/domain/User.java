package be.willemsdieter.hexagon.domain;

import java.util.Objects;
import java.util.StringJoiner;

public class User {

	private final UserId id;
	private final Name name;
	private final Email email;

	private User(final Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.email = builder.email;
	}

	public UserId getId() {
		return id;
	}

	public Name getName() {
		return name;
	}

	public Email getEmail() {
		return email;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private UserId id;
		private Name name;
		private Email email;

		public Builder id(final UserId id) {
		    this.id = id;
		    return this;
		}

		public Builder name(final Name name) {
		    this.name = name;
		    return this;
		}

		public Builder email(final Email email) {
		    this.email = email;
		    return this;
		}

		public User build() {
			return new User(this);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final User user = (User) o;
		return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, email);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("name=" + name)
				.add("email=" + email)
				.toString();
	}
}
