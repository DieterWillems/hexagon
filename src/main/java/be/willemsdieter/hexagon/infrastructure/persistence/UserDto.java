package be.willemsdieter.hexagon.infrastructure.persistence;

public record UserDto(long id, String firstName, String lastName) {

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private long id;
		private String firstName;
		private String lastName;

		public Builder id(final long id) {
		    this.id = id;
		    return this;
		}

		public Builder firstName(final String firstName) {
		    this.firstName = firstName;
		    return this;
		}

		public Builder lastName(final String lastName) {
		    this.lastName = lastName;
		    return this;
		}
		public UserDto build() {
			return new UserDto(this.id, this.firstName, this.lastName);
		}
	}
}
