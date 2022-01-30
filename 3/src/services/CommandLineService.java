package services;

import interfaces.utils.IDataContext;
import models.User;
import services.utils.Service;
import utils.enums.LoginResult;
import utils.enums.UserRole;

import java.util.List;
import java.util.Scanner;

public abstract class CommandLineService extends Service {
    public CommandLineService(IDataContext dataContext) {
        super(dataContext);
    }

    protected LoginResult commandLineLogIn(Scanner scanner, List<User> users) throws Exception {
        scanner.nextLine();
        System.out.println("Enter email:");
        var email = scanner.nextLine();
        System.out.println("Enter password:");
        var password = scanner.nextLine();
        var loginResult = tryLogIn(users, email, password);
        if (loginResult == LoginResult.Succeed)
            System.out.println("Logged in successfully.");
        else
            System.out.println("Logged in fail");
        return tryLogIn(users, email, password);
    }

    protected LoginResult commandLineCreateAccount(Scanner scanner, UserRole userRole) throws Exception {
        var userToAdd = new User();
        scanner.nextLine();
        System.out.println("Enter first name");
        userToAdd.setFirstName(scanner.nextLine());
        System.out.println("Enter last name");
        userToAdd.setLastName(scanner.nextLine());
        System.out.println("Enter email");
        userToAdd.setEmail(scanner.nextLine());
        System.out.println("Enter password");
        userToAdd.setPassword(scanner.nextLine());
        userToAdd.setUserRole(userRole);
        dataContext.addUser(userToAdd);
        currentlyLoggedUser = userToAdd;
        return LoginResult.Succeed;
    }
    
}
