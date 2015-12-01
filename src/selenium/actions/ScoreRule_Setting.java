package selenium.actions;


import test.selenium.ExtComponentFinder;
import org.openqa.selenium.WebDriver;

/**
 * スコアルール設定
 * @author a.toru.yano
 *
 */
public class ScoreRule_Setting extends Common
{
    /**
     * xpath指定用
     */
	private final int MONTH;
	private final int DAY;
	private final int RULE_OK;
	private final int RULE_CANCEL;
	private final int PARAM_NEW;
	private final int PARAM_NAME;
	private final int PARAM_VALUE;
	private final int PARAM_OK;
	private final int PARAM_CANCEL;
	private final int PARAM_TABLE;
	private final int KEYWORD_OK;
	private final int KEYWORD_CANCEL;
	private final int KEYWORD_NEW;
	private final int REQUEST_SITE;
	private final int REQUEST_PAGE;
	private final int REQUEST_TABLE;
	private final int REFERRER_SITE;
	private final int REFERRER_PAGE;
	private final int REFERRER_TABLE;
	private final int SCORE_OK;
	private final int EDIT_CANCEL;
	private final int APP_NEW;
	private final int APP_OK;
	private final int APP_COMBO;
	private final int APP_CANCEL;
	private final int APP_NAME;
	private final int APP_KEY;
	private final int APP_TABLE;
	private final ExtComponentFinder ecf;
	private final Grid_Click gc;
	private final String APP_PANEL;
	private final String REQUEST_PANEL;
	private final String REFFERER_PANEL;
	private final String LINE_PANEL;

	/**
	 * コンストラクタ
	 */
	public ScoreRule_Setting( final WebDriver driver )
	{
		MONTH = 1;
		DAY  = 11;
		RULE_OK = 13;
		RULE_CANCEL = 10;
		SCORE_OK = 12;
		EDIT_CANCEL = 13;
		REQUEST_SITE = 0;
		REQUEST_PAGE = 1;
		REQUEST_TABLE = 2;
		REFERRER_SITE = 0;
		REFERRER_PAGE = 1;
		REFERRER_TABLE = 5;
		PARAM_OK = 4;
		PARAM_CANCEL = 4;
		PARAM_NEW = 1;
		PARAM_NAME = 5;
		PARAM_VALUE = 3;
		PARAM_TABLE = 4;
		KEYWORD_OK = 7;
		KEYWORD_CANCEL = 7;
		KEYWORD_NEW = 2;
		APP_NEW = 3;
		APP_NAME = 0;
		APP_KEY = 1;
		APP_OK = 10;
		APP_COMBO = 10;
		APP_CANCEL = 8;
		APP_TABLE = 8;
		count = 0;

		APP_PANEL = "rt_appKeyConditionPanel#appKeyConditionPanel";
		REQUEST_PANEL = "rt_requestConditionPanel#requestConditionPanel";
		REFFERER_PANEL = "rt_referrerConditionPanel#referrerConditionPanel";
		LINE_PANEL = "rt_lineConditionPanel#lineConditionPanel";

		ecf = new ExtComponentFinder( driver );
		gc = new Grid_Click( driver );
		Set_Drive( driver );
	}

	/**
	 * スコアルール新規作成ボタン
	 */
	public boolean Rule_Add()
	{
		return Js_Click( "bp_newButton#new" );
	}

    /**
     * 有効ボタン
     * @return
     */
    public boolean Enable_Rule( )
    {
        return Js_Click( "bp_enableButton" );
    }

    /**
     * 無効ボタン
     * @return
     */
    public boolean Disable_Rule( )
    {
        return Js_Click( "bp_disableButton" );
    }

	/**
	 * スコア条件新規作成ボタン
	 */
	public boolean Scoring_Add()
	{
		return Js_Click( "bp_newButton#newReward" );
	}

	/**
	 * スコアルール編集
	 *
	 * @param index	編集したい行数
	 * @return
	 * @throws InterruptedException
	 */
	public boolean Rule_Edit( int index ) throws InterruptedException
	{
	    index--;

		return Edit_Click( index );
	}

	/**
	 * スコアルールコピー
	 *
	 * @param index	編集したい行数
	 * @return
	 */
	public boolean Copy_Rule( int index )
	{
	    index--;

		return Copy_Click( index );
	}

	/**
	 * スコアルール名編集
	 *
	 * @param edit
	 * @return
	 */
	public boolean Edit_Rulename( final String edit )
	{
		return Sendkey( "name", edit, Element_Type.NAME );
	}

	/**
	 * 設定グループによる絞り込み
	 *
	 * @param index
	 * @return
	 */
	public void Select_Sitegroup( final int index )
	{
	    ecf.selectCombobox( "bp_combo#siteGroup", index );

		return;
	}

