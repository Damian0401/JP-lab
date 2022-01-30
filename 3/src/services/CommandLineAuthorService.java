package services;

import interfaces.IAuthorService;
import interfaces.utils.IDataContext;
import models.Record;
import utils.enums.*;

import java.util.Scanner;

public class CommandLineAuthorService extends CommandLineService implements IAuthorService {

    public CommandLineAuthorService(IDataContext dataContext){
        super(dataContext);
    }

    @Override
    public LoginResult logIn() {
        try {
            var scanner = new Scanner(System.in);
            System.out.println("**Author command line user interface**");
            System.out.println("Select action:\n1.Log in\n2.Create account");
            var selectedAction = scanner.nextInt();
            switch (selectedAction){
                case 1:
                    System.out.println("**Log in to author account**");
                    return commandLineLogIn(scanner, dataContext.getAuthors());
                case 2:
                    System.out.println("**Create author account**");
                    return commandLineCreateAccount(scanner, UserRole.Author);
            }
            System.out.println("Entered invalid data");
            return LoginResult.Failed;
        } catch (Exception e) {
            System.out.println("Error");
            return LoginResult.Failed;
        }
    }

    @Override
    public AuthorAction selectAction() {
        try {
            System.out.println("-".repeat(50));
            System.out.println("Select action:");
            System.out.println("1.Display records");
            System.out.println("2.Display record details");
            System.out.println("3.Create new record");
            System.out.println("4.Edit existed record");
            System.out.println("5.Submit record");
            System.out.println("6.Delete record");
            System.out.println("7.Exit");
            var scanner = new Scanner(System.in);
            var selectedOption = scanner.nextInt();
            System.out.println("-".repeat(50));
            switch (selectedOption){
                case 1:
                    return AuthorAction.DisplayAuthorRecords;
                case 2:
                    return AuthorAction.DisplayRecordById;
                case 3:
                    return AuthorAction.CreateRecord;
                case 4:
                    return AuthorAction.EditRecord;
                case 5:
                    return AuthorAction.SubmitRecord;
                case 6:
                    return AuthorAction.DeleteRecord;
                case 7:
                    return AuthorAction.Exit;
            }
            System.out.println("Entered invalid data");
            return AuthorAction.Exit;
        }catch (Exception e){
            System.out.println("Error");
            return AuthorAction.Exit;
        }
    }

    @Override
    public void displayAuthorRecords() {
        try {
            var records = dataContext.getAuthorRecords(currentlyLoggedUser.getId());
            if (records.size() == 0){
                System.out.println("There is nothing to show");
                return;
            }
            for (var record : records)
                System.out.println(record.toShortString());
        } catch (Exception e) {
            System.out.println("Error, unable to read data");
        }
    }

    @Override
    public void displayRecordById() {
        try {
            System.out.println("Enter id of the record that you want to display:");
            var scanner = new Scanner(System.in);
            var id = scanner.nextInt();
            var recordToDisplay = dataContext.getRecordById(id);
            var validationResult = validateRecord(recordToDisplay);
            if (validationResult == ValidationDataResult.Failed)
                return;
            System.out.println(recordToDisplay.toString());
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    @Override
    public void createRecord() {
        try {
            var scanner = new Scanner(System.in);
            var recordToAdd = new Record();
            System.out.println("Enter record name:");
            recordToAdd.setName(scanner.nextLine());
            System.out.println("Enter description:");
            recordToAdd.setDescription(scanner.nextLine());
            recordToAdd.setRecordStatus(RecordStatus.InEdition);
            recordToAdd.setAuthorId(currentlyLoggedUser.getId());
            dataContext.addRecord(recordToAdd);
            System.out.println("Record added successfully");
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    @Override
    public void editRecord() {
        try {
            var scanner = new Scanner(System.in);
            System.out.println("Enter id of the record that you want to edit:");
            var recordId = scanner.nextInt();
            var recordToEdit = dataContext.getRecordById(recordId);
            var validationResult = validateRecord(recordToEdit);
            if (validationResult == ValidationDataResult.Failed)
                return;
            editRecordData(scanner, recordToEdit);
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    @Override
    public void submitRecord() {
        try {
            System.out.println("Enter id of the record that you want to submit:");
            var scanner = new Scanner(System.in);
            var recordId = scanner.nextInt();
            var recordToSubmit = dataContext.getRecordById(recordId);
            var validationResult = validateRecord(recordToSubmit);
            if (validationResult == ValidationDataResult.Failed)
                return;
            if (recordToSubmit.getRecordStatus() != RecordStatus.InEdition){
                System.out.println("Record is already submitted");
                return;
            }
            recordToSubmit.setRecordStatus(RecordStatus.ReadyToSubmit);
            dataContext.updateRecord(recordId, recordToSubmit);
            System.out.println("Record submitted successfully");
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    @Override
    public void deleteRecord() {
        try {
            var scanner = new Scanner(System.in);
            System.out.println("Enter if of the record that you want to delete");
            var recordId = scanner.nextInt();
            tryDeleteRecord(recordId);
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    private void tryDeleteRecord(int recordId) throws Exception {
        var recordToDelete = dataContext.getRecordById(recordId);
        if (recordToDelete == null){
            System.out.println("Record not found");
            return;
        }
        if (recordToDelete.getAuthorId() != currentlyLoggedUser.getId()){
            System.out.println("You have no permissions to delete this record");
            return;
        }
        var isRecordDeleted = dataContext.deleteRecord(recordId);
        if(isRecordDeleted)
            System.out.println("Record deleted successfully");
        else
            System.out.println("Unable to delete record");
    }

    private void editRecordData(Scanner scanner ,Record recordToEdit) throws Exception {
        System.out.println("Do you want to edit record name?\n1.Yes\n2.No");
        var selectedOption = scanner.nextInt();
        if (selectedOption == 1){
        System.out.println("Enter new record name:");
        scanner.nextLine();
        recordToEdit.setName(scanner.nextLine());
        }
        System.out.println("Do you want to edit record description?\n1.Yes\n2.No");
        selectedOption = scanner.nextInt();
        if (selectedOption == 1){
            System.out.println("Enter new record description");
            scanner.nextLine();
            recordToEdit.setDescription(scanner.nextLine());
        }
        var response = dataContext.updateRecord(recordToEdit.getId(), recordToEdit);
        if (response)
            System.out.println("Changed saved successfully");
        else
            System.out.println("Unable to edit record");
    }

    protected ValidationDataResult validateRecord(Record recordToValidate){
        if (recordToValidate == null){
            System.out.println("Record not found");
            return ValidationDataResult.Failed;
        }
        if (recordToValidate.getAuthorId() != currentlyLoggedUser.getId()){
            System.out.println("You have no permissions to see this record");
            return ValidationDataResult.Failed;
        }
        return ValidationDataResult.Success;
    }
}
