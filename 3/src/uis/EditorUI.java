package uis;

import interfaces.IAuthorService;
import interfaces.IEditorService;
import interfaces.utils.IDataContext;
import services.CommandLineAuthorService;
import services.CommandLineEditorService;
import utils.JsonDataContext;
import utils.enums.AuthorAction;
import utils.enums.EditorAction;
import utils.enums.LoginResult;

public class EditorUI {
    public static void main(String[] args) {
        IDataContext dataContext = new JsonDataContext();
        IEditorService editorService = new CommandLineEditorService(dataContext);

        run(editorService);
    }

    public static void run(IEditorService editorService){
        var loginResult = editorService.logIn();

        if (loginResult == LoginResult.Failed)
            System.exit(-1);

        var selectedAction = editorService.selectAction();

        while (selectedAction != EditorAction.Exit){
            switch (selectedAction){
                case DisplayRecords:
                    editorService.displayRecords();
                    break;
                case DisplayRecordById:
                    editorService.displayRecordById();
                    break;
                case ChangeRecordStatus:
                    editorService.changeRecordStatus();
                    break;
            }
            selectedAction = editorService.selectAction();
        }
    }
}
