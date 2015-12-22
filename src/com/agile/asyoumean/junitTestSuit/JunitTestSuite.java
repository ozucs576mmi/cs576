package com.agile.asyoumean.junitTestSuit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

<<<<<<< HEAD
import com.agile.asyoumean.junitTestSuit.testCase.Algorithm1Test;
import com.agile.asyoumean.junitTestSuit.testCase.AsYouMeanServiceTest;
import com.agile.asyoumean.junitTestSuit.testCase.DAOServicesTest;
=======
import com.agile.asyoumean.junitTestSuit.testCase.*;
>>>>>>> origin/master

@RunWith(Suite.class)
@Suite.SuiteClasses({ Algorithm1Test.class, AsYouMeanServiceTest.class, DAOServicesTest.class })

public class JunitTestSuite {
	
}