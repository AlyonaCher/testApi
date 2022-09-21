package com.testexample.apitesting.framework.core.runner;

import com.testexample.apitesting.framework.core.configuration.Environment;

public class RunningProperties {

    private static final Environment DEFAULT_TEST_ENV = Environment.QA;

    private static Environment testEnvironment;

    public static Environment getTestEnvironment() {
        return testEnvironment;
    }

    public static void setTestEnvironment(Environment env) {
        if (testEnvironment!=null){
            testEnvironment = env;
        } else {
            testEnvironment = DEFAULT_TEST_ENV;
        }
    }
}