	/**
	 * リクエストサイト編集
	 *
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Search_Scorename( final String edit )
	{
		return Js_Sendkey( "bp_text#scoreRuleName", edit, REQUEST_SITE );
	}

	/**
	 * スコアグループによる絞り込み
	 *
	 * @param index
	 * @return
	 */
	public void Filter_ScoreGroup( final int index )
	{
        ecf.selectCombobox( "bp_combo#scoreGroup", index );

		return;
	}

	/**
	 * スコア項目による絞り込み
	 *
	 * @param index
	 * @return
	 */
	public void Filter_ScoreKey( final int index )
	{
	    ecf.selectCombobox( "bp_combo#scoreKey", index );

		return;
	}

	/**
	 * リクエストタブ
	 * @return
	 */
	public boolean Tab_Request()
	{
		return Click( "请求", Element_Type.LINK );
	}

	/**
	 * スケジュールタブ
	 * @return
	 */
	public boolean Tab_Schedul()
	{
		return Click( "时间表", Element_Type.LINK );
	}

	/**
	 * リファラタブ
	 * @return
	 */
	public boolean Tab_Referre()
	{
		return Click( "参照位址", Element_Type.LINK );
	}

	/**
	 * アプリキータブ
	 * @return
	 */
	public boolean Tab_Appli()
	{
		return Click( "APP KEY", Element_Type.LINK );
	}

    /**
     * 地域タブ
     * @return
     */
    public boolean Tab_Area()
    {
        return Click( "地区", Element_Type.LINK );
    }

    /**
     * 回線タブ
     * @return
     */
    public boolean Tab_Line()
    {
        return Click( "线路", Element_Type.LINK );
    }

	/**
	 * スケジュールの選択
	 * @param index	選択したい月
	 * @return
	 */
	public boolean Schedul_Month( int index )
	{
		//見た目と合わせる調整用
		index = index - MONTH;

		return Toggle_Click( index );
	}

	/**
	 * スケジュールの選択
	 * @param index	選択したい日
	 * @return
	 */
	public boolean Schedul_Day( int index )
	{
		//見た目と合わせる調整用
		index = index + DAY;

		return Toggle_Click( index );
	}

	/**
	 * スケジュールの選択
	 * @param str	曜日を文字列で渡す。祝日指定も含む
	 * @return
	 */
	public boolean Schedul_Week( final String str )
	{
		return Click( str, Element_Type.LINK );
	}

	/**
	 * 時間帯選択（開始）
	 *
	 * @param index	選択リストのindex値　0が午前12時、23が午後11時
	 * @return
	 */
	public void Schedul_Start_Hour( final int index )
	{
		ecf.selectCombobox("timefield#fromHour", index );

		return;
	}

	/**
	 * 時間帯選択（終了）
	 *
	 * @param index	選択リストのindex値　0が午前12時、23が午後11時
	 * @return
	 */
	public void Schedul_End_Hour( final int index )
	{
	    ecf.selectCombobox("timefield#toHour", index );

		return;
	}

