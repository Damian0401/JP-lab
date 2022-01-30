package services;

import interfaces.IReviewerService;
import interfaces.utils.IDataContext;
import models.Record;
import utils.enums.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CommandLineReviewerService extends CommandLineService implements IReviewerService {

    public CommandLineReviewerService(IDataContext dataContext){
        super(dataContext);
    }

    @Override
    public LoginResult logIn() {
        try {
            var scanner = new Scanner(System.in);
            System.out.println("**Reviewer command line user interface**");
            System.out.println("Select action:\n1.Log in\n2.Create account");
            var selectedAction = scanner.nextInt();
            switch (selectedAction){
                case 1:
                    System.out.println("**Log in to reviewer account**");
                    return commandLineLogIn(scanner, dataContext.getReviewers());
                case 2:
                    System.out.println("**Create reviewer account**");
                    return commandLineCreateAccount(scanner, UserRole.Reviewer);
            }
            System.out.println("Entered invalid data");
            return LoginResult.Failed;

        } catch (Exception e) {
            System.out.println("Error");
            return LoginResult.Failed;
        }
    }

    @Override
    public ReviewerAction selectAction() {
        try {
            System.out.println("-".repeat(50));
            System.out.println("Select action:");
            System.out.println("1.Display records");
            System.out.println("2.Display record details");
            System.out.println("3.Review record");
            System.out.println("4.Exit");
            var scanner = new Scanner(System.in);
            var selectedOption = scanner.nextInt();
            System.out.println("-".repeat(50));
            switch (selectedOption){
                case 1:
                    return ReviewerAction.DisplayRecords;
                case 2:
                    return ReviewerAction.DisplayRecordWithId;
                case 3:
                    return ReviewerAction.ReviewRecord;
                case 4:
                    return ReviewerAction.Exit;
            }
            System.out.println("Entered invalid data");
            return ReviewerAction.Exit;
        }catch (Exception e){
            System.out.println("Error");
            return ReviewerAction.Exit;
        }
    }

    @Override
    public void displayRecords() {
        try {
            var recordsToDisplay = getSubmittedRecords();
            if (recordsToDisplay.size() == 0){
                System.out.println("There is nothing to show");
                return;
            }
            for (var record : recordsToDisplay)
                System.out.println(record.toShortString());
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @Override
    public void displayRecordWithId() {
        try {
            System.out.println("Enter id of the record that you want to display:");
            var scanner = new Scanner(System.in);
            var recordId = scanner.nextInt();
            var recordToDisplay = dataContext.getRecordById(recordId);
            var validateResult = validateRecord(recordToDisplay);
            if (validateResult == ValidationDataResult.Failed)
                return;
            System.out.println(recordToDisplay.toString());
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    @Override
    public void reviewRecord() {
        try {
            System.out.println("Enter id of the record:");
            var scanner = new Scanner(System.in);
            var recordId = scanner.nextInt();
            var recordToEdit = dataContext.getRecordById(recordId);
            var validateResult = validateRecord(recordToEdit);
            if (validateResult == ValidationDataResult.Failed)
                return;
            tryChangeRecordStatus(scanner, recordToEdit);
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    @Override
    protected ValidationDataResult validateRecord(Record recordToValidate) {
        if (recordToValidate == null){
            System.out.println("Record not found");
            return ValidationDataResult.Failed;
        }
        if (recordToValidate.getRecordStatus() != RecordStatus.Submitted){
            System.out.println("You have no permissions to see this record");
            return ValidationDataResult.Failed;
        }
        return ValidationDataResult.Success;
    }

    List<Record> getSubmittedRecords() throws Exception {
        var records = dataContext.getRecords();
        return records.stream()
                .filter(x -> x.getRecordStatus() == RecordStatus.Submitted)
                .collect(Collectors.toList());
    }

    private void tryChangeRecordStatus(Scanner scanner, Record record) throws Exception {
        System.out.println("Do you want to change record status from '"
                + record.getRecordStatus() + "'?");
        System.out.println("1.Yes");
        System.out.println("2.No");
        var selectedOption = scanner.nextInt();
        if (selectedOption != 1)
            return;
        System.out.println("Select status to set:");
        System.out.println("1.'" + RecordStatus.InReview + "'");
        System.out.println("2.'" + RecordStatus.InEdition + "'");
        System.out.println("3.Cancel");
        selectedOption = scanner.nextInt();
        if (selectedOption != 1 && selectedOption != 2)
            return;
        System.out.println("Enter comment:");
        scanner.nextLine();
        record.setComment(scanner.nextLine());
        record.setRecordStatus(selectedOption == 1
                ? RecordStatus.InReview
                : RecordStatus.InEdition);
        dataContext.updateRecord(record.getId(), record);
    }
}
