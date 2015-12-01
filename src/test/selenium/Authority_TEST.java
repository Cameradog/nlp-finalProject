package test.selenium;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import selenium.actions.*;

/**
 * Authority check. Check visually by image save the screen display
 * @author a.toru.yano
 *
 */
public class Authority_TEST
{
    private final Menu_Select ms;
    private boolean result;
    private final ExtComponentFinder ecf;
    private final WebDriver driver;
    private final String ken1 = "ken1";
    private final String ken2 = "ken2";
    private final String ken3 = "ken3";
    private final String ken4 = "ken4";
    private final String ken5 = "ken5";
    private final String ken6 = "ken6";
    private final String ken7 = "ken7";
    private final String ken8 = "ken8";
    String filepath = null;
    String path = "Authority check";

    /**
     * Constructor
     * @param driver
     */
    public Authority_TEST( final WebDriver driver )
    {
        this.driver = driver;
        ecf = new ExtComponentFinder( driver );
        ms = new Menu_Select( driver );
    }

    /**
     * Test run
     * @throws InterruptedException
     */
    public void Excution() throws InterruptedException
    {
        for( int i = 1; i <= 8; i++ )
        {
            Each_Operator( i );
        }


        return;
    }

    /**
     * Authority check of menu
     * @throws InterruptedException
     */
    public void Menu() throws InterruptedException
    {
        String filename = "Content Management.png";

        ms.Contents_manage();

        ms.GetScreenshot( filepath, filename );

        filename = "Recommendation management.png";

        ms.Recommend_manage();

        ms.GetScreenshot( filepath, filename );

        filename = "Scoring management.png";

        ms.Recommend_manage();

        ms.GetScreenshot( filepath, filename );

        filename = "Site management.png";

        ms.Site_manage();

        ms.GetScreenshot( filepath, filename );

        return;
    }

    /**
     * Screen by authorization checks
     * @throws InterruptedException
     */
    public void Function_Auth() throws InterruptedException
    {
    	
        Web_Report();

        Web_recommend_Content();

        Mail_recommend_Content();

        Item_List();

        Web_Template();

        Web_Recommend();

        Mail_Recommend();

        Mail_Pattern();

        Ads_Recommend();

        Score_Group();

        Score_Rule();

        Attribute();

        User_Segment();

        User_Info();

        Item_Setting();

        Conversion();

        Table();

        Apply();

        return;
    }

    /**
     * Check of effect measurement report
     * @throws InterruptedException
     */
    public void Web_Report() throws InterruptedException
    {
        final String filename = "Effect measurement report.png";

        Thread.sleep(2000);

        ms.Report_Manage();

        ms.Report();

        if( !Jadge("rt_webReport") )
        {
            Not_Auth( filename );
            return;
        }

        ms.GetScreenshot( filepath, filename );

        return;
    }

    /**
     * Check the Web recommendation content
     * @throws InterruptedException
     */
    public void Web_recommend_Content() throws InterruptedException
    {
        final String filename = "Setting web recommendation content.png";
        final Web_Recommend_Content wrc = new Web_Recommend_Content( this.driver );

        if( !ms.Contentes_Web() )
        {
            ms.Contents_manage();

            ms.Contentes_Web();
        }

        if( !Jadge("rt_webRecommendContent") )
        {
            Not_Auth( filename );
            return;
        }

        ms.GetScreenshot( filepath, filename );

        Sitegroup_Check( wrc, "bp_combo#siteGroup", filename );

        result = wrc.Webcontent_Add();

        //Check the list contents of the configuration group from New
        //It is not executed in the case of viewing rights only
        if( result != false )
        {
            List_Get( wrc, "bp_combo#siteGroup", filename );

            wrc.Cancel();
        }

        return;
    }

    /**
     * Check e-mail recommendation content
     * @throws InterruptedException
     */
    public void Mail_recommend_Content() throws InterruptedException
    {
        String filename = "Email recommendation content.png";
        final Mail_Content mc = new Mail_Content( this.driver );

        if( !ms.Contentes_Mail() )
        {
            ms.Contents_manage();

            ms.Contentes_Mail();
        }

        if( !Jadge("rt_mailRecommendContent") )
        {
            Not_Auth( filename );
            return;
        }

        ms.GetScreenshot( filepath, filename );

        mc.Content_Edit(1);

        filename = "Email recommendation content edit dialog.png";

        ms.GetScreenshot( filepath, filename );

        mc.Cancel();

        return;
    }

