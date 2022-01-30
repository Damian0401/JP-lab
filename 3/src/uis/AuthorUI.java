package uis;

import interfaces.IAuthorService;
import interfaces.utils.IDataContext;
import services.CommandLineAuthorService;
import utils.JsonDataContext;
import utils.enums.AuthorAction;
import utils.enums.LoginResult;

public class AuthorUI {
    public static void main(String[] args) {
        IDataContext dataContext = new JsonDataContext();
        IAuthorService authorService = new CommandLineAuthorService(dataContext);

        run(authorService);
    }

    public static void run(IAuthorService authorService){
        var loginResult = authorService.logIn();

        if (loginResult == LoginResult.Failed)
            System.exit(-1);

        var selectedAction = authorService.selectAction();

        while (selectedAction != AuthorAction.Exit){
            switch (selectedAction){
                case DisplayAuthorRecords:
                    authorService.displayAuthorRecords();
                    break;
                case DisplayRecordById:
                    authorService.displayRecordById();
                    break;
                case CreateRecord:
                    authorService.createRecord();
                    break;
                case EditRecord:
                    authorService.editRecord();
                    break;
                case SubmitRecord:
                    authorService.submitRecord();
                    break;
                case DeleteRecord:
                    authorService.deleteRecord();
            }
                selectedAction = authorService.selectAction();
        }
    }
}
