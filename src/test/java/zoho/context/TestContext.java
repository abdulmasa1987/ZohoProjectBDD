package zoho.context;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import zoho.managers.PageObjectManager;
import zoho.reports.ExtentManager;

public class TestContext {
	ExtentReports report;
	ExtentTest test;
	private PageObjectManager pageObjectManager;
	
	public TestContext()
	{
		System.out.println("Constructor TestContext");
		report=ExtentManager.getReports();
		this.pageObjectManager=new PageObjectManager();
		//test=report.createTest("Add Stock Test");
	
	}
	
	public PageObjectManager getPageObjectManager()
	{
		return pageObjectManager;
	}
	
	public void createscenario(String scenarioName)
	{
		test=report.createTest(scenarioName);
		this.pageObjectManager.getWebDriverManager().init(test);
	}
	
	public void endscenario()
	{
		this.pageObjectManager.getWebDriverManager().quit();
		report.flush();	
		
	}

	public void log(String msg)
	{
		this.pageObjectManager.getWebDriverManager().log(msg);
	}
}
