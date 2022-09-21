package com.testexample.apitesting.tests;

import com.testexample.apitesting.tests.base.BaseTest;
import org.testng.annotations.Test;

import static com.testexample.apitesting.framework.core.testdata.GlobalConstants.ID_FIELD;
import static com.testexample.apitesting.framework.core.testdata.GlobalConstants.POSTS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetSpecificPostTests extends BaseTest {

    @Test(description = "Validate that service returns correct post based on its ID")
    public void validateGetWithExistingPostId() {

        Integer postId = testDataService.getExistingPostId();

        given(requestSpecification)
                .when()
                .get(POSTS + "/" + postId)
                .then()
                .assertThat()
                .spec(responseSpecCode200)
                .body(ID_FIELD, equalTo(postId));
    }

    @Test(description = "Validate that service returns an empty result for the post wth non-existing ID")
    public void validateGetWithNotExistingPostId() {

        given(requestSpecification)
                .when()
                .get(POSTS + testDataService.getNotExistingPostId())
                .then()
                .assertThat()
                .spec(responseSpecCodeGreater400)
                .spec(responseSpecIsEmptyBody);
    }
}

