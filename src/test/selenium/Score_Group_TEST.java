package test.selenium;


import java.util.List;
import java.util.Random;

import org.openqa.selenium.*;

import selenium.actions.*;

/**
 * Test score group / score item
 *
 * @author a.toru.yano
 *
 */

public class Score_Group_TEST
{
	private final ScoreGroup_Setting sg;
	private final Menu_Select ms;
	private boolean result;
	String filepath = "Score group";


	/**
	 * Constructor
	 * @param driver
	 * @throws InterruptedException
	 */
	public Score_Group_TEST( final WebDriver driver ) throws InterruptedException
	{
		sg = new ScoreGroup_Setting( driver );
		ms = new Menu_Select( driver );

		ms.Report_Manage();
		ms.Score_Segment_manage();
		ms.Score_Group();

		result = false;
	}

	/**
	 * All test run
	 * @throws InterruptedException
	 */
	public void Excution( ) throws InterruptedException
	{

		New_TEST();

		Thread.sleep(500);
		ScoreGroup_TEST();

		Thread.sleep(500);
		ScoreItem_TEST();		
		
		Thread.sleep(500);
		Delete_Group(24);

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
	 * Score group-based test
	 * @throws InterruptedException
	 */
	public void ScoreGroup_TEST() throws InterruptedException
	{
		GroupName_Max();

		Expires_Check();

		Same_GroupName();

		GroupSet_Max();

		return;
	}

	/**
	 * Score Item system test
	 * @throws InterruptedException
	 */
	public void ScoreItem_TEST() throws InterruptedException
	{
		ItemName_Max();

		ItemSet_Error();

		ItemSet_Max();

		return;
	}

	/**
	 * Score group name that you can not enter more than 51 characters
	 * @throws InterruptedException
	 */
	public void GroupName_Max() throws InterruptedException
	{
		final String filename = "Score group name.png";			
		String over_str = "1234567890123456789012345678901234567890123456789E-------";
		String value;

		sg.Group_Add();

		sg.Edit_Groupname( over_str );

		value = Common.Element_Value( "bp_text#name", 0 );

		if( !value.equals( "1234567890123456789012345678901234567890123456789E" ) )
		{
			System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
		}

		over_str = "樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛～～～";

		sg.Edit_Groupname( over_str );

		value = Common.Element_Value( "bp_text#name", 0 );

		if( !value.equals( "樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛" ) )
		{
			System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
		}

		sg.Edit_Groupname( "樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創MAX" );

		sg.Group_OK();

		sg.GetScreenshot( filepath, filename );

		return;
	}

	/**
	 * That the same score group name can not be registered
	 * @throws InterruptedException
	 */
	public void Same_GroupName() throws InterruptedException
	{
		final String filename = "Score group name.png";			
		//@TODO : i18n
		final String error = "评分群组名称被复制";
		String str = "Same group name";

		sg.Group_Add();

		sg.Edit_Groupname( str );

		sg.Group_OK();

		//it is not too early become an error
		Thread.sleep(500);

		sg.Group_Add();

		sg.Edit_Groupname( str );

		sg.Group_OK();

		//it is not too early become an error
		Thread.sleep(500);

		result = sg.String_Check( error );

		//Error string judgment
		if( result != true )
		{
			System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
		}

		result = sg.Error_OK();

		str = "test";

		sg.Group_Cancel();

		sg.Group_Add();

		sg.Edit_Groupname( str );

		sg.Group_OK();

		sg.GetScreenshot( filepath, filename );

		return;
	}

	/**
	 * The maximum number of registrations is consistent with the maximum number of subscriber information. That you can not register any more
	 * @throws InterruptedException
	 */
	public void GroupSet_Max() throws InterruptedException
	{
		int count = 0;
		String str = null;

		while( true )
		{
			result = sg.Group_Add();

			if( result == false )
			{
				break;
			}

			str = Integer.toString( count );

			sg.Edit_Groupname( str );

			sg.Group_OK();

			//if no that there is a case in which for some reason you would click on the header part
			Thread.sleep(200);

			count++;
		}

		//The maximum number of registered check
		sg.Get_MaxNumber();

		return;
	}

	/**
	 * That a transition to the number of pages that you specify in the ENTER key directly from the input to the paging
	 * Since the Enter key is not moving for some reason so we well manually ...
	 *
	 * @throws InterruptedException
	 */
	public void Direct_Page() throws InterruptedException
	{
		String filename = "Paging directly move.png";			

		sg.Set_Page( "3" );

		sg.GetScreenshot( filepath, filename );

		sg.Set_Page( "1" );

		filename = "Paging directly move 2.png";

		sg.GetScreenshot( filepath, filename );

		sg.Set_Page( "50" );

		filename = "Paging direct move the number of pages over.png";

		sg.GetScreenshot( filepath, filename );

		return;
	}

	/**
	 * Score item name that you can not enter more than 51 characters
	 */
	public void ItemName_Max()
	{
		String over_str = "1234567890123456789012345678901234567890123456789E-------";
		String value = null;
		int index = 1;

		sg.Group_Edit( index );

		sg.Item_Add();

		sg.Edit_Scoreitem( over_str );

		value = Common.Element_Value( "bp_text#keyName", 0 );

	    sg.Itemedit_OK();

		if( !value.equals( "1234567890123456789012345678901234567890123456789E" ) )
		{
			System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
		}

		over_str = "樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛～～～";

		sg.Item_Edit(1);

		sg.Edit_Scoreitem( over_str );

		value = Common.Element_Value( "bp_text#keyName", 0 );

	    sg.Itemedit_OK();

		if( !value.equals( "樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛" ) )
		{
			System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
		}

		sg.Item_Edit(1);

		sg.Edit_Scoreitem( "樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛創新樂愛MAX" );

		sg.Itemedit_OK();

		sg.Group_Cancel();

		return;
	}

	/**
	 * The maximum number of registrations is consistent with the maximum number of subscriber information. That you can not register any more
	 * @throws InterruptedException
	 */
	public void ItemSet_Max() throws InterruptedException
	{
		int count = 0;
		String str = null;

		int index = 1;

		sg.Group_Edit( index );

		while( true )
		{
			result = sg.Item_Add();

			if( !result )
			{
				break;
			}

			str = Integer.toString( count );

			sg.Edit_Scoreitem( str );

			sg.Itemedit_OK();

			//if no that there is a case in which for some reason you would click on the header part
			Thread.sleep(500);

			count++;
		}

		/*	Wait to put the process of comparison between a maximum number here	*/

		sg.Edit_Groupname( "Maximum score item" );

		sg.Group_OK();

		return;
	}

	/**
	 * And that it is an error if not entered at the time score item creation
	 */
	public void ItemSet_Error()
	{
		//@TODO i18n
		final String error = "找不到所需信息评分项目名称";

		int index = 1;

		sg.Group_Edit( index );

		sg.Item_Add();

		sg.Itemedit_OK();

		result = sg.String_Check( error );

		//Error string judgment
		if( result != true )
		{
			System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
		}

		sg.Error_OK();

		sg.Group_Cancel();

		return;
	}

	/**
	 * Required icon that appears in the required fields
	 * That help icon is displayed on the retention period (confirmation of tool tip is not allowed)
	 * @throws InterruptedException
	 */
	public void Icon_Check() throws InterruptedException
	{
		final String filename = "Icon check.png";					//保存するファイル名

		sg.Group_Add();

		sg.GetScreenshot( filepath, filename );

		sg.Group_Cancel();

		return;
	}

	/**
	 * Retention period that is an error non-numeric of 1-365
	 * It can become an unlimited period of time if the retention period has not been input
	 * @throws InterruptedException
	 */
	public void Expires_Check() throws InterruptedException
	{
		final Random ran = new Random();
		//@TODO i18n
		final String over_error = "请输入范围为1到365 评分保留期限（天";
		final String invaild_error = "评分保留期限（天数）请输入单字节数";
		int number = 0;						//For registration number
		final int PAGE_MAX = 20;						//Number of elements because it is 10 displayed in one page up to 20
		int index = 1;							//For editing score group button
		String filename = null;				//File name to save

		//Edit button and the cancel button I bet the two as there number of items
		number = sg.Get_Number_View() * 2;

		//I use as it is that number if 20 or less
		//To random selection with up to 20 if more than 20
		if( number > PAGE_MAX )
		{
			number = PAGE_MAX;
		}

		//I choose the score group in random
		//Edit button must be odd.		

		sg.Group_Edit( index );

		sg.Edit_Expires( "366" );

		sg.Group_OK();

		result = sg.String_Check( over_error );

		//Error string judgment
		if( result != true )
		{
			System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
		}

		sg.Error_OK();

		sg.Edit_Expires( "ab%" );

		sg.Group_OK();

		result = sg.String_Check( invaild_error );

		//Error string judgment
		if( result != true )
		{
			System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
		}

		sg.Error_OK();

		sg.Edit_Expires( "0" );

		sg.Group_OK();

		result = sg.String_Check( over_error );

		//Error string judgment
		if( result != true )
		{
			System.out.println("TEST FAILED!!\n\n" + StackTrace.calledFrom( ) );
		}

		sg.Error_OK();

		//Normal value input
		sg.Edit_Expires( "365" );

		sg.Group_OK();

		filename = "Retention period 365 days.png";

		sg.GetScreenshot( filepath, filename );

		sg.Group_Edit( index );

		sg.Edit_Expires( "" );

		sg.Group_OK();

		filename = "Retention period unlimited.png";

		sg.GetScreenshot( filepath, filename );

		return;
	}

    /**
     * In order to remove it altogether
     * However, it is only to remove argument passed from the last page
     * @param number　Number you want to delete
     * @throws InterruptedException
     */
    public void Delete_Group( int number ) throws InterruptedException
    {
        List<WebElement> elms;
        int set = 0;

        sg.Loading();

        sg.Last();

        sg.Loading();

        //Until the number of deletion is zero
        while( 0 < number )
        {
            //I get the number of display of check box
            elms = sg.Wait_Elements( "x-grid-row-checker", Element_Type.CLASS );

            set = elms.size();
            
            //no element to be deleted
            if(set == 0){
            	break;
            }

            while( true )
            {
                //Break When you reach up to the number you want to or delete all checked
                if( set == 0 || number == 0 )
                {
                    break;
                }

                sg.Check_Box( set );

                set--;
                number--;
            }

            sg.Delete();

            sg.Delete_OK();

            //Up to the number you want to delete you did not reach the last page
            if( set == 0 && number != 0 )
            {
                Thread.sleep(500);

                sg.Last();
            }
        }

        return;
    }
}
