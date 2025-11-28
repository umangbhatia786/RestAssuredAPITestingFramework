package org.umangqa.library.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount=0;
    private static final int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("[RetryAnalyzer] Retrying test "
                    + result.getName() + " again, attempt " + (retryCount + 1));
            return true; // tell TestNG to re-run the test
        }
        return false; // no more retries
    }
}
