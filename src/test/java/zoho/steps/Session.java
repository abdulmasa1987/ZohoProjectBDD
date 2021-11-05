package zoho.steps;


import io.cucumber.java.en.Given;
import zoho.context.TestContext;
import zoho.pages.HomePage;
import zoho.pages.LoginPage;

public class Session {
	public TestContext context;	
	public HomePage homepage;
	public LoginPage loginpage;
	
	
	public Session(TestContext context)
	{
		System.out.println("Session Constructor");
		this.context=context;
		this.homepage=this.context.getPageObjectManager().getHomePage();
		this.loginpage=this.context.getPageObjectManager().getLoginPage();
	}

    @Given("I am logged in zoho.com")
    public void zohologin()
    {
    	context.log("I am logged in zoho.com");
    	
    	//login
    	//properties file access
    	//HomePage, LoginPage + webdrivermanger
          homepage.load();
          homepage.gotoLoginPage();
          loginpage.doLogin();
      
          
    }

   


}
