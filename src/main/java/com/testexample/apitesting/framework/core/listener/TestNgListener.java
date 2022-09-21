package com.testexample.apitesting.framework.core.listener;

import com.testexample.apitesting.framework.core.logger.Log;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import static java.lang.String.format;

public class TestNgListener implements ITestListener, ISuiteListener {

    public static final String LOG_SPLITTER = "==========";
    public static final String TEST_STARTED_MESSAGE_PATTERN = "%s TEST [%s] is started %s";
    public static final String TEST_FINISHED_MESSAGE_PATTERN = "%s TEST [%s] is finished %s";
    public static final String METHOD_STARTED_MESSAGE_PATTERN = "%s Method [%s] is started. Description: [%s] %s";
    public static final String METHOD_FINISHED_SUCCESS_MESSAGE_PATTERN = "%s Method [%s] is successfully finished %s";
    public static final String METHOD_SKIPPED_MESSAGE_PATTERN = "%s Method [%s] was skipped %s";
    public static final String METHOD_FAILED_MESSAGE_PATTERN = "%s Method [%s] was failed %s";

    @Override
    public void onStart(ITestContext context) {
        logResult(TEST_STARTED_MESSAGE_PATTERN, context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logResult(TEST_FINISHED_MESSAGE_PATTERN, context.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        ITestNGMethod method = iTestResult.getMethod();
        Log.LOG.info(format(METHOD_STARTED_MESSAGE_PATTERN, LOG_SPLITTER, method.getMethodName(), method.getDescription(),
                LOG_SPLITTER));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logResult(METHOD_FINISHED_SUCCESS_MESSAGE_PATTERN, iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logError(METHOD_FAILED_MESSAGE_PATTERN, iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logResult(METHOD_SKIPPED_MESSAGE_PATTERN, iTestResult.getMethod().getMethodName());
    }

    private void logResult(String pattern, String methodName) {
        Log.LOG.info(format(pattern, LOG_SPLITTER, methodName, LOG_SPLITTER));
    }

    private void logError(String pattern, String methodName) {
        Log.LOG.error(format(pattern, LOG_SPLITTER, methodName, LOG_SPLITTER));
    }

}