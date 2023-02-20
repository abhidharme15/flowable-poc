package com.example.fc.dto;

public class Document {
    private String id;
    private String user;
    private String content;

    public Document() {}

    public Document(String id, String user, String content) {
        this.id = id;
        this.user = user;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
