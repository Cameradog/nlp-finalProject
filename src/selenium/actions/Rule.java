package selenium.actions;


import org.openqa.selenium.WebDriver;
import test.selenium.ExtComponentFinder;
/**
 * ルール設定
 * 地域の都道府県選択は未実装
 * @author a.toru.yano
 *
 */
public class Rule extends Common
{
    protected Grid_Click gc = null;
    private final int RATIO;
    private final int NUMBER;
    private final int POINT_SELECT;
    private final int RANK_SELECT;
    private final int RATIO_SELECT;
    private final int PAGE;
    private final int VISIT;
    private final int INTERVAL;
    private final int POINT;
    private final int RANK;
    private final int ATTRIBUTE_OK;
    private final int ATTRIBUTE_NEW;
    private final int ATTRIBUTE_CANCEL;
    private final int SCORE_NEW;
    private final int SCORE_OK;
    private final int INFLLOW_TABLE;
    private final int KEYWORD_NEW;
    private final int KEYWORD_OK;
    private final int KEYWORD_CANCEL;
    private final int PARAM_NEW;
    private final int PARAM_NAME;
    private final int PARAM_VALUE;
    private final int PARAM_TABLE;
    private final int PARAM_OK;
    private final int PARAM_CANCEL;
    private final int RULE_ADD;
    private final int RULE_OK;
    private final int USERAGENT;
    private final int SITESTAY;
    private final int IP_ADDRESS;
    private final int REQUEST_SITE;
    private final int REQUEST_PAGE;
    private final int REQUEST_TABLE;
    private final int LINE_TABLE;
    private final int DOMAIN;

    //コンストラクタ
    public Rule( final WebDriver driver )
    {
        Set_Drive( driver );
        gc = new Grid_Click( driver );
        RULE_ADD = 1;
        RULE_OK = 14;
        USERAGENT = 0;
        SITESTAY = 1;
        IP_ADDRESS = 2;
        REQUEST_SITE = 3;
        REQUEST_PAGE = 4;
        REQUEST_TABLE = 1;
        PARAM_OK = 8;
        PARAM_CANCEL = 8;
        ATTRIBUTE_NEW = 2;
        ATTRIBUTE_CANCEL = 1;
        SCORE_NEW = 3;
        SCORE_OK = 2;
        PARAM_NEW = 4;
        PARAM_NAME = 5;
        PARAM_VALUE = 6;
        PARAM_TABLE = 2;
        LINE_TABLE = 8;
        INFLLOW_TABLE = 1;
        KEYWORD_NEW = 5;
        KEYWORD_OK = 9;
        KEYWORD_CANCEL = 9;
        DOMAIN = 8;
        ATTRIBUTE_OK = 1;
        PAGE = 0;
        VISIT = 1;
        NUMBER = 1;
        INTERVAL = 2;
        POINT = 3;
        RANK = 4;
        RATIO = 5;
        POINT_SELECT = 3;
        RANK_SELECT = 4;
        RATIO_SELECT = 5;

    }

    /**
     * ルール新規作成ボタン
     * @return
     */
    public boolean Rule_Add( )
    {
        return Js_Click( "bp_newButton#new", RULE_ADD );
    }

    /**
     * ルールOKボタン
     * @return
     */
    public boolean Rule_OK( )
    {
        return Js_Click( "bp_button#ok", RULE_OK );
    }

    /**
     * 訪問タブ
     * @return
     */
    public boolean Tab_Visit()
    {
        return Click( "訪問", Element_Type.LINK );
    }

    /**
     * 属性タブ
     * @return
     */
    public boolean Tab_Attirbute()
    {
        return Click( "属性", Element_Type.LINK );
    }

    /**
     * スコアタブ
     * @return
     */
    public boolean Tab_Score()
    {
        return Click( "评分", Element_Type.LINK );
    }

    public boolean Tab_Schedul()
    {
        return Click( "时间表", Element_Type.LINK );
    }

    /**
     * リクエストタブ
     * @return
     */
    public boolean Tab_Rquest()
    {
        return Click( "请求", Element_Type.LINK );
    }

    /**
     * 流入タブ
     */
    public boolean Tab_Infllow()
    {
        return Click( "流入源", Element_Type.LINK );
    }


