package selenium.actions;

import org.openqa.selenium.WebDriver;

public class Ads_Recommend extends Ads_Segment
{
    private final int PROFIT;
    private int icon_count;

    //コンストラクタ
    public Ads_Recommend( WebDriver driver )
    {
        super( driver );
        PROFIT = 1;
        icon_count = 0;
    }

    /**
     * 広告レコメンドリストタブ
     * @return
     */
    public boolean Tab_Recommnd()
    {
        if( icon_count == 0 )
        {
            icon_count = Get_Icon();
        }

        return Click( "広告レコメンドリスト", Element_Type.LINK );
    }

    /**
     * レコメンドリストの編集ボタン
     * @param index
     * @return
     */
    public boolean Recommend_Edit( int index )
    {
        index = index + icon_count;

        return Edit_Click( index );
    }

    /**
     * レコメンド方法変更
     * @return
     */
    public boolean Change_Recommend()
    {
        return Js_Click( "bp_button#switchRecommendType" );
    }

    /**
     * ユーザー軸自動レコメンド
     * @return
     */
    public boolean Type_Userauto()
    {
        return Js_Click( "label#userAutoConversion" );
    }

    /**
     * アイテム閲覧
     * @return
     */
    public boolean Type_History()
    {
        return Js_Click( "label#history" );
    }

    /**
     * 残留内カート履歴
     * @return
     */
    public boolean Type_Cart()
    {
        return Js_Click( "label#abandonment" );
    }

    /**
     * 最終購買から自動レコメンド
     * @return
     */
    public boolean Type_Lastauto()
    {
        return Js_Click( "label#transactionAuto" );
    }

    /**
     * カテゴリフィルタ選択
     * @param index
     */
    public void Select_Category( int index )
    {
        ecf.selectCombobox( "bp_combo#categoryFilter", index );

        return;
    }

    /**
     * カテゴリ入力
     * @param str
     * @return
     */
    public boolean Edit_Category1( String str )
    {
        return Js_Sendkey( "bp_text#category0", str );
    }

    /**
     * カテゴリ入力
     * @param str
     * @return
     */
    public boolean Edit_Category2( String str )
    {
        return Js_Sendkey( "bp_text#category1", str );
    }

    /**
     * カテゴリ入力
     * @param str
     * @return
     */
    public boolean Edit_Category3( String str )
    {
        return Js_Sendkey( "bp_text#category2", str );
    }

    /**
     * カテゴリ入力
     * @param str
     * @return
     */
    public boolean Edit_Category4( String str )
    {
        return Js_Sendkey( "bp_text#category3", str );
    }

    /**
     * カテゴリ入力
     * @param str
     * @return
     */
    public boolean Edit_Category5( String str )
    {
        return Js_Sendkey( "bp_text#category4", str );
    }

    /**
     * グループ名入力
     * @param str
     * @return
     */
    public boolean Edit_Group( String str )
    {
        return Js_Sendkey( "bp_text#groupFilter", str );
    }

    /**
     * 価格下限入力
     * @param str
     * @return
     */
    public boolean Edit_Pricefrom( String str )
    {
        return Js_Sendkey( "bp_text#from", str );
    }

    /**
     * 価格上限入力
     * @param str
     * @return
     */
    public boolean Edit_Priceto( String str )
    {
        return Js_Sendkey( "bp_text#from", str );
    }

    /**
     * 価値下限入力
     * @param str
     * @return
     */
    public boolean Edit_Profitfrom( String str )
    {
        return Js_Sendkey( "bp_text#from", str, PROFIT );
    }

    /**
     * 価値上限入力
     * @param str
     * @return
     */
    public boolean Edit_Profitto( String str )
    {
        return Js_Sendkey( "bp_text#from", str, PROFIT );
    }
}
