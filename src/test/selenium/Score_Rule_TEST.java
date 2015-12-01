package test.selenium;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.*;

import selenium.actions.*;

public class Score_Rule_TEST
{
	private ScoreRule_Setting sr;
	private Menu_Select ms;
	private boolean result;
	String filepath = "Score rule setting";


	/**
	 * Constructor
	 * @param driver
	 * @throws InterruptedException
	 */
	public Score_Rule_TEST( WebDriver driver ) throws InterruptedException
	{ 
		sr = new ScoreRule_Setting( driver );
		ms = new Menu_Select( driver );

		ms.Report_Manage();
		ms.Score_Segment_manage();
		ms.Score_Rule();

		result = false;
	}


	/**
	 * All test run
	 * @throws InterruptedException
	 */
	public void Excution( ) throws InterruptedException
	{
		Reffere_SiteMax();

		Rule_Set_Max();

	    Delete_Rule( 25 );	   

		return;
	}

	/**
	 * Test of NG new specification
	 * @throws InterruptedException
	 */
	public void  New_TEST() throws InterruptedException
	{
		Icon_Check();

		return;
	}

	/**
	 * Required icon that appears in the required fields
	 *
	 * @throws InterruptedException
	 */
	public void Icon_Check() throws InterruptedException
	{
		String filename = "Icon check.png";

		sr.Rule_Add();

		sr.GetScreenshot( filepath, filename );

		sr.Rule_Cancel();

		return;
	}

	/**
	 * The maximum number of registrations is consistent with the maximum number of subscriber information. That you can not register any more
	 * @throws InterruptedException
	 *
	 */
	public void ScoreRule_Max() throws InterruptedException
	{
		int count = 0;
		String str = null;

		sr.Select_Sitegroup(2);

		while( true )
		{
			result = sr.Rule_Add();

			if( result == false )
			{
				break;
			}

			str = Integer.toString( count );

			sr.Edit_Rulename( str );

			sr.Tab_Request();

			sr.Edit_Request_Site( str );

			sr.Select_Request_Site(1);
			//if no that there is a case in which for some reason you would click on the header part
			Thread.sleep(200);

			count++;
		}

		//The maximum number of registered check
		sr.Get_MaxNumber();

		return;
	}

	/**
     * Schedule setting
     */
    public void Schedul()
    {
    	sr.Tab_Schedul();

    	sr.Schedul_Month(2);

    	sr.Schedul_Month(4);

    	sr.Schedul_Month(7);

    	sr.Schedul_Day(1);

    	sr.Schedul_Day(10);

    	sr.Schedul_Day(22);

    	sr.Schedul_Day(28);
    	//@TODO: i18n
    	sr.Schedul_Week( "工作日" );

    	sr.Score_Cancel();

    	return;
    }

	/**
	 * Referrer site / page maximum number of characters confirmation
	 * @throws InterruptedException 
	 */
	public void Reffere_SiteMax() throws InterruptedException
	{
//		String filename = "リファラサイト名.png";			//保存するファイル名
		String over_str = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789E----------------------";
		String value;

		sr.Rule_Add();
		
		Thread.sleep(200);

		sr.Tab_Referre();
		
		Thread.sleep(200);

		sr.Edit_Referre_Site( over_str );
		
		Thread.sleep(200);

		value = Common.Element_Value( "bp_text#textValue", 5 );

		if( !value.equals( "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789E" ) )
		{
			System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
		}

		over_str = "樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新～～～～～～～～～";

		sr.Edit_Referre_Site( over_str );
		
		Thread.sleep(200);

		value = Common.Element_Value( "bp_text#textValue", 5 );

		if( !value.equals( "樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新" ) )
		{
			System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
		}

		/*
	     String str = "";
	     String page = "";
	     File file = new File( "1000over_han.txt" );

	     //Direct string input I input from a file because it does not cool
	     try
	     {
	         FileReader red = new FileReader( file );
	         BufferedReader br = new BufferedReader( red );

	         try
	         {
	             while( true )
	             {
	                 str = br.readLine( );

	                 if( str == null )
	                 {
	                     break;
	                 }

	                 page = page + str + "\n";
	             }

	             red.close();
	         }
	         catch( IOException e)
	         {
	             System.out.println(e);
	         }

	     }
	     catch( FileNotFoundException e )
	     {
	        return;
	     }

		sr.Edit_Referre_Page( page );

		value = Common.Element_Value( "bp_text#textValue", 6 );

		if( value.equals( page ) )
		{
			System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
		}
		 */
		sr.Score_Cancel();

		return;
	}