    /**
     * Check the item list setting
     * @throws InterruptedException
     */
    public void Item_List() throws InterruptedException
    {
        String filename = "Item list content setting.png";
        final Itemlist_Setting ils = new Itemlist_Setting( this.driver );

        if( !ms.Contentes_Itemlist() )
        {
            ms.Contents_manage();

            ms.Contentes_Itemlist();
        }

        if( !Jadge("rt_itemList") )
        {
            Not_Auth( filename );
            return;
        }

        if( ils.Tab_Rulelist() )
        {
            if( !Jadge("rt_ruleItemListConfig") )
            {
                Not_Auth( filename );
            }
            else
            {
                ms.GetScreenshot( filepath, filename );
            }
        }

        if( ils.Tab_Externallist() )
        {
            if( !Jadge("rt_userItemListConfig") )
            {
                Not_Auth( filename );
                return;
            }

            filename = "User-axis external list.png";

            ms.GetScreenshot( filepath, filename );
        }

        return;
    }

    /**
     * Check template settings
     * @throws InterruptedException
     */
    public void Web_Template() throws InterruptedException
    {
        String filename = "Web Template Setting.png";
        final Template_Setting ts = new Template_Setting( this.driver );

        if( !ms.Contentes_Template() )
        {
            ms.Contents_manage();

            ms.Contentes_Template();
        }

        if( !Jadge("rt_template") )
        {
            Not_Auth( filename );
            return;
        }

        if( ts.Tab_Template() )
        {
            if( !Jadge("rt_webTemplateConfig") )
            {
                Not_Auth( filename );
            }
            else
            {
                ms.GetScreenshot( filepath, filename );
            }
        }

        if( ts.Tab_Template_Group() )
        {
            filename = "Setting Web template group setting.png";

            if( !Jadge("rt_webTemplateGroupConfig") )
            {
                Not_Auth( filename );
                return;
            }

            ms.GetScreenshot( filepath, filename );
        }

        return;
    }

    /**
     * Check the Web recommendation setting
     * @throws InterruptedException
     */
    public void Web_Recommend() throws InterruptedException
    {
        String filename = "Setting web recommendation setting.png";
        final Web_Recommend wr = new Web_Recommend( this.driver );

        if( !ms.Recommend_Web() )
        {
            ms.Recommend_manage();

            ms.Recommend_Web();
        }

        if( !Jadge("rt_webRecommendPosition") )
        {
            Not_Auth( filename );
            return;
        }

        ms.GetScreenshot( filepath, filename );

        if( !wr.Position_Add() )
        {
            return;
        }

        filename = "Web recommendation method.png";

        ms.GetScreenshot( filepath, filename );

        if( wr.Type_Userauto() )
        {
            filename = "Web user auto.png";

            ms.GetScreenshot( filepath, filename );

            wr.Change_Recommend();
        }

        if( wr.Type_History() )
        {

            filename = "Web browsing history.png";

            ms.GetScreenshot( filepath, filename );

            wr.Change_Recommend();
        }

        if( wr.Type_Historyauto() )
        {

            filename = "Web item auto on browsing history.png";

            ms.GetScreenshot( filepath, filename );

            wr.Change_Recommend();
        }

        if( wr.Type_Reminder() )
        {

            filename = "Web reminder.png";

            ms.GetScreenshot( filepath, filename );

            wr.Change_Recommend();
        }

        if( wr.Type_Itemauto() )
        {

            filename = "Web item auto.png";

            ms.GetScreenshot( filepath, filename );

            wr.Change_Recommend();
        }

        if( wr.Type_Bav() )
        {

            filename = "Web buy after viewing.png";

            ms.GetScreenshot( filepath, filename );

            wr.Change_Recommend();
        }

        if( wr.Type_Ranking() )
        {

            filename = "Web ranking.png";

            ms.GetScreenshot( filepath, filename );

            wr.Change_Recommend();
        }

        if( wr.Type_Ranking() )
        {

            filename = "Web item external.png";

            ms.GetScreenshot( filepath, filename );

        }

        wr.Cancel();

        return;
    }

