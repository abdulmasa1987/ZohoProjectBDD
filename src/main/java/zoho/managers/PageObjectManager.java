package zoho.managers;

import zoho.pages.CreateLeadPage;
import zoho.pages.HomePage;
import zoho.pages.LeadsDescriptionPage;
import zoho.pages.LeadsDetailPage;
import zoho.pages.LoginPage;
import zoho.pages.TopMenuComponent;

public class PageObjectManager {

	HomePage homepage;
	LoginPage loginpage;
	TopMenuComponent topMenu;
	LeadsDetailPage leadDetailPage;
	CreateLeadPage createLeadPage;
	LeadsDescriptionPage leadDescriptionPage;
	WebDriverManager app;
	
	public PageObjectManager()
	{
		this.app=new WebDriverManager();
	}
	
	public WebDriverManager getWebDriverManager()
	{
		return app;
	}
	
	public HomePage getHomePage()
	{
		if(homepage==null)
			homepage=new HomePage(app);
		
		return homepage;	
	}
 

	public LoginPage getLoginPage() {
		if(loginpage==null)
			loginpage=new LoginPage(app);
		
		return loginpage;		
		}
	
	public TopMenuComponent getTopMenu() {
		if(topMenu==null)
			topMenu=new TopMenuComponent(app);
		
		return topMenu;		
		}
	
	public LeadsDetailPage getleadDetailsPage() {
		if(leadDetailPage==null)
			leadDetailPage=new LeadsDetailPage(app);
		
		return leadDetailPage;		
		}
	
	
	public CreateLeadPage getCreateLeadPage() {
		if(createLeadPage==null)
			createLeadPage=new CreateLeadPage(app);
		
		return createLeadPage;		
		}
	
	
	
	public LeadsDescriptionPage getLeadDescriptionPage()
	{
		if(leadDescriptionPage==null)
			leadDescriptionPage=new LeadsDescriptionPage(app);
		
		return leadDescriptionPage;	
	}
 
}
