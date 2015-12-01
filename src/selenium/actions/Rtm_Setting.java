package selenium.actions;

import org.openqa.selenium.WebDriver;

public class Rtm_Setting extends Common
{
    WebDriver driver;
    private final Menu_Select ms;
    private String filepath;

    /**
     * コンストラクタ
     * @param driver
     */
    public Rtm_Setting( final WebDriver driver )
    {
        ms = new Menu_Select( driver );
        Set_Drive( driver );
    }

    /**
     *
     * @throws InterruptedException
     */
    public void menu() throws InterruptedException
    {
        ms.Logo();

        ms.System_Set();

        ms.Server();

        return;
    }

    public void System()
    {
        return;
    }

    public void Opereator()
    {
        return;
    }

    public void Site_Group()
    {
        return;
    }

    public void Limit() throws InterruptedException
    {
        final String filename = "設定上限.png";

        ms.System_Set();

        Js_Click( "bp_button#limit" );

        GetScreenshot(filepath, filename);

        return;
    }

}
