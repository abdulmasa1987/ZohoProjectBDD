package zoho.pages;

import zoho.managers.WebDriverManager;

public class HomePage {
	WebDriverManager app;
	
	public HomePage(WebDriverManager app)
	{
		this.app=app;
	}
	
	public void load()
	{
		app.openBrowser("Mozilla");
		app.log("Trying to load homepage after launching brower");
		app.navigate("url");
		if(!app.verifyTitle("homepagetitle"))
			app.logFailure("Titles do not match",false);
        //verify if home page is loaded		
	}
	
	public void gotoLoginPage()
	{
		//webdriver code to click on the sign link
		app.log("Trying to load login page");
		app.click("signin_link_css");
		if(!app.isElementPresent("username_id"))
			app.logFailure("Login Page did not loaded",true);
	}

}
