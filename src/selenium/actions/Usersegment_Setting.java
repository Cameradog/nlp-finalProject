package selenium.actions;

import test.selenium.ExtComponentFinder;

import org.openqa.selenium.WebDriver;


public class Usersegment_Setting extends Common
{

	/**
	 * xpath指定用
	 */
	private final int SEGMENT_ADD;
	private final int RULE_ADD;
	private final int PAGE;
	private final int NUMBER;
	private final int INTERVAL;
	private final int VISIT;
	private final int POINT;
	private final int RANK;
	private final int RATIO;
	private final int ATTRIBUTE_OK;
	private final int SCORE_OK;
    private final int RULEEDIT_OK;
	private final int POINT_SELECT;
	private final int RANK_SELECT;
	private final int RATIO_SELECT;
	private final int ATTRIBUTE_NEW;
	private final int SCORE_NEW;
	private final int CANCEL_RULE;
	private final int ATTRIBUTE;
	private final int SCORE;
	private final int EXIST;
	private final int NOSCORE;

	protected ExtComponentFinder ecf;
	private Grid_Click gc = null;
	//どちらを先に開いたかでXPATHがずれるので、その判定用
	protected static int count;
	private int segment_flag;

	public Usersegment_Setting( final WebDriver driver )
	{
		segment_flag = 0;
		SEGMENT_ADD = 0;
		RULE_ADD = 1;
		PAGE = 0;
		ATTRIBUTE_OK = 1;
		SCORE_OK = 2;
		RULEEDIT_OK = 3;
		VISIT = 1;
		NUMBER = 1;
		INTERVAL = 2;
		POINT = 3;
		RANK = 4;
		RATIO = 5;
		POINT_SELECT = 3;
		RANK_SELECT = 4;
		RATIO_SELECT = 5;
		ATTRIBUTE_NEW = 2;
		SCORE_NEW = 3;
		CANCEL_RULE = 2;
		ATTRIBUTE = 0;
		SCORE = 1;
		EXIST = 2;
		NOSCORE = 3;
		count = 0;

		ecf = new ExtComponentFinder( driver );
		gc = new Grid_Click( driver );
		Set_Drive( driver );
	}

	/**
	 * セグメント新規作成ボタン
	 * @return
	 */
	public boolean Segment_Add( )
	{
		return Js_Click( "bp_newButton#new", SEGMENT_ADD );
	}

	/**
	 * ユーザーセグメント編集
	 * @param index　編集したい行数を指定する
	 * @return
	 */
	public boolean Segment_Edit( final int index )
	{
		return Edit_Click( index );
	}

	/**
	 * ユーザーセグメント取消
	 * @param index　編集したい行数を指定する
	 * @return
	 */
	public boolean Segment_Return( final int index )
	{
		return Undo_Click( index );
	}

	/**
	 * ユーザーセグメントルール編集ボタン
	 * @param index　編集したい行数を指定する
	 * @return
	 */
	public boolean Segment_Rule( final int index )
	{
		if( segment_flag == 0 )
		{
			segment_flag = 1;
			count = Get_Icon();
		}

		return Rule_Click( index - 1 );
	}

	/**
	 * セグメント名編集
	 * @param edit セグメント名
	 * @return
	 */
	public boolean Edit_Segmentname( final String edit )
	{
		return Js_Sendkey( "bp_text#name", edit );

	}

	/**
	 * 設定グループ変更_編集画面中
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public void Select_Sitegroup( final int index )
	{
		ecf.selectCombobox( "bp_combo#siteGroup", index );

		return;
	}

	/**
	 * 備考編集
	 * @param edit 備考内容
	 * @return
	 */
	public boolean Edit_Memo( final String edit )
	{
		return Js_Sendkey( "bp_textarea#memo", edit );

	}

	/**
	 * OKボタン
	 */
	public boolean Ruleedit_OK( )
	{
		return Js_Click( "bp_button#ok", RULEEDIT_OK );
	}

	/**
	 * 属性の確定ボタン
	 * @throws InterruptedException
	 */
	public void Decide_Attribute( ) throws InterruptedException
	{
	    Js_Click( "bp_button#ok", ATTRIBUTE_OK );

        return;
	}

