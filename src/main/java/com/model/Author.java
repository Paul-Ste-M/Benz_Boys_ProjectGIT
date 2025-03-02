package com.model;

import java.util.UUID;

public class Author {
    private String name;
    private String username;
    private UUID authorID;

    public Author(String name, String username) {
        this.name = name;
        this.authorID = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public UUID getAuthorID() {
        return authorID;
    }
}
