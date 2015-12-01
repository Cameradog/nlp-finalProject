package selenium.actions;

import java.util.List;

import test.selenium.ExtComponentFinder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Webレコメンドコンテンツ設定
 * @author a.toru.yano
 *
 */
public class Web_Recommend_Content extends Common
{
    private final int CONTENT;

    private ExtComponentFinder ecf;
    //コンストラクタ
    public Web_Recommend_Content( WebDriver driver )
    {
        Set_Drive( driver );
        ecf = new ExtComponentFinder( driver );
        CONTENT = 1;
    }

    /**
     * レコメンドコンテンツ追加
     * @return
     */
    public boolean Webcontent_Add()
    {
        return Js_Click( "bp_newButton#new" );
    }

    /**
     * コンテンツ名で検索
     * @param contentname 検索したい場所名/エレメントID
     * @return
     */
    public boolean Search_Positionname( String contentname )
    {
        return Js_Sendkey( "bp_text#webRecommendContentName", contentname );
    }

    /**
     * 検索したいタグ
     * @param index
     */
    public void Search_Tag( int index )
    {
        ecf.selectCombobox( "bp_combo#tag", index );

        return;
    }

    /**
     * 検索したい設定グループ
     * @param index
     */
    public void Search_Sitegroup( int index )
    {
        ecf.selectCombobox( "bp_combo#siteGroup", index );

        return;
    }

    /**
     * 検索したいステータス
     * @param index
     */
    public void Search_Status( int index )
    {
        ecf.selectCombobox( "rt_useStatusCombo#useStatus", index );

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
        return Js_Click( "bp_button#clearCondition" );
    }

    /**
     * コンテンツビューの確認
     * @param index
     */
    public boolean Content_View( int index )
    {
        List<WebElement> elms = null;

        elms = Wait_Elements( "rt_preview", Element_Type.CLASS );

        if( elms == null )
        {
            return false;
        }

        //行数と合わせるために「-1」
        elms.get( index - 1 ).click();

        return true;
    }
    /**
     * Webレコメンドコンテンツの編集ボタン
     * @param index　編集したい行数
     * @return
     */
    public boolean Webcontent_Edit( int index )
    {
        return Edit_Click( index );
    }

    /**
     * レコメンドコンテンツ名編集
     * @param name
     * @return
     */
    public boolean Edit_Contentname( String name )
    {
        return Js_Sendkey( "bp_text#name", name );
    }

    /**
     * コンテンツ備考編集
     * @param tag
     * @return
     */
    public boolean Edit_Memo( String memo )
    {
        return Js_Sendkey( "bp_textarea#memo", memo );
    }

    /**
     * 設定グループを選択（一覧表示中）
     * @param index
     */
    public void Select_Sitegroup( int index )
    {
        ecf.selectCombobox( "bp_combo#siteGroup", index );

        return;
    }

    /**
     * 設定グループを選択（コンテンツ編集画面）
     * @param index
     */
    public void Edit_Select_Sitegroup( int index )
    {
        ecf.selectCombobox( "bp_combo#siteGroup", CONTENT, index );

        return;
    }

    /**
     * コンテンツ選択方法を選択
     * @param index
     */
    public void Select_Type( int index )
    {
        ecf.selectCombobox( "bp_combo#type", index );

        return;
    }

    /**
     * セッション内は同一コンテンツを表示
     * @return
     */
    public boolean Check_Session( )
    {
        return Js_Click( "bp_checkbox#sessionzeContent" );
    }

    /**
     * コンテンツのクリックを追跡しない
     * @return
     */
    public boolean Check_Notrack( )
    {
        return Js_Click( "bp_checkbox#noEventContent" );
    }

    /**
     * タグ名編集
     * @param tag
     * @return
     */
    public boolean Edit_Tag( String tag )
    {
        return Js_Sendkey( "bp_text#input", tag );
    }

    /**
     * タグの追加ボタン
     * @return
     */
    public boolean Tag_Add( )
    {
        return Js_Click( "bp_checkbox#newTag" );
    }

    /**
     * タグの削除ボタン
     * @param index 削除したい行数
     * @return
     */
    public boolean Tag_Delete( int index )
    {
        List<WebElement> elms = null;

        elms = Wait_Elements( "bp_closableLabelIcon", Element_Type.CLASS );

        if( elms == null )
        {
            return false;
        }

        //行数と合わせるために「-1」
        elms.get( index - 1 ).click();

        return true;
    }

    /**
     * コンテンツ追加
     * @return
     */
    public boolean Content_Add()
    {
        return Js_Click( "bp_newButton#newContent" );
    }

    /**
     * コンテンツの編集ボタン
     * @param index　編集したい行数
     * @return
     */
    public boolean Content_Edit( int index )
    {
        return Edit_Window_Click( index );
    }

    /**
     * コンテンツの削除ボタン
     * @param index　削除したい行数
     * @return
     */
    public boolean Content_Delete( int index )
    {
        return Delete_Window_Click( index );
    }

    /**
     * コンテンツの取消ボタン
     * @param index　取消たい行数
     * @return
     */
    public boolean Content_Undo( int index )
    {
        return Undo_Window_Click( index );
    }

    /**
     * コンテンツ備考編集
     * @param tag
     * @return
     */
    public boolean Edit_Contentmemo( String memo )
    {
        return Js_Sendkey( "bp_text#contentMemo", memo );
    }

    /**
     * コンテンツ編集
     * @param content
     * @return
     */
    public boolean Edit_Content( String content )
    {
        return Js_Sendkey( "bp_textarea#contentHtml", content );
    }

    /**
     * 開始日時編集
     * @param edit 入力する文字列
     * @return
     */
    public boolean Edit_Effectivedate( String edit )
    {
        return Js_Sendkey( "datefield#effectiveDate", edit );
    }

    /**
     * 開始日時編集
     * @param edit 入力する文字列
     * @return
     */
    public boolean Edit_Effectivetime( String edit )
    {
        return Js_Sendkey( "timefield#effectiveTime", edit );
    }

    /**
     * 終了日時編集
     * @param edit 入力する文字列
     * @return
     */
    public boolean Edit_Expiringdate( String edit )
    {
        return Js_Sendkey( "datefield#expiringDate", edit );
    }

    /**
     * 終了日時編集
     * @param edit 入力する文字列
     * @return
     */
    public boolean Edit_Expiringtime( String edit )
    {
        return Js_Sendkey( "timefield#expiringTime", edit );
    }

    /**
     * コンテンツの確定ボタン
     * @return
     */
    public boolean Decide_Content()
    {
        return Js_Click( "bp_button#ok" );
    }

    /**
     * OKボタン
     * @return
     */
    public boolean OK()
    {
        return Js_Click( "bp_button#ok", CONTENT );
    }

    /**
     * コンテンツのキャンセルボタン
     * @return
     */
    public boolean Cancel_Content()
    {
        return Js_Click( "bp_button#cancel" );
    }

    /**
     * キャンセルボタン
     * @return
     */
    public boolean Cancel()
    {
        return Js_Click( "bp_button#cancel", CONTENT );
    }
}
