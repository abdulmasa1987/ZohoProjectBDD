package zoho.pages;

import zoho.managers.WebDriverManager;

public class LoginPage {
	WebDriverManager app;
	
	public LoginPage(WebDriverManager app)
	{
		this.app=app;
	}
	
	public void doLogin()
	{
		//login to the site
		//success        - ModuleSelectionPage
		//not successful - remain at login page
	 app.log("Trying to login to ZOHO.COM");	
	 app.type("username_id",app.getProperty("userid"));
	 app.click("nextbutton_xp");
	 app.type("password_name",app.getProperty("password"));
	 app.click("signinbutton_xp");
	 app.click("crm_xp");
	 
	}

}
