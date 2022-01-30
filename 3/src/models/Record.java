package models;

import utils.enums.RecordStatus;

public class Record {
    private int id;
    private String name;
    private String description;
    private String comment;
    private RecordStatus recordStatus;
    private int authorId;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String toShortString(){
        return "Record{" +
                "id='" + id + '\'' +
                " name='" + name + '\'' +
                ", description='" + description.substring(0, Math.min(description.length(), 5)) + "..." + '\'' +
                ", comment='" + (comment != null ? comment.substring(0, Math.min(comment.length(), 5)) + "..." : "") + '\'' +
                ", recordStatus=" + recordStatus +
                ", authorId=" + authorId +
                '}';
    }

    @Override
    public String toString() {
        return "Record{\n" +
                "id='" + id + "'\n" +
                "name='" + name + "'\n" +
                "description='" + description + "'\n" +
                "comment='" + comment + "'\n" +
                "recordStatus='" + recordStatus + "'\n" +
                "authorId=" + authorId +
                "\n}";
    }
}
