package com.testexample.apitesting.framework.core.configuration;

import java.util.ArrayList;
import java.util.List;

import static picocli.CommandLine.*;

public class CliOptions {

    @Option(names = {"-env", "--environment"}, defaultValue = "qa", description = "Test environment to run tests")
    private static String testEnvironment;

    @Option(names = {"-t", "--testng-xml-set"},
            description = "Set of TestNG XML files, comma separated values, it is required parameter. Example: -t test1.xml,test2.xml", required = true)
    private static List<String> testNgXmlSet = new ArrayList<>();

    public String getTestEnvironment() {
        return testEnvironment;
    }

    public static List<String> getTestNgXmlSet() {
        return testNgXmlSet;
    }
}
