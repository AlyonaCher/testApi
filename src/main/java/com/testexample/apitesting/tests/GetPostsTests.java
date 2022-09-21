package com.testexample.apitesting.tests;

import com.testexample.apitesting.tests.base.BaseTest;
import org.testng.annotations.Test;

import static com.testexample.apitesting.framework.core.testdata.GlobalConstants.POSTS;
import static io.restassured.RestAssured.given;

public class GetPostsTests extends BaseTest {

    @Test(description = "Validate that service returns not empty list when getting all posts")
    public void getAllPostsTest() {
        given(requestSpecification)
                .when()
                .get(POSTS)
                .then()
                .assertThat()
                .spec(responseSpecCode200)
                .spec(responseSpecSizeGreaterThanZero);
    }
}
