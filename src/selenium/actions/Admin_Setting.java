package selenium.actions;

import org.openqa.selenium.WebDriver;

/**
 * サイト編集機能
 * @author a.toru.yano
 *
 */
public class Admin_Setting
{
    Common com;
    Grid_Click gc;
    String id;

    /**
     * コンストラクタ
     * @param driver
     */
    public Admin_Setting( final WebDriver driver )
    {
        com = new Common();
        com.Set_Drive( driver );

        gc = new Grid_Click( driver );
        id = null;
    }

    /**
     * 各サイトの編集ボタン
     * @param index　編集したいサイトのINDEXを指定
     */
    public void Edit_Site( final int index )
    {
        com.Edit_Click( index - 1 );

        return;
    }

    /**
     * 各サイトの削除ボタン
     * @param index　削除したいサイトのINDEXを指定
     */
    public void Delete_Site( final int index )
    {
        com.Delete_Click( index - 1 );

        return;
    }

    /**
     * サイト名編集
     * @param name
     */
    public void Edit_Sitename( final String name )
    {
        com.Js_Sendkey( "bp_text#name", name );

        return;
    }

    /**
     * URL編集
     * @param url
     */
    public void Edit_Url( final String url )
    {
        com.Js_Sendkey( "bp_text#uri", url );

        return;
    }

    /**
     * ドメイン編集
     * @param domain
     */
    public void Edit_Domain( final String domain )
    {
        com.Js_Sendkey( "bp_text#domain", domain );

        return;
    }

    /**
     * メンバ値キー名編集
     * @param key
     */
    public void Edit_Memberkey( final String key )
    {
        com.Js_Sendkey( "bp_text#memberKey", key );

        return;
    }

    /**
     * グループID編集
     * @param id
     */
    public void Edit_Groupid( final String id )
    {
        com.Js_Sendkey( "bp_text#groupId", id );

        return;
    }

    /**
     * ホストID編集
     * @param id
     */
    public void Edit_Hostid( final String id )
    {
        com.Js_Sendkey( "bp_text#hostId", id );

        return;
    }

    /**
     * 自動共通
     * @return
     */
    public boolean Tab_Common()
    {
        id = gc.Get_Id( "rt_commonOptionPanel#commonOptionPanel" );

        return com.Click( "自動共通", Element_Type.LINK );
    }

    /**
     * アイテムタブ
     * @return
     */
    public boolean Tab_Item()
    {
        id = gc.Get_Id( "rt_itemOptionPanel#itemOption" );

        return com.Click( "アイテム", Element_Type.LINK );
    }

    /**
     * ユーザータブ
     * @return
     */
    public boolean Tab_User()
    {
        id = gc.Get_Id( "rt_userOptionPanel#userOption" );

        return com.Click( "ユーザー", Element_Type.LINK );
    }

    /**
     * メールタブ
     * @return
     */
    public boolean Tab_Mail()
    {
        return com.Click( "メール", Element_Type.LINK );
    }

    /**
     * 広告タブ
     * @return
     */
    public boolean Tab_Ads()
    {
        return com.Click( "広告", Element_Type.LINK );
    }

    /**
     * 閲覧軸オプション
     * @return
     */
    public boolean Check_Autoview()
    {
        if( id != null )
        {
            return gc.Checkbox( id, 0 );
        }
        else
        {
            return com.Js_Click( "bp_checkbox#autoView" );
        }
    }

    /**
     * 購買軸オプション
     * @return
     */
    public boolean Check_AutoConvertion()
    {
        if( id != null )
        {
            return gc.Checkbox( id, 1 );
        }
        else
        {
            return com.Js_Click( "bp_checkbox#autoConversion" );
        }
    }

    /**
     * 閲覧後購買ランキング
     * @return
     */
    public boolean Check_Bav()
    {
        return com.Js_Click( "bp_checkbox#bav" );
    }

    /**
     * グループフィルタ
     * @return
     */
    public boolean Check_Group()
    {
        return com.Js_Click( "bp_checkbox#groupFilter" );
    }

    /**
     * カテゴリフィルタ
     * @return
     */
    public boolean Check_Category()
    {
        if( id != null )
        {
            return gc.Checkbox( id, 3 );
        }
        else
        {
            return com.Js_Click( "bp_checkbox#categoryFilter" );
        }
    }

    /**
     * 流入キーワード
     * @return
     */
    public boolean Check_Keyword()
    {
        return com.Js_Click( "bp_checkbox#keyword" );
    }

    /**
     * 最大表示枠数編集
     * @param num
     * @return
     */
    public boolean Edit_Itemnum( final String num )
    {
        return com.Js_Sendkey( "bp_text#itemNum", num );
    }

    /**
     * 閲覧履歴：最大分析対象期間
     * @param day
     * @return
     */
    public boolean Edit_View_Maxday( final String day )
    {
        return com.Js_Sendkey( "bp_text#analysisViewDays", day );
    }

    /**
     * 購買履歴：最大分析対象期間
     * @param day
     * @return
     */
    public boolean Edit_Conversion_Maxday( final String day )
    {
        return com.Js_Sendkey( "bp_text#analysisConversionDays", day );
    }
}
