package interfaces.utils;

import models.Record;
import models.User;

import java.util.List;

public interface IDataContext {
    /**
     * Get the list of authors from database
     * @return List of authors
     * @throws Exception Different exception based on implementations
     */
    List<User> getAuthors() throws Exception;
    /**
     * Get list of editors from database
     * @return List of editors
     * @throws Exception Different exception based on implementations
     */
    List<User> getEditors() throws Exception;
    /**
     * Get list of reviewers from database
     * @return List of reviewers
     * @throws Exception
     */
    List<User> getReviewers() throws Exception;
    /**
     * Get list of all users from database
     * @return List of users
     * @throws Exception Different exception based on implementations
     */
    List<User> getUsers() throws Exception;
    /**
     * Get list all records from database
     * @return List of records
     * @throws Exception Different exception based on implementations
     */
    List<Record> getRecords() throws Exception;
    /**
     * Get author's records
     * @param authorId Id of the author
     * @return List of author's records
     * @throws Exception Different exception based on implementations
     */
    List<Record> getAuthorRecords(int authorId) throws Exception;
    /**
     * Get records with specified id
     * @param id Id of the record
     * @return Record with specified id
     * @throws Exception Different exception based on implementations
     */
    Record getRecordById(int id) throws Exception;
    /**
     * Get users with specified id
     * @param id id of the user
     * @return User with specified id
     * @throws Exception Different exception based on implementations
     */
    User getUserById(int id) throws Exception;
    /**
     * Add a new user
     * @param user User to add 
     * @return Id of the new user
     * @throws Exception Different exception based on implementations
     */
    int addUser(User user) throws Exception;
    /**
     * Add a new record
     * @param record Record to add 
     * @return Id of the new record
     * @throws Exception Different exception based on implementations
     */
    int addRecord(Record record) throws Exception;
    /**
     * Update existed user
     * @param id Id of the user to update
     * @param user New data for user
     * @return 'true' is user is updated successfully, otherwise 'false'
     * @throws Exception Different exception based on implementations
     */
    boolean updateUser(int id, User user) throws Exception;
    /**
     * Update existed record
     * @param id Id of the record to update
     * @param record New data for record
     * @return 'true' is record is updated successfully, otherwise 'false'
     * @throws Exception Different exception based on implementations
     */
    boolean updateRecord(int id, Record record) throws Exception;

    /**
     * Delete record
     * @param id Id of the record to delete
     * @return 'true' if record is deleted successfully, otherwise 'false'
     */
    boolean deleteRecord(int id) throws Exception;
}
