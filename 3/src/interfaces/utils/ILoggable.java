package interfaces.utils;

import utils.enums.LoginResult;

public interface ILoggable {
    /**
     * Login user to the interface
     * @return Login result
     */
    LoginResult logIn();
}