    /**
     * 地域タブクリック
     * @return
     */
    public boolean Tab_Area()
    {
        return Click( "地区", Element_Type.LINK );
    }

    /**
     * 回線タブクリック
     * @return
     */
    public boolean Tab_Line()
    {
        return Click( "线路", Element_Type.LINK );
    }

    /**
     * 時間帯選択（開始）
     *
     * @param index 選択リストのindex値　0が午前12時、23が午後11時
     * @return
     */
    public void Schedul_Start_Hour( final int hour )
    {
        ecf.selectCombobox("timefield#fromHour", hour );

        return;
    }

    /**
     * 時間帯選択（終了）
     *
     * @param index 選択リストのindex値　0が午前12時、23が午後11時
     * @return
     */
    public void Schedul_End_Hour( final int hour )
    {
        ecf.selectCombobox("timefield#toHour", hour );

        return;
    }

    /**
     * 開始日時編集
     * @param edit 入力する文字列
     * @return
     */
    public boolean Edit_Effectivedate( final String edit )
    {
        return Js_Sendkey( "datefield#effectiveDate", edit );
    }

    /**
     * 開始日時編集
     * @param edit 入力する文字列
     * @return
     */
    public boolean Edit_Effectivetime( final String edit )
    {
        return Js_Sendkey( "timefield#effectiveTime", edit );
    }

    /**
     * 終了日時編集
     * @param edit 入力する文字列
     * @return
     */
    public boolean Edit_Expiringdate( final String edit )
    {
        return Js_Sendkey( "datefield#expiringDate", edit );
    }

    /**
     * 終了日時編集
     * @param edit 入力する文字列
     * @return
     */
    public boolean Edit_Expiringtime( final String edit )
    {
        return Js_Sendkey( "timefield#expiringTime", edit );
    }

    /**
     * ユーザー属性のOKボタン
     */
    public void Decide_Attribute( )
    {
        Js_Click( "bp_button#ok", ATTRIBUTE_OK );

        return;
    }

    /**
     *属性新規作成ボタン
     */
    public boolean Attribute_Add()
    {
        return Js_Click( "bp_newButton#new", ATTRIBUTE_NEW );
    }

    /**
     * ユーザー属性選択
     *
     * @param index 選択リストのindex値
     * @return
     */
    public void Select_Attribute( final int index )
    {
        ecf.selectCombobox( "bp_combo#attributeName", index );

        return;
    }

    /**
     * ユーザー属性値選択
     *
     * @param index 選択リストのindex値
     * @return
     */
    public void Select_Attribute_Value( final int index )
    {
        ecf.selectCombobox( "bp_combo#attributeValue", index );

        return;
    }

    /**
     * 一致する/一致しないの選択
     * @param index 0:一致する  1:一致しない
     */
    public void Select_Attribute_Match( final int index )
    {
        ecf.selectCombobox( "rt_attributeOperatorCombo#operator", index );

        return;
    }

    /**
     * 属性値が存在しない場合も含む
     */
    public boolean Match_NotExists()
    {
        return Js_Click( "bp_checkbox#matchNotExists" );
    }

    /**
     * ユーザー属性のキャンセルボタン
     * @return
     */
    public boolean Cancel_Attribute()
    {
        return Js_Click( "bp_button#cancel", ATTRIBUTE_CANCEL );
    }

    /**
     * ユーザー属性編集
     *
     * @param index 編集したい行数
     * @return
     */
    public boolean Attribute_Edit( int index )
    {
        index--;

        return gc.Edit_Click( "rt_attributeConditionPanel", index );
    }

    /**
     * ユーザー属性削除
     *
     * @param index 編集したい行数
     * @return
     */
    public boolean Attribute_Delete( int index )
    {
        //XPATH指定用の調整
        index--;

        return gc.Delete_Click( "rt_attributeConditionPanel", index );
    }

    /**
     * スコア条件追加
     */
    public boolean Score_Add()
    {
        return Js_Click( "bp_newButton#new",SCORE_NEW );
    }

    /**
     * スコア条件追加
     */
    public void Decide_Score()
    {
        Js_Click( "bp_button#ok", SCORE_OK );

        return;
    }

