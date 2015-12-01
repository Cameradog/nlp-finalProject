package selenium.actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * ByChainedを使用して、特定のエレメント配下にあるエレメントを操作する
 * 例：グリッド内のボタンをクリックするために使用する
 * @author a.toru.yano
 *
 */
public class Grid_Click
{
    WebDriver driver = null;

    public Grid_Click( final WebDriver driver )
    {
        this.driver = driver;
    }

    /**
     * グリッド内の編集ボタンクリック
     * @param elm_str どこの編集ボタンかを特定するための要素名
     * @param index   編集したい行数
     */
    public boolean Edit_Click( final String elm_str, final int index )
    {
        List<WebElement> elms = null;
        String str = null;

        str = Get_Id( elm_str );

        elms = Bychained( str, "bp_editGridButtonIcon" );

        if( elms == null )
        {
            return false;
        }

        elms.get( index ).click();

        return true;
    }

    /**
     * グリッド内の削除ボタンクリック
     * @param elm_str どこの削除ボタンかを特定するための要素名
     * @param index   削除したい行数
     */
    public boolean Delete_Click( final String elm_str, final int index )
    {
        List<WebElement> elms = null;
        String str = null;

        str = Get_Id( elm_str );

        elms = Bychained( str, "bp_deleteGridButtonIcon" );

        if( elms == null )
        {
            return false;
        }

        elms.get( index ).click();

        return true;
    }

    /**
     * グリッド内の取消ボタンクリック
     * @param elm_str どこの取消ボタンかを特定するための要素名
     * @param index   取消したい行数
     */
    public boolean Undo_Click( final String elm_str, final int index )
    {
        List<WebElement> elms = null;
        String str = null;

        str = Get_Id( elm_str );

        elms = Bychained( str, "bp_undoGridButtonIcon" );

        if( elms == null )
        {
            return false;
        }

        elms.get( index ).click();

        return true;
    }

    /**
     * グリッド内のルールボタンクリック
     * @param elm_str どこの取消ボタンかを特定するための要素名
     * @param index   取消したい行数
     */
    public boolean Rule_Click( final String elm_str, final int index )
    {
        List<WebElement> elms = null;
        String str = null;

        str = Get_Id( elm_str );

        elms = Bychained( str, "rt_ruleIcon" );

        if( elms == null )
        {
            return false;
        }

        elms.get( index ).click();

        return true;
    }

    /**
     * チェックボックス
     * @param elm_str どこチェックボックスかを特定するための要素名
     * @param index   チェックしたい行数
     */
    public boolean Checkbox( final String elm_str, final int index )
    {
        List<WebElement> elms = null;

        elms = Bychained( elm_str, "x-form-checkbox" );

        if( elms == null )
        {
            return false;
        }

        elms.get( index ).click();

        return true;
    }

    /**
     * コンボボックス
     * @param elm_str どこチェックボックスかを特定するための要素名
     * @param index   チェックしたい行数
     */
    public boolean Combobox( final String elm_str, final int index )
    {
        List<WebElement> elms = null;

        elms = Bychained( elm_str, "rt_comboBox" );

        if( elms == null )
        {
            return false;
        }

        elms.get( index ).click();

        return true;
    }

    /**
     * テキスト入力（主にルール用）
     * @param elm_str
     * @param edit
     * @return
     */
    public boolean Sendkey( final String elm_str, final String edit )
    {
        return Sendkey( elm_str, edit, 0 );
    }

    /**
     * テキスト入力（主にルール用）
     * @param elm_str
     * @param edit
     * @param index
     * @return
     */
    public boolean Sendkey( final String elm_str, final String edit, final int index )
    {
        String str = null;
        List<WebElement> elms = null;

        str = Get_Id( elm_str );

        elms = Bychained( str, "x-form-text" );

        if( elms == null )
        {
            return false;
        }

        elms.get( index ).sendKeys( edit );

        return true;
    }

