package test.selenium;

import java.io.File;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import selenium.actions.*;

/**
 * Manual for the capture
 * @author a.toru.yano
 *
 */
public class Manual extends Common
{
    WebDriver driver;
    Menu_Select ms;
    String filepath = "For manual";

    /**
     * Constructor
     * @param drivre
     */
    public Manual( final WebDriver driver )
    {
        this.driver = driver;
        Set_Drive( this.driver );
        ms = new Menu_Select( this.driver );
    }

    /**
     * Score rule
     * @throws InterruptedException
     */
    public void ScoreRule() throws InterruptedException
    {
        String filename = "Score rule.png";
        String filepath = null;

        filepath = this.filepath + File.separator + "Score rule";

        final ScoreRule_Setting ss = new ScoreRule_Setting( this.driver );

        ms.Report_Manage();
        ms.Score_Segment_manage();
        ms.Score_Rule();

        ss.Select_Sitegroup( 3 );

        Loading();

        ss.Check_Box( 3 );

        GetScreenshot( filepath, filename );

        ss.Rule_Add();

        Window_Size( 1280, 960 );

        filename = "Score rule schedule.png";
        GetScreenshot( filepath, filename );

        ss.Tab_Request();

        Window_Size_Reset();

        filename = "Score rule request.png";
        GetScreenshot( filepath, filename );

        ss.Tab_Referre();

        filename = "Score rule referrer.png";
        GetScreenshot( filepath, filename );

        ss.Tab_Appli();

        filename = "Score rule app key.png";
        GetScreenshot( filepath, filename );

        ss.App_Add();

        ss.Edit_App_Name("app");
        ss.Edit_App_Key("param");
        ss.App_Radio_Table();
        ss.Select_AppTable(1);
        ss.Select_App_Key(1);
        ss.Decide_App();

        ss.Tab_Area();

        filename = "Score rules region.png";
        GetScreenshot( filepath, filename );

        ss.Tab_Line();

        filename = "Score rules line.png";
        GetScreenshot( filepath, filename );

        ss.Cancel();

        return;
    }

    /**
     * Recommendation rules set
     * @throws InterruptedException
     */
    public void Recommend_Rule() throws InterruptedException
    {
        String filename = "Recommendation rule.png";
        String filepath = null;

        filepath = this.filepath + File.separator + "Recommendation rule";

        ms.Report_Manage();
        ms.Recommend_manage();
        ms.Recommend_Web();
        
        Thread.sleep(1000);
        
        final Web_Recommend wr = new Web_Recommend( this.driver );
        final Rule rr = new Rule( this.driver );

        wr.Rule_View(1);

        wr.Loading();

        rr.Check_Box(1);

        GetScreenshot( filepath, filename );

        rr.Rule_Add();

        filename = "Recommendation rule visit.png";

        GetScreenshot( filepath, filename );

        rr.Tab_Attirbute();
        rr.Attribute_Add();

        filename = "Recommendation rule attribute.png";

        GetScreenshot( filepath, filename );

        rr.Tab_Area();

        filename = "Recommendation rules region.png";

        GetScreenshot( filepath, filename );

        rr.Tab_Schedul();

        Window_Size( 1280, 1040 );

        filename = "Recommendation rules schedule.png";

        GetScreenshot( filepath, filename );

        rr.Tab_Score();
        rr.Score_Add();

        Window_Size( 1280, 960 );

        filename = "Recommendation rules score.png";

        GetScreenshot( filepath, filename );

        rr.Tab_Rquest();

        filename = "Recommendation rules request.png";

        GetScreenshot( filepath, filename );

        rr.Tab_Infllow();

        filename = "Recommendation rules influx.png";

        GetScreenshot( filepath, filename );

        rr.Tab_Line();

        filename = "Recommendation rules line.png";

        GetScreenshot( filepath, filename );

        Window_Size_Reset();

        rr.Cancel();

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
}
