package com.testexample.apitesting.framework.core.runner;

import com.testexample.apitesting.framework.core.configuration.CliOptions;
import com.testexample.apitesting.framework.core.listener.TestNgListener;
import org.testng.TestNG;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;

import static com.testexample.apitesting.framework.core.configuration.Environment.*;
import static com.testexample.apitesting.framework.core.runner.RunningProperties.*;

public class TestRunner {

    private static final String PATH_TO_TESTNG_FOLDER = ".//src//main//resources//testng//";
    private static CliOptions cliOptions = new CliOptions();

    public static void main(String... args) {
        readCliArgs(args);
        runTests();
    }

    private static void readCliArgs(String... args) {
        new CommandLine(cliOptions).parseArgs(args);
        setTestEnvironment(getEnvByValue(cliOptions.getTestEnvironment()));
    }

    private static void runTests() {
        List<String> suites = new ArrayList<>();
        cliOptions.getTestNgXmlSet()
                .forEach(element -> suites.add(PATH_TO_TESTNG_FOLDER + element));
        TestNG testNG = new TestNG();
        testNG.addListener(new TestNgListener());
        testNG.setTestSuites(suites);
        testNG.run();
    }

}
