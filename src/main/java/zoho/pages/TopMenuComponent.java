package zoho.pages;

import zoho.managers.WebDriverManager;

public class TopMenuComponent {
	WebDriverManager app;
	
	public TopMenuComponent(WebDriverManager app)
	{
		this.app=app;
	}
	
	
	public void load(String menuOption)
	{
		if(menuOption.equals("leads"))
			app.click("leads_xp");
		else if(menuOption.equals("Contacts"))
			app.click("contact_xp");
	}

}
