package test.selenium;


import java.util.List;

import org.openqa.selenium.*;

import selenium.actions.*;

/**
 * スコアグループ/スコア項目のテスト
 * 一応、各種観点別のメソッドをpublicにしておきます。
 *
 * @author a.toru.yano
 *
 */

public class Web_Recommend_TEST
{
	private Web_Recommend wr;
	private Menu_Select ms;
	private ExtComponentFinder ecf;
	private boolean result;
	String filepath = "Web recommendation setting";


	/**
	 * Constructor
	 * @param driver
	 * @throws InterruptedException
	 */
	public Web_Recommend_TEST( WebDriver driver ) throws InterruptedException
	{
		wr = new Web_Recommend( driver );
		ms = new Menu_Select( driver );
		ecf = new ExtComponentFinder( driver );

		ms.Report_Manage();
		ms.Recommend_manage();
		ms.Recommend_Web();

		result = false;
	}

	/**
	 * All test run
	 * @throws InterruptedException
	 */
	public void Excution( ) throws InterruptedException
	{
		Thread.sleep(500);
		Position_TEST();

		return;
	}

	/**
	 * Score group-based test
	 * @throws InterruptedException
	 */
	public void Position_TEST() throws InterruptedException
	{
		
		Delete_Position( 20 );
		
	    Type_ItemCheck();

	    PositionSet_Max();

	    Delete_Position( 19 );

		return;
	}

	/**
	 * Recommendation location name that you can not enter more than 51 characters
	 * @throws InterruptedException
	 */
	public void Recommend_Name_Max() throws InterruptedException
	{
		String over_str = "1234567890123456789012345678901234567890123456789E-------";
		String value;

		wr.Position_Add();

		wr.Type_Rule();

		wr.Edit_Positionname( over_str );

		value = Common.Element_Value( "bp_text#name", 0 );

		if( !value.equals( "1234567890123456789012345678901234567890123456789E" ) )
		{
			System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
		}

		over_str = "樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛～～～";

		wr.Edit_Positionname( over_str );

		value = Common.Element_Value( "bp_text#name", 0 );

		if( !value.equals( "樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創" ) )
		{
			System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
		}

		wr.Edit_Positionname( "樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛創意樂愛" );

		wr.Position_Cancel();

		return;
	}

	/**
	 * Same recommendation location name / element ID that can not be registered
	 * @throws InterruptedException
	 */
	public void Same_PositionName() throws InterruptedException
	{
		//@TODO: i18n
		String error = "推荐名稱被复制";
		String str = "Same recommend position name";

        wr.Position_Add();

        wr.Type_Rule();

        wr.Edit_Positionname( str );

        wr.Edit_Elementid( "abeshi" );

		wr.Position_OK();

		//It is too early to become an error
		Thread.sleep(500);

        wr.Position_Add();

        wr.Type_Rule();

        wr.Edit_Positionname( str );

        wr.Edit_Elementid( "hidebu" );

        wr.Position_OK();

        //It is too early to become an error
		Thread.sleep(500);

		result = wr.String_Check( error );

		//Error string judgment
		if( result != true )
		{
			System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
		}

		wr.OK();

        wr.Edit_Positionname( "Different recommend position name" );

        wr.Edit_Elementid( "abeshi" );

        wr.Position_OK();

        //It is too early to become an error
        Thread.sleep(500);

        //@TODO: i18n
        error = "元件ID被复制";

        result = wr.String_Check( error );

        //Error string judgment
        if( result != true )
        {
            System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
        }

		wr.Position_Cancel();

		return;
	}

	/**
	 * The maximum number of registrations is consistent with the maximum number of subscriber information. That you can not register any more
	 * @throws InterruptedException
	 */
	public void PositionSet_Max() throws InterruptedException
	{
		int count = 0;
		String elementid;
		String str = null;

		while( true )
		{
	        result = wr.Position_Add();

	        elementid = "a";

			if( result == false )
			{
				break;
			}

			str = Integer.toString( count );

			elementid = elementid + str;

	        wr.Type_Rule();

	        wr.Edit_Positionname( str );

	        wr.Edit_Elementid( elementid );

	        wr.Position_OK();
			//if no that there is a case in which for some reason you would click on the header part
			Thread.sleep(200);

			count++;
		}

		//The maximum number of registered check
		wr.Get_MaxNumber();

		return;
	}

	/**
	 * Check the display item of each recommendation method
	 * @throws InterruptedException
	 */
	public void Type_ItemCheck() throws InterruptedException
	{
	    String filename = "Rule-based.png";

	    wr.Position_Add();

	    wr.Type_Rule();

	    wr.Change_Recommend();

	    wr.Type_Userauto();

	    filename = "User auto.png";

        wr.GetScreenshot( filepath, filename );

        wr.Change_Recommend();

        wr.Type_Userexternal();

        filename = "User external.png";

        wr.GetScreenshot( filepath, filename );

        wr.Change_Recommend();

        wr.Type_Itemauto();

        filename = "Item auto.png";

        wr.GetScreenshot( filepath, filename );

        wr.Change_Recommend();

        wr.Type_History();

        filename = "Browsing History.png";

        wr.GetScreenshot( filepath, filename );

        wr.Change_Recommend();

        wr.Type_Historyauto();

        filename = "Item auto on browsing History.png";

        wr.GetScreenshot( filepath, filename );

        wr.Change_Recommend();

        wr.Type_Ranking();

        filename = "Ranking.png";

        wr.GetScreenshot( filepath, filename );

        wr.Change_Recommend();

        wr.Type_Reminder();

        filename = "Reminder.png";

        wr.GetScreenshot( filepath, filename );

        wr.Change_Recommend();

        wr.Type_Bav();

        filename = "Buy after viewing.png";

        wr.GetScreenshot( filepath, filename );
        
        wr.Position_Cancel();

	    return;
	}

    /**
     * To delete summarizes the rules
     * However, it is only to remove argument minute passed from the last page
     * @param number　The number you want to delete
     * @throws InterruptedException
     */
    public void Delete_Position( int number ) throws InterruptedException
    {
        List<WebElement> elms;
        int set = 0;

        wr.Last();

        wr.Loading();

        //Until the number of deletion is zero
        while( 0 < number )
        {
            //get the number of display of check box
            elms = wr.Wait_Elements( "x-grid-row-checker", Element_Type.CLASS );

            set = elms.size();
            
            if(set == 0){
            	break;
            }

            while( true )
            {
                //Break When you reach up to the number you want to or delete all checked
                if( set == 0 || number == 0)
                {
                    break;
                }

                wr.Check_Box( set );

                set--;
                number--;
            }

            wr.Delete();

            wr.Delete_OK();

            //Up to the number you want to delete you did not reach the last page
            if( set == 0 && number != 0)
            {
                Thread.sleep(200);

                wr.Last();
            }
        }

        return;
    }

}
