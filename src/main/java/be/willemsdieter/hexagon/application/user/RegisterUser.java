package be.willemsdieter.hexagon.application.user;

import be.willemsdieter.hexagon.domain.Email;
import be.willemsdieter.hexagon.domain.Name;

public record RegisterUser(Name name, Email email) {

}