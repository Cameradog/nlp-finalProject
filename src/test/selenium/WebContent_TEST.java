package test.selenium;

import org.openqa.selenium.WebDriver;

import selenium.actions.Menu_Select;
import selenium.actions.Web_Recommend_Content;

public class WebContent_TEST
{
    private Menu_Select ms;
    private Web_Recommend_Content  wrc;
    private boolean result;
    String filepath = "Web recommendation content setting";

    /**
     * Constructor
     * @param driver
     * @throws InterruptedException
     */
    public WebContent_TEST( WebDriver driver ) throws InterruptedException
    {
        ms = new Menu_Select( driver );
        wrc = new Web_Recommend_Content( driver );

        ms.Report_Manage();
        ms.Contents_manage();
        ms.Contentes_Web();

    }

    /**
     *
     * @throws InterruptedException
     */
    public void Excution( ) throws InterruptedException
    {
        WebContent_Max();

        return;
    }

    /**
     * Web recommendation content maximum registration
     * @throws InterruptedException
     */
    public void WebContent_Max() throws InterruptedException
    {
        int count = 1;
        String str = null;

        while( true )
        {
            result = wrc.Webcontent_Add();

            if( result == false )
            {
                break;
            }

            str = Integer.toString( count );

            wrc.Edit_Contentname( str );

            wrc.Content_Add();

            wrc.Edit_Content( str );

            wrc.Decide_Content();

            wrc.OK();

            //if no that there is a case in which for some reason you would click on the header part
            Thread.sleep(200);

            count++;
        }

        //The maximum number of registered
        wrc.Get_MaxNumber();

        return;
    }

    /**
     * Number of contents maximum registration
     * @throws InterruptedException
     */
    public void Content_Max() throws InterruptedException
    {
        int count = 1;
        String str = null;

        result = wrc.Webcontent_Add();

        wrc.Edit_Contentname( "MAX" );

        while( true )
        {
            result = wrc.Content_Add();

            if( result == false )
            {
                break;
            }

            str = Integer.toString( count );

            wrc.Edit_Content( str );

            wrc.Edit_Contentmemo( str );

            wrc.Decide_Content();

            //if no that there is a case in which for some reason you would click on the header part
            Thread.sleep(500);

            count++;
        }

        wrc.OK();

        return;
    }

    /**
     * Content editing is set correctly 
     * @throws InterruptedException
     */
    public void Content_Edit() throws InterruptedException
    {
        String filename = "Content editing confirmation 1.png";

        wrc.Edit_Click(1);

        wrc.Select_Sitegroup(2);

        wrc.Select_Type(1);

        wrc.Content_Edit(1);

        wrc.Edit_Contentmemo("Edit");

        wrc.Edit_Effectivedate("2011/01/01");

        wrc.Edit_Expiringdate("2014/2/2");

        wrc.Decide_Content();

        wrc.OK();

        wrc.Edit_Click(1);

        wrc.GetScreenshot( filepath, filename );

        filename = "Content editing confirmation 2.png";

        wrc.Content_Edit(1);

        wrc.GetScreenshot( filepath, filename );

        wrc.Cancel();

        return;
    }
}
