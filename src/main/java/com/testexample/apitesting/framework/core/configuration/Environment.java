package com.testexample.apitesting.framework.core.configuration;

import java.util.Arrays;
import java.util.Objects;

public enum Environment {

    SPRINT("spr", "https://jsonplaceholder.typicode.com"),

    QA("qa", "https://jsonplaceholder.typicode.com"),

    STAGE("stg", "https://jsonplaceholder.typicode.com"),

    PRODUCTION("prod", "https://jsonplaceholder.typicode.com");

    private String value;
    private String endpoint;

    Environment(String value, String endpoint) {
        this.value = value;
        this.endpoint = endpoint;
    }

    public static Environment getEnvByValue(String value) {
        return Arrays.stream(values())
                .filter(element -> Objects.equals(element.value, value))
                .findFirst()
                .orElseThrow();
    }

    public String getEndpoint() {
        return endpoint;
    }
}