	/**
	 * Confirmation of referrer type
	 */
	public void Reffere_Type()
	{
		try
		{
			sr.Select_Referre( 1 );
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		sr.Rule_Cancel();

		return;
	}

	/**
	 * May only be selected which are set in the keyword table
	 * @throws InterruptedException
	 */
	public void Reffere_Keyword() throws InterruptedException
	{
		sr.Rule_Add();

		sr.Tab_Referre();

		sr.Keyord_Add();

		sr.Js_Click("bp_combo#tableValue", 7);

		sr.GetScreenshot( filepath, "Keyword table confirmation.png" );

		sr.Rule_Cancel();

		return;
	}

	/**
	 * That the same keyword table can not be set
	 * @throws InterruptedException
	 */
	public void Same_Keyword() throws InterruptedException
	{
		String filename = "Same keyword table.png";

		sr.Rule_Add();

		sr.Tab_Referre();

		sr.Keyord_Add();

		sr.Select_KeywordTable( 1 );

		//That somehow become invisible
		sr.Select_Keyword( 1 );

		sr.Decide_Keyword();

		sr.Keyord_Add();

		sr.Select_KeywordTable( 1 );

		sr.Select_Keyword( 2 );

		sr.Decide_Keyword();

		sr.GetScreenshot( filepath, filename );

		sr.Error_OK();

		return;
	}

    /**
     * Request site / page maximum number of characters confirmation
     */
    public void Request_SiteMax()
    {
//      String filename = "リファラサイト名.png";           //保存するファイル名
        String over_str = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789E----------------------";
        String value;

        sr.Rule_Add();

        sr.Tab_Request();

        sr.Edit_Referre_Site( over_str );

        value = Common.Element_Value( "bp_text#textValue", 5 );

        if( !value.equals( "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789E" ) )
        {
            System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
        }

        over_str = "樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新～～～～～～～～～";

        sr.Edit_Referre_Site( over_str );

        value = Common.Element_Value( "bp_text#textValue", 5 );

        if( !value.equals( "樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新" ) )
        {
            System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
        }

        /*
         String str = "";
         String page = "";
         File file = new File( "1000over_han.txt" );

         //直接文字列入力はイケてないのでファイルから入力する
         try
         {
             FileReader red = new FileReader( file );
             BufferedReader br = new BufferedReader( red );

             try
             {
                 while( true )
                 {
                     str = br.readLine( );

                     if( str == null )
                     {
                         break;
                     }

                     page = page + str + "\n";
                 }

                 red.close();
             }
             catch( IOException e)
             {
                 System.out.println(e);
             }

         }
         catch( FileNotFoundException e )
         {
            return;
         }

        sr.Edit_Referre_Page( page );

        value = Common.Element_Value( "bp_text#textValue", 6 );

        if( value.equals( page ) )
        {
            System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
        }
        
        */

        sr.Score_Cancel();

        return;
    }

    /**
     * May only be selected which are set in the keyword table
     * @throws InterruptedException
     */
    public void Param_Keyword() throws InterruptedException
    {
        sr.Rule_Add();

        sr.Tab_Request();

        sr.Param_Add();

        sr.Param_Radio_Table();

        sr.Js_Click( "bp_combo#tableValue", 4 );

        sr.GetScreenshot( filepath, "Parameter table confirmation.png" );

        sr.Rule_Cancel();

        return;
    }

    /**
     * Same parameter name that can not be set
     * @throws InterruptedException
     */
    public void Same_Param() throws InterruptedException
    {
        String filename = "Same parameter name.png";

        sr.Rule_Add();

        sr.Tab_Request();

        sr.Param_Add();

        sr.Edit_Paramname( "Parameters" );

        sr.Param_Radio_Table();

        //That somehow become invisible
        sr.Select_Param_Table(2);

        sr.Decide_Param();

        sr.Param_Add();

        sr.Edit_Paramname( "Parameters" );

        sr.Select_Param_Table(1);

        sr.Decide_Param();

        sr.GetScreenshot( filepath, filename );

        sr.Error_OK();

        sr.Rule_Cancel();

        return;
    }

    /**
     * Same application key name that can not be set
     * @throws InterruptedException
     */
	public void App() throws InterruptedException
	{
        String filename = "Same application key name.png";

        sr.Rule_Add();

        sr.Tab_Appli();

        sr.App_Add();

        sr.Edit_App_Name("Application");

        sr.App_Radio_Table();

        //That somehow become invisible
        sr.Select_AppTable(2);

        sr.Decide_App();

        sr.App_Add();

        sr.Edit_App_Name("Application");

        sr.App_Radio_Table();

        //That somehow become invisible
        sr.Select_AppTable(2);

        sr.Decide_App();

        sr.GetScreenshot( filepath, filename );

        sr.Error_OK();

        sr.Rule_Cancel();

        return;
	}

	/**
	 * The same score item can not be set
	 * @throws InterruptedException
	 */
	public void Same_ScoreKey() throws InterruptedException
	{
	    String filename = "Same score item name.png";

	    sr.Rule_Add();

	    sr.Scoring_Add();

	    sr.Select_ScoreGroup(3);

	    sr.Select_ScoreKey(2);

	    sr.Decide_Score();

	    sr.Scoring_Add();

        sr.Select_ScoreGroup(3);

        sr.Select_ScoreKey(2);

        sr.Decide_Score();

        sr.GetScreenshot( filepath, filename );

        sr.Error_OK();

        sr.Rule_Cancel();

	    return;
	}

	/**
     * Same score items that can not be set (dependent score)
     * @throws InterruptedException
     */
    public void Same_ScoreKey_Dependent() throws InterruptedException
    {
        String filename = "Same score item name.png";

        sr.Rule_Add();

        sr.Scoring_Add();

        sr.Select_ScoreGroup(3);

        sr.Select_ScoreKey(2);

        sr.Select_Depend_ScoreGroup(3);

        sr.Select_Depend_ScoreKey(2);

        sr.Decide_Score();

        sr.GetScreenshot( filepath, filename );

        sr.Error_OK();

        sr.Rule_Cancel();

        return;
    }

    /**
     * The maximum number of registrations is consistent with the maximum number of subscriber information. That you can not register any more
     * @throws InterruptedException
     */
    public void Rule_Set_Max() throws InterruptedException
    {
        int count = 0;
        String str = null;       

        while( true )
        {
            result = sr.Rule_Add();

            if( result == false )
            {
                break;
            }

            str = Integer.toString( count );

            sr.Edit_Rulename( str );

            sr.Tab_Appli();

            sr.App_Add();

            sr.Edit_App_Name(str);

            sr.Edit_App_Key(str);

            sr.Select_App_Key(1);

            sr.Decide_App();

            sr.Scoring_Add();

            sr.Select_ScoreGroup(1); 

            sr.Select_ScoreKey(0);

            sr.Decide_Score();

            sr.Rule_OK();

            //if no that there is a case in which for some reason you would click on the header part
            Thread.sleep(200);

            count++;
        }

        //The maximum number of registered check
        sr.Get_MaxNumber();

        return;
    }

    /**
     * To delete summarizes the rules
     * However, it is only to remove argument passed from the last page
     * @param number Number you want to delete
     * @throws InterruptedException
     */
    public void Delete_Rule( int number ) throws InterruptedException
    {
        List<WebElement> elms;
        int set = 0;

        sr.Last();

        sr.Loading();

        //Until the number of deletion is zero
        while( 0 < number )
        {
            //get the number of display of check box
            elms = sr.Wait_Elements( "x-grid-row-checker", Element_Type.CLASS );

            set = elms.size();

            while( true )
            {
                //Break When you reach up to the number you want to or delete all checked
                if( set == 0 || number == 0)
                {
                    break;
                }

                sr.Check_Box( set );

                set--;
                number--;
            }

            sr.Delete();

            sr.Delete_OK();

            //Up to the number you want to delete you did not reach the last page
            if( set == 0 && number != 0)
            {
                Thread.sleep(200);

                sr.Last();
            }
        }

        return;
    }

}
