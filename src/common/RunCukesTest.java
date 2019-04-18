package common;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "test/features", plugin = { "pretty",
"html:target/cucumber-html-report" }, glue = { "WeatherDefinations" }, tags = {})
public class RunCukesTest {

}
