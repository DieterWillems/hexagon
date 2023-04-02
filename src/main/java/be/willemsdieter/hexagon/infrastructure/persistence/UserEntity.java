package be.willemsdieter.hexagon.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "User")
@Table(name = "users")
class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String email;
	@Column
	private String nickName;

	private UserEntity(final Builder builder) {
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.email = builder.email;
		this.nickName = builder.nickName;
	}

	UserEntity() {
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getNickName() {
		return nickName;
	}

	public static Builder builder() {
		return new Builder();
	}

	static class Builder {

		private long id;
		private String firstName;
		private String lastName;
		private String email;
		private String nickName;

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

		public Builder email(final String email) {
		    this.email = email;
		    return this;
		}

		public Builder nickName(final String nickName) {
		    this.nickName = nickName;
		    return this;
		}

		public UserEntity build() {
			return new UserEntity(this);
		}
	}
}
