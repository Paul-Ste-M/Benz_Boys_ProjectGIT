package com.model;

/**
 * A comment made by a user on a song.
 * @author Benz Boyz
 */
public class Comment {
    private String commenterName;
    private String commentText;
    private String username;

    /**
     * Constructs a Comment object.
     *
     * @param commenterName The display name of the commenter.
     * @param commentText   The text content of the comment.
     * @param username      The username of the commenter (used internally).
     */
    public Comment(String commenterName, String commentText, String username) {
        this.commenterName = commenterName;
        this.commentText = commentText;
        this.username = username;
    }

    /**
     * Returns the display name of the commenter.
     * @return The commenter's name.
     */
    public String getCommenterName() {
        return commenterName;
    }

    /**
     * Sets the display name of the commenter.
     * @param commenterName The new commenter name.
     */
    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    /**
     * Returns the text content of the comment.
     * @return The comment text.
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     * Sets the comment text.
     * @param comment The new comment text.
     */
    public void setComment(String comment) {
        this.commentText = comment;
    }

    /**
     * Returns this Comment object
     * @return The current Comment instance.
     */
    public Comment getComment() {
        return this;
    }

    /**
     * Returns the username of the commenter.
     * @return The commenter's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the commenter.
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
