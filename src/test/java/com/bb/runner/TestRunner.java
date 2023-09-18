package com.bb.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/java"},
		glue = {"com.bb.stepDefinition"},
		plugin = {"pretty","json:target/cucumber.json"}
//		tags="@FormAuthentication"
		)

public class TestRunner {

}