	/**
	 * クリアボタン
	 * @return
	 */
	public boolean Schedul_Clear()
	{
		return Click( "清除", Element_Type.LINK );
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
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Request_Site( final String edit )
	{
		return Js_Sendkey( "bp_text#textValue", edit, REQUEST_SITE );
	}

	/**
	 * リクエストサイトの条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public void Select_Request_Site( final int index )
	{
		ecf.selectCombobox( "rt_stringOperatorCombo#stringOperatorCombo", REQUEST_SITE, index );

		return;
	}

	/**
	 * リクエストページ編集
	 *
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Request_Page( final String edit )
	{
		return Js_Sendkey( "bp_text#textValue", edit, REQUEST_PAGE );
	}

	/**
	 * リクエストページの条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public void Select_Request_Page( final int index )
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
        ecf.selectCombobox( "bp_combo#tableValue", PARAM_TABLE, index );

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
	 * リファラ種別
	 * @param index
	 * @return
	 * @throws InterruptedException
	 */
	public void Select_Referre( final int index ) throws InterruptedException
	{
		ecf.selectCombobox( "bp_combo#referrerType", index );

        return;
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
	 * リファラサイト編集
	 *
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Referre_Site( final String edit )
	{
		return Js_Sendkey( "bp_text#textValue", edit, 5 );
	}

	public void Select_Referre_Site( final int index )
	{
		ecf.selectCombobox( "rt_stringOperatorCombo#stringOperatorCombo", REFERRER_SITE, index );

        return;
	}

	/**
	 * リファラページ編集
	 *
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Referre_Page( final String edit )
	{
		return Js_Sendkey( "bp_text#textValue", edit, 6 );
	}

	public void Select_Referre_Page( final int index )
	{
		ecf.selectCombobox( "rt_stringOperatorCombo#stringOperatorCombo", REFERRER_PAGE, index );

        return;
	}

	/**
     * 直接入力
     * @return
     */
    public void Referre_Radio_Direct( )
    {
        ecf.RadioGroup_Select( "bp_radioGroup#type", REFERRER_TABLE, "TEXT" );

        return;
    }

    /**
     * テーブル
     * @return
     */
    public void Referre_Radio_Table( )
    {
        ecf.RadioGroup_Select( "bp_radioGroup#type", REFERRER_TABLE, "TABLE" );

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
		ecf.selectCombobox( "bp_combo#tableValue", 7, index  );

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
	 * アプリキー新規作成ボタン
	 */
	public boolean App_Add()
	{
		return Js_Click( "bp_newButton#new", APP_NEW );
	}

	/**
     * 直接入力
     * @return
     */
    public void App_Radio_Direct( )
    {
        ecf.RadioGroup_Select( "bp_radioGroup#type", APP_TABLE, "TEXT" );

        return;
    }

    /**
     * テーブル
     * @return
     */
    public void App_Radio_Table( )
    {
        ecf.RadioGroup_Select( "bp_radioGroup#type", APP_TABLE, "TABLE" );

        return;
    }

	/**
	 * アプリ名編集
	 * @param edit
	 * @return
	 */
	public boolean Edit_App_Name( final String edit )
	{
	    return gc.Sendkey( APP_PANEL, edit, APP_NAME );
	}

	/**
	 * アプリキー編集
	 *
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_App_Key( final String edit )
	{
	    return gc.Sendkey( APP_PANEL, edit, APP_KEY );
	}

	/**
     * キーワードテーブルを選択
     * @param index
     * @return
     * @throws InterruptedException
     */
    public void Select_AppTable( final int index ) throws InterruptedException
    {
        ecf.selectCombobox( "bp_combo#tableValue", 8, index  );

        return;
    }

	/**
	 * アプリキーの条件選択
	 * @param index
	 * @return
	 */
	public void Select_App_Key( final int index )
	{
		ecf.selectCombobox( "rt_stringOperatorCombo#stringOperatorCombo", APP_COMBO, index );

        return;
	}

	/**
	 * アプリキーのOKボタン
	 */
	public boolean Decide_App( )
	{
		return Js_Click( "bp_button#ok", APP_OK );
	}

	/**
     * キーワードのキャンセルボタン
     * @return
     */
    public boolean Cancel_App()
    {
        return Js_Click( "bp_button#cancel", APP_CANCEL );
    }

	/**
	 * アプリキー編集
	 *
	 * @param index	編集したい行数
	 * @return
	 */
	public boolean App_Edit( int index )
	{
        index--;

        return gc.Edit_Click( "rt_appKeyConditionPanel", index );
	}

	/**
	 * アプリキー削除
	 *
	 * @param index	編集したい行数
	 * @return
	 */
	public boolean App_Delete( int index )
	{
        index--;

        return gc.Delete_Click( "rt_appKeyConditionPanel", index );
	}

	/**
     * スコア条件の編集
     *
     * @param index 編集したい行数
     * @return
     */
    public boolean Score_Edit( int index )
    {
        index--;

        return gc.Edit_Click( "panel#rewardPanel", index );
    }

    /**
     * スコア条件の削除
     *
     * @param index 削除したい行数
     * @return
     */
    public boolean Score_Delete( int index )
    {
        index--;

        return gc.Delete_Click( "panel#rewardPanel", index );
    }

	/**
	 * スコアグループ選択
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public void Select_ScoreGroup( final int index )
	{
		ecf.selectCombobox( "bp_combo#group", index );

        return;
	}

	/**
	 * スコア項目選択
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public void Select_ScoreKey( final int index )
	{
	    ecf.selectCombobox( "bp_combo#key", index );

        return;
	}

	/**
	 * 依存スコアグループ選択
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public void Select_Depend_ScoreGroup( final int index )
	{
		ecf.selectCombobox( "bp_combo#dependingGroup", index );

        return;
	}

	/**
	 * 依存スコア項目選択
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public void Select_Depend_ScoreKey( final int index )
	{
		ecf.selectCombobox( "bp_combo#dependingKey", index );

        return;
	}

	/**
	 * ポイント編集
	 *
	 * @param edit	ポイント
	 * @return
	 */
	public boolean Edit_Point( final String edit )
	{
		return Js_Sendkey( "bp_text#point", edit );
	}

	/**
	 * スコア条件のOKボタン
	 * @return
	 */
	public boolean Decide_Score( )
	{
		return Js_Click( "bp_button#ok", SCORE_OK );
	}

	/**
	 * スコア条件のキャンセルボタン
     * @return
     */
	public boolean Score_Cancel( )
	{
		return Js_Click( "bp_button#cancel", EDIT_CANCEL );
	}

	/**
	 * スコアルール全体のOKボタン
     * @return
     */
	public boolean Rule_OK( )
	{
		return Js_Click( "bp_button#ok", RULE_OK );
	}

	/**
	 * スコアルール全体のキャンセルボタン
     * @return
     */
	public boolean Rule_Cancel( )
	{
		return Js_Click( "bp_button#cancel", RULE_CANCEL );
	}

	/**
	 * エラーダイアログのOKボタン
     * @return
     */
	public boolean Error_OK( )
	{
		return Js_Click( "button#ok" );
	}

}
