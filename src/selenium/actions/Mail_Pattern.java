package selenium.actions;

import org.openqa.selenium.WebDriver;

/**
 * メールレコメンドパターン設定
 * @author a.toru.yano
 *
 */
public class Mail_Pattern extends Itemlist_Setting
{
    private final int PAGING;
    private int icon_count;
    //コンストラクタ
    public Mail_Pattern( WebDriver driver )
    {
        super( driver );
        PAGING = 2;
        icon_count = 0;
    }

    /**
     * メールレコメンド場所追加
     * @return
     */
    public boolean Tab_Mailpattern()
    {
        return Click( "メールレコメンドパターン", Element_Type.LINK );
    }

    /**
     * パターン編集
     * @param index 編集したい行数
     * @return
     */
    public boolean Edit_Pattern( int index )
    {
        index = index + icon_count;

        check_count = Checkboxnum();

        return Edit_Click( index );
    }

    /**
     * メールレコメンド場所リストのチェックボックス
     * @param index
     * @return
     */
    public boolean Position_Check( int index )
    {
        return Item_Chck( index );
    }

    /**
     * 追加ボタン
     * @return
     */
    public boolean Position_Add()
    {
        return Item_Add();
    }

    /**
     * 登録済みパターンリストのチェックボックス
     * @param index
     * @return
     */
    public boolean Pattern_Check( int index )
    {
        return List_Chck( index );
    }

    /**
     * 前ページへ
     */
    public boolean Preview( )
    {
        return Js_Click( "button#prev", PAGING );
    }

    /**
     * 最初のページへ
     */
    public boolean Frist( )
    {
        return Js_Click( "button#frist", PAGING );
    }

    /**
     * 次ページへ
     */
    public boolean Next( )
    {
        return Js_Click( "button#next", PAGING );
    }

    /**
     * 最後のページへ
     */
    public boolean Last( )
    {
        return Js_Click( "button#last", PAGING );
    }


}
