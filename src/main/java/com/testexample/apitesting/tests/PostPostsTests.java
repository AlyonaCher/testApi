package com.testexample.apitesting.tests;

import com.testexample.apitesting.framework.domain.domain.businesobjects.Post;
import com.testexample.apitesting.framework.domain.domain.businesobjects.PostBuilder;
import com.testexample.apitesting.tests.base.BaseTest;
import org.testng.annotations.Test;

import static com.testexample.apitesting.framework.core.testdata.GlobalConstants.POSTS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasValue;

public class PostPostsTests extends BaseTest {

    @Test(description = "Validate that service returns next ID after max when new post was created with no fields")
    public void validatePostPostsWithoutBody() {
        Integer expectedPostId = testDataService.getMaxPostId() + 1;

        given(requestSpecification)
                .body("")
                .when()
                .post(POSTS)
                .then()
                .assertThat()
                .spec(responseSpecCode201)
                .body("", hasValue(expectedPostId));
    }

    @Test(description = "Validate that service returns next ID after max when new post was created with all the fields except ID")
    public void validatePostPostsWithAllFieldsExceptId() {
        Integer expectedPostId = testDataService.getMaxPostId() + 1;

        Post post = new PostBuilder()
                .setUserId(testDataService.getExistingIdFromUsers())
                .setTitle(dataFaker.book().title())
                .setBody(dataFaker.weather().description())
                .build();

        given(requestSpecification)
                .body(post)
                .when()
                .post(POSTS)
                .then()
                .assertThat()
                .spec(responseSpecCode201)
                .body("", hasValue(expectedPostId));
    }

    @Test(description = "Validate that service returns next ID after max when new post was created with all the fields")
    public void validatePostPostsWithAllFields() {
        Integer expectedPostId = testDataService.getMaxPostId() + 1;

        Post post = new PostBuilder()
                .setId(dataFaker.number().randomDigitNotZero())
                .setUserId(testDataService.getExistingIdFromUsers())
                .setTitle(dataFaker.book().title())
                .setBody(dataFaker.weather().description())
                .build();

        given(requestSpecification)
                .body(post)
                .when()
                .post(POSTS)
                .then()
                .assertThat()
                .spec(responseSpecCode201)
                .body("", hasValue(expectedPostId));
    }
}
