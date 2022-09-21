package com.testexample.apitesting.tests;

import com.testexample.apitesting.tests.base.BaseTest;
import org.testng.annotations.Test;

import static com.testexample.apitesting.framework.core.testdata.GlobalConstants.POSTS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DeletePostsTests extends BaseTest {

    @Test(description = "Validate that service returns an empty result after post is successfully deleted")
    public void validateDeletingExistingPost() {

        int existingPostId = testDataService.getExistingPostId();

        given(requestSpecification)
                .when()
                .delete(POSTS + "/" + existingPostId)
                .then()
                .assertThat()
                .spec(responseSpecCode200)
                .spec(responseSpecIsEmptyBody);
    }

    @Test(description = "Validate that service returns an empty result after trying to delete post with non-existing ID")
    public void validateDeletingNotExistingPost() {

        int notExistingPostId = testDataService.getNotExistingPostId();

        given(requestSpecification)
                .when()
                .delete(POSTS + "/" + notExistingPostId)
                .then()
                .assertThat()
                .spec(responseSpecCode200)
                .spec(responseSpecIsEmptyBody);
    }
}
