package services.utils;

import interfaces.utils.IDataContext;
import models.Record;
import models.User;
import utils.enums.LoginResult;
import utils.enums.ValidationDataResult;

import java.util.List;

public abstract class Service {
    protected final IDataContext dataContext;
    protected User currentlyLoggedUser;

    public Service(IDataContext dataContext){
        this.dataContext = dataContext;
    }

    public Service(IDataContext dataContext, User currentlyLoggedUser){
        this(dataContext);
        this.currentlyLoggedUser = currentlyLoggedUser;
    }

    protected LoginResult tryLogIn(List<User> users, String email, String password){
        currentlyLoggedUser = users.stream()
                .filter(x -> x.getEmail().equals(email) && x.getPassword().equals(password))
                .findAny().orElse(null);
        return currentlyLoggedUser != null ? LoginResult.Succeed : LoginResult.Failed;
    }

    protected abstract ValidationDataResult validateRecord(Record recordToValidate);
}