    /**
     * Recommendation rule
     * @throws InterruptedException
     */
    public void Web_Rule() throws InterruptedException
    {
        final String filename = "Recommendation rule.png";
        final Web_Recommend wr = new Web_Recommend( this.driver );
        final Rule rule = new Rule( this.driver );

        if( !ms.Recommend_Web() )
        {
            ms.Recommend_manage();

            ms.Recommend_Web();
        }

        wr.Rule_View(1);

        rule.Rule_Add();

        ms.GetScreenshot( filepath, filename );

        rule.Close();

        return;
    }

    /**
     * Check e-mail recommendation setting
     * @throws InterruptedException
     */
    public void Mail_Recommend() throws InterruptedException
    {
        String filename = "Mail recommendation setting.png";
        final Mail_Recommend mr = new Mail_Recommend( this.driver );

        if( !ms.Recommend_Mail() )
        {
            ms.Recommend_manage();

            ms.Recommend_Mail();
        }

        if( !Jadge("rt_mail") )
        {
            Not_Auth( filename );
            return;
        }

        ms.GetScreenshot( filepath, filename );

        mr.Tab_Mailposition();

        if( !Jadge("rt_mailRecommendPosition") )
        {
            Not_Auth( filename );
            return;
        }

        if( !mr.Mailposition_Add() )
        {
            return;
        }

        filename = "Mail recommendation method.png";

        ms.GetScreenshot( filepath, filename );

        if( mr.Type_Userauto() )
        {
            filename = "Mail user auto.png";

            ms.GetScreenshot( filepath, filename );

            mr.Change_Recommend();
        }

        if( mr.Type_History() )
        {

            filename = "Mail browsing history.png";

            ms.GetScreenshot( filepath, filename );

            mr.Change_Recommend();
        }

        if( mr.Type_Historyauto() )
        {

            filename = "Mail item auto on browsing history.png";

            ms.GetScreenshot( filepath, filename );

            mr.Change_Recommend();
        }

        if( mr.Type_Reminder() )
        {

            filename = "Mail reminder.png";

            ms.GetScreenshot( filepath, filename );

            mr.Change_Recommend();
        }

        if( mr.Type_Cart() )
        {

            filename = "Mail shopping cart.png";

            ms.GetScreenshot( filepath, filename );

            mr.Change_Recommend();
        }

        if( mr.Type_Cartauto() )
        {

            filename = "Mail shopping cart auto.png";

            ms.GetScreenshot( filepath, filename );

            mr.Change_Recommend();
        }

        if( mr.Type_Ranking() )
        {

            filename = "Mail ranking.png";

            ms.GetScreenshot( filepath, filename );

            mr.Change_Recommend();
        }

        if( mr.Type_Lastauto() )
        {

            filename = "Mail purchase history auto.png";

            ms.GetScreenshot( filepath, filename );

        }

        mr.Cancel();

        return;
    }

    /**
     * Email recommendation pattern
     * @throws InterruptedException
     */
    public void Mail_Pattern() throws InterruptedException
    {
        final String filename = "Email pattern.png";
        final Mail_Pattern mp = new Mail_Pattern( this.driver );

        if( !ms.Recommend_Mail() )
        {
            ms.Recommend_manage();

            ms.Recommend_Mail();

            mp.Tab_Mailpattern();
        }

        Thread.sleep(1000);

        if( !Jadge("rt_mailRecommendPattern") )
        {
            Not_Auth( filename );
            return;
        }

        ms.GetScreenshot( filepath, filename );

        return;
    }

    /**
     * Ad recommendation
     * @throws InterruptedException
     */
    public void Ads_Recommend() throws InterruptedException
    {
        String filename = "Advertising segment.png";
        final Ads_Recommend ar = new Ads_Recommend( this.driver );

        if( !ms.Recommend_Ads() )
        {
            ms.Recommend_manage();

            ms.Recommend_Ads();

        }

        if( !Jadge("rt_ads") )
        {
            Not_Auth( filename );
            return;
        }

        ms.GetScreenshot( filepath, filename );

        ar.Tab_Recommnd();

        filename = "Ad recommendation list.png";

        ms.GetScreenshot( filepath, filename );

        return;
    }

