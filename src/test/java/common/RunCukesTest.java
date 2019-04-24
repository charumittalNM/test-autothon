package common;



import org.junit.runner.RunWith;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.api.testng.*;

//@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", plugin = { "pretty",
"html:target/cucumber-html-report","json:target/cucumber-json-report.json" }, glue = { "WeatherDefinations" }, tags = {})
public class RunCukesTest extends AbstractTestNGCucumberTests{

	@BeforeClass
	public void setupClassName(ITestContext context) {
	context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(5);
	//context.getCurrentXmlTest().getSuite().setPreserveOrder(false);
	}
	
	@Test(dataProvider = "TestsToRun", threadPoolSize = 5, invocationCount = 1)
	public void RunCukesTest(String city) {
		
	}
	
	@DataProvider(name = "TestsToRun", parallel = true)
	public Object[][] TestsToRun() throws Exception {
//		prepareOr();
		
		Object[][] obj = new Object[][] { {"Gurugram"},{"London"},{"Delhi"},{"Pune"},{"Simla"}};
		

		return obj;
}
}