    /**
     * ユーザーエージェント編集
     *
     * @param edit  入力する文字列
     * @return
     */
    public boolean Edit_Useragent( final String edit )
    {
        return Js_Sendkey( "bp_text#textValue", edit, USERAGENT );
    }

    /**
     * ユーザーエージェントの条件指定
     *
     * @param index 選択リストのindex値
     * @return
     */
    public void Select_Useragent( final int index )
    {
        ecf.selectCombobox( "rt_stringOperatorCombo#stringOperatorCombo", USERAGENT, index );

        return;
    }

    /**
     * サイト滞在時間編集
     *
     * @param edit  入力する文字列
     * @return
     */
    public boolean Edit_Sitestay( final String edit )
    {
        return Js_Sendkey( "bp_text#textValue", edit, SITESTAY );
    }

    /**
     * サイト滞在時間の条件指定
     *
     * @param index 選択リストのindex値
     * @return
     */
    public void Select_Sitestay( final int index )
    {
        ecf.selectCombobox( "rt_stringOperatorCombo#stringOperatorCombo", SITESTAY, index );

        return;
    }

    /**
     * サイト滞在時間編集
     *
     * @param edit  入力する文字列
     * @return
     */
    public boolean Edit_Ipaddress( final String edit )
    {
        return Js_Sendkey( "bp_text#textValue", edit, IP_ADDRESS );
    }

    /**
     * サイト滞在時間の条件指定
     *
     * @param index 選択リストのindex値
     * @return
     */
    public void Select_Ipaddress( final int index )
    {
        ecf.selectCombobox( "rt_stringOperatorCombo#stringOperatorCombo", SITESTAY, index );

        return;
    }

    /**
     * 直接入力
     * @return
     */
    public void Request_Radio_Direct( )
    {
        ecf.RadioGroup_Select( "bp_radioGroup#type", REQUEST_TABLE, "TEXT" );

        return;
    }

    /**
     * テーブル
     * @return
     */
    public void Request_Radio_Table( )
    {
        ecf.RadioGroup_Select( "bp_radioGroup#type", REQUEST_TABLE, "TABLE" );

        return;
    }

    /**
     * リクエストサイト編集
     *
     * @param edit  入力する文字列
     * @return
     */
    public boolean Edit_Rquest_Site( final String edit )
    {
        return Js_Sendkey( "bp_text#textValue", edit, REQUEST_SITE );
    }

    /**
     * リクエストサイトの条件指定
     *
     * @param index 選択リストのindex値
     * @return
     */
    public void Select_Rquest_Site( final int index )
    {
        ecf.selectCombobox( "rt_stringOperatorCombo#stringOperatorCombo", REQUEST_SITE, index );

        return;
    }

    /**
     * リクエストページ編集
     *
     * @param edit  入力する文字列
     * @return
     */
    public boolean Edit_Rquest_Page( final String edit )
    {
        return Js_Sendkey( "bp_text#textValue", edit, REQUEST_PAGE );
    }

    /**
     * リクエストページの条件指定
     *
     * @param index 選択リストのindex値
     * @return
     */
    public void Select_Rquest_Page( final int index )
    {
        ecf.selectCombobox( "rt_stringOperatorCombo#stringOperatorCombo", REQUEST_PAGE, index );

        return;
    }

    /**
     * パラメータ新規作成ボタン
     */
    public boolean Param_Add()
    {
        return Js_Click( "bp_newButton#new", PARAM_NEW );
    }

    /**
     * パラメータ編集ボタン
     * @param index
     * @return
     */
    public boolean Param_Edit( int index )
    {
        index--;

        return gc.Edit_Click( "rt_requestConditionPanel", index );
    }

    /**
     * パラメータ削除ボタン
     * @param index
     * @return
     */
    public boolean Param_Delete( int index )
    {
        index--;

        return gc.Delete_Click( "rt_requestConditionPanel", index );
    }

    /**
     * パラメータ名編集
     *
     * @param edit
     * @return
     */
    public boolean Edit_Paramname( final String edit )
    {
        return Js_Sendkey( "bp_text#name", edit, PARAM_NAME );
    }

    /**
     * パラメータ値編集
     *
     * @param edit
     * @return
     */
    public boolean Edit_Paramvalue( final String edit )
    {
        return Js_Sendkey( "bp_text#textValue", edit, PARAM_VALUE );
    }