	/**
	 * スコア条件の確定ボタン
	 * @throws InterruptedException
	 */
	public void Decide_Score( ) throws InterruptedException
	{
	    Js_Click( "bp_button#ok", SCORE_OK );

        return;
	}

	/**
	 * セグメントのキャンセルボタン
	 */
	@Override
    public boolean Cancel( )
	{
		return Js_Click( "bp_button#cancel" );
	}

	/**
	 * ユーザー属性のキャンセルボタン
	 */
	public boolean Cancel_Attribute( )
	{
		return Js_Click( "bp_button#cancel", ATTRIBUTE );
	}

	/**
	 * スコア条件のキャンセルボタン
	 */
	public boolean Cancel_Score( )
	{
		return Js_Click( "bp_button#cancel", SCORE );
	}

	/**
	 * ルール編集のキャンセルボタン
	 */
	public boolean Cancel_Rule( )
	{
		return Js_Click( "bp_button#cancel", CANCEL_RULE );
	}

	/**
	 * 保存してルールへ
	 */
	public boolean After_Rule( )
	{

		return Js_Click( "bp_button#ruleConfig" );
	}

	/**
	 * セグメントルール新規作成ボタン
	 * @return
	 */
	public boolean Rule_Add( )
	{
		return Js_Click( "bp_newButton#new", RULE_ADD );
	}

	/**
	 * ルール編集ボタン
	 * @param index　編集したい行数を指定する
	 * @return
	 * @throws InterruptedException
	 */
	public boolean Rule_Edit( int index ) throws InterruptedException
	{
		index--;

		return gc.Edit_Click( "rt_ruleConditionPanel", index );
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
	    return Click( "スコア", Element_Type.LINK );
	}

	/**
	 * コピーボタン
	 * @param index　編集したい行数を指定する
	 * @return
	 */
	public boolean Rule_Copy( int index )
	{
		index = index + count;

		return Copy_Click( index );
	}

	/**
	 * 総ページ数入力
	 *
	 * @param edit	ページ数
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
	 * @param edit	スコアポイント
	 * @return
	 */
	public boolean Edit_Point( final String edit )
	{
		return Js_Sendkey( "bp_text#value", edit, POINT );
	}

	/**
	 * スコア順位入力
	 *
	 * @param edit	スコア順位
	 * @return
	 */
	public boolean Edit_Rank( final String edit )
	{
		return Js_Sendkey( "bp_text#value", edit, RANK );
	}

	/**
	 * スコア比率入力
	 *
	 * @param edit	スコア比率
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

	/**
	 * 属性新規作成ボタン
	 */
	public boolean Attribute_Add()
	{
		return Js_Click( "bp_newButton#new", ATTRIBUTE_NEW );
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
     * @param index 削除したい行数
     * @return
     */
    public boolean Attribute_Delete( int index )
    {
        index--;

        return gc.Delete_Click( "rt_attributeConditionPanel", index );
    }

	/**
	 * ユーザー属性選択
	 *
	 * @param index	選択リストのindex値
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
	 * @param index	選択リストのindex値
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
	 *スコア新規作成ボタン
	 */
	public boolean Score_Add()
	{
		return Js_Click( "bp_newButton#new", SCORE_NEW );
	}

	/**
	 * スコアの編集ボタン
	 *
	 * @param index		編集したい行数
	 * @return
	 */
	public boolean Score_Edit( int index )
	{
		index--;

		return gc.Edit_Click( "rt_scoreConditionPanel", index );
	}

	/**
	 * スコアの削除ボタン
	 *
	 * @param index		編集したい行数
	 * @return
	 */
	public boolean Score_Delete( int index )
	{
        index--;

        return gc.Delete_Click( "rt_scoreConditionPanel", index );
	}

	/**
	 * スコアグループ選択
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public void Select_ScoreGroup( final int index )
	{
		ecf.selectCombobox( "bp_combo#scoreGroup", index );

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
		ecf.selectCombobox( "bp_combo#scoreKey", index );

		return;
	}

	/**
	 * スコアが存在する
	 * @return
	 */
	public boolean Raido_Exsit( )
	{
		return Js_Click( "bp_radio", EXIST );
	}

	/**
	 * スコアが存在しない
	 * @return
	 */
	public boolean Raido_Noscore( )
	{
		return Js_Click( "bp_radio", NOSCORE );
	}


}
