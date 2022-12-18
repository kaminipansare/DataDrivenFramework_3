package com.edusoln.base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTests implements IRetryAnalyzer {

	int count = 0;
	int max_retry = 2;

	@Override
	public boolean retry(ITestResult result) {
		if (count < max_retry) {
			count++;
			return true;
		}

		return false;
	}

}
