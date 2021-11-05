package zoho.steps;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import zoho.context.TestContext;
import zoho.pages.CreateLeadPage;
import zoho.pages.LeadsDescriptionPage;
import zoho.pages.LeadsDetailPage;
import zoho.steps.data.LeadData;

public class Leads {
	
	public TestContext context;
	public LeadsDetailPage leadDetailPage;
	public CreateLeadPage createLeadPage;
	public LeadsDescriptionPage leadDescriptionPage;
	
	public Leads(TestContext context)
	{
		System.out.println("Leads Constructor");
		this.context=context;
		this.leadDetailPage=context.getPageObjectManager().getleadDetailsPage();
		this.createLeadPage=context.getPageObjectManager().getCreateLeadPage();
		this.leadDescriptionPage=context.getPageObjectManager().getLeadDescriptionPage();
	}
	
	
	@Before
	public void before(Scenario scenario)
	{
		context.createscenario(scenario.getName());
		context.log("Starting scenario "+scenario.getName());

	}
	
	
	@After
	public void after(Scenario scenario)
	{
		context.log("Ending scenario "+scenario.getName());
		context.endscenario();
	}
	
	/*
	@When("I go to {string} page")
	public void verifyLeadsPage(String pageName)
	{
		context.log("I go to "+pageName+" page");	
	}
	*/
	
	@When("I go to create lead page")
	public void goToCreateLead()
	{
		leadDetailPage.goToCreateLeadPage();	
	}
	
	
	 @And("enter and submit lead details")
	 public void submitDetails(List<LeadData> leadData) 
	 {
		 context.log("enter and submit lead details");
		 createLeadPage.submitLeadDetails(leadData);
		 
 
	 }
	 
	 @DataTableType
	 public LeadData entry(Map<String,String> entry)
	 {
		 System.out.println(entry.toString());
		 return new LeadData(entry.get("FirstName"),entry.get("LastName"),entry.get("Email"),entry.get("Company"));
	 }

	 @Then("Lead Description Page should load")
	 public void verifyLeadDetailPage()
	 {
		 leadDescriptionPage.hasLoaded();
		 
	 }
	 
	 
	 @And("I verify lead details")
	 public void verifyLeadDetails()
	 {
		 context.log("I verify lead details");	

	 }
	 
	 @Then("Lead {string} should {string} inside the grid")
	 public void verifyLeadCreation(String leadName,String condition)
	 {
		 if(condition.equals("not be present"))
			 leadDetailPage.validateLeadNotPresent(leadName);
		 else if(condition.equals("be present"))
			 leadDetailPage.validateLeadPresent(leadName);
	 }
	 
	 @When("I select the lead {string}")
	 public void selectLead(String leadName)
	 {
		 context.log("Selecting lead"+leadName);
		 leadDetailPage.selectLead(leadName);
	 }
	 
	 @And("I click on delete button")
	 public void deleteLead()
	 {
		 leadDetailPage.deletelead();
	 }
	 
	 

}
