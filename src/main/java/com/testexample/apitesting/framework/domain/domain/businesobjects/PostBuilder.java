package com.testexample.apitesting.framework.domain.domain.businesobjects;

public class PostBuilder implements Builder<Post> {

    private int userId;
    private int id;
    private String title;
    private String body;

    public PostBuilder setUserId(int userId){
        this.userId = userId;
        return this;
    }

    public PostBuilder setId(int id){
        this.id = id;
        return this;
    }

    public PostBuilder setTitle(String title){
        this.title = title;
        return this;
    }

    public PostBuilder setBody(String body){
        this.body = body;
        return this;
    }

    @Override
    public Post build() {
        return new Post(userId, id, title, body);
    }
}
