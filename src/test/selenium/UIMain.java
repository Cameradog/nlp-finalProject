package test.selenium;


import org.openqa.selenium.*;

import selenium.actions.*;




/****************************************************************************************************************************
 * Main thread execution 
 * Basically invoke method calls of each class 
 ****************************************************************************************************************************/
public class UIMain
{
    static WebDriver driver = null;

	//constructor
	UIMain( )
	{

	}

	public static void uiTest() throws InterruptedException
	{
		System.out.println("Manager UI Autotest Start");
		final Common com = new Common();
		final Menu_Select ms;
		final Ads_Segment rs;
		final Admin_Setting as;
		
		driver = com.Start_driver();
		com.Set_Drive( driver );
		ms = new Menu_Select( driver );
		rs = new Ads_Segment( driver );
		//recommendationDisplay();
		//Login page
		com.Login_Asp();

		scoreGroup();
		
		System.out.println("scoreGroup complete");
		
		scoreRule();
		
		System.out.println("scoreRule complete");
		
		webRecommendContent();
		
		System.out.println("webRecommendContent complete");
		
		WebRecommendation();	
		
		System.out.println("WebRecommendation complete");
		
		Capture(com);
		
		System.out.println("capture complete");
		/*
		 * no place to import ads
		ms.Recommend_Ads();
		rs.Import();
	    rs.file_Select();
		rs.Import_File( "広告セグメント.exe" );
		*/
		com.Logout();
		//login as operator
//		authorityCheck();
//		
//		System.out.println("authorityCheck complete");
		
		//recommendationDisplay();
		driver.close();
	    //Process remains and not Chrome
        driver.quit();
		System.out.println("Manager UI Autotest Stop");
		return;
		
	}

	private static void recommendationDisplay() throws InterruptedException
	{
	    final Recommend rec = new Recommend();

	//    rec.周期調整("TEST1", 10);
	    rec.adjustment("TEST1", 10);
	    rec.adjustment("TEST2", 10);	     
	    rec.adjustment("TEST3", 10);
	    rec.adjustment("TEST4", 10);
	    rec.adjustment("TEST5", 10);
	    rec.adjustment("TEST6", 10);
	    rec.adjustment("TEST7", 10);
	    rec.adjustment("TEST8", 10);
	    rec.adjustment("TEST9", 10);
	    
	    return;
	}

	private static void scoreGroup() throws InterruptedException
	{
	    final Score_Group_TEST sgt = new Score_Group_TEST( driver );

	    sgt.Excution();
	}

	private static void scoreRule() throws InterruptedException
	{
	    final Score_Rule_TEST srt = new Score_Rule_TEST( driver );

	    srt.Excution();
	}

	private static void webRecommendContent() throws InterruptedException
	{
	    final WebContent_TEST test = new WebContent_TEST( driver );
	    test.Excution();

	    return;
	}

	private static void WebRecommendation() throws InterruptedException
	{
	    final Web_Recommend_TEST wrt = new Web_Recommend_TEST( driver );
	    final Rule_TEST rt = new Rule_TEST( driver );

	    rt.Rule_Max();
	    wrt.Excution();

	    return;
    }

	private static void authorityCheck() throws InterruptedException
	{
	    final Authority_TEST at = new Authority_TEST( driver );

	    at.Excution();

	    return;
	}

	/**
	 * For image for manual
	 * @param com
	 * @throws InterruptedException
	 */
	private static void Capture( final Common com ) throws InterruptedException
	{
		/*
	    com.Logout();

	    com.Login_Manual();
	    */

	    final Manual ml = new Manual( driver );

	    ml.Recommend_Rule();

	    return;
	}
    public static void main(String[] args) throws Exception {
    	UIMain t = new UIMain();
    	UIMain.uiTest();
    }
}