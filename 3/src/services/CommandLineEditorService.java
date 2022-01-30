package services;

import interfaces.IEditorService;
import interfaces.utils.IDataContext;
import models.Record;
import utils.enums.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CommandLineEditorService extends CommandLineService implements IEditorService {

    public CommandLineEditorService(IDataContext dataContext){
        super(dataContext);
    }

    @Override
    public LoginResult logIn() {
        try {
            var scanner = new Scanner(System.in);
            System.out.println("**Editor command line user interface**");
            System.out.println("Select action:\n1.Log in\n2.Create account");
            var selectedAction = scanner.nextInt();
            switch (selectedAction){
                case 1:
                    System.out.println("**Log in to editor account**");
                    return commandLineLogIn(scanner, dataContext.getEditors());
                case 2:
                    System.out.println("**Create editor account**");
                    return commandLineCreateAccount(scanner, UserRole.Editor);
            }
            System.out.println("Entered invalid data");
            return LoginResult.Failed;

        } catch (Exception e) {
            System.out.println("Error");
            return LoginResult.Failed;
        }
    }

    @Override
    public EditorAction selectAction() {
        try {
            System.out.println("-".repeat(50));
            System.out.println("Select action:");
            System.out.println("1.Display records");
            System.out.println("2.Display record details");
            System.out.println("3.Change Record status");
            System.out.println("4.Exit");
            var scanner = new Scanner(System.in);
            var selectedOption = scanner.nextInt();
            System.out.println("-".repeat(50));
            switch (selectedOption){
                case 1:
                    return EditorAction.DisplayRecords;
                case 2:
                    return EditorAction.DisplayRecordById;
                case 3:
                    return EditorAction.ChangeRecordStatus;
                case 4:
                    return EditorAction.Exit;
            }
            System.out.println("Entered invalid data");
            return EditorAction.Exit;
        }catch (Exception e){
            System.out.println("Error");
            return EditorAction.Exit;
        }
    }

    @Override
    public void displayRecords() {
        try {
            var records = getRecordsReadyToSubmitOrInReview();
            if (records.size() == 0){
                System.out.println("There is nothing to show");
                return;
            }
            for (var record : records)
                System.out.println(record.toShortString());
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    @Override
    public void displayRecordById() {
        try {
            System.out.println("Enter id of the record that you want to display");
            var scanner = new Scanner(System.in);
            var recordId = scanner.nextInt();
            var recordToDisplay = dataContext.getRecordById(recordId);
            var validationResult = validateRecord(recordToDisplay);
            if (validationResult == ValidationDataResult.Failed)
                return;
            System.out.println(recordToDisplay.toString());
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    @Override
    public void changeRecordStatus() {
        try {
            System.out.println("Enter id of the record that you want to edit");
            var scanner = new Scanner(System.in);
            var recordId = scanner.nextInt();
            var recordToEdit = dataContext.getRecordById(recordId);
            var validationResult = validateRecord(recordToEdit);
            if (validationResult == ValidationDataResult.Failed)
                return;
            tryChangeRecordStatus(scanner, recordToEdit);
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    private List<Record> getRecordsReadyToSubmitOrInReview() throws Exception {
        var records =  dataContext.getRecords();
        return records.stream()
                .filter(x -> x.getRecordStatus() == RecordStatus.ReadyToSubmit
                        || x.getRecordStatus() == RecordStatus.InReview)
                .collect(Collectors.toList());
    }

    protected ValidationDataResult validateRecord(Record recordToValidate){
        if (recordToValidate == null){
            System.out.println("Record not found");
            return ValidationDataResult.Failed;
        }
        if (recordToValidate.getRecordStatus() != RecordStatus.ReadyToSubmit
        && recordToValidate.getRecordStatus() != RecordStatus.InReview){
            System.out.println("You have no permissions to see this record");
            return ValidationDataResult.Failed;
        }
        return ValidationDataResult.Success;
    }

    private void tryChangeRecordStatus(Scanner scanner, Record record) throws Exception {
        System.out.println("Do you want to change record status from '" +
                record.getRecordStatus() + "'?\n1.Yes\n2.No");
        var selectedOption = scanner.nextInt();
        if (selectedOption != 1)
            return;
        var statusToSet = record.getRecordStatus()
                == RecordStatus.ReadyToSubmit
                ? RecordStatus.Submitted
                : RecordStatus.Accepted;
        System.out.println("Select status to set:");
        System.out.println("1.'" + statusToSet + "'");
        System.out.println("2.'" + RecordStatus.InEdition + "'");
        System.out.println("3.Cancel");
        selectedOption = scanner.nextInt();
        switch (selectedOption){
            case 1:
                record.setRecordStatus(statusToSet);
                break;
            case 2:
                record.setRecordStatus(RecordStatus.InEdition);
                break;
            case 3:
                return;
            default:
                System.out.println("Entered invalid data");
                return;
        }
        dataContext.updateRecord(record.getId(), record);
        System.out.println("Changed status successfully");
    }
}
