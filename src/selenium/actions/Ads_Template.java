package selenium.actions;

import org.openqa.selenium.WebDriver;

/**
 * 広告テンプレート
 * @author a.toru.yano
 *
 */
public class Ads_Template extends Common
{
    private final int ADS;
    //コンストラクタ
    public Ads_Template( WebDriver driver )
    {
        ADS = 1;
        Set_Drive( driver );
    }

    /**
     * テンプレートタブ
     * @return
     */
    public boolean Tab_Template()
    {
        return Click( "広告テンプレート", Element_Type.LINK );
    }

    /**
     * 編集ボタン
     * @return
     */
    public boolean Edit()
    {
        return Js_Click( "bp_editButton" );
    }

    /**
     * アイテムコード編集
     * @param str　入力する文字列
     * @return
     */
    public boolean Edit_Itemcode( String str )
    {
        return Js_Sendkey( "bp_text#code", str );
    }

    /**
     * アイテム名編集
     * @param str　入力する文字列
     * @return
     */
    public boolean Edit_Itemname( String str )
    {
        return Js_Sendkey( "bp_text#name", str );
    }

    /**
     * リンク先URL編集
     * @param str　入力する文字列
     * @return
     */
    public boolean Edit_Linkurl( String str )
    {
        return Js_Sendkey( "bp_text#linkUrl", str );
    }

    /**
     * 画像URL編集
     * @param str　入力する文字列
     * @return
     */
    public boolean Edit_Imageurl( String str )
    {
        return Js_Sendkey( "bp_text#imageUrl", str );
    }

    /**
     * 価格編集
     * @param str　入力する文字列
     * @return
     */
    public boolean Edit_Price( String str )
    {
        return Js_Sendkey( "bp_text#price", str );
    }

    /**
     * アイテム説明編集
     * @param str　入力する文字列
     * @return
     */
    public boolean Edit_Description( String str )
    {
        return Js_Sendkey( "bp_text#description", str );
    }

    /**
     * OKボタン
     */
    public boolean OK()
    {
        return Js_Click( "button#ok", ADS );
    }

    /**
     * OKボタン
     */
    public boolean Cancel()
    {
        return Js_Click( "button#cancel", ADS );
    }
}
