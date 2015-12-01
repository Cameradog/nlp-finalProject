package selenium.actions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import test.selenium.ExtComponentFinder;


/**
 * アイテムリスト設定
 * @author a.toru.yano
 *
 */
public class Itemlist_Setting extends Common
{
    private int icon_count;
    protected int check_count;
    private ExtComponentFinder ecf;


    public Itemlist_Setting( WebDriver driver )
    {
        icon_count = 0;
        check_count = 0;
        ecf = new ExtComponentFinder( driver );
        Set_Drive( driver );
    }

    /**
     * ルール用リストの新規作成
     * @return
     */
    public boolean Rulelist_Add()
    {
       return Js_Click("bp_newButton#new");
    }

    /**
     * ルール用リストのタブクリック
     * @return
     */
    public boolean Tab_Rulelist()
    {
        return Click( "ルール用リスト設定", Element_Type.LINK );
    }

    /**
     * ユーザー軸外部リストのタブクリック
     * @return
     * @throws InterruptedException
     */
    public boolean Tab_Externallist() throws InterruptedException
    {
        icon_count = Get_Icon();

        Click( "ユーザー軸外部リスト設定", Element_Type.LINK );

        Thread.sleep(500);

        return true;
    }

    /**
     * ルール用リストの編集ボタン
     * @param index
     * @return
     */
    public boolean Edit_Rulelist( int index )
    {
        check_count = Checkboxnum();

        return Edit_Click( index );
    }

    /**
     * ルール用リストの取消ボタン
     * @param index
     * @return
     */
    public boolean Undo_Rulelist( int index )
    {
        return Undo_Click( index );
    }

    /**
     * リスト名編集
     *
     * @param edit  入力するフィルタ名
     * @return
     */
    public boolean Edit_listrname( String edit )
    {
        return Js_Sendkey( "bp_text#name", edit );
    }

    /**
     * アイテム検索用の入力
     * 100件以上のヒットする場合はダイアログが出るのでその後の実装に注意
     * @param itemcode
     * @return
     */
    public boolean Edit_Itemcode( String itemcode )
    {
        return Js_Sendkey( "bp_text#searchForm_itemCode", itemcode );
    }

    /**
     * アイテム検索ボタン
     * @return
     * @throws InterruptedException
     */
    public boolean Search() throws InterruptedException
    {
        Js_Click( "bp_button#searchForm_submit" );

        Thread.sleep(5000);

        return true;
    }

    /**
     * 選択方法
     * @param index
     */
    public void Select_Type( int index )
    {
        ecf.selectCombobox( "bp_combo#type", index );

        return;
    }

    /**
     * アイテムのチェックボックス（左側）
     * @param index
     * @return
     */
    public boolean Item_Chck( int index )
    {
        index = index + check_count;

        return Check_Box( index );
    }

    /**
     * 追加ボタン
     * @return
     */
    public boolean Item_Add()
    {
        return Js_Click( "bp_button#register" );
    }

    /**
     * リストのチェックボックス（右側）
     * @param index
     * @return
     */
    public boolean List_Chck( int index )
    {
        List<WebElement> elms = null;

        //配列は0からなので調整
        index = index - 1;

        elms = Wait_Elements( "x-grid-checkcolumn", Element_Type.CLASS );

        if( elms == null )
        {
            return false;
        }

        elms.get( index ).click();

        return true;
    }

    /**
     * 追加ボタン
     * @return
     */
    public boolean Item_Delete()
    {
        return Js_Click( "bp_deleteButton#delete_registeredItems" );
    }

    /**
     * ルール用リストの新規作成
     * @return
     */
    public boolean Externallisit_Add()
    {
       return Js_Click("bp_newButton#new", 1 );
    }

    /**
     * ユーザー軸外部リスト編集
     * @param index
     * @return
     */
    public boolean Edit_Externallisit( int index )
    {
        index = index + icon_count;

        return Edit_Click( index );
    }

    /**
     * アイテム側のチェックボックスの位置調整用
     * @return
     */
    protected int Checkboxnum()
    {
        List<WebElement> elms = null;

        elms = Wait_Elements( "x-grid-row-checker", Element_Type.CLASS );

        return elms.size();
    }
}
