package com.model;

public class Comment {
    private String commenterName;
    private String commentText;
    private String username;


    public Comment(String commenterName, String commentText, String username) {
        this.commenterName = commenterName;
        this.commentText = commentText;
        this.username = username;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getCommentText() {
        return commentText;
    }
    
    public void setComment(String comment) {
        this.commentText = comment;
    }

    public Comment getComment() {
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
}
