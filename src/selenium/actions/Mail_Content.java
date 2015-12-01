package selenium.actions;

import org.openqa.selenium.WebDriver;

/**
 * メールレコメンドコンテンツ設定
 * @author a.toru.yano
 *
 */
public class Mail_Content extends Web_Recommend_Content
{
    private int icon_count;
    //コンストラクタ
    public Mail_Content( WebDriver driver )
    {
        super( driver );
        this.icon_count = 0;
    }

    /**
     * レコメンドコンテンツ追加
     * @return
     */
    public boolean Mailcontent_Add()
    {
        return Js_Click( "bp_newButton#new" );
    }

    /**
     * Mailレコメンドコンテンツの編集ボタン
     * @param index　編集したい行数
     * @return
     */
    public boolean Mailcontent_Edit( int index )
    {
        icon_count = Get_Icon();

        return Edit_Click( index - 1 );
    }

    /**
     * コンテンツの編集ボタン
     * @param index　編集したい行数
     * @return
     */
    public boolean Content_Edit( int index )
    {
        index = index + this.icon_count;

        return Edit_Click( index );
    }

    /**
     * コンテンツ2編集
     * @param content
     * @return
     */
    public boolean Edit_Content2( String content )
    {
        return Js_Sendkey( "bp_textarea#content2Html", content );
    }

    /**
     * URL1編集
     * @param content
     * @return
     */
    public boolean Edit_Url1( String url )
    {
        return Js_Sendkey( "bp_text#url1", url );
    }

    /**
     * URL2編集
     * @param content
     * @return
     */
    public boolean Edit_Url2( String url )
    {
        return Js_Sendkey( "bp_text#url2", url );
    }
}