    /**
     * 特定のエレメント配下のエレメントを指定する
     * @param elm_str1 最初に指定するエレメント（これはユニークのものを指定する）
     * @param elm_str2 elm_str1の配下にあるエレメント（ユニークじゃなくてもOK）
     * @return
     */
    public List<WebElement> Bychained( final String elm_str1, final String elm_str2 )
    {
        return this.driver.findElements(new ByChained( By.id( elm_str1 ), By.className( elm_str2 ) ) );
    }

    /**
     * ウィンドウ内のボタンを指定する
     * @param elm_str
     * @return
     */
    public List<WebElement> Button_InWindow( final String elm_str )
    {
        return driver.findElements( new ByChained( By.className( "x-window" ), By.className( elm_str ) ) );
    }

    /**
     * 使うかも？単体要素指定用
     * @param elm_str1
     * @param elm_str2
     * @return
     */
    public WebElement Bychained_One( final String elm_str1, final String elm_str2 )
    {
        return this.driver.findElement(new ByChained( By.id( elm_str1 ), By.linkText( elm_str2 ) ) );
    }

    /**
    * ダイアログ内にある要素を操作したい場合に呼ぶ
    * @param elm_str HTML要素の文字列
    * @param type    要素の属性。idとかnameとか
    * @return        要素取得に失敗したらNULLを返す
    */
   public List<WebElement> Elements_Indialog( final String elm_str,final Element_Type type )
   {
       List<WebElement> elms;
       final WebDriverWait wait = new WebDriverWait( driver, 3 );

       final ExpectedCondition<List<WebElement>> elm_wait = new ExpectedCondition<List<WebElement>>()
       {
           List<WebElement> Elements;

        public List<WebElement> apply(final WebDriver driver)
           {
               switch( type )
               {
                   case ID:
                       Elements = driver.findElements( new ByChained( By.className( "x-window" ), By.id( elm_str ) ) );
                       break;

                   case CSS:
                       Elements = driver.findElements( new ByChained( By.className( "x-window" ), By.cssSelector( elm_str ) ) );
                       break;

                   case XPATH:
                       Elements = driver.findElements( new ByChained( By.className( "x-window" ), By.xpath( elm_str ) ) );
                       break;

                   case NAME:
                       Elements = driver.findElements( new ByChained( By.className( "x-window" ), By.name( elm_str ) ) );
                       break;

                   case LINK:
                       Elements = driver.findElements( new ByChained( By.className( "x-window" ), By.linkText( elm_str ) ) );
                       break;

                   case CLASS:
                       Elements = driver.findElements( new ByChained( By.className( "x-window" ), By.className( elm_str ) ) );
                       break;

                   case TAG:
                       Elements = driver.findElements( new ByChained( By.className( "x-window" ), By.tagName( elm_str ) ) );
                       break;

                   default:
                       Elements = null;
                       break;
               }

               return Elements;
           }
       };

       try
       {
           elms = wait.until( elm_wait );
       }
       catch(final Exception e)
       {
           //タイムアウトしたらNULL
           elms = null;

           System.out.println("NULLが代入されています！！スピードが速すぎるか、要素名が間違ってる！？\n\n");
       }

       return elms;
   }

    /**
     * <p>
     * コンポーネントを指定してIDを取得
     * </p>
     * 主にBychainedを使うために使用
     * @param query     ComponentQueryの検索文
     * @return          idを文字列で返す。
     */
    public String Get_Id( final String query )
    {
        String id = null;
        final WebDriverWait wait = new WebDriverWait( this.driver, 6 );

        final ExpectedCondition<String> elm_wait = new ExpectedCondition<String>( )
        {
            private JavascriptExecutor jsdr;

            public String apply( final WebDriver driver )
            {
                jsdr = (JavascriptExecutor) driver;
                String str = null;

                str = (String) jsdr.executeScript( "return Ext.ComponentQuery.query('" + query + "')" + "[0].getId();" );

                return str;
            }
        };

        try
        {
            id = wait.until( elm_wait );
        }
        catch(final Exception e)
        {
            System.out.println("要素の取得に失敗...\n\n" + StackTrace.calledFrom() );
        }

        return id;
    }



}
