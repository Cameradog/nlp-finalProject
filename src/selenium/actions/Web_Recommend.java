package selenium.actions;

import org.openqa.selenium.WebDriver;

/**
 * Webレコメンド設定
 * @author a.toru.yano
 *
 */
public class Web_Recommend extends Mail_Recommend
{
	
	private final int RULE_OK;
	
    //コンストラクタ
    public Web_Recommend( final WebDriver driver )
    {
        super( driver );
        
        RULE_OK = 0;
    }

    /**
     * レコメンド場所追加
     * @return
     */
    public boolean Position_Add()
    {
        return Js_Click( "bp_newButton#new" );
    }

    /**
     * 場所名、エレメントIDで検索
     * @param positionname 検索したい場所名/エレメントID
     * @return
     */
    public boolean Search_Positionname( final String positionname )
    {
        return Js_Sendkey( "bp_text#webRecommendPositionName", positionname );
    }

    /**
     * 検索したいレコメンド方法
     * @param index 以下参照
     * 0:空白
     * 1:ルールベース
     * 2:ユーザー軸自動レコメンド
     * 3:ユーザー軸外部リスト
     * 4:アイテム軸自動レコメンド
     * 5:アイテム軸外部リスト
     * 6:アイテム閲覧履歴
     * 7:アイテム閲覧履歴からの自動レコメンド
     * 8:ランキング
     * 9:閲覧後の購買ランキング
     * 10:周期性レコメンド
     *
     */
    public void Search_Recommendtype( final int index )
    {
        ecf.selectCombobox( "rt_recommendTypeCombo#recommendType", index );

        return;
    }

    /**
     * 検索したい設定グループ
     * @param index
     */
    public void Search_Sitegroup( final int index )
    {
        ecf.selectCombobox( "bp_combo#siteGroup", index );

        return;
    }

    /**
     * 絞り込みボタン
     * @return
     */
    public boolean Search()
    {
        return Js_Click( "bp_button#search" );
    }

    /**
     * 条件クリアボタン
     * @return
     */
    public boolean Clear()
    {
        return Js_Click( "bp_button#clear" );
    }

    /**
     * レコメンド場所編集ボタン
     * @param index
     * @return
     */
    public boolean Position_Edit( final int index )
    {
        return Edit_Click( index );
    }

    /**
     * アイテム軸自動レコメンド
     * @return
     */
    public boolean Type_Itemauto()
    {
        return Js_Click( "label#itemAuto" );
    }
    public boolean Type_ItemExternal()
    {
        return Js_Click( "label#itemManual" );
    }

    /**
     * 閲覧後の購買ランキング
     * @return
     */
    public boolean Type_Bav()
    {
        return Js_Click( "label#bav" );
    }

    /**
     * エレメントID編集
     * @param edit 入力する文字列
     * @return
     */
    public boolean Edit_Elementid( final String edit )
    {
        return Js_Sendkey( "bp_text#elementId", edit );
    }

    /**
     * 複数表示チェック
     * @return
     */
    public boolean Check_Multiple()
    {
        return Js_Click( "bp_checkbox#multiple" );
    }

    /**
     * アイテム表示数入力
     * @param edit 入力する文字列
     * @return
     */
    public boolean Edit_Itemnum( final String edit )
    {
        return Js_Sendkey( "bp_text#itemNum", edit );
    }

    /**
     * ルール不一致の効果測定チェック
     * @return
     */
    public boolean Check_Ruleunmatch()
    {
        return Js_Click( "bp_checkbox#captureUnmatch" );
    }

    /**
     * コントロール群チェック
     * @return
     */
    public boolean Check_Control()
    {
        return Js_Click( "bp_checkbox#controlGroup" );
    }

    /**
     * 流入キーワード加味チェック
     * @return
     */
    public boolean Check_Reffer()
    {
        return Js_Click( "bp_checkbox#weightedByKeyword" );
    }

    /**
     * コントロール群入力
     * @param edit 入力する文字列
     * @return
     */
    public boolean Edit_Control( final String edit )
    {
        return Js_Sendkey( "bp_text#controlGroupRate", edit );
    }

    /**
     * テンプレート種別選択（テンプレート）
     */
    public void Radio_Template()
    {
        ecf.RadioGroup_Select( "bp_radioGroup#templateType", "TEMPLATE" );

        return;
    }

    /**
     * テンプレート種別選択（テンプレートグループ）
     */
    public void Radio_Templategroup()
    {
        ecf.RadioGroup_Select( "bp_radioGroup#templateType", "RULE" );

        return;
    }

    /**
     * テンプレート選択
     * @param index
     */
    public void Select_Template( final int index )
    {
        ecf.selectCombobox( "bp_combo#template", index );

        return;
    }

    /**
     * テンプレートグループ選択
     * @param index
     */
    public void Select_Templategroup( final int index )
    {
        ecf.selectCombobox( "bp_combo#templateGroup", index );

        return;
    }
    
    /**
	 * OK button of the recommend rules
     * @return
     */
	public boolean Rule_OK( )
	{
		return Js_Click( "bp_button#ok", RULE_OK );
	}

    /**
     * レコメンド場所のOKボタン
     * @return
     */
    public boolean Position_OK()
    {
        return Js_Click( "bp_button#ok" );
    }

    /**
     * レコメンド場所のOKボタン
     * @return
     */
    public boolean Position_Cancel()
    {
        return Js_Click( "bp_button#cancel" );
    }
}