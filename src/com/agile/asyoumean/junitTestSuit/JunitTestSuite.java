package com.agile.asyoumean.junitTestSuit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.agile.asyoumean.junitTestSuit.testCase.Algorithm1Test;
import com.agile.asyoumean.junitTestSuit.testCase.AsYouMeanServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	Algorithm1Test.class,
	AsYouMeanServiceTest.class
})
public class JunitTestSuite {   
}
