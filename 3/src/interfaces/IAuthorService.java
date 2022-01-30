package interfaces;

import interfaces.utils.ILoggable;
import utils.enums.AuthorAction;

public interface IAuthorService extends ILoggable {
    /**
     * Select an action to perform
     * @return Enum which represents selected action
     */
    AuthorAction selectAction();
    /**
     * Display the author's records
     */
    void displayAuthorRecords();
    /**
     * Display record with based on selected id
     */
    void displayRecordById();
    /**
     * Create new record
     */
    void createRecord();
    /**
     * Edit existed record
     */
    void editRecord();
    /**
     * Submit the record
     */
    void submitRecord();
    /**
     * Delete the record
     */
    void deleteRecord();
}
