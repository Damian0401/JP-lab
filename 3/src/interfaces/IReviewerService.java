package interfaces;

import interfaces.utils.ILoggable;
import utils.enums.ReviewerAction;

public interface IReviewerService extends ILoggable {
    /**
     * Select an action to perform
     * @return Enum which represents selected action
     */
    ReviewerAction selectAction();
    /**
     * Display the records with Submitted status
     */
    void displayRecords();
    /**
     * Display the record with given id
     */
    void displayRecordWithId();
    /**
     * Review the record
     */
    void reviewRecord();
}
