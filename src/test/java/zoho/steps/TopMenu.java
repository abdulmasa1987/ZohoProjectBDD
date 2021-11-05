package zoho.steps;

import io.cucumber.java.en.And;
import zoho.context.TestContext;
import zoho.pages.TopMenuComponent;

public class TopMenu {
	public TestContext context;
	public TopMenuComponent topMenu;
	
	public TopMenu(TestContext context)
	{
		System.out.println("TopMenu Constructor");
		this.context=context;
		this.topMenu=this.context.getPageObjectManager().getTopMenu();
	}
	
	
	@And("I click on {string} in top menu")
		public void loadPage(String menuOption) throws InterruptedException
		{
			context.log("I click on "+menuOption+" in top menu");
			topMenu.load(menuOption);
		}

}
