package be.willemsdieter.hexagon.application.common;

import java.util.Objects;
import java.util.StringJoiner;

public class Result<T> {

	private Status status;
	private T value;

	public Result(Status status, T value) {
		this.status = status;
		this.value = value;
	}

	public static <R> Result<R> success(R userId) {
		return new Result<>(Status.SUCCESS, userId);
	}

	public boolean isSuccess() {
		return this.status == Status.SUCCESS;
	}

	public T getValue() {
		return value;
	}

	public enum Status {
		SUCCESS, FAILURE
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Result<?> result = (Result<?>) o;
		return status == result.status && Objects.equals(value, result.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(status, value);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Result.class.getSimpleName() + "[", "]")
				.add("status=" + status)
				.add("value=" + value)
				.toString();
	}
}
