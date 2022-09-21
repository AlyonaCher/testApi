package com.testexample.apitesting.tests;

import com.testexample.apitesting.tests.base.BaseTest;
import org.testng.annotations.Test;

import static com.testexample.apitesting.framework.core.testdata.GlobalConstants.COMMENTS;
import static com.testexample.apitesting.framework.core.testdata.GlobalConstants.POSTS_ID_FIELD_IN_COMMENTS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class GetCommentsForPostTests extends BaseTest {

    @Test(description = "Validate that service returns all comments for the post")
    public void validateCommentsForPostIDPositive() {

        int postIdWithComments = testDataService.getPostIdWhichHasComments();
        int numberOfComments = testDataService.getNumberOfCommentsForPostWithId(postIdWithComments);

        given(requestSpecification)
                .param(POSTS_ID_FIELD_IN_COMMENTS, postIdWithComments)
                .when()
                .get(COMMENTS)
                .then()
                .assertThat()
                .spec(responseSpecCode200)
                .body("size()", is(numberOfComments));
    }

    @Test(description = "Validate that service returns empty list of comments for the non-existing post")
    public void validateNoCommentsForNotExistingPostID() {

        int notExistingPostId = testDataService.getNotExistingPostId();

        given(requestSpecification)
                .param(POSTS_ID_FIELD_IN_COMMENTS, notExistingPostId)
                .when()
                .get(COMMENTS)
                .then()
                .assertThat()
                .spec(responseSpecCode200)
                .spec(responseSpecIsEmptyBody);
    }

}
