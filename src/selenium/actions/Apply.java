package selenium.actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;


/**
 * 設定を反映
 * @author a.toru.yano
 *
 */
public class Apply extends Common
{
    private String tabname;

    //コンストラクタ
    public Apply( WebDriver driver )
    {
        Set_Drive( driver );
        tabname = null;
    }

    /**
     * 設定を反映ボタンクリック
     * @return
     */
    public boolean Apply_Button()
    {
        return Js_Click( "button#applyConfig" );
    }

    /**
     * 全て保留
     * @return
     */
    public boolean All_Hold()
    {
        return Js_Click( "bp_button#setAllAsNONE" );
    }

    /**
     * 全て適用
     * @return
     */
    public boolean All_Accept()
    {
        return Js_Click( "bp_button#setAllAsACCPT" );
    }

    /**
     * 全て差戻し
     * @return
     */
    public boolean All_Decline()
    {
        return Js_Click( "bp_button#setAllAsDECLINE" );
    }

    /**
     * 全て差戻し
     * @return
     */
    public boolean All_Cancel()
    {
        return Js_Click( "bp_button#setAllAsREVOKE" );
    }

    /**
     * タブクリック
     * @param tabname クリックしたいタブ名
     * @return
     */
    public boolean Tab_Click( String tabname )
    {
        this.tabname = tabname;

        return Click( tabname, Element_Type.LINK );
    }

    /**
     * 設定の反映
     * @return
     */
    public boolean Refrect()
    {
        return OK();
    }

    /**
     * プレビューボタンクリック
     * @param index
     * @return
     */
    public boolean Preview( int index )
    {
        List<WebElement> elms = null;

        elms = Bychained_Apply( this.tabname, "rt_preview" );

        if( elms == null )
        {
            return false;
        }

        elms.get( index ).click();

        return true;
    }

    /**
     * 設定を反映用
     * 特定のエレメント配下のエレメントを指定する
     * @param elm_str1 最初に指定するエレメント（これはユニークのものを指定する）
     * @param elm_str2 elm_str1の配下にあるエレメント（ユニークじゃなくてもOK）
     * @return
     */
    private List<WebElement> Bychained_Apply( String elm_str1, String elm_str2 )
    {
        WebDriver driver = null;

        driver = Get_Drive();

        return driver.findElements(new ByChained( By.linkText( elm_str1 ), By.className( elm_str2 ) ) );
    }

}
