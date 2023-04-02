package be.willemsdieter.hexagon.application.user;

import be.willemsdieter.hexagon.domain.User;

public interface SaveUserPort {

	User save(User user);
}
