package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import interfaces.utils.IDataContext;
import models.Record;
import models.User;
import utils.enums.RecordStatus;
import utils.enums.UserRole;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class JsonDataContext implements IDataContext {
    private String userFileName = "users.json";
    private String recordFileName = "records.json";
    private String resourcePath = "data/";
    private final ObjectMapper objectMapper;
    private final ObjectWriter objectWriter;

    public JsonDataContext(){
        objectMapper = new ObjectMapper();
        objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
    };

    public JsonDataContext(String userFileName, String recordFileName){
        this();
        this.userFileName = userFileName;
        this.recordFileName = recordFileName;
    }

    public JsonDataContext(String userFileName, String recordFileName, String resourcePath){
        this(userFileName, recordFileName);
        this.resourcePath = resourcePath;
    }

    @Override
    public List<User> getAuthors() throws Exception {
        List<User> users = getUsers();
        return users.stream()
                .filter(x -> x.getUserRole() == UserRole.Author)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getEditors() throws Exception {
        List<User> users = getUsers();
        return users.stream()
                .filter(x -> x.getUserRole() == UserRole.Editor)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getReviewers() throws Exception {
        List<User> users = getUsers();
        return users.stream()
                .filter(x -> x.getUserRole() == UserRole.Reviewer)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getUsers() throws Exception {
        return objectMapper.readValue(new File(resourcePath + userFileName), new TypeReference<>(){});
    }

    @Override
    public List<Record> getRecords() throws Exception {
        return objectMapper.readValue(new File(resourcePath + recordFileName), new TypeReference<>(){});
    }

    @Override
    public List<Record> getAuthorRecords(int authorId) throws Exception {
        return getRecords().stream()
                .filter(x -> x.getAuthorId() == authorId)
                .collect(Collectors.toList());
    }

    @Override
    public Record getRecordById(int id) throws Exception {
        var records = getRecords();
        return records.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User getUserById(int id) throws Exception {
        var users = getUsers();
        return users.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int addUser(User user) throws Exception {
        var users = getUsers();
        var maxId = getMaxUserId(users);
        user.setId(maxId + 1);
        users.add(user);
        setUsers(users);
        return user.getId();
    }

    @Override
    public int addRecord(Record record) throws Exception {
        var records = getRecords();
        var maxId = getMaxRecordId(records);
        record.setId(maxId + 1);
        records.add(record);
        setRecords(records);
        return record.getId();
    }

    @Override
    public boolean updateUser(int id, User user) throws Exception{
        var users = getUsers();
        var userToUpdate = users.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
        if (userToUpdate == null)
            return false;
        mapUser(user, userToUpdate);
        setUsers(users);
        return true;
    }

    @Override
    public boolean updateRecord(int id, Record record) throws Exception {
        var records = getRecords();
        var recordToUpdate = records.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
        if (recordToUpdate == null)
            return false;
        mapRecord(record, recordToUpdate);
        setRecords(records);
        return true;
    }

    @Override
    public boolean deleteRecord(int id) throws Exception {
        var allRecords = getRecords();
        var recordToDelete = allRecords.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
        if (recordToDelete == null)
            return false;
        allRecords.remove(recordToDelete);
        setRecords(allRecords);
        return true;
    }

    private int getMaxRecordId(List<Record> records){
        int maxId = 0;
        for(var record : records)
            if(record.getId() > maxId)
                maxId = record.getId();
        return maxId;
    }

    private int getMaxUserId(List<User> users){
        int maxId = 0;
        for(var user : users)
            if(user.getId() > maxId)
                maxId = user.getId();
        return maxId;
    }

    private void setUsers(List<User> users) throws Exception{
        objectWriter.writeValue(new File(resourcePath + userFileName), users);
    }

    private void setRecords(List<Record> records) throws Exception{
        objectWriter.writeValue(new File(resourcePath + recordFileName), records);
    }

    private void mapUser(User userFrom, User userTo){
        userTo.setFirstName(userFrom.getFirstName());
        userTo.setLastName(userFrom.getLastName());
        userTo.setEmail(userFrom.getEmail());
        userTo.setPassword(userFrom.getPassword());
        userTo.setUserRole(userFrom.getUserRole());
    }

    private void mapRecord(Record recordFrom, Record recordTo){
        recordTo.setName(recordFrom.getName());
        recordTo.setDescription(recordFrom.getDescription());
        recordTo.setRecordStatus(recordFrom.getRecordStatus());
        recordTo.setAuthorId(recordFrom.getAuthorId());
        recordTo.setComment(recordFrom.getComment());
    }
}
