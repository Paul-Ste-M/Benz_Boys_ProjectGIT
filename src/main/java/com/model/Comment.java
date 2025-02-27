package com.model;

public class Comment {
    private String commenterName;
    private String comment;
    private String username;


    public Comment(String commenterName, String comment, String username) {
        this.commenterName = commenterName;
        this.comment = comment;
        this.username = username;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
}
