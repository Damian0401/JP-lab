package interfaces;

import interfaces.utils.ILoggable;
import utils.enums.EditorAction;

public interface IEditorService extends ILoggable {
    /**
     * Select an action to perform
     * @return Enum which represents selected action
     */
    EditorAction selectAction();
    /**
     * Display records with ReadyToSubmit or InReview status
     */
    void displayRecords();
    /**
     * Display record with given id
     */
    void displayRecordById();
    /**
     * Change the status of the record
     */
    void changeRecordStatus();
}