    /**
     * Score rule setting
     * @throws InterruptedException
     */
    public void Score_Rule() throws InterruptedException
    {
        String filename = "Score rule setting.png";
        final ScoreRule_Setting sr = new ScoreRule_Setting( this.driver );

        if( !ms.Score_Rule() )
        {
            ms.Score_Segment_manage();

            ms.Score_Rule();

        }

        if( !Jadge("rt_scoreRule") )
        {
            Not_Auth( filename );
            return;
        }

        ms.GetScreenshot( filepath, filename );

        if( !sr.Rule_Add() )
        {
            return;
        }

        filename = "score rule editing.png";

        ms.GetScreenshot( filepath, filename );

        sr.Close();

        return;
    }

    /**
     * Score group setting
     * @throws InterruptedException
     */
    public void Score_Group() throws InterruptedException
    {
        final String filename = "Score group setting.png";

        if( !ms.Score_Group() )
        {
            ms.Score_Segment_manage();

            ms.Score_Group();

        }

        if( !Jadge("rt_scoreGroup") )
        {
            Not_Auth( filename );
            return;
        }

        ms.GetScreenshot( filepath, filename );

        return;
    }

    /**
     * User attribute setting
     * @throws InterruptedException
     */
    public void Attribute() throws InterruptedException
    {
        final String filename = "User attribute setting.png";

        if( !ms.Segment_User_Attribute() )
        {
            ms.Score_Segment_manage();

            ms.Segment_User_Attribute();

        }

        if( !Jadge("rt_attribute") )
        {
            Not_Auth( filename );
            return;
        }

        ms.GetScreenshot( filepath, filename );

        return;
    }

    /**
     * User segment setting
     * @throws InterruptedException
     */
    public void User_Segment() throws InterruptedException
    {
        final String filename = "user segment setting.png";

        if( !ms.Segment_User_Segment() )
        {
            ms.Score_Segment_manage();

            ms.Segment_User_Segment();
        }

        if( !Jadge("rt_segment") )
        {
            Not_Auth( filename );
            return;
        }

        ms.GetScreenshot( filepath, filename );

        return;
    }

    /**
     * User information display
     * @throws InterruptedException
     */
    public void User_Info() throws InterruptedException
    {
        final String filename = "user information.png";

        if( !ms.Segment_User_Information() )
        {
            ms.Score_Segment_manage();

            ms.Segment_User_Information();

        }

        if( !Jadge("rt_userInformation") )
        {
            Not_Auth( filename );
            return;
        }

        Window_Size(1280,1040);

        ms.Js_Sendkey( "bp_text#member", "TEST1" );
        ms.Js_Click( "bp_button#memberSearch" );
        //if user not exist
        ms.Js_Click( "messagebox button[text=确定]" );

        ms.GetScreenshot( filepath, filename );

        Window_Size_Reset();

        return;
    }

    /**
     * Item set
     * @throws InterruptedException
     */
    public void Item_Setting() throws InterruptedException
    {
        final String filename = "item set.png";

        if( !ms.Site_ItemSetting() )
        {
            ms.Site_manage();

            ms.Site_ItemSetting();
        }

        if( !Jadge("rt_item") )
        {
            Not_Auth( filename );
            return;
        }

        ms.GetScreenshot( filepath, filename );

        return;
    }

    /**
     * Site basic setting
     * @throws InterruptedException
     */
    public void Site_Basic() throws InterruptedException
    {
        String filename = "site basic setting.png";

        if( !ms.Site_BaseSetting() )
        {
            ms.Site_manage();

            ms.Site_BaseSetting();

        }

        ms.GetScreenshot( filepath, filename );

        ms.Js_Click("bp_editButton");

        filename = "site basic setting.png";

        ms.GetScreenshot( filepath, filename );

        ms.Cancel();

        return;
    }

    /**
     * Conversion page setting
     * @throws InterruptedException
     */
    public void Conversion() throws InterruptedException
    {
        final String filename = "conversion page setting.png";

        if( !ms.Site_Conversionpage() )
        {
            ms.Site_manage();

            ms.Site_Conversionpage();

        }

        if( !Jadge("rt_conversionPage") )
        {
            Not_Auth( filename );
            return;
        }

        ms.GetScreenshot( filepath, filename );

        return;
    }