    /**
     * 直接入力
     * @return
     */
    public void Param_Radio_Direct( )
    {
        ecf.RadioGroup_Select( "bp_radioGroup#type", PARAM_TABLE, "TEXT" );

        return;
    }

    /**
     * テーブル
     * @return
     */
    public void Param_Radio_Table( )
    {
        ecf.RadioGroup_Select( "bp_radioGroup#type", PARAM_TABLE, "TABLE" );

        return;
    }

    /**
     * 直接入力ならこっち
     * @param index
     */
    public void Select_Param_String( final int index )
    {
        ecf.selectCombobox( "rt_stringOperatorCombo#stringOperatorCombo", PARAM_VALUE, index );

        return;
    }

    /**
     * テーブル選択ならこっち
     * @param index
     */
    public void Select_Param_Table( final int index )
    {
        ecf.selectCombobox( "rt_tableOperatorCombo#tableOperatorCombo", PARAM_VALUE, index );

        return;
    }

    /**
     * パラメータ条件のOKボタン
     */
    public boolean Decide_Param( )
    {
        return Js_Click( "bp_button#ok", PARAM_OK );
    }

    /**
     * パラメータのキャンセルボタン
     * @return
     */
    public boolean Cancel_Param()
    {
        return Js_Click( "bp_button#cancel", PARAM_CANCEL );
    }

    /**
     * 直接入力
     * @return
     */
    public void Referre_Radio_Direct( )
    {
        ecf.RadioGroup_Select( "bp_radioGroup#type", INFLLOW_TABLE, "TEXT" );

        return;
    }

    /**
     * テーブル
     * @return
     */
    public void Referre_Radio_Table( )
    {
        ecf.RadioGroup_Select( "bp_radioGroup#type", INFLLOW_TABLE, "TABLE" );

        return;
    }

    /**
     * キーワード新規作成ボタン
     */
    public boolean Keyord_Add()
    {
        return Js_Click( "bp_newButton#new", KEYWORD_NEW );
    }

    /**
     * キーワード条件のOKボタン
     */
    public boolean Decide_Keyword( )
    {
        return Js_Click( "bp_button#ok", KEYWORD_OK );
    }

    /**
     * キーワードのキャンセルボタン
     * @return
     */
    public boolean Cancel_Keyword()
    {
        return Js_Click( "bp_button#cancel", KEYWORD_CANCEL );
    }


    /**
     * 既に設定済みのキーワードから編集
     *
     * @param index 編集したい行数
     * @return
     */
    public boolean Keyword_Edit( int index )
    {
        index--;

        return gc.Edit_Click( "rt_referrerConditionPanel", index );
    }

    /**
     * キーワード削除
     *
     * @param index 編集したい行数
     * @return
     */
    public boolean Keyword_Delete( int index )
    {
        index--;

        return gc.Delete_Click( "rt_referrerConditionPanel", index );
    }

    /**
     * キーワードテーブルを選択
     * @param index
     * @return
     * @throws InterruptedException
     */
    public void Select_KeywordTable( final int index ) throws InterruptedException
    {
        ecf.selectCombobox( "bp_combo#tableValue", index );

        return;
    }

    /**
     * キーワードの条件選択
     * @param index
     * @return
     */
    public void Select_Keyword( final int index )
    {
        ecf.selectCombobox( "rt_tableOperatorCombo#tableOperatorCombo", index );

        return;
    }

    /**
     * 直接入力
     * @return
     */
    public void Line_Radio_Direct( )
    {
        ecf.RadioGroup_Select( "bp_radioGroup#type", LINE_TABLE, "TEXT" );

        return;
    }

    /**
     * テーブル
     * @return
     */
    public void Line_Radio_Table( )
    {
        ecf.RadioGroup_Select( "bp_radioGroup#type", LINE_TABLE, "TABLE" );

        return;
    }

    /**
     * アクセス元ドメイン編集
     *
     * @param edit
     * @return
     */
    public boolean Edit_Domain( final String edit )
    {
        return Js_Sendkey( "bp_text#textValue", edit, DOMAIN );
    }

