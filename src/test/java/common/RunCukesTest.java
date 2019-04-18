package common;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", plugin = { "pretty",
"html:target/cucumber-html-report","json:target/cucumberjson" }, glue = { "WeatherDefinations" }, tags = {})
public class RunCukesTest {

}
