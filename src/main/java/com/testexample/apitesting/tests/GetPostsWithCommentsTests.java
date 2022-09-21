package com.testexample.apitesting.tests;

import com.testexample.apitesting.tests.base.BaseTest;
import org.testng.annotations.Test;

import static com.testexample.apitesting.framework.core.testdata.GlobalConstants.ID_FIELD;
import static com.testexample.apitesting.framework.core.testdata.GlobalConstants.POSTS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GetPostsWithCommentsTests extends BaseTest {

    @Test(description = "Validate that service returns list with exact number of comments for the post")
    public void validateNumberOfCommentsForExistingPostId() {

        int postIdWithComments = testDataService.getPostIdWhichHasComments();
        int numberOfComments = testDataService.getNumberOfCommentsForPostWithId(postIdWithComments);

        given(requestSpecification)
                .pathParams(ID_FIELD, postIdWithComments)
                .when()
                .get(POSTS + "/{id}/comments")
                .then()
                .assertThat()
                .statusCode(equalTo(200))
                .body("size()", is(numberOfComments));
    }

    @Test(description = "Validate that service returns an empty result for post ID without comments")
    public void validateThatNoCommentsArePresentForNotExistingPostId() {

        int notExistingPostId = testDataService.getNotExistingPostId();

        given(requestSpecification)
                .pathParams(ID_FIELD, notExistingPostId)
                .when()
                .get(POSTS + "/{id}/comments")
                .then()
                .assertThat()
                .spec(responseSpecCode200)
                .spec(responseSpecIsEmptyBody);
    }

}
