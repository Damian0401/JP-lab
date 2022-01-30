package uis;

import interfaces.IReviewerService;
import interfaces.utils.IDataContext;
import services.CommandLineReviewerService;
import utils.JsonDataContext;
import utils.enums.LoginResult;
import utils.enums.ReviewerAction;

public class ReviewerUI {
    public static void main(String[] args) {
        IDataContext dataContext = new JsonDataContext();
        IReviewerService reviewerService = new CommandLineReviewerService(dataContext);

        run(reviewerService);
    }

    public static void run(IReviewerService reviewerService){
        var loginResult = reviewerService.logIn();

        if (loginResult == LoginResult.Failed)
            System.exit(-1);

        var selectedAction = reviewerService.selectAction();

        while (selectedAction != ReviewerAction.Exit){
            switch (selectedAction){
                case DisplayRecords:
                    reviewerService.displayRecords();
                    break;
                case ReviewRecord:
                    reviewerService.reviewRecord();
                    break;
                case DisplayRecordWithId:
                    reviewerService.displayRecordWithId();
                    break;
            }
            selectedAction = reviewerService.selectAction();
        }
    }
}
