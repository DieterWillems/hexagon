package be.willemsdieter.hexagon.testdouble.fake;

import be.willemsdieter.hexagon.application.user.SaveUserPort;
import be.willemsdieter.hexagon.domain.User;

public class UserPortFake implements SaveUserPort {

	@Override
	public User save(User user) {
		throw new UnsupportedOperationException("to be implemented");
	}
}
