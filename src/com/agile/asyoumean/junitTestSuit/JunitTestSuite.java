package com.agile.asyoumean.junitTestSuit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.agile.asyoumean.junitTestSuit.testCase.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({ Algorithm1Test.class, AsYouMeanServiceTest.class, DAOServicesTest.class })

public class JunitTestSuite {
	
}
