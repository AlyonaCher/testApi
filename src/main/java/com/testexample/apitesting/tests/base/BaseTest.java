package com.testexample.apitesting.tests.base;

import com.testexample.apitesting.framework.core.runner.RunningProperties;
import com.testexample.apitesting.framework.core.listener.TestNgListener;
import com.testexample.apitesting.framework.domain.domain.actions.TestDataService;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.testexample.apitesting.framework.core.logger.Log.LOG;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

@Listeners(TestNgListener.class)
public class BaseTest {
    protected TestDataService testDataService;
    protected Faker dataFaker;
    protected RequestSpecification requestSpecification;
    protected ResponseSpecification responseSpecCode200;
    protected ResponseSpecification responseSpecCode201;
    protected ResponseSpecification responseSpecIsEmptyBody;
    protected ResponseSpecification responseSpecSizeGreaterThanZero;
    protected ResponseSpecification responseSpecCodeGreater400;
    protected String endpoint = RunningProperties.getTestEnvironment().getEndpoint();

    @BeforeMethod
    public void prepareSpecification() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(endpoint)
                .build();
        LOG.info(String.format("Endpoint is: [%s]", endpoint));

        responseSpecCode200 = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

        responseSpecCode201 = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();

        responseSpecCodeGreater400 = new ResponseSpecBuilder()
                .expectStatusCode(greaterThan(400))
                .build();

        responseSpecSizeGreaterThanZero = new ResponseSpecBuilder()
                .expectBody("size()", greaterThan(0))
                .build();

        responseSpecIsEmptyBody = new ResponseSpecBuilder()
                .expectBody("isEmpty()", is(true))
                .build();

        testDataService = new TestDataService(requestSpecification);
        dataFaker = new Faker();
    }
}
