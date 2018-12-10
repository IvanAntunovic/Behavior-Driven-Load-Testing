package test_runners;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Features",
		glue = "tests",
		format = {"pretty", "html:target/cucumber"})
public class CucumberRunner {
	
}
