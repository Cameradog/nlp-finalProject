package selenium.actions;

import org.openqa.selenium.WebDriver;

/**
 * メールレコメンド場所設定
 * @author a.toru.yano
 *
 */
public class Mail_Recommend extends Ads_Recommend
{
    private final int RULE_ADD;
    private final int ATTRIBUTE_NEW;
    private final int SCORE_NEW;
    private final int MONTH;
    private final int DAY;
    //コンストラクタ
    public Mail_Recommend( final WebDriver driver )
    {
        super(driver);
        RULE_ADD = 2;
        ATTRIBUTE_NEW = 3;
        SCORE_NEW = 4;
        MONTH = 1;
        DAY = 11;
    }

    /**
     * メールレコメンド場所追加
     * @return
     */
    public boolean Tab_Mailposition()
    {
        return Click( "メールレコメンド場所", Element_Type.LINK );
    }

    /**
     * メールレコメンド場所追加
     * @return
     */
    public boolean Mailposition_Add()
    {
        return Js_Click( "bp_newButton#new" );
    }

    /**
     * ルール一覧を開く
     * @param index 編集したい行数
     * @return
     */
    public boolean Rule_View( final int index )
    {
        count = Get_Icon();

        return Rule_Click( index - 1 );
    }

    /**
     * ルール一覧の編集ボタン
     */
    @Override
    public boolean Rule_Edit( int index )
    {
        index = index + count;

        count = Get_Icon();

        return Edit_Click( index );
    }

    /**
     * 場所名編集
     * @param edit 入力する文字列
     * @return
     */
    public boolean Edit_Positionname( final String name )
    {
        return Js_Sendkey( "bp_text#name", name );

    }

    /**
     * ブロックID編集
     * @param edit 入力する文字列
     * @return
     */
    public boolean Edit_Blockid( final String edit )
    {
        return Js_Sendkey( "bp_text#blockId", edit );
    }

    /**
     * ルールベース
     * @return
     */
    public boolean Type_Rule()
    {
        return Js_Click( "label#ruleBase" );
    }

    /**
     * ユーザー軸自動レコメンド
     * @return
     */
    @Override
    public boolean Type_Userauto()
    {
        return Js_Click( "label#userAuto" );
    }

    /**
     * ユーザー軸外部レコメンド
     * @return
     */
    public boolean Type_Userexternal()
    {
        return Js_Click( "label#userManual" );
    }

    /**
     * アイテム閲覧履歴から自動レコメンド
     * @return
     */
    public boolean Type_Historyauto()
    {
        return Js_Click( "label#historyAuto" );
    }

    /**
     * ランキング
     * @return
     */
    public boolean Type_Ranking()
    {
        return Js_Click( "label#ranking" );
    }

    /**
     * 周期性レコメンド
     * @return
     */
    public boolean Type_Reminder()
    {
        return Js_Click( "label#reminder" );
    }

    /**
     * 残留カートから自動レコメンド
     * @return
     */
    public boolean Type_Cartauto()
    {
        return Js_Click( "label#abandonmentAuto" );
    }

    /**
     * ユーザー軸外部リストを選択
     * @param index
     */
    public void Select_Userexternal( final int index )
    {
        ecf.selectCombobox( "bp_combo#userItemList", index );

        return;
    }

    /**
     * 閲覧ベース選択
     * @return
     */
    public boolean Radio_View()
    {
        return Js_Click( "bp_radio#learningTypeView" );
    }

    /**
     * 購買ベース選択
     * @return
     */
    public boolean Radio_Conversion()
    {
        return Js_Click( "bp_radio#learningTypeConversion" );
    }

    /**
     * カテゴリを分散
     * @return
     */
    public boolean Check_DisperseCategory()
    {
        return Js_Click( "bp_checkbox#disperseCategory" );
    }

    /**
     * 枠補完無効
     * @return
     */
    public void Radio_無効()
    {
        ecf.RadioGroup_Select( "bp_radioGroup#fillType", "DISABLE" );

        return;
    }

    /**
     * 枠補完有効
     * @return
     */
    public void Radio_有効()
    {
        ecf.RadioGroup_Select( "bp_radioGroup#fillType", "ENABLE" );

        return;
    }



    /**
     * タグを選択
     * @param index
     */
    public void Select_Tag( final int index )
    {
        ecf.selectCombobox( "bp_combo#tags", index );

        return;
    }

    /**
     * コンテンツを選択
     * @param index
     */
    public void Select_Content( final int index )
    {
        ecf.selectCombobox( "bp_combo#mailRecommendContent", index );

        return;
    }

    /**
     * ルール用リストを選択
     * @param index
     */
    public void Select_Rulelist( final int index )
    {
        ecf.selectCombobox( "bp_combo#ruleItemList", index );

        return;
    }

    /**
     * スケジュールタブ
     * @return
     */
    public boolean Tab_Schedule()
    {
        return Click( "スケジュール", Element_Type.LINK );
    }

    /**
     *属性新規作成ボタン
     */
    @Override
    public boolean Attribute_Add()
    {
        return Js_Click( "bp_newButton#new", ATTRIBUTE_NEW );
    }

    /**
     *スコア条件新規作成ボタン
     */
    @Override
    public boolean Score_Add()
    {
        return Js_Click( "bp_newButton#new", SCORE_NEW );
    }

    /**
     * コンバージョン済みを除外チェック
     * @return
     */
    public boolean Check_Conversion()
    {
        return Js_Click( "bp_checkbox#conversionFilter" );
    }

    /**
     * スケジュールの選択
     * @param index 選択したい月
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
     * @param index 選択したい日
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
     * @param str   曜日を文字列で渡す。祝日指定も含む
     * @return
     */
    public boolean Schedul_Week( final String str )
    {
        return Click( str, Element_Type.LINK );
    }

    /**
     * クリアボタン
     * @return
     */
    public boolean Schedul_Clear()
    {
        return Click( "クリア", Element_Type.LINK );
    }
}
