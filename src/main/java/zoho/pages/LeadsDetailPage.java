package zoho.pages;

import zoho.managers.WebDriverManager;

public class LeadsDetailPage {
	WebDriverManager app;
	public LeadsDetailPage(WebDriverManager app)
	{
		this.app=app;
	}
	
	
	public void goToCreateLeadPage()
	{
		app.click("leads_xp");
		app.click("createleadbutton_xp");
	}


	public void selectLead(String leadName) {
		int rowNum=app.getLeadRowNumberWithCellData(leadName);
		if(rowNum==-1)
			app.logFailure("Lead not found in the lead list",true);
		app.log("Lead found on row number-->"+rowNum);
		app.selectLeadCheckbox(rowNum);
	}
	
	public void validateLeadPresent(String leadName) {
		int row=app.getLeadRowNumberWithCellData(leadName);
		if(row==-1)
			app.logFailure("Lead not found in the lead list",true);		
	}
	
	
	public void validateLeadNotPresent(String leadName) {
		int row=app.getLeadRowNumberWithCellData(leadName);
		if(row!=-1)
			app.logFailure("Lead found in the lead list",true);		
	}


	public void deletelead() {
		app.click("actionbutton_id");
		app.click("deleteoption_xp");
		app.click("deletebutton_id");
		
	}

}