    /**
     * Table setting
     * @throws InterruptedException
     */
    public void Table() throws InterruptedException
    {
        String filename = "table setting.png";
        final Table_Setting ts = new Table_Setting( this.driver );

        if( !ms.Table_Setting() )
        {
            ms.Site_manage();

            ms.Table_Setting();

        }

        if( !Jadge("rt_table") )
        {
            Not_Auth( filename );
            return;
        }

        ms.GetScreenshot( filepath, filename );

        ts.Tab_Domain();
        filename = "domain table.png";

        if( !Jadge("rt_domainTable") )
        {
            Not_Auth( filename );
        }
        else
        {
            ms.GetScreenshot( filepath, filename );
        }

        ts.Tab_IP();
        filename = "IP table.png";

        if( !Jadge("rt_ipTable") )
        {
            Not_Auth( filename );
        }
        else
        {
            ms.GetScreenshot( filepath, filename );
        }

        return;
    }

    /**
     * Reflection setting
     * @throws InterruptedException
     */
    public void Apply() throws InterruptedException
    {
        final String filename = "Reflection setting.png";

        final Apply ap = new Apply(driver);

        ap.Apply_Button();

        ap.GetScreenshot( filepath, filename );

        ap.Close();

        return;
    }

    /**
     * Authority check of each setting group from the list
     * @param function
     * @param elm_str
     * @param filename
     * @throws InterruptedException
     */
    private void Sitegroup_Check( final Common function, final String elm_str, final String filename ) throws InterruptedException
    {
        List<WebElement> elms = null;
        final String add = "Site setting group each item.png";
        String name = null;
        String number = null;
        int list = 0;

        if( function.Js_Click( elm_str ) )
        {
            elms = function.Wait_Elements( "x-boundlist-item", Element_Type.CLASS );

            list = elms.size() - 1;

            for( ; list >= 0; list-- )
            {
                ecf.selectCombobox( elm_str, list );
                number = Integer.toString( list );

                //絞り込みボタン
                function.Js_Click( "bp_button#search" );

                name = filename + number + add;

                ms.GetScreenshot( filepath, name );
            }

        }

        return;
    }

    /**
     * List of site setting group content
     * @param function
     * @param elm_str
     * @param filename
     * @throws InterruptedException
     */
    private void List_Get( final Common function, final String elm_str, String filename ) throws InterruptedException
    {
        final String add = "list content.png";

        if( !function.Js_Click( elm_str ) )
        {
            filename = filename + add;
        }

        ms.GetScreenshot( filepath, filename );

        return;
    }
    private void Window_Size( final int high, final int width )
    {
        final Dimension dime = new Dimension( high, width );

        this.driver.manage().window().setSize( dime );

        return;
    }

    private void Window_Size_Reset( )
    {
        final Dimension dime = new Dimension( 1280, 768 );

        this.driver.manage().window().setSize( dime );

        return;
    }

    /**
     * False if unauthorized
     * @param query
     * @return
     */
    private boolean Jadge( final String query )
    {
        WebElement elm = null;

        elm = ecf.find( query, 0 );

        if( elm == null )
        {
            System.out.println( "No authority" );
            return false;
        }

        return true;
    }

    private void Not_Auth( final String str )
    {
        String base;

        base = "Image storage" + File.separator +  "Firefox" + File.separator + filepath + File.separator + str + ".txt";

        try
        {
            final File file = new File(base);
            final FileWriter filewriter = new FileWriter(file);

            filewriter.write("No authority");

            filewriter.close();
        }
        catch(final IOException e)
        {
            System.out.println(e);
        }

    }

    private void Each_Operator( final int number ) throws InterruptedException
    {
        final Common com = new Common();

        com.Set_Drive(driver);
        com.Login( number );

        switch( number )
        {
        case 1:
            filepath = path + File.separator + ken1;
            break;

        case 2:
            filepath = path + File.separator + ken2;
            break;

        case 3:
            filepath = path + File.separator + ken3;
            break;

        case 4:
            filepath = path + File.separator + ken4;
            break;

        case 5:
            filepath = path + File.separator + ken5;
            break;

        case 6:
            filepath = path + File.separator + ken6;
            break;

        case 7:
            filepath = path + File.separator + ken7;
            break;

        case 8:
            filepath = path + File.separator + ken8;
            break;

            default:
                break;
        }

//        Menu();

        Function_Auth();

        Thread.sleep(1000);

        com.Logout();

        return;
    }
}