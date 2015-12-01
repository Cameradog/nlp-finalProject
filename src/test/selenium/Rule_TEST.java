package test.selenium;

import java.util.Date;

import org.openqa.selenium.WebDriver;

import selenium.actions.Menu_Select;
import selenium.actions.Rule;
import selenium.actions.Web_Recommend;

public class Rule_TEST
{

    private Rule rule;
    private Menu_Select ms;
    private Web_Recommend  wr;
    private boolean result;
    String filepath = "Recommendation rule set";

    /**
     * Recommendation rule set
     * @param driver
     * @throws InterruptedException
     */
    public Rule_TEST( WebDriver driver ) throws InterruptedException
    {
        rule = new Rule( driver );
        ms = new Menu_Select( driver );
        wr = new Web_Recommend( driver );

        ms.Report_Manage();
        ms.Recommend_manage();
        ms.Recommend_Web();

        result = false;
    }

    public void Rule_Max( ) throws InterruptedException
    {
        wr.Position_Add();

        wr.Type_Rule();

        wr.Edit_Positionname(String.valueOf(new Date().getTime()));

        wr.Edit_Elementid("auto_" + String.valueOf(new Date().getTime()));

        wr.After_Rule();

        int count = 1;
        String str = null;

        while( true )
        {
            result = rule.Rule_Add();
            //wait for ui reponse
            Thread.sleep(200);

            if( result == false )
            {
                break;
            }

            str = Integer.toString( count );

            rule.Edit_Rulename( str );

            rule.Edit_Visit( str );

            rule.Select_Visitnumber(2);
            
            rule.Select_Recommend_Content(0);

            rule.Rule_OK();

            //if no that there is a case in which for some reason you would click on the header part
            Thread.sleep(200);

            count++;
        }

        //The maximum number of registered check
        rule.Get_MaxNumber();
        
        wr.Rule_OK();

        return;
    }


}