    /**
     * アクセス元ドメインの条件指定
     *
     * @param index 選択リストのindex値
     * @return
     */
    public void Select_Domaindirect( final int index )
    {
        ecf.selectCombobox( "rt_stringOperatorCombo#stringOperatorCombo", DOMAIN, index );

        return;
    }

    /**
     * 回線のチェックボックス
     * @param index 1：ダイヤルアップ　2：フレッツISDN　3：フレッツADSL　4：アッカ.......
     * @return
     */
    public boolean Check_Accsess( final int index )
    {
        return Js_Click( "checkbox", index );
    }

    /**
     * コンテンツ種別（レコメンドコンテンツ）
     * @return
     */
    public void Radio_Content( )
    {
        ecf.RadioGroup_Select( "bp_radioGroup#contentType", "CONTENT" );

        return;
    }

    /**
     * コンテンツ種別（ルール用リスト）
     * @return
     */
    public void Radio_Rulelist( )
    {
        ecf.RadioGroup_Select( "bp_radioGroup#contentType", "ITEM_GROUP" );

        return;
    }

    /**
     * ルール名編集
     * @param edit セグメント名
     * @return
     */
    public boolean Edit_Rulename( final String edit )
    {
        return Js_Sendkey( "bp_text#name", edit );

    }

    /**
     * 総ページ数入力
     *
     * @param edit  ページ数
     * @return
     */
    public boolean Edit_Pageview( final String edit )
    {
        return Js_Sendkey( "bp_text#value", edit, PAGE );
    }

    /**
     * 訪問回数入力
     *
     * @param edit 訪問回数
     * @return
     */
    public boolean Edit_Visit( final String edit )
    {
        return Js_Sendkey( "bp_text#value", edit, VISIT );
    }

    /**
     * 訪問間隔入力
     * @param edit 訪問間隔
     * @return
     */
    public boolean Edit_Interval( final String edit )
    {
       return Js_Sendkey( "bp_text#value", edit, INTERVAL );
    }

    /**
     * スコアポイント入力
     *
     * @param edit  スコアポイント
     * @return
     */
    public boolean Edit_Point( final String edit )
    {
        return Js_Sendkey( "bp_text#value", edit, POINT );
    }

    /**
     * スコア順位入力
     *
     * @param edit  スコア順位
     * @return
     */
    public boolean Edit_Rank( final String edit )
    {
        return Js_Sendkey( "bp_text#value", edit, RANK );
    }

    /**
     * スコア比率入力
     *
     * @param edit  スコア比率
     * @return
     */
    public boolean Edit_Ratio( final String edit )
    {
        return Js_Sendkey( "bp_text#value", edit, RATIO );
    }

    /**
     * ページビューの条件選択
     * @param index
     * @return
     */
    public void Select_Page( final int index )
    {
        ecf.selectCombobox( "rt_numericOperatorCombo#operator", PAGE, index );

        return;
    }

    /**
     * 訪問回数の条件選択
     * @param index
     * @return
     */
    public void Select_Visitnumber( final int index )
    {
        ecf.selectCombobox( "rt_numericOperatorCombo#operator", NUMBER, index );

        return;
    }

    /**
     * 訪問間隔の条件選択
     * @param index
     * @return
     */
    public void Select_Interval( final int index )
    {
        ecf.selectCombobox( "rt_numericOperatorCombo#operator", INTERVAL , index );

        return;
    }

    /**
     * ポイントの条件選択
     * @param index
     * @return
     */
    public void Select_Point( final int index )
    {
        ecf.selectCombobox( "rt_numericOperatorCombo#operator", POINT_SELECT, index );

        return;
    }

    /**
     * 順位の条件選択
     * @param index
     * @return
     */
    public void Select_Rank( final int index )
    {
        ecf.selectCombobox( "rt_numericOperatorCombo#operator", RANK_SELECT, index );

        return;
    }

    /**
     * 比率の条件選択
     * @param index
     * @return
     */
    public boolean Select_Ratio( final int index )
    {
        ecf.selectCombobox( "rt_numericOperatorCombo#operator", RATIO_SELECT, index );

        return true;
    }
    
    public boolean Select_Recommend_Content( final int index )
    {
        ecf.selectCombobox( "bp_combo#webRecommendContent", index );

        return true;
    }
}
