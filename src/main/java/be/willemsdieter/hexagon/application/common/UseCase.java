package be.willemsdieter.hexagon.application.common;

public interface UseCase<T, R> {

	R handle(T data);
}
