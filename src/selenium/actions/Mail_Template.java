package selenium.actions;

import org.openqa.selenium.WebDriver;

/**
 * メールテンプレート設定（広告テンプレとまったく同様）
 * @author a.toru.yano
 *
 */
public class Mail_Template extends Ads_Template
{

    public Mail_Template(WebDriver driver)
    {
        super(driver);
        // TODO 自動生成されたコンストラクター・スタブ
    }

    /**
     * テンプレートタブ
     * @return
     */
    public boolean Tab_Template()
    {
        return Click( "メールテンプレート", Element_Type.LINK );
    }

}
