package com.testexample.apitesting.framework.domain.domain.actions;

import io.restassured.specification.RequestSpecification;

import java.util.List;

import static com.testexample.apitesting.framework.core.testdata.GlobalConstants.COMMENTS;
import static com.testexample.apitesting.framework.core.testdata.GlobalConstants.POSTS;
import static com.testexample.apitesting.framework.core.testdata.GlobalConstants.ID_FIELD;
import static com.testexample.apitesting.framework.core.testdata.GlobalConstants.POSTS_ID_FIELD_IN_COMMENTS;
import static com.testexample.apitesting.framework.core.testdata.GlobalConstants.USERS;
import static io.restassured.RestAssured.given;

public class TestDataService {
    private final RequestSpecification requestSpecification;

    public TestDataService(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public int getNotExistingPostId() {
        return getMaxPostId() + 1;
    }

    public Integer getMaxPostId() {
        return getAllPostsId()
                .stream()
                .max(Integer::compareTo)
                .orElse(null);
    }

    public Integer getExistingPostId() {
        return getAllPostsId()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public Integer getExistingIdFromUsers() {
        return getAllUsersIds().stream()
                .findFirst()
                .orElse(null);
    }

    public Integer getPostIdWhichHasComments() {
        return getAllPostIDsFromComments()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public Integer getNumberOfCommentsForPostWithId(int postId) {
        return Math.toIntExact(getAllPostIDsFromComments()
                .stream()
                .filter(element -> element.equals(postId))
                .count());
    }

    private List<Integer> getAllPostsId() {
        return given(requestSpecification)
                .when()
                .get(POSTS)
                .then()
                .extract()
                .body()
                .path(ID_FIELD);
    }

    private List<Integer> getAllPostIDsFromComments() {
        return given(requestSpecification)
                .when()
                .get(COMMENTS)
                .then()
                .extract()
                .body()
                .path(POSTS_ID_FIELD_IN_COMMENTS);
    }

    private List<Integer> getAllUsersIds(){
        return given(requestSpecification)
                .when()
                .get(USERS)
                .then()
                .extract()
                .body()
                .path(ID_FIELD);
    }
}
