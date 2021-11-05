package zoho.runner;


import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features="src/test/resources/zoho",
		glue= {"zoho.steps"},
		plugin= {"pretty","html:target/cucumber-reports.html","rerun:rerun/failed_scenarios.txt"},
		//tags="@CreateLead or @DeleteLead",
		monochrome=true
		)
public class MyRunner extends AbstractTestNGCucumberTests{

	@DataProvider(parallel=false)
	public Object[][] scnearios()
	{
		return super.scenarios();
	}
}
